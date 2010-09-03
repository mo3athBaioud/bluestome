package com.bizhi;

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

public class BIZHIParser {

	static String URL_ = "http://www.bizhi.com/";

	static String URL = "http://www.bizhi.com";

	static String PIC_SAVE_PATH = "D:\\share\\bizhi\\";

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
						new HasAttributeFilter("id", "mainNav"));

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
					if (link.getLink().endsWith(".html")) {
						tmp = new WebsiteBean();
						tmp.setName(link.getLinkText());
						if (!link.getLink().startsWith("http://")) {
							System.out.println(URL + link.getLink() + "\n");
							tmp.setUrl(URL + link.getLink());
						} else {
							System.out.println(link.getLink() + "\n");
							tmp.setUrl(link.getLink());
						}
						tmp.setParentId(1200);
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
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter(cls, value));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());

			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2);
			if (null != list && list2.size() > 0) {
				String tmp = null;
				LinkBean l1 = null;
				for (int i = 0; i < list2.size(); i++) {
					l1 = new LinkBean();
					LinkTag link2 = (LinkTag) list2.elementAt(i);
					if (!link2.getLink().startsWith("http://")) {
						tmp = URL_ + link2.getLink();
					} else {
						tmp = link2.getLink();
					}
					tmp = tmp.replace("&amp;", "&");
					l1.setLink(tmp);
					l1.setTitle(link2.getLinkText());
					result.put(tmp, l1);
				}
				result.setBool(true);
			}
			if (null != p2)
				p2 = null;
		} else {
			result.setBool(b);
		}
		return result;
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
	static ResultBean hasPaging2(String url)
			throws Exception {
		boolean b = false;
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "page"));
		int pageNum = 1;
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());

			NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2);
			if (null != list && list2.size() > 0) {
				
				String tmp = null;
				LinkBean l1 = null;
				if(list2.size() > 10){
					LinkTag tmpLink = (LinkTag) list2.elementAt(8);
					try{
						pageNum = Integer.valueOf(tmpLink.getLinkText());
					}catch(Exception e){
						System.err.println(">> 分页出现异常");
						throw new Exception("分页出现异常");
					}
					for(int i=1;i<pageNum+1;i++){
						tmp = url.replace("1.html", i+".html");
						l1 = new LinkBean();
						l1.setLink(tmp);
						l1.setTitle(""+i);
						result.put(tmp, l1);
						System.out.println("分页地址："+tmp);
					}
					result.setBool(true);
				}else{
					for (int i = 0; i < list2.size(); i++) {
						l1 = new LinkBean();
						LinkTag link2 = (LinkTag) list2.elementAt(i);
						if(!link2.getLink().equalsIgnoreCase("#")){
							if (!link2.getLink().startsWith("http://")) {
								tmp = URL + link2.getLink();
							} else {
								tmp = link2.getLink();
							}
							l1.setLink(tmp);
							l1.setTitle(link2.getLinkText());
							result.put(tmp, l1);
							System.out.println("分页地址："+tmp);
						}
						l1.setLink(url);
						l1.setTitle(link2.getLinkText());
						result.put(url, l1);
					}
					result.setBool(true);
				}
				
			}
			if (null != p2)
				p2 = null;
		} else {
			result.setBool(b);
		}
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
	public static void secondURL(LinkBean link, int webId) throws Exception {
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("GB2312");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "uip uip_250"));
		if (list != null && list.size() > 0) {
			Parser p2 = null;
			CompositeTag div = (CompositeTag) list.elementAt(0);
			p2 = new Parser();
			p2.setInputHTML(div.toHtml());
			
			NodeFilter filter2 = new NodeClassFilter(Span.class);
			NodeList list2 = p2.extractAllNodesThatMatch(filter2);
			Article article = null;
			String url = null;
			if(null != list2 && list2.size() > 0){
				for(int i=0;i<list2.size();i++){
					Span span = (Span)list2.elementAt(i);
					NodeList tmpNode = span.getChildren();
					if(null != tmpNode && tmpNode.size() > 0){
						LinkTag linkTag = (LinkTag)tmpNode.elementAt(0);
						System.out.println(linkTag.getLinkText()+"|"+linkTag.getLink());
						
						if (!linkTag.getLink().startsWith("http://")) {
							url = URL + linkTag.getLink();
						} else {
							url = linkTag.getLink();
						}
						
						if (null == client.get(url)) {
							article = new Article();
							article.setWebId(webId);
							article.setArticleUrl(url);
							article.setText("NED"); // NED_WALLCOO
							article.setIntro("");
							NodeList tmpNode2 = linkTag.getChildren();
							if(null != tmpNode2 && tmpNode2.size() > 0){
								ImageTag imgTag = (ImageTag)tmpNode2.elementAt(0);
								System.out.println(imgTag.getAttribute("alt")
										+ "|[" + url + "]");
								article.setActicleXmlUrl(URL+imgTag.getImageURL());
								article.setTitle(imgTag.getAttribute("alt")); // NT:No
							}
							int key = articleDao.insert(article);
							if (key > 0) {
								System.out.print(article.getTitle() + "\t|" + url);
								client.add(url, url);
							}
						} else {
							System.err.println(">> 已存在相同的内容 [" + linkTag.getLinkText()
									+ "]");
						}
					}
				}
			}

			if (null != p2){
				p2 = null;
			}
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
		boolean b = true;
		ResultBean result = hasPaging2(article.getArticleUrl());
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println(""+key);
				LinkBean link = result.getMap().get(key);
				b = getImage(link, article.getId());
			}
		}

		return b;
	}

	/**
	 * 获取分类下数据
	 * 
	 * @param link
	 * @param webId
	 * @throws Exception
	 */
	public static boolean getImage(LinkBean link, int artId) throws Exception {
		Parser p2 = new Parser();
		p2.setURL(link.getLink());
		int size = 0;
		boolean b = true;
		String length = "0";
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = p2.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("id", "listimg"));

		if(null != list && list.size() > 0){
		Parser p3 = new Parser();
		p3.setInputHTML(list.toHtml());

		NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
		NodeList list2 = p3.extractAllNodesThatMatch(filter2)
				.extractAllNodesThatMatch(new HasAttributeFilter("target", "_blank"));
			String imgSrc = null;
			if(null != list2 && list2.size() > 0){
				ImageBean imgBean = null;
				String url = null;
				System.out.println(">> 列表数量:"+list2.size());
				for(int i=0;i<list2.size();i++){
					LinkTag linkTag = (LinkTag)list2.elementAt(i);
					
					if (!linkTag.getLink().startsWith("http://")) {
						url = URL + linkTag.getLink();
					} else {
						url = linkTag.getLink();
					}
					
					imgSrc = getImageURL(url);
					System.out.println("imgSrc:"+imgSrc);
					if(null != imgSrc){
						if (null == client.get(imgSrc)) {
							imgBean = new ImageBean();
							imgBean.setArticleId(artId);
							imgBean.setHttpUrl(imgSrc);
							NodeList tmpNode2 = linkTag.getChildren();
							if(null != tmpNode2 && tmpNode2.size() > 0){
								if(tmpNode2.elementAt(0) instanceof ImageTag){
									ImageTag imgTag = (ImageTag)tmpNode2.elementAt(0);
									if (null != imgTag.getImageURL())
										imgBean.setImgUrl(URL_+imgTag.getImageURL());
									if (null != imgTag.getAttribute("alt"))
										imgBean
												.setTitle(imgTag
														.getAttribute("alt"));
									else
										imgBean.setTitle("NT:"
												+ CommonUtil.getDateTimeString());
								}
							}
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
							try{
								int key = imageDao.insert(imgBean);
								if (key > 0) {
									System.out.println(imgBean.getTitle() + "\t|"+ imgBean.getHttpUrl());
									client.add(imgBean.getHttpUrl(), imgBean.getHttpUrl());
								}
							}catch(Exception e){
								b = false;
								break;
							}
						} else {
							System.err.println(">> 已存在相同的内容 ["+ artId + "]");
						}
					}
				}
			}

			if(null != p3)
				p3 = null;
		}

		if(null != p2)
			p2 = null;

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

			NodeFilter filter = new NodeClassFilter(ImageTag.class);
			NodeList list = p1.extractAllNodesThatMatch(filter);

			if (null != list && list.size() == 1) {
				ImageTag link = (ImageTag) list.elementAt(0);
				result = link.getImageURL();
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
		parser.setEncoding("GB2312");
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
			p2.setEncoding("GB2312");
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
							if (cnl.elementAt(0) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(0);
								String url = ""
										+ getImageUrl(nl.getLink());
								if (null == client.get(url)) {
									length = HttpClientUtils
											.getHttpHeaderResponse(url,
													"Content-Length");
									imgBean = new ImageBean();
									imgBean.setArticleId(articleId);
									imgBean.setHttpUrl(url);
									imgBean.setImgUrl(it.getImageURL());
									imgBean.setTitle(it.getAttribute("alt"));
									try {
										size = Integer.parseInt(length);
										imgBean.setFileSize(Long.valueOf(size));
										imgBean.setStatus(3);
									} catch (Exception e) {
										e.printStackTrace();
										System.err
												.println(">> IMAGE SIZE ERROR");
										size = 0;
										imgBean.setFileSize(0l);
										imgBean.setStatus(1);
									}
									imgBean.setLink("NED");
									imgBean.setOrderId(i);
									imgBean.setArticleId(articleId);
									// HttpClientUtils
									int result = imageDao.insert(imgBean);
									if (result > 0) {
										System.out.println(">> add article["
												+ articleId + "] image id["
												+ result + "] to DB");
										imgBean.setId(result);
										client.add(url, url);
									} else {
										System.err.println(">> 未添加[" + url
												+ "]到数据库中");
									}
								} else {
									System.err.println(">> 缓存中已存在相同的内容 ["
											+ nl.getLink() + "]");
								}
							}
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
		List<WebsiteBean> webList = webSiteDao.findByParentId(1200);
		for (WebsiteBean bean : webList) {
				ResultBean result = hasPaging2(bean.getUrl());
				if (result.isBool()) {
					Iterator it = result.getMap().keySet().iterator();
					while (it.hasNext()) {
						String key = (String) it.next();
						if(HttpClientUtils.validationURL(key)){
							LinkBean link = result.getMap().get(key);
							try {
								secondURL(link, bean.getId());
							} catch (Exception e) {
								e.printStackTrace();
								System.err.println("**********************ERROR****************************");
								System.err.println("key:" + key);
								System.err.println("**********************ERROR-END************************");
								continue;
							}
						}else{
							continue;
						}
					}
			}
		}
	}

	public static void main(String[] args) {
		// init();
		try {
//			 catalog(URL);
//			 update();
//			loadImg();
			imgDownload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void loadImg() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(1200);
		for (WebsiteBean bean : webList) {
			List<Article> list = articleDao.findByWebId(bean.getId(),"0");
			for (Article art : list) {
				List<ImageBean> imgList = imageDao.findImage(art.getId());
				if(imgList.size() == 0){
					if (getImage(art)) {
						art.setText("FD");
						if (articleDao.update(art)) {
							System.out
									.println("更新记录[" + art.getTitle() + "]成功");
						}
					}
				}
			}
		}
	}

	static void imgDownload() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(1200);
		for(WebsiteBean website:webList){
			System.out.println(website.getId()+"|"+website.getName()+"|"+website.getUrl());
			List<Article> artList = articleDao.findByWebId(website.getId(), "FD");
			System.out.println("文章数量:"+artList.size());
			for(Article article:artList){
				List<ImageBean> list = imageDao.findImage(article.getId());
				System.out.println(">> 图片数量:["+article.getTitle()+"["+article.getId()+"]]"+list.size()+"\n**************************");
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
		String date = CommonUtil.getDate("");
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		s_fileName = s_fileName.replace(".", "_s.");
		try {
			bean = new PicfileBean();
			if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ date + File.separator
					+ imgBean.getArticleId() + File.separator
					+ fileName.replace(".", "_s."))) == null) {
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ date + File.separator
						+ imgBean.getArticleId() + File.separator
						+ fileName.replace(".", "_s."));
			}

			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ date + File.separator
					+ imgBean.getArticleId() + File.separator + fileName)) == null) {
				IOUtil.createPicFile(imgBean.getHttpUrl(), PIC_SAVE_PATH
						+ date + File.separator
						+ imgBean.getArticleId() + File.separator + fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			bean.setSmallName( date+ File.separator
					+ imgBean.getArticleId() + File.separator + s_fileName);
			bean.setName(date + File.separator
					+ imgBean.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				boolean b = picFiledao.insert(bean);
				if (b) {
					client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
							+ bean.getName()), bean);
					client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
							+ bean.getSmallName()), bean);
				} else {
					return false;
				}
			} catch (Exception e) {
				System.out.println("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			System.out.println("网络连接，文件IO异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
