package com.chinamilitary.htmlparser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDocDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;

public class PCPOPHtmlParser {

	static final String PCPOP_URL = "http://www.pcpop.com/";

	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();
	
	static HashMap<String,String> HTMLHASH = new HashMap<String,String>();

	static ArticleDocDao articleDocDao = DAOFactory.getInstance().getArticleDocDao();	
	
	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();
	
	static MemcacheClient client = MemcacheClient.getInstance();
	
	final static String PCPOP="WEB_PCPOP";
	
	/**
	 * 获取分类链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	static void catalog(String url) throws Exception { // WebsiteBean bean
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "xpn6")); // id
																	// blk_lmdh_01
																	// "class",
																	// "nav"
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					if (link.getLink().toLowerCase().startsWith("http://")) {
						System.out.println(link.getLinkText());
						System.out.println(link.getLink() + "\n");
						tmp = new WebsiteBean();
						tmp.setName(link.getLinkText());
						tmp.setUrl(link.getLink());
						tmp.setParentId(166);
						boolean b = dao.insert(tmp);
						if (b) {
							System.out.println("成功");
						} else {
							System.out.println("失败");
						}
					}
				}
			}
		}
	}

	/**
	 * 获取泡泡网全国分站
	 * 
	 * @param url
	 * @throws Exception
	 */
	static void cityCatalog(String url) throws Exception {
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "classify2")); // id
																		// blk_lmdh_01
																		// "class",
																		// "nav"
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					if (link.getLink().toLowerCase().startsWith("http://")) {
						tmp = new WebsiteBean();
						tmp.setName(link.getLinkText());
						tmp.setUrl(link.getLink());
						tmp.setParentId(166);
						boolean b = dao.insert(tmp);
						if (b) {
							System.out.println("成功");
						} else {
							System.out.println("失败");
						}
					}
				}
			}
		}
	}
	
	static void html(String url) throws Exception{
		Long start = System.currentTimeMillis();
	}
	
	/**
	 * 获取指定列表中页面源码
	 * @param webList
	 */
	static void initHTML(List<WebsiteBean> webList){
		Long start = System.currentTimeMillis();
		for(WebsiteBean bean:webList){
			try{
				Long start1 = System.currentTimeMillis();
				String content = ViewSourceFrame(bean.getUrl());
				client.add(bean.getId()+":"+bean.getUrl(), content);
				HTMLHASH.put(bean.getId()+":"+bean.getUrl(),content);
				Long end1 = System.currentTimeMillis();
				System.out.println("单条耗时:"+(end1-start1)+"长度："+content.getBytes().length);
			}catch(Exception e){
				System.out.println("Exception:"+e.getMessage());
				continue;
			}
		}
		Long end = System.currentTimeMillis();
		System.out.print("总耗时:"+(end-start));
	}

	/**
	 * 获取指定URL下的源码
	 * @param url1
	 * @return
	 */
	    public static String ViewSourceFrame(String url1) throws Exception{
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
	static void doc(String url, String pre) throws Exception {
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
					if (link.getLink().toLowerCase().startsWith(pre) && !link.getLinkText().equalsIgnoreCase("详细内容")){
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
	static void docByHTML(String content, String pre) throws Exception {
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
					if (link.getLink().toLowerCase().startsWith(pre) && !link.getLinkText().equalsIgnoreCase("详细内容")){
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
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static String author(String url) throws Exception{
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter).extractAllNodesThatMatch(
				new HasAttributeFilter("class", "otb14"));
		String author = null;
		if (list != null && list.size() > 0) {
			Div div = (Div)list.elementAt(0);
			String tmp = div.getStringText();
			author = tmp;
		}
		
		if(null == author){
//			System.out.println("重新解析作者栏");
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");
			
			NodeFilter fileter1 = new NodeClassFilter(Div.class);
			NodeList list1 = parser.extractAllNodesThatMatch(fileter1).extractAllNodesThatMatch(
					new HasAttributeFilter("class", "pop_2_1_2"));
			if(null != list1 && list1.size() > 0){
				Div div = (Div)list1.elementAt(1);
				String tmp = div.getStringText();
				author = tmp.substring(tmp.indexOf("</a>")+4);
				System.out.println("author:"+author);
			}
		}
		return author;
	}
	
	/**
	 * 根据URL获取内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static String content(String url) throws Exception{
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter).extractAllNodesThatMatch(
				new HasAttributeFilter("id", "contentDiv"));
		String content = null;
		if(null != list && list.size() > 0){
			Div div = (Div)list.elementAt(0);
			String tmp = div.getStringText();
//			System.out.println("author:"+tmp);
			content = tmp;
		}
		return content;
	}

	public static void main(String args[]) {
		try{
//		content("http://www.pcpop.com/doc/0/543/543944_3.shtml");
			
//		List<WebsiteBean> weblist = webSiteDao.findByParentId(166);
//		initHTML(weblist);
//		System.out.println("数量："+HTMLHASH.size());
		
//		init();
			
		process();
			
		contentProcess();
		
		HTMLHASH.clear();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void contentProcess(){
		try{
			List<ArticleDoc> list = articleDocDao.findAll(2);
			for(ArticleDoc bean : list){
				if(!bean.getUrl().startsWith("http://www.pcpop.com/doc/")){
					continue;
				}
				
				try{
					String content = content(bean.getUrl());
//					if(null != content){
						bean.setContent(content);
						bean.setStatus(3);
						if(!articleDocDao.update(bean)){
							System.out.println("更新作者失败:"+bean.getUrl());
						}else{
							System.out.println("["+bean.getId()+"]更新文章内容成功");
						}
//					}
				}catch(java.io.FileNotFoundException e){
					bean.setStatus(10);
					bean.setContent(e.getMessage());
					if(!articleDocDao.update(bean)){
						System.out.println("更新文章内容失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为10[文件或地址查找找不到]:"+bean.getUrl());
					}
					continue;
				}catch(org.htmlparser.util.ParserException e){
					bean.setStatus(10);
					bean.setContent(e.getMessage());
					if(!articleDocDao.update(bean)){
						System.out.println("更新文章内容失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为10[文件或地址查找找不到]:"+bean.getUrl());
					}
					continue;
				}catch(Exception e){
					bean.setStatus(11);
					bean.setContent(e.getMessage());
					if(!articleDocDao.update(bean)){
						System.out.println("更新文章内容失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为11[其他异常情况]:"+bean.getUrl());
					}
					continue;
				}
			}
		}catch(Exception e){
			
		}
	}
	
	static void init(){
		try{
			List<WebsiteBean> weblist = webSiteDao.findByParentId(166);
			System.out.println("初始化数据库数据");
			for(WebsiteBean bean:weblist){
				List<ArticleDoc> docList = articleDocDao.findByWebId(bean.getId());
				for(ArticleDoc doc:docList){
					if(client.get(getKey(doc.getUrl())) == null){
						System.out.println("添加对象到数据库");
						client.add(getKey(doc.getUrl()), doc);
					}else{
						client.replace(getKey(doc.getUrl()), doc);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static String getKey(String url){
		return PCPOP+":"+url;
	}
	
	static void process(){
		try {
			//指定父ID下的网站列表
			List<WebsiteBean> weblist = webSiteDao.findByParentId(166);
			
			//将获取的页面放入缓存
			initHTML(weblist);
			Iterator hit = HTMLHASH.keySet().iterator();
			while(hit.hasNext()){
				String key = (String) hit.next();
				System.out.println("key:"+key);
				String[] keys = key.split(":");
				String content = (String) HTMLHASH.get(key);
				try{
					docByHTML(content, "http://www.pcpop.com/doc/");
					Iterator it = LINKHASH.keySet().iterator();
					ArticleDoc doc = null;
					while (it.hasNext()) {
						doc = new ArticleDoc();
						String key1 = (String) it.next();
						LinkBean link = (LinkBean) LINKHASH.get(key1);
						doc.setTitle(link.getName());
						doc.setUrl(link.getLink());
						doc.setWebId(Integer.valueOf(keys[0]));
						int id = articleDocDao.insert(doc);
						if(client.get(getKey(doc.getUrl())) != null){
							System.out.println("Memcached has store this object");
							continue;
						}
						if(!(id>0)){
							System.out.println("失败，\t链接名称：" + link.getName() + "\n链接地址："+ link.getLink());
						}else{
							doc.setId(id);
							doc.setStatus(1);
							client.add(getKey(doc.getUrl()), doc);
							System.out.println("Memcached now store this object");
						}
					}
					LINKHASH.clear();
					}catch(Exception e){
						System.out.println("解析异常，跳过:"+key+"\tException:"+e.getMessage());
						continue;
					}
			}
			
//			for(WebsiteBean bean:weblist){
//				try{
//				System.out.println("url:"+bean.getUrl());
//				doc(bean.getUrl(), "http://www.pcpop.com/doc/");
//				System.out.println("文章数：" + LINKHASH.size());
//				Iterator it = LINKHASH.keySet().iterator();
//				ArticleDoc doc = null;
//				while (it.hasNext()) {
//					doc = new ArticleDoc();
//					String key = (String) it.next();
//					LinkBean link = (LinkBean) LINKHASH.get(key);
//					doc.setTitle(link.getName());
//					doc.setUrl(link.getLink());
//					doc.setWebId(bean.getId());
//					int id = articleDocDao.insert(doc);
//					if(client.get(getKey(doc.getUrl())) != null){
//						System.out.println("Memcached has store this object");
//						continue;
//					}
//					if(!(id>0)){
//						System.out.println("失败，\t链接名称：" + link.getName() + "\n链接地址："+ link.getLink());
//					}else{
//						doc.setId(id);
//						doc.setStatus(1);
//						client.add(getKey(doc.getUrl()), doc);
//						System.out.println("Memcached now store this object");
//					}
//				}
//				LINKHASH.clear();
//				}catch(Exception e){
//					System.out.println("解析异常，跳过:"+bean.getUrl()+"\tException:"+e.getMessage());
//					continue;
//				}
//			}
			
			
			
			//1584
			List<ArticleDoc> list = articleDocDao.findAll(1);
			for(ArticleDoc bean : list){
				
				if(!bean.getUrl().startsWith("http://www.pcpop.com/doc/")){
					continue;
				}
				System.out.println("获取文章数据");
				//更新文章的作者和发布时间
				try{
					String author = author(bean.getUrl());
					String tmp1 = author.substring(author.lastIndexOf("作者")+3,author.lastIndexOf("编辑")-1);
					String tmp2 = author.substring(0,author.indexOf(":")-2);
					bean.setAuthor(tmp1);
					bean.setPublishTime(tmp2);
					bean.setStatus(2);
					if(!articleDocDao.update(bean)){
						System.out.println("更新作者失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新成功:"+bean.getUrl());
					}
				}catch(java.io.FileNotFoundException e){
					bean.setStatus(10);
					if(!articleDocDao.update(bean)){
						System.out.println("更新作者失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为10[文件或地址查找找不到]:"+bean.getUrl());
					}
					continue;
				}catch(org.htmlparser.util.ParserException e){
					bean.setStatus(10);
					if(!articleDocDao.update(bean)){
						System.out.println("更新作者失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为10[URL解析失败]:"+bean.getUrl());
					}
					continue;
				}catch(Exception e){
					bean.setStatus(11);
					bean.setContent(e.getMessage());
					if(!articleDocDao.update(bean)){
						System.out.println("更新作者和文章发布时间失败:"+bean.getUrl());
					}else{
						System.out.println("["+bean.getId()+"]更新记录状态为11[其他异常情况]:"+bean.getUrl());
					}
					continue;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
