package com.tuku.htmlparser;

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
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.ResultBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.chinamilitary.test.TestHttpClient;
import com.chinamilitary.util.IOUtil;

public class TUKUParser {

	static String URL = "http://www.tuku.cn/";
	
	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	static List<Article> ARTICLELIST = new ArrayList<Article>();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();
	
	/**
	 * 获取分类链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	static void catalog(String url) throws Exception { // WebsiteBean bean
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("UTF-8");
		NodeFilter fileter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter);
		
		if(null != list && list.size() > 0){
			//主页中的第7个table
			TableTag table = (TableTag)list.elementAt(6);
			Parser p2 = new Parser();
			p2.setInputHTML(table.getChildrenHTML());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText());
					System.out.println(URL+link.getLink() + "\n");
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					tmp.setUrl(URL+link.getLink());
					tmp.setParentId(400);
					boolean b = webSiteDao.insert(tmp);
					if (b) {
						client.add(tmp.getUrl(), tmp.getUrl());
						System.out.println("成功");
					} else {
						System.out.println("失败");
					}
				}
			}
		}
		if(null != parser)
			parser = null;
	}
	
	/**
	 * 获取分类下的分页信息
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	static ResultBean hasPaging(String url,String attribute,String value) throws Exception{
		boolean b = false;
		int count = 1;
		String link = url;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		
		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Span.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter(attribute, value));
		if(list != null  && list.size() > 0){
			Span span = (Span)list.elementAt(0);
			String tmp = span.getChildrenHTML();
			int start = tmp.indexOf("\">");
			int end = tmp.indexOf("</font>");
			String tmpCount = tmp.substring(start+2,end);
			try{
				count = Integer.parseInt(tmpCount);
			}catch(Exception e){
				e.printStackTrace();
			}
			for(int i=1;i<count+1;i++){
				LinkBean l1 = null;
				if(i>1){
					link = url+"&Page="+i;
				}
				boolean isTrue = TestHttpClient.urlValidation(link);
				if(isTrue){
					l1 = new LinkBean();
					l1.setLink(link);//"htm/"+
					l1.setName(link);//link.getLinkText()
					result.put(link, l1);
				}else{
					link = url+"?Page="+i;
					if(TestHttpClient.urlValidation(link)){
						l1 = new LinkBean();
						l1.setLink(link);//"htm/"+
						l1.setName(link);//link.getLinkText()
						result.put(link, l1);
					}
				}
			}
			b = true;
			result.setBool(true);
		}else{
			result.setBool(b);
		}
		return result;
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
	 * 获取指定列表中页面源码
	 * @param webList
	 */
	static void initHTML(List<WebsiteBean> webList){
		Long start = System.currentTimeMillis();
		for(WebsiteBean bean:webList){
			try{
				Long start1 = System.currentTimeMillis();
				String content = ViewSourceFrame(bean.getUrl());
				if(null != content && !"".equalsIgnoreCase(content)){
//					processWithDoc(bean.getId(), content);
					Long end1 = System.currentTimeMillis();
					System.out.println("单条耗时:"+(end1-start1)+"长度："+content.getBytes().length);
				}
			}catch(Exception e){
				System.out.println("Exception:"+e.getMessage());
				continue;
			}
		}
		Long end = System.currentTimeMillis();
		System.out.print("总耗时:"+(end-start));
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
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "DataList1"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("UTF-8");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Article article = null;
							if (cnl.elementAt(0) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(0);
								String url = URL + nl.getLink();
								if(null == client.get(url)){
									article = new Article();
									article.setWebId(webId);
									article.setArticleUrl(url);
									article.setTitle(it.getAttribute("alt"));
									article.setText("NED"); //NED_WALLCOO
									article.setIntro("");
									int key = articleDao.insert(article);
									if (key > 0) {
										System.out.print(">> 连接名称:"+it.getAttribute("alt"));
										System.out.println(">> URL:"+url);
										client.add(url, url);
									} 
								}else{
									System.err.println(">> 已存在相同的内容 ["+nl.getLink()+"]");
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void getImage(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "DataList1"));
		if (list != null && list.size() > 0) {
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeFilter imageFilter = new NodeClassFilter(ImageTag.class);
			OrFilter lastFilter = new OrFilter();
			lastFilter
					.setPredicates(new NodeFilter[] { linkFilter, imageFilter });
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			p2.setEncoding("UTF-8");
			NodeList list4 = p2.parse(lastFilter);
			if (list4 != null || list4.size() > 0) {
				for (int i = 0; i < list4.size(); i++) {
					// 地址
					if (list4.elementAt(i) instanceof LinkTag) {
						LinkTag nl = (LinkTag) list4.elementAt(i);
						NodeList cnl = nl.getChildren();
						if (cnl != null && cnl.size() > 0) {
							// 小图 可能存在部分图片无法访问，需要判断
							Article article = null;
							if (cnl.elementAt(0) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(0);
								String url = URL + nl.getLink();
								if(null == client.get(url)){
									article = new Article();
									article.setWebId(webId);
									article.setArticleUrl(url);
									article.setTitle(it.getAttribute("alt"));
									article.setText("NED"); //NED_WALLCOO
									article.setIntro("");
									int key = articleDao.insert(article);
									if (key > 0) {
										System.out.print(">> 连接名称:"+it.getAttribute("alt"));
										System.out.println(">> URL:"+url);
										client.add(url, url);
									} 
								}else{
									System.err.println(">> 已存在相同的内容 ["+nl.getLink()+"]");
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		StringBuffer sb = new StringBuffer();
		try{
//			catalog(URL);
			
			List<WebsiteBean>  webList = webSiteDao.findByParentId(400);
//			for(WebsiteBean bean:webList){
//				ResultBean result = hasPaging(bean.getUrl(), "id", "lblPageCount");
//				if(result.isBool()){
//					Iterator it = result.getMap().keySet().iterator();
//					while(it.hasNext()){
//						String key = (String)it.next();
//						LinkBean link = result.getMap().get(key);
//						sb.append(link.getLink()+"\n");
//						try{
//							secondURL(link,bean.getId());
//						}catch(Exception e){
//							e.printStackTrace();
//							System.out.println("key:"+key);
//							continue;
//						}
//					}
//				}
//			}
			
			for(WebsiteBean bean:webList){
				List<Article> articleList = articleDao.findByWebId(bean.getId());
				System.out.println("文章数量:"+articleList.size());
			}
			
//			ResultBean result = hasPaging("http://www.tuku.cn/aclass.aspx?id=6", "id", "lblPageCount");
//			if(result.isBool()){
//				Iterator it = result.getMap().keySet().iterator();
//				while(it.hasNext()){
//					String key = (String)it.next();
//					LinkBean link = result.getMap().get(key);
//					secondURL(link,412);
//				}
//			}
			IOUtil.createFile(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
			IOUtil.createFile(sb.toString());
		}
	}
	
	
}
