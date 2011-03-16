package com.ssi.htmlparser.pcpop;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.ssi.common.dal.domain.ArticleDoc;
import com.ssi.common.dal.domain.Website;
import com.ssi.htmlparser.BaseHtmlParser;
import com.ssi.htmlparser.bean.LinkBean;

public class PCPOPHtmlParser extends BaseHtmlParser{

	final String PCPOP = "WEB_PCPOP";
	
	int docCount = 0;
	
	int processCount = 0;
	
	//处理的浏览
	long filesize = 0;

	HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();
	
	HashMap<String,String> HTMLHASH = new HashMap<String,String>();
	
	/**
	 * 获取指定列表中页面源码
	 * 
	 * @param webList
	 */
	void initHTML(List<Website> webList) {
		Long start = System.currentTimeMillis();
		for (Website bean : webList) {
			try {
				Long start1 = System.currentTimeMillis();
				String content = ViewSourceFrame(bean.getUrl());
				if (null != content && !"".equalsIgnoreCase(content)) {
					processWithDoc(bean.getId(), content);
					Long end1 = System.currentTimeMillis();
					logger.debug("单条耗时:" + (end1 - start1) + "长度："
							+ content.getBytes().length);
					filesize += content.length();
				}
			} catch (Exception e) {
				logger.error("Exception:" + e.getMessage());
				continue;
			}
		}
		Long end = System.currentTimeMillis();
		logger.debug("总耗时:" + (end - start));
	}

	/**
	 * 获取指定URL下的源码
	 * 
	 * @param url1
	 * @return
	 */
	public String ViewSourceFrame(String url1) throws Exception {
		String url = url1;
		String linesep = System.getProperty("line.separator");
		String htmlLine;
		String htmlSource = "";
		java.net.URL source = new URL(url);
		InputStream in = new BufferedInputStream(source.openStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		while ((htmlLine = br.readLine()) != null) {
			htmlSource = htmlSource + htmlLine + linesep;
		}
		return htmlSource;

	}

	/**
	 * 获取文章链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	void doc(String url, String pre) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(LinkTag.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter);
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					LinkBean bean = null;
					if (link.getLink().toLowerCase().startsWith(pre)
							&& !link.getLinkText().equalsIgnoreCase("详细内容")) {
						bean = new LinkBean();
						bean.setLink(link.getLink());
						bean.setName(link.getLinkText());
						LINKHASH.put(link.getLink(), bean);
					}
				}
			}
		}
	}

	/**
	 * 获取文章链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	 void docByHTML(String content, String pre) throws Exception {
		Parser parser = new Parser();
		parser.setInputHTML(content);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(LinkTag.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter);
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					LinkBean bean = null;
					if (link.getLink().toLowerCase().startsWith(pre)
							&& !link.getLinkText().equalsIgnoreCase("详细内容")) {
						if (null == articleDocCache.get(getKey(link.getLink()))) {
							bean = new LinkBean();
							bean.setLink(link.getLink());
							bean.setName(link.getLinkText());
							LINKHASH.put(link.getLink(), bean);
						} else {
							logger.info(">> 已存在 [" + link.getLink()
									+ "] 地址");
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	String author(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "otb14"));
		String author = null;
		if (list != null && list.size() > 0) {
			Div div = (Div) list.elementAt(0);
			String tmp = div.getStringText();
			author = tmp;
		}

		if (null == author) {
			// logger.debug("重新解析作者栏");
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");

			NodeFilter fileter1 = new NodeClassFilter(Div.class);
			NodeList list1 = parser.extractAllNodesThatMatch(fileter1)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "pop_2_1_2"));
			if (null != list1 && list1.size() > 0) {
				Div div = (Div) list1.elementAt(1);
				String tmp = div.getStringText();
				author = tmp.substring(tmp.indexOf("</a>") + 4);
				logger.debug("author:" + author);
			}
		}
		return author;
	}

	/**
	 * 根据URL获取内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static String content(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "contentDiv"));
		String content = null;
		if (null != list && list.size() > 0) {
			Div div = (Div) list.elementAt(0);
			String tmp = div.getStringText();
			// logger.debug("author:"+tmp);
			content = tmp;
		}
		return content;
	}

	void contentProcess() {
		HashMap map = new HashMap();
		map.put("status", 2);
		try {
			List<ArticleDoc> list = articleDocDao.find(map);
			for (ArticleDoc bean : list) {
				if (!bean.getUrl().startsWith("http://www.pcpop.com/doc/")) {
					continue;
				}

				try {
					// String content = content(bean.getUrl());
					// if(null != content){
					// bean.setContent(content);
					bean.setStatus(3);
					if (articleDocDao.update(bean) == 0) {
						logger.debug("更新作者失败:" + bean.getUrl());
					} else {
						if (null != client.get(getKey(bean.getUrl()))) {
							client.remove(getKey(bean.getUrl()));
							client.put(getKey(bean.getUrl()), bean);
						}
						logger.debug("[" + bean.getId() + "]更新文章内容成功");
					}
					// }
				} catch (Exception e) {
					bean.setStatus(11);
					bean.setContent(e.getMessage());
					if (articleDocDao.update(bean) == 0) {
						logger.debug("更新文章内容失败:" + bean.getUrl());
					} else {
						logger.debug("[" + bean.getId()
								+ "]更新记录状态为11[其他异常情况]:" + bean.getUrl());
					}
					continue;
				}
			}
		} catch (Exception e) {

		}
	}

	public void init() {
		try {
			List<Website> weblist = websiteDao.findByFatherId(166);
			logger.debug("初始化数据库数据");
			HashMap map = new HashMap();
			for (Website bean : weblist) {
				map.put("webId", bean.getId());
				List<ArticleDoc> docList = articleDocDao.find(map);
				for (ArticleDoc doc : docList) {
					if (articleDocCache.get(getKey(doc.getUrl())) == null) {
						logger.debug("添加对象到缓存");
						articleDocCache.put(getKey(doc.getUrl()), doc);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String getKey(String url) {
		return PCPOP + ":" + url;
	}

	/**
	 * 根据文章内容获取最新发布的文章
	 * 
	 * @param webid
	 * @param content
	 * @throws Exception
	 */
	public void processWithDoc(int webid, String content) throws Exception {
		docByHTML(content, "http://www.pcpop.com/doc/");
		Iterator it = LINKHASH.keySet().iterator();
		ArticleDoc doc = null;
		while (it.hasNext()) {
			String key1 = (String) it.next();
			if (null == articleDocCache.get(getKey(key1))) {
				LinkBean link = (LinkBean) LINKHASH.get(key1);
				doc = new ArticleDoc();
				doc.setTitle(link.getName());
				doc.setUrl(link.getLink());
				doc.setWebId(webid);
				int id = articleDocDao.insert(doc);
				if (!(id > 0)) {
					logger.info("失败，\t链接名称：" + link.getName()
							+ "\n链接地址：" + link.getLink());
				} else {
					docCount ++;
					doc.setId(id);
					doc.setStatus(1);
					articleDocCache.put(getKey(doc.getUrl()), doc);
					logger.info("processWithDoc,docCount:"+docCount);
				}
			}
		}
		LINKHASH.clear();
	}

	/**
	 * 获取文章作者，发布时间等数据
	 * 
	 */
	public void processAuthor() throws Exception {
		HashMap map = new HashMap();
		map.put("status",1);
		List<ArticleDoc> list = articleDocDao.find(map);
		for (ArticleDoc bean : list) {

			if (!bean.getUrl().startsWith("http://www.pcpop.com/doc/")) {
				continue;
			}
			logger.debug("获取文章数据");
			// 更新文章的作者和发布时间
			try {
				String author = author(bean.getUrl());
				String tmp1 = author.substring(author.lastIndexOf("作者") + 3,
						author.lastIndexOf("编辑") - 1);
				String tmp2 = author.substring(0, author.indexOf(":") - 2);
				bean.setAuthor(tmp1);
				bean.setPublishTime(tmp2);
				bean.setStatus(2);
				if (articleDocDao.update(bean) == 0) {
					logger.info("更新作者失败:" + bean.getUrl());
				} else {
					processCount++;
					logger.info("[" + bean.getId() + "]更新成功:"
							+ bean.getUrl() + "\tprocessCount:"+processCount);
				}
			} catch (java.io.FileNotFoundException e) {
				bean.setStatus(10);
				if (articleDocDao.update(bean) == 0) {
					logger.error("更新作者失败:" + bean.getUrl());
				} else {
					logger.error("[" + bean.getId()
							+ "]更新记录状态为10[文件或地址查找找不到]:" + bean.getUrl());
				}
				continue;
			} catch (org.htmlparser.util.ParserException e) {
				bean.setStatus(10);
				if (articleDocDao.update(bean) == 0) {
					logger.error("更新作者失败:" + bean.getUrl());
				} else {
					logger.error("[" + bean.getId()
							+ "]更新记录状态为10[URL解析失败]:" + bean.getUrl());
				}
				continue;
			} catch (Exception e) {
				bean.setStatus(11);
				bean.setContent(e.getMessage());
				if (articleDocDao.update(bean) == 0) {
					logger.error("更新作者和文章发布时间失败:" + bean.getUrl());
				} else {
					logger.error("[" + bean.getId()
							+ "]更新记录状态为11[其他异常情况]:" + bean.getUrl());
				}
				continue;
			}
		}
	}

	public void processWithDoc() throws Exception {
		// Iterator hit = HTMLHASH.keySet().iterator();
		// while(hit.hasNext()){
		// String key = (String) hit.next();
		// logger.debug("key:"+key);
		// String[] keys = key.split(":");
		// String content = (String) HTMLHASH.get(key);
		// try{
		// docByHTML(content, "http://www.pcpop.com/doc/");
		// Iterator it = LINKHASH.keySet().iterator();
		// ArticleDoc doc = null;
		// while (it.hasNext()) {
		// String key1 = (String) it.next();
		// if(null == client.get(getKey(key1))){
		// LinkBean link = (LinkBean) LINKHASH.get(key1);
		// doc = new ArticleDoc();
		// doc.setTitle(link.getName());
		// doc.setUrl(link.getLink());
		// doc.setWebId(Integer.valueOf(keys[0]));
		// int id = articleDocDao.insert(doc);
		// if(!(id>0)){
		// logger.debug("失败，\t链接名称：" + link.getName() + "\n链接地址："+
		// link.getLink());
		// }else{
		// doc.setId(id);
		// doc.setStatus(1);
		// client.add(getKey(doc.getUrl()), doc);
		// logger.debug("Memcached now store this object");
		// }
		// }
		// }
		// LINKHASH.clear();
		// }catch(Exception e){
		// logger.debug("解析异常，跳过:"+key+"\tException:"+e.getMessage());
		// continue;
		// }
		// }

		HashMap map = new HashMap();
		map.put("status",1);
		
		List<ArticleDoc> list = articleDocDao.find(map);
		for (ArticleDoc bean : list) {

			if (!bean.getUrl().startsWith("http://www.pcpop.com/doc/")) {
				continue;
			}
			logger.debug("获取文章数据");
			// 更新文章的作者和发布时间
			try {
				String author = author(bean.getUrl());
				String tmp1 = author.substring(author.lastIndexOf("作者") + 3,
						author.lastIndexOf("编辑") - 1);
				String tmp2 = author.substring(0, author.indexOf(":") - 2);
				bean.setAuthor(tmp1);
				bean.setPublishTime(tmp2);
				bean.setStatus(2);
				if (articleDocDao.update(bean) == 0) {
					logger.debug("更新作者失败:" + bean.getUrl());
				} else {
					logger.debug("[" + bean.getId() + "]更新成功:"
							+ bean.getUrl());
				}
			} catch (java.io.FileNotFoundException e) {
				bean.setStatus(10);
				if (articleDocDao.update(bean)  == 0) {
					logger.debug("更新作者失败:" + bean.getUrl());
				} else {
					logger.debug("[" + bean.getId()
							+ "]更新记录状态为10[文件或地址查找找不到]:" + bean.getUrl());
				}
				continue;
			} catch (org.htmlparser.util.ParserException e) {
				bean.setStatus(10);
				if (articleDocDao.update(bean) == 0) {
					logger.debug("更新作者失败:" + bean.getUrl());
				} else {
					logger.debug("[" + bean.getId()
							+ "]更新记录状态为10[URL解析失败]:" + bean.getUrl());
				}
				continue;
			} catch (Exception e) {
				bean.setStatus(11);
				bean.setContent(e.getMessage());
				if (articleDocDao.update(bean) == 0) {
					logger.debug("更新作者和文章发布时间失败:" + bean.getUrl());
				} else {
					logger.debug("[" + bean.getId()
							+ "]更新记录状态为11[其他异常情况]:" + bean.getUrl());
				}
				continue;
			}
		}
	}
	
	//获取处理的流量数据
	public long getFilesize(){
		return filesize;
	}

	public void process() {
		try {
			// 指定父ID下的网站列表
			List<Website> weblist = websiteDao.findByFatherId(166);
			
			// 将获取的页面放入缓存
			initHTML(weblist);
			
			//处理页面中作者以及发布时间
			processAuthor();
			
			filesize = 0l;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}