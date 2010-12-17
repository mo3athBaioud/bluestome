package com.chinamilitary.htmlparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.ResultBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ArticleDocDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.common.Constants;

public class VeryCDSoftwareParser {

	private static Log log = LogFactory.getLog(VeryCDSoftwareParser.class);
	
	static final String PIC_SAVE_PATH = Constants.FILE_SERVER;
	
	final static Integer D_PARENT_ID = 1300;

	final static Integer _D_PARENT_ID = 1350;
	
	private static final String URL = "http://www.verycd.com";

	private static final String URL_ = "http://www.verycd.com/";

	private static final String URL_ARCHIVES = "http://www.verycd.com/archives/";
	
	private static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	private static HashMap<String, String> CATALOGHASH = new HashMap<String, String>();
	
	private static HashMap<String, LinkBean> SECONDLINKHASH = new HashMap<String, LinkBean>();

	private static HashMap<String, ResultBean> PAGELINKHASH = new HashMap<String, ResultBean>();

	private static int COUNT = 0;
	
	final static int PAGE_SIZE = 40;

	private static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	private static ArticleDao articleDao = DAOFactory.getInstance()
			.getArticleDao();

	private static WebSiteDao wesiteDao = DAOFactory.getInstance()
			.getWebSiteDao();

	private static PicFileDao picFileDao = DAOFactory.getInstance()
			.getPicFileDao();

	private static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDocDao articleDocDao = DAOFactory.getInstance().getArticleDocDao();	
	
	final static String VERYCD="WEB_VERYCD";
	
	/**
	 * 首页分类
	 * 
	 * @throws Exception
	 */
	public static void catelogyArchives(String url) throws Exception {
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "nav"));
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");
			NodeFilter tagFilter = new NodeClassFilter(CompositeTag.class);
			NodeList tagList = p1.extractAllNodesThatMatch(tagFilter).extractAllNodesThatMatch(
					new HasAttributeFilter("id", "libCatalog"));
			if (tagList != null && tagList.size() > 0) {
				Parser p2 = new Parser();
				p2.setInputHTML(tagList.toHtml());
				p2.setEncoding("GB2312");
				NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
				NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println("text:"+link.getLinkText());
					System.out.println("link:"+link.getLink());
					tmp = new WebsiteBean();
					int start = link.getLinkText().indexOf("d");
					int end = link.getLinkText().lastIndexOf(")");
					if(start != -1 && end != -1){
						tmp.setName(link.getLinkText().substring(0,start));
						System.out.println(">> tmp.getName():"+tmp.getName());
					}
					if(link.getLink().startsWith("\\")){
						tmp.setUrl(URL_ + link.getLink());
						System.out.println("new:"+ URL_ + link.getLink()+ "\n");
					}else{
						tmp.setUrl(URL+link.getLink());
						System.out.println("new:"+URL+link.getLink()+"\n");
					}
					tmp.setParentId(_D_PARENT_ID);
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
	
	
	/**
	 * 首页分类中的数据
	 * 
	 * @throws Exception
	 */
	public static void catelogy(String url) throws Exception {
//		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "nav"));
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");
			NodeFilter tagFilter = new NodeClassFilter(CompositeTag.class);
			NodeList tagList = p1.extractAllNodesThatMatch(tagFilter).extractAllNodesThatMatch(
					new HasAttributeFilter("id", "libCatalog"));
			if (tagList != null && tagList.size() > 0) {
				Parser p2 = new Parser();
				p2.setInputHTML(tagList.toHtml());
				p2.setEncoding("GB2312");
				NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
				NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
//					int start = link.getLinkText().indexOf("(");
//					int end = link.getLinkText().indexOf(")");
//					int count = 0;
//					if(start != -1 && end != -1){
//						try{
//							count = Integer.valueOf(link.getLinkText().substring(start+1,end));
//						}catch(Exception e){
//							System.err.println(e);
//						}
//					}
					if(link.getLink().startsWith("\\")){
						CATALOGHASH.put(URL+link.getLink(), link.getLinkText());
//						CATALOGHASH.put(URL_+link.getLink(), count);
					}else{
//						CATALOGHASH.put(URL+link.getLink(), count);
						CATALOGHASH.put(URL+link.getLink(), link.getLinkText());
					}
				}
			}
		}
	}
	
	/**
	 * 首页分类
	 * 
	 * @throws Exception
	 */
	public static void catelogy(WebsiteBean bean) throws Exception {
		WebSiteDao dao = DAOFactory.getInstance().getWebSiteDao();
		Parser parser = new Parser();
		parser.setURL(bean.getUrl());
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "libCatalog"));
		if (list != null && list.size() > 0) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText());
					System.out.println(URL_ + link.getLink() + "\n");
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					tmp.setUrl(link.getLink());
					tmp.setParentId(bean.getId());
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

	/**
	 * 分页判断
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static ResultBean hasPagingByCount(WebsiteBean bean, String attribute,
			String value) throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(bean.getUrl());
		parser.setEncoding("GB2312");

		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter(attribute, value));
		if (list != null && list.size() == 1) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if(linkList != null && linkList.size() > 0){
				for(int i=0;i<linkList.size();i++){
					LinkTag link = (LinkTag)linkList.elementAt(i);
					LinkBean tmp = null;
					if(link.getLink().startsWith("/")){
						tmp = new LinkBean();
						if(!link.getLink().startsWith("http://")){
							tmp.setLink(URL+link.getLink());
						}else{
							tmp.setLink(link.getLink());
						}
						tmp.setName(link.getLinkText());
						result.getMap().put(tmp.getLink(), tmp);
					}
				}
				result.setBool(true);
			}
		}
		return result;
	}
	

	/**
	 * 分页判断
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static ResultBean hasPaging(WebsiteBean bean, String attribute,
			String value) throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(bean.getUrl());
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter(attribute, value));
		if (list != null && list.size() == 1) {
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p1.extractAllNodesThatMatch(linkFilter);
			if(linkList != null && linkList.size() > 0){
				for(int i=0;i<linkList.size();i++){
					LinkTag link = (LinkTag)linkList.elementAt(i);
					LinkBean tmp = null;
					if(link.getLink().startsWith("/")){
						tmp = new LinkBean();
						if(!link.getLink().startsWith("http://")){
							tmp.setLink(URL+link.getLink());
						}else{
							tmp.setLink(link.getLink());
						}
						tmp.setName(link.getLinkText());
						result.getMap().put(tmp.getLink(), tmp);
					}
				}
				result.setBool(true);
			}
		}
		LinkBean l1 = null;
		l1 = new LinkBean();
		l1.setLink(bean.getUrl());
		result.add(l1);
		result.getMap().put(l1.getLink(), l1);
		result.setBool(true);
		return result;
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void secondURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "archiveResourceList"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("GB2312");
			NodeList list4 = p2.parse(linkFilter);
			if (list4 != null || list4.size() > 0) {
//				System.out.println(">> URL["+link.getLink()+"]的记录数量:"+list4.size());
				for (int i = 0; i < list4.size(); i++) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						ArticleDoc doc = null;
						doc = new ArticleDoc();
						doc.setTitle(nl.getLinkText());
						if(nl.getLink().startsWith("http://")){
							doc.setUrl(nl.getLink());
						}else{
							doc.setUrl(URL+nl.getLink());
						}
						doc.setWebId(webId);
						if(null == client.get(getKey(doc.getUrl()))){
							int id = articleDocDao.insert(doc);
							if(!(id>0)){
								System.out.println("失败，\t链接名称：" + doc.getTitle() + "\n链接地址："+ doc.getUrl());
							}else{
								System.out.println("\t>> Title:"+doc.getTitle());
								System.out.println("\t>> Url:"+doc.getUrl());
								doc.setId(id);
								doc.setStatus(1);
								client.add(getKey(doc.getUrl()), doc);
								System.out.println("Memcached now store this object");
							}
						}else{
							System.out.println(">> 已存在相同的内容 ["+nl.getLinkText()+"]");
						}
				}
			}
		}
	}

	/**
	 * 获取文章下图片
	 * @param url
	 * @return
	 * @throws Exception
	 */
	static String getAlbumPicUrl(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "topicImg"));
		ImageTag link = null;
		if (list != null && list.size() > 0) {
			System.out.println("\t>> html:"+list.toHtml());
			Parser p1 = new Parser();
			p1.setInputHTML(list.toHtml());
			p1.setEncoding("GB2312");

			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			NodeList nodes = p1.extractAllNodesThatMatch(imageFilter);
			if (nodes != null && nodes.size() > 0) {
				System.out.println(">> nodes.size:"+nodes.size());
				if (nodes.elementAt(0) instanceof ImageTag) {
					link = (ImageTag) nodes.elementAt(0);
				}
			}
		}
		if (link != null) {
			return link.getImageURL();
		} else {
			return null;
		}
	}


	static void getFileDownloadUrl(String url) throws Exception {
		Parser parser = null;
		if (null != url) {
			parser = new Parser();
			parser.setURL(url);
			parser.setEncoding("GB2312");
			
			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","iptcomED2K"));
			if(null != list && list.size() > 0){
				Parser p1 = new Parser();
				p1.setInputHTML(list.toHtml());
				p1.setEncoding("GB2312");
				
				NodeFilter inputFilter = new NodeClassFilter(InputTag.class);
				NodeList inputList = p1.extractAllNodesThatMatch(inputFilter).extractAllNodesThatMatch(new HasAttributeFilter("class","forminput"));
				if(inputList != null && inputList.size() > 0){
					for(int i=0;i<inputList.size();i++){
						InputTag input = (InputTag)inputList.elementAt(i);
						String value = input.getAttribute("value");
						if(null != value){
							System.out.println("\t>> value:\t"+value);
						}
					}
				}
			}
		}
	}

	static void getFileDownloadUrl(ArticleDoc doc) throws Exception {
		Parser parser = null;
		if (doc != null) {
			parser = new Parser();
			parser.setURL(doc.getUrl());
			parser.setEncoding("GB2312");
			
			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter).extractAllNodesThatMatch(new HasAttributeFilter("id","iptcomED2K"));
			if(null != list && list.size() > 0){
				System.out.println("\thtml:"+list.toHtml());
			}
		}
	}

	/**
	 * 更新Article介绍 Wallcoo
	 * 
	 * @param article
	 */
	static String getArticleText(String articleUrl) {
		Parser parser = null;
		String intro = "";
		try {
			parser = new Parser();
			parser.setURL(articleUrl);
			parser.setEncoding("GB2312");
			NodeFilter filter = new NodeClassFilter(Div.class);
			NodeList list = parser.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "intro2"));
			if (list != null) {
				Div div = (Div) list.elementAt(0);
				NodeList divList = div.getChildren();
				if (divList != null && divList.size() > 0) {
					intro = divList.toHtml();
				}
				/**
				 * String[] ids = div.getIds(); for(int i=0;i<ids.length;i++){
				 * System.out.println("ids["+i+"]:"+ids[i]); }
				 */
			}
			parser = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intro;
	}

	static void clear() {
		if (LINKHASH.size() > 0) {
			LINKHASH.clear();
		}

		if (SECONDLINKHASH.size() > 0) {
			SECONDLINKHASH.clear();
		}

		if (PAGELINKHASH.size() > 0) {
			PAGELINKHASH.clear();
		}
	}
	
	static void update() throws Exception{
		List<WebsiteBean> list = wesiteDao.findByParentId(D_PARENT_ID);
		/**
		 * 文章获取并入库 List<WebsiteBean> list = wesiteDao.findByParentId(125);
		 */
		if (list != null && list.size() > 0) {
			ResultBean result = null;
			int i = 0;
			for (WebsiteBean bean : list) {
				try{
					result = hasPaging(bean, "id", "archivePageList");
					if (result.isBool()) {
						Iterator it = result.getMap().keySet().iterator();
						while(it.hasNext()){
							String key = (String)it.next();
//							System.out.println("key:"+key);
//							LinkBean link = (result.getMap().get(key));
//							try {
//								secondURL(link, bean.getId());
//							} catch (org.htmlparser.util.EncodingChangeException e) {
//								e.printStackTrace();
//							}
						}
						result.getMap().clear();
					}
					i++;
					// 更新菜单列表排序
					wesiteDao.update(bean);
				}catch(Exception e){
					System.out.println(">> Exception :"+e);
				}
			}
		}
	}
	
	/**
	 * 更新文章
	 * @throws Exception
	 */
	static void updateArchives() throws Exception{
		List<WebsiteBean> list = wesiteDao.findByParentId(_D_PARENT_ID);
		if (list != null && list.size() > 0) {
			ResultBean result = null;
			for (WebsiteBean bean : list) {
				try{
					if(bean.getId() == 1351){
						if(!needUpdate(bean)){
							log.debug(">> 首页数据相同，表明记录没有增加，不需要更新!");
							break;
						}else{
							continue;
						}
					}
					//判断是否需要进行更新记录
					if(!needUpdate(bean)){
						log.debug(">> ["+bean.getUrl()+"]数据相同，表明记录没有增加，不需要更新!");
						continue;
					}
					result = hasPagingByCount(bean, "id", "archivePageList");
					if (result.isBool()) {
						Iterator it = result.getMap().keySet().iterator();
						while(it.hasNext()){
							String key = (String)it.next();
							System.out.println("\tkey:"+key);
//							LinkBean link = (result.getMap().get(key));
//							try {
//								secondURL(link, bean.getId());
//							} catch (org.htmlparser.util.EncodingChangeException e) {
//								e.printStackTrace();
//								continue;
//							}
						}
						result.getMap().clear();
					}
				}catch(Exception e){
					System.out.println(">> Exception :"+e);
				}
			}
		}
	}
	
	/**
	 * 判断是否需要更新
	 * @param bean
	 * @return boolean true: 需要更新 false: 不需要更新
	 */
	static boolean needUpdate(WebsiteBean bean){
		boolean b = true;
		String value = CATALOGHASH.get(bean.getUrl());
		if(null != value &&  !"".equalsIgnoreCase(value)){
			if(value.equalsIgnoreCase(bean.getName())){
				b = false;
			}
		}
		return b;
	}
	
	public static void main(String args[]) {
		try {
			//初始化Archives文章目录
//			catelogyArchives(URL_ARCHIVES);
			
			//获取一级目录
			catelogy("http://www.verycd.com/");
			
			updateArchives();
			
//			update();
			
			
			
			/*******************************华丽的分割线：测试部分**************************************/
//			String img = getAlbumPicUrl("http://www.verycd.com/topics/2870019/");
//			if(null != img){
//				System.out.println(">>"+img);
//			}
//			getFileDownloadUrl("http://www.verycd.com/topics/2749864/");
			
//			patch();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				CATALOGHASH.clear();
				System.gc();
			}
	}
	
	static void patch() throws Exception{
		List<WebsiteBean> list = wesiteDao.findByParentId(_D_PARENT_ID);
		for(WebsiteBean bean:list){
			String key = bean.getUrl().replace("/archives","/sto");
			System.out.println(">> key:"+key);
			String value = CATALOGHASH.get(key);
			if(null != value  && !"".equalsIgnoreCase(value)){
				System.out.println(">> value:"+value);
				bean.setName(value);
				if(wesiteDao.update(bean)){
					System.out.println(">> update website success!");
				}else{
					System.out.println(">> update website failure!");
				}
			}
		}
	}
	
	static void test() throws Exception{
		System.out.println("****************************Split Line********************************");
		Iterator it = CATALOGHASH.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			System.out.println(">> key:"+key);
			String value = CATALOGHASH.get(key);
			System.out.println(">> value:"+value);
		}
	}

	//制造缓存KEY
	static String getKey(String url){
		return VERYCD+":"+url;
	}

}
