package com.tupian;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
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
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ArticleDoc;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.PicfileBean;
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
import com.chinamilitary.util.CommonUtil;
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.util.IOUtil;
import com.chinamilitary.util.StringUtils;
import com.common.Constants;

public class TUPIANParser {

	static String URL_ = "http://www.tupian.com/";

	static String URL = "http://www.tupian.com";

	static String PIC_SAVE_PATH = Constants.FILE_SERVER;

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
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "menu"));

		if (null != list && list.size() > 0) {
			// 主页中的第7个table
			Div table = (Div) list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(table.getChildrenHTML());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText());
					tmp = new WebsiteBean();
					tmp.setName(link.getLinkText());
					if (!link.getLink().startsWith("http://")) {
						System.out.println(URL + link.getLink() + "\n");
						tmp.setUrl(URL + link.getLink());
					} else {
						System.out.println(link.getLink() + "\n");
						tmp.setUrl(link.getLink());
					}
					tmp.setParentId(1000);
					boolean b = webSiteDao.insert(tmp);
					if (b) {
					 client.add(tmp.getUrl(), tmp.getUrl());
					 System.out.println("成功");
					} else {
					 System.out.println("失败");
					}
				}
			}
			if (null != p2)
				p2 = null;
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
		int pageno = 0;
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
				String tmp = null;
				LinkBean l1 = null;
				int count = list2.size();
				LinkTag li = (LinkTag) list2.elementAt(count-1);
				try{
					if(null != li && null != li.getLinkText()){
						pageno = Integer.valueOf(li.getLinkText());
						for(int i=1;i<pageno+1;i++){
							String tmpUrl = url.replace(".htm", "-"+i+".htm");
							l1 = new LinkBean();
							l1.setLink(tmpUrl);
							l1.setTitle(tmpUrl);
							result.put(tmpUrl, l1);
						}
					}else{
						for (int i = 0; i < list2.size(); i++) {
							LinkTag link2 = (LinkTag) list2.elementAt(i);
							l1 = new LinkBean();
							if (!link2.getLink().startsWith("http://")) {
								String prefix = url.substring(0,url.lastIndexOf("/")+1);
								tmp = prefix + link2.getLink();
							} else {
								tmp = link2.getLink();
							}
							l1.setLink(tmp);
							l1.setTitle(link2.getLinkText());
							result.put(tmp, l1);
						}
					}
				}catch(NumberFormatException e){
					for (int i = 0; i < list2.size(); i++) {
						LinkTag link2 = (LinkTag) list2.elementAt(i);
						l1 = new LinkBean();
						if (!link2.getLink().startsWith("http://")) {
							String prefix = url.substring(0,url.lastIndexOf("/")+1);
							tmp = prefix + link2.getLink();
						} else {
							tmp = link2.getLink();
						}
						l1.setLink(tmp);
						l1.setTitle(link2.getLinkText());
						result.put(tmp, l1);
					}
				}
			}else{
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

	/**
	 * 获取指定URL下的源码
	 * 
	 * @param url1
	 * @return
	 */
	public static String ViewSourceFrame(String url1) throws Exception {
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
	 * 
	 * @param webList
	 */
	static void initHTML(List<WebsiteBean> webList) {
		Long start = System.currentTimeMillis();
		for (WebsiteBean bean : webList) {
			try {
				Long start1 = System.currentTimeMillis();
				String content = ViewSourceFrame(bean.getUrl());
				if (null != content && !"".equalsIgnoreCase(content)) {
					// processWithDoc(bean.getId(), content);
					Long end1 = System.currentTimeMillis();
					System.out.println("单条耗时:" + (end1 - start1) + "长度："
							+ content.getBytes().length);
				}
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
				continue;
			}
		}
		Long end = System.currentTimeMillis();
		System.out.print("总耗时:" + (end - start));
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void secondURL(LinkBean link,int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter2 = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter2).extractAllNodesThatMatch(new HasAttributeFilter("id","photos"));
		if (list != null && list.size() > 0) {
				Article article = null;
				String url = null;
//				for (int i = 0; i < list.size(); i++) {
					Div tmp = (Div)list.elementAt(0);
					
					Parser p2 = new Parser();
					p2.setInputHTML(tmp.toHtml());
					NodeFilter filter3 = new NodeClassFilter(LinkTag.class);
					NodeList list2 = p2.extractAllNodesThatMatch(filter3);
					
					if(null != tmp && tmp.getChildCount() > 0){
						for(int i=0;i<list2.size();i++){
						
						LinkTag node = (LinkTag)list2.elementAt(i);
						System.out.println(">> 标题:"+node.getLinkText()+"\t连接:"+node.getLink());
						
						if (null == client.get(node.getLink())) {
							article = new Article();
							article.setTitle(node.getLinkText());
							article.setWebId(webId);
							if(!node.getLink().startsWith("http://")){
								article.setArticleUrl(URL+node.getLink());
							}else{
								article.setArticleUrl(node.getLink());
							}
							article.setText("NED"); // NED_WALLCOO
							article.setIntro("NPP[NO Preview Picture]");
							
							int key = articleDao.insert(article);
							if (key > 0) {
								System.out.print(node.getLinkText() + "\t|" + url);
//								client.add(node.getLink(), node.getLink());
							}
						} else {
							System.err.println(">> 已存在相同的内容 [" + node.getLinkText()
									+ "],"+node.getLink());
						}
						
						}
					}
//				}
		}
		if (null != parser)
			parser = null;
	}

	/**
	 * 获取图片地址下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static boolean getImage(Article article) throws Exception {
		boolean b = false;
		ResultBean result = hasPaging(article.getArticleUrl(), "class",
				"pages");
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println(" >> key:"+key);
				b = getImage(key, article.getId());
			}
		}
		return b;
	}

	static void getLink(String url) throws Exception {
		Parser p1 = new Parser();
		p1.setURL(url);
		p1.setEncoding("UTF-8");

		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		LinkBean bean = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				System.out.println(link.getLinkText() + "\t" + link.getLink());
				bean = new LinkBean();
				bean.setLink(link.getLink());
				String name = StringUtils
						.illageString(link.getAttribute("title") == null ? (link
								.getLinkText() == null ? "无话题" : link
								.getLinkText())
								: link.getAttribute("title"));
				if (name.indexOf("“") != -1 || name.indexOf("”") != -1) {
					name = name.replaceAll("“", "");
					name = name.replace("”", "");
				}
				// 判断连接中是否存在创建文件夹时的非法字符
				if (name.indexOf("\"") != -1 && name.indexOf("\"") != -1) {
					name = name.replace("\"", "");
				}

				bean.setName(name);
				LINKHASH.put(bean.getLink(), bean);
			}
		}
		p1 = null;
	}
	
	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static boolean getImage(String url, int artId) throws Exception {
		Parser p1 = null;
		Parser p2 = null;
		int size = 0;
		boolean b = true;
		String length = "0";
		ImageBean imgBean = null;
		try{
			p1 = new Parser();
			p1.setURL(url);
			
			NodeFilter filter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "box"));
			if(null != list && list.size() > 0){
				CompositeTag tag = (CompositeTag)list.elementAt(0);
				if(tag.getChildCount() > 0){
					if(tag.getChild(1) instanceof LinkTag){
						LinkTag link = (LinkTag)tag.getChild(1);
						if(link.getChildCount() > 0){
							if(link.getChild(0) instanceof ImageTag){
								ImageTag img = (ImageTag)link.getChild(0);
								imgBean = new ImageBean();
								imgBean.setArticleId(artId);
								imgBean.setHttpUrl(img.getImageURL());
								imgBean.setImgUrl(img.getImageURL());
								if (null != img.getAttribute("alt"))
									imgBean.setTitle(img
											.getAttribute("alt"));
								else
									imgBean.setTitle("NT:"
											+ CommonUtil
													.getDateTimeString());
								imgBean.setLink("NED");
								imgBean.setCommentshowurl(link.getLink());
								try {
									size = Integer.parseInt(length);
									imgBean.setFileSize(Long.valueOf(size));
									imgBean.setStatus(3);
								} catch (Exception e) {
									e.printStackTrace();
									System.err.println(">> IMAGE SIZE ERROR");
									size = 0;
									imgBean.setFileSize(0l);
									imgBean.setStatus(1);
								}
								try {
									int key = imageDao.insert(imgBean);
									if (key > 0) {
										System.out.println(" >> 添加："+imgBean.getTitle()+"成功!");
										client.add(imgBean.getHttpUrl(), imgBean.getHttpUrl());
									}
								} catch (Exception e) {
									b = false;
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if( null != p1)
				p1 = null;
			if( null != p2)
				p2 = null;
		}
		return b;
	}

	/**
	 * 获取图片实际地址
	 * 
	 * @param url
	 * @return
	 */
	static String getImageURL(String url) {
		String result = null;
		try {
			Parser p1 = new Parser();
			p1.setURL(url);

			NodeFilter filter = new NodeClassFilter(CompositeTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("class", "a_fen12bi"));

			if (null != list && list.size() == 1) {
				LinkTag link = (LinkTag) list.elementAt(0);
				result = link.getLink();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取图片地址下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static void getImage(LinkBean link, int webId, int articleId)
			throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		String length = "0";
		int size = 0;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "DataList1"));
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
							ImageBean imgBean = null;
							if (cnl.elementAt(0) instanceof ImageTag) {}
						}
					}
					Thread.sleep(1000);
				}
			}
		}
	}

	static String getImageUrl(String link) {
		int start = link.indexOf("=");
		int end = link.length();
		String imgUrl = link.substring(start + 1, end);
		return imgUrl;
	}

	static String getTitle(String title, String defaultTitle) {
		if (null == title || "".equalsIgnoreCase(title)) {
			return defaultTitle + ":" + System.currentTimeMillis();
		}
		return title;
	}

	static void init() {
		try {
			List<WebsiteBean> webList = webSiteDao.findByParentId(701);
			for (WebsiteBean bean : webList) {
				List<Article> articleList = articleDao
						.findByWebId(bean.getId());
				for (Article article : articleList) {
					if (null == client.get(article.getArticleUrl())) {
						client.add(article.getArticleUrl(), article
								.getArticleUrl());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void update() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(1000);
		for (WebsiteBean bean : webList) {
//			if(bean.getId() != 1623 ){
//				continue;
//			}
			ResultBean result = hasPaging(bean.getUrl()+"index.htm", "class",
						"pages");
			if (result.isBool()) {
				Iterator it = result.getMap().keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					System.out.println("分页地址:"+key);
					LinkBean link = result.getMap().get(key);
					try {
						secondURL(link, bean.getId());
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					/**
					**/
				}
			}
		}
	}

	public static void main(String[] args) {
		// init();
		try {
//			 catalog(URL);
//			 update();
			// vistDesk();
//			loadImg();
			imgDownload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void loadImg() throws Exception {
		// WebsiteBean bean = webSiteDao.findById(702);
		List<WebsiteBean> webList = webSiteDao.findByParentId(1000);
		for (WebsiteBean bean : webList) {
			List<Article> list = articleDao.findByWebId(bean.getId(),"NED");
			for (Article art : list) {
				int count = imageDao.getCount("select count(*) from tbl_image where d_article_id = "+art.getId());
				if(count > 0){
					continue;
				}
				if (getImage(art)) {
					art.setText("FD");
					if (articleDao.update(art)) {
						System.out.println("更新记录[" + art.getTitle() + "]成功\r\n");
					}
				}
			}
		}
	}

	static void vistDesk() throws Exception {
		Parser parser = new Parser();
		parser.setURL("http://vista.zol.com.cn/desk.html");
		parser.setEncoding("GB2312");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "deskP1"));
		if (list != null && list.size() > 0) {

			// System.out.println(list.toHtml());
			Parser p2 = null;
			for (int i = 0; i < list.size(); i++) {
				Div div = (Div) list.elementAt(i);
				p2 = new Parser();
				p2.setInputHTML(div.toHtml());

				NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(filter2);

				Article article = null;
				String url = null;
				if (null != list2 && list2.size() > 0) {
					
				}
			}
			if (null != parser)
				parser = null;
		}
	}

	static void imgDownload() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(1000);
		for(WebsiteBean website:webList){
			System.out.println(website.getId()+"|"+website.getName()+"|"+website.getUrl());
			List<Article> artList = articleDao.findByWebId(website.getId(), "FD");
			for(Article article:artList){
				List<ImageBean> list = imageDao.findImage(article.getId());
				if(list.size() == 0){
					article.setText("NED");
					if(articleDao.update(article)){
						System.out.println(">> 更新图片记录数据为0的文章成功");
					}
				}else{
					for(ImageBean bean : list) {
						if(bean.getLink().equalsIgnoreCase("NED")){
							if(download(bean)){
								bean.setStatus(1);
								bean.setLink("FD");
								if(imageDao.update(bean)){
									System.out.println(">> 更新图片对象["+bean.getId()+"]成功");
								}
							}
						}
					}
				}
		}
		}
	}

	static boolean download(ImageBean imgBean) {
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		String length = "0";
		try {
			byte[] big = null;
			big = HttpClientUtils.getResponseBodyAsByte(imgBean.getCommentshowurl(), null, imgBean.getHttpUrl());
			if(null == big)
				return false;
			length = String.valueOf(big.length);
			if(length.equalsIgnoreCase("20261")){
				return false;
			}
			if(!imgBean.getImgUrl().equals(imgBean.getHttpUrl())){
				//小图
				if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ s_fileName)) == null) {
					IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
							+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
							+ imgBean.getArticleId() + File.separator
							+ s_fileName);
				}
			}
			
			//大图
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ fileName)) == null) {
				IOUtil.createFile(big, PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			if(!imgBean.getImgUrl().equals(imgBean.getHttpUrl())){
				bean.setSmallName(File.separator
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ s_fileName);
			}else{
				bean.setSmallName("nopic.jpg");
			}
			bean.setName(File.separator
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				imgBean.setFileSize(Long.valueOf(length));
				if(imageDao.update(imgBean)){
					boolean b = picFiledao.insert(bean);
					if (b) {
							client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
									+ bean.getName()), bean);
							client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
									+ bean.getSmallName()), bean);
					} else {
						return false;
					}
				}
			} catch (Exception e) {
				System.out.println("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			System.out.println("网络连接，文件IO异常");
			return false;
		}
		return true;
	}

}
