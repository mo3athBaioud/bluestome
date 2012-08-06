package com.p163;

import java.util.ArrayList;
import java.util.HashMap;
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
								System.err.println("站点["+link.getLink()+"]已存在");
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

	public static void main(String args[]){
		try{
			catalog(URL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
