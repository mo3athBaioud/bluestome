package com.bizhizhan.htmlparser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.ImageBean;
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
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.util.IOUtil;
import org.htmlparser.PrototypicalNodeFactory; 
public class BIZHIZHANParser {

	static String URL = "http://www.bizhizhan.com/";
	
	static String IMAGE_URL = "http://image6.tuku.cn/";
	
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
		
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "nav")); // id
		
		if(null != list && list.size() > 0){
			
			System.out.println(""+list.size());
			
			Div div = (Div)list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(div.toHtml());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText()+"|"+URL+link.getLink() + "\n");
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					tmp.setUrl(URL+link.getLink());
					tmp.setParentId(600);
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
	static ResultBean hasPaging(String url) throws Exception{
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		
		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "page padding5"));
		if(list != null  && list.size() > 0){
			Parser p2 = new Parser();
			p2.setInputHTML(list.elementAt(0).toHtml());
			
			NodeFilter optionFilter = new NodeClassFilter(OptionTag.class);
			NodeList optionList = p2.extractAllNodesThatMatch(optionFilter);
			if(optionList != null && optionList.size() > 1){
				result.setBool(true);
				LinkBean oplink = null;
				for(int j=0;j<optionList.size();j++){
					OptionTag option = (OptionTag)optionList.elementAt(j);
					oplink = new LinkBean();
					String url2 = url+option.getValue();
					oplink.setLink(url+option.getValue());
					result.put(url2, oplink);
				}
			}
			b = true;
			result.setBool(b);
		}
		return result;
	}
	
	public static void getArtcile(String url,int webId) throws Exception{
		Parser p1 = new Parser();
		p1.setURL(url);
		
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = p1.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "list"));
		
		if(null != list && list.size() > 0){
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			
			NodeFilter filter2 = new NodeClassFilter(CompositeTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2).
				extractAllNodesThatMatch(
					new HasAttributeFilter("class", "cont_list"));
			;
			
			if(null != list2){
				Parser p3 = new Parser();
				p3.setInputHTML(list2.toHtml());
				
				NodeFilter filter3 = new NodeClassFilter(LinkTag.class);
				NodeList list3 = p3.extractAllNodesThatMatch(filter3);
				Article article = null;
				for(int i=0;i<list3.size();i++){
					LinkTag link = (LinkTag)list3.elementAt(i);
//					NodeList imgNode = link.getChildren();
//					if(null != imgNode){
//        				if(imgNode.elementAt(0) instanceof ImageTag){
//        					ImageTag img = (ImageTag)imgNode.elementAt(0);
        					String url2 = URL+link.getLink();
//            				System.out.println("第"+i+"条记录");
            				System.out.println(link.getLinkText()+"|"+url2);
            				if(null == client.get(url2)){
								article = new Article();
								article.setWebId(webId);
								article.setArticleUrl(url2);
								article.setTitle(link.getLinkText());
								article.setText("NED"); // No Execute Download
								int key = articleDao.insert(article);
								if (key > 0) {
									client.add(url, url);
									System.out.println("添加" + link.getLinkText()+ ",成功");
								} else {
									System.out.println("添加" + link.getLinkText() + "失败,已存在相同标题的内容");
								}
            				}else{
            					System.out.println(">> 缓存中存在该图片["+url2+"]地址 <<");
            				}
//        				}
//					}
				}
				
			}
		}
		
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
	
	public static void main(String[] args){
		try{
//			catalog(URL);
//			ResultBean  result = hasPaging("http://www.bizhizhan.com/fzlsjbz/");
//			if(result.isBool()){
//				Iterator it = result.getMap().keySet().iterator();
//				while(it.hasNext()){
//					String key = (String)it.next();
//					System.out.println("key:"+key);
//				}
//			}
			
//			getImage("http://www.bizhizhan.com/fzlsjbz/23-1.html");
			
			List<WebsiteBean> list = webSiteDao.findByParentId(600);
			for(WebsiteBean bean:list){
				ResultBean  result = hasPaging(bean.getUrl());
				if(result.isBool()){
					Iterator it = result.getMap().keySet().iterator();
					while(it.hasNext()){
						String key = (String)it.next();
						System.out.println("key:"+key);
						getArtcile(key, bean.getId());
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
