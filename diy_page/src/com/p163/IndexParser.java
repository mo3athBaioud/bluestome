package com.p163;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.ResultBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;

/**
 * 163新闻网首页数据
 * 
 * @author Bluestome.Zhang
 * 
 */
public class IndexParser {
	
	static byte[] LOCK = new byte[0];
	
	static int D_WEB_ID = 9884;

	static String URL = "http://news.163.com/photo/";

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
	 * @param url 指定的网页
	 * @throws Exception
	 */
	static void catalog(String url) throws Exception {
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("UTF-8");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter).extractAllNodesThatMatch(new HasAttributeFilter("class","nav_sub"));

		if (null != list && list.size() > 0) {
			// 主页中的第7个table
			Div div = (Div) list.elementAt(0);
			if(null != div && div.toHtml() != null && !div.toHtml().equals("")){
				Parser p1 = new Parser();
				p1.setInputHTML(div.toHtml());
				
				fileter = new NodeClassFilter(LinkTag.class);
				list = p1.extractAllNodesThatMatch(fileter);
				
				WebsiteBean website = null;
				for(int i=0;i<list.size();i++){
					synchronized(LOCK){
						LinkTag link = (LinkTag)list.elementAt(i);
						if(null != link && !link.getLink().equals("#")){
							System.out.println(""+link.getLinkText()+"|"+link.getLink());
							if(null == webSiteDao.findByUrl(link.getLink())){
								website = new WebsiteBean();
								website.setParentId(D_WEB_ID);
								website.setName(link.getLinkText());
								website.setUrl(link.getLink());
								website.setType(1);
								website.setStatus(1);
								if(webSiteDao.insert(website)){
									System.err.println(" >> 站点["+website.getName()+"|"+website.getUrl()+"]添加成功!");
								}
							}else{
								System.err.println("站点["+link.getLinkText()+"|"+link.getLink()+"]已存在");
								ResultBean result = hasPaging(link.getLink(),"class","bar_page");
								if(result.isBool()){
									Iterator it = result.getMap().keySet().iterator();
									while(it.hasNext()){
										String key = (String)it.next();
										LinkBean value = result.getMap().get(key);
										if(null != value){
											System.out.println(key);
										}
									}
								}
							}
						}
					}
				}
				if(null != list && list.size() > 0){
					list = null;
				}
				if(null != fileter){
					fileter = null;
				}
				if(null != p1){
					p1 = null;
				}
			}
		}
		if (null != parser)
			parser = null;
	}
	
	/**
	 * 获取分类下的分页信息
	 * 
	 * @param url
	 * @param attribute
	 * @param value
	 * @return
	 * @throws Exception
	 */
	static ResultBean hasPaging(String url, String cls, String value)
			throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");
		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter(cls, value));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2);
			if (null != list2 && list2.size() > 0) {
				for(int i=0;i<list2.size();i++){
					LinkTag lt = (LinkTag)list2.elementAt(i);
					if(null != lt && lt.getLink().startsWith("http://")){
						LinkBean l1 = new LinkBean();
						l1.setLink(lt.getLink());
						l1.setTitle(lt.getLinkText());
						result.put(lt.getLink(), l1);
					}
				}
			} else {
				LinkBean l1 = new LinkBean();
				l1.setLink(url);
				result.put(url, l1);
			}

			if (null != p2)
				p2 = null;
		}
		LinkBean l1 = new LinkBean();
		l1.setLink(url);
		result.put(url, l1);
		result.setBool(true);
		return result;
	}
	
	

	public static void main(String args[]){
		try{
			List<WebsiteBean> list = webSiteDao.findByParentId(D_WEB_ID);
			for(WebsiteBean bean:list){
				System.out.println(""+bean.getName()+"|"+bean.getUrl());
				ResultBean result = hasPaging(bean.getUrl(),"class","bar_page");
				//TODO 因为类型不一样，需要根据不同的网站结构指定不同的页面解析器
				//TODO 例如：大事件图片报道,摄影界 页面中子内容中的界面结构就不一样，需要分别解析。
				if(result.isBool()){
					Iterator it = result.getMap().keySet().iterator();
					while(it.hasNext()){
						String key = (String)it.next();
						LinkBean value = result.getMap().get(key);
						if(null != value){
							System.out.println(key);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
