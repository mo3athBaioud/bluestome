package com.zhuoku;

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
import org.htmlparser.filters.NotFilter;
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

public class ZHUOKUParser {

	private static final String PIC_SAVE_PATH = Constants.FILE_SERVER;//"d:\\share\\zhuoku\\";//"d:\\share\\zhuoku\\";

	static String URL_ = "http://www.zhuoku.com/";
	
	static String URL = "http://www.zhuoku.com";
	
	static String IMAGE_URL = "http://image6.tuku.cn/";
	
	static String ARTICLE_COM_URL = "";
	
	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	static List<Article> ARTICLELIST = new ArrayList<Article>();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();
	
	/**
	 * 获取第一级分类链接
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
						new HasAttributeFilter("id", "topnav"));

		if (null != list && list.size() > 0) {
			Div table = (Div) list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(table.getChildrenHTML());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
			if (linkList != null && linkList.size() > 0) {
				WebsiteBean tmp = null;
				for (int i = 0; i < linkList.size(); i++) {
					LinkTag link = (LinkTag) linkList.elementAt(i);
					System.out.println(link.getLinkText()+"|"+link.getLink());
					if (link.getLink().endsWith(".htm") && !link.getLink().equalsIgnoreCase("http://www.05sun.com")
							&&!link.getLink().equalsIgnoreCase("http://desk.zhuoku.com")
							&&!link.getLink().equalsIgnoreCase("http://www.letget.com")
							&&!link.getLink().equalsIgnoreCase("http://www.wbtheme.com")) { // 
						tmp = new WebsiteBean();
						tmp.setName(link.getLinkText());
						if (!link.getLink().startsWith("http://")) {
							System.out.println(URL + link.getLink() + "\n");
							tmp.setUrl(URL + link.getLink());
						} else {
							System.out.println(link.getLink() + "\n");
							tmp.setUrl(link.getLink());
						}
						tmp.setParentId(900);
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
	 * 获取第一级分类链接
	 * 
	 * @param url
	 * @throws Exception
	 */
	static void catalogLevel2(String url,int webid) throws Exception { // WebsiteBean bean
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");
		NodeFilter fileter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(fileter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("id", "liebiao"));

		if (null != list && list.size() > 0) {
			Div table = (Div) list.elementAt(0);
			Parser p2 = new Parser();
			p2.setInputHTML(table.getChildrenHTML());
			NodeFilter linkFilter = new NodeClassFilter(LinkTag.class);
			NodeList linkList = p2.extractAllNodesThatMatch(linkFilter);
				if (linkList != null && linkList.size() > 0) {
					WebsiteBean tmp = null;
					for (int i = 0; i < linkList.size(); i++) {
						LinkTag link = (LinkTag) linkList.elementAt(i);
						if (link.getLink().startsWith("http://") && link.getLink().endsWith(".htm")) { // 
							tmp = new WebsiteBean();
							tmp.setName(link.getLinkText());
							if (!link.getLink().startsWith("http://")) {
								System.out.println(link.getLinkText()+"|"+URL + link.getLink() + "\n");
								tmp.setUrl(URL + link.getLink());
							} else {
								System.out.println(link.getLinkText()+"|"+link.getLink() + "\n");
								tmp.setUrl(link.getLink());
							}
							tmp.setParentId(webid);
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
	static ResultBean hasPaging2(String url) throws Exception {
		ResultBean result = new ResultBean();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("GB2312");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "turn"));
		if (list != null && list.size() > 0) {
			Parser p2 = new Parser();
			p2.setInputHTML(list.toHtml());
			
			NodeFilter filter2 = new NodeClassFilter(SelectTag.class);
			NodeList  list2 = p2.extractAllNodesThatMatch(filter2);
			if(null != list2 && list2.size() > 0){
				SelectTag select = (SelectTag)list2.elementAt(0);
				if(select != null){
					NodeList options = select.getChildren();
					if(options != null && options.size() > 0 ){
						LinkBean l1 = null;
						int start = url.lastIndexOf("/")+1;
						String url_ = url.substring(0,start);
						for(int j=0;j<options.size();j++){
							l1 = new LinkBean();
							OptionTag option = (OptionTag)options.elementAt(j);
							l1.setLink(url_+option.getValue());
							result.getMap().put(l1.getLink(), l1);
						}
					}
				}
				result.setBool(true);
			}
			if(null != p2)
				p2 = null;
		}else{
			LinkBean l1 = new LinkBean();
			l1.setLink(url);
			result.getMap().put(url, l1);
			result.setBool(true);
		}
		if (null != parser)
			parser = null;
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
		parser.setEncoding("UTF-8");

		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "bizhiin"));
		if (list != null && list.size() > 0) {
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
					LinkTag ltmp = (LinkTag) list2.elementAt(0);
					if (!ltmp.getLink().startsWith("http://")) {
						url = URL + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}

					if (null == client.get(url)) {
						article = new Article();
						article.setWebId(webId);
						article.setArticleUrl(url);
						article.setText("NED"); // NED_WALLCOO
						article.setIntro("");
						NodeList tmp = ltmp.getChildren();
						if (tmp != null && tmp.size() > 0) {
							ImageTag imgTag = (ImageTag) tmp.elementAt(0);
							article.setActicleXmlUrl(imgTag.getImageURL());
							article.setTitle(imgTag.getAttribute("alt"));
						}
//						System.out.println("*****************Start***************");
//						System.out.println("ArticleUrl:"+article.getArticleUrl());
//						System.out.println("ActicleXmlUrl:"+article.getActicleXmlUrl());
//						System.out.println("Title:"+article.getTitle());
//						System.out.println("Text:"+article.getText());
//						System.out.println("*****************End***************\n");
						int key = articleDao.insert(article);
						if (key > 0) {
							System.out.print(ltmp.getLinkText() + "\t|" + url);
							client.add(url, url);
							getImage(article);
						}
					} else {
						System.err.println(">> 已存在相同的内容 [" + ltmp.getLinkText()
								+ "]");
					}
				}

				if (null != p2)
					p2 = null;
			}
		}
		if (null != parser)
			parser = null;
	}

	static String getRealArticleURL(String url1) throws Exception {
		String url = null;

		Parser p1 = new Parser();
		p1.setURL(url1);

		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "abot"));

		if (null != list && list.size() > 0) {
			LinkTag link = (LinkTag) list.elementAt(0);
			System.out.println(link.getLinkText() + "|" + link.getLink());
			if (!link.getLink().startsWith("http://")) {
				url = URL + link.getLink();
			} else {
				url = link.getLink();
			}
		}

		if (null != p1)
			p1 = null;
		return url;

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

		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		boolean resultB = true;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "bizhiin"));
		if (null != list && list.size() > 0) {
			Parser p2 = null;
			String length = "0";
			int size = 0;
			for (int i = 0; i < list.size(); i++) {
				Div div = (Div) list.elementAt(i);
				p2 = new Parser();
				p2.setInputHTML(div.toHtml());

				NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(filter2);

				ImageBean imgBean = null;
				String url = null;
				String imgSrc = null;
				if (null != list2 && list2.size() > 0) {
					LinkTag ltmp = (LinkTag) list2.elementAt(0);
					int start = link.getLink().lastIndexOf("/")+1;
					String url_ = link.getLink().substring(0,start);
					if (!ltmp.getLink().startsWith("http://")) {
						url = url_ + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}
					
					imgSrc = getImageURL(url);
					if (null != imgSrc) {
						if (null == client.get(imgSrc)) {
							imgBean = new ImageBean();
							imgBean = new ImageBean();
							imgBean.setArticleId(artId);
							imgBean.setHttpUrl(imgSrc);
							NodeList tmp = ltmp.getChildren();
							if (tmp != null && tmp.size() > 0) {
								ImageTag imgTag = (ImageTag) tmp.elementAt(0);
								if (null != imgTag.getImageURL())
									imgBean.setImgUrl(imgTag.getImageURL());
								if (null != imgTag.getAttribute("alt"))
									imgBean
											.setTitle(imgTag.getAttribute("alt"));
								else
									imgBean.setTitle(ltmp.getAttribute("title"));
							}
							imgBean.setCommentshowurl(url);
							imgBean.setLink("NED");
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
//							System.out.println("Title:"+imgBean.getTitle());
//							System.out.println("ArticleId:"+imgBean.getArticleId());
//							System.out.println("大图地址:"+imgBean.getHttpUrl());
//							System.out.println("小图地址:"+imgBean.getImgUrl());
							int key = imageDao.insert(imgBean);
							if (key > 0) {
								System.out.println("添加图片记录:["+imgBean.getTitle() + "]\t|" + url+"\n");
								client.add(imgSrc, imgSrc);
							}else{
								ImageBean tmpImg = imageDao.findByHttpUrl(imgBean.getHttpUrl());
								if(null != tmpImg){
									tmpImg.setCommentshowurl(url);
									if(!imageDao.update(tmpImg)){
										System.out.println("更新图片记录:["+tmpImg.getArticleId()+"|"+tmpImg.getTitle() + "]\t|" + url+"\t成功");
										client.add(imgSrc, imgSrc);
									}
								}
							}
						} else {
							ImageBean tmpImg = imageDao.findByHttpUrl(imgBean.getHttpUrl());
							if(null != tmpImg){
								tmpImg.setCommentshowurl(url);
								if(!imageDao.update(tmpImg)){
									System.out.println("更新图片记录:["+tmpImg.getArticleId()+"|"+tmpImg.getTitle() + "]\t|" + url+"\t成功");
									client.add(imgSrc, imgSrc);
								}
							}
						}
					} else {
						resultB = false;
						System.err.println(">> 出现异常，文章ID["+imgBean.getArticleId()+"|"+imgBean.getArticleId()+"]\t返回False");
						break;
					}
				}

				if (null != p2)
					p2 = null;
			}
		}
		if(null != parser)
			parser = null;
		
		return resultB;
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
			NodeList list = p1.extractAllNodesThatMatch(filter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "imageview"));

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
							if (cnl.elementAt(0) instanceof ImageTag) {
								ImageTag it = (ImageTag) cnl.elementAt(0);
								String url = IMAGE_URL
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
		List<WebsiteBean> webList = webSiteDao.findByParentId(900);
		for (WebsiteBean bean : webList) {
			List<WebsiteBean> subList = webSiteDao.findByParentId(bean.getId());
			for(WebsiteBean website:subList){
				ResultBean result = hasPaging2(website.getUrl());
				if (result.isBool()) {
					Iterator it = result.getMap().keySet().iterator();
					while (it.hasNext()) {
						String key = (String) it.next();
						LinkBean link = result.getMap().get(key);
						try {
							secondURL(link, website.getId());
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println("key:" + key);
							continue;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// init();
		try {
			 update();
			 loadImg();
			 imgDownload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取二级菜单地址 基本上是第一次使用而已
	 * @throws Exception
	 */
	static void loadlevel2() throws Exception {
		// WebsiteBean bean = webSiteDao.findById(702);
		List<WebsiteBean> webList = webSiteDao.findByParentId(900);
		for (WebsiteBean bean : webList) {
			System.out.println(bean.getName()+"|"+bean.getUrl());
			catalogLevel2(bean.getUrl(),bean.getId());
		}
	}

	/***
	 * 获取文章下的图片
	 * @throws Exception
	 */
	static void loadImg() throws Exception {
		
		List<WebsiteBean> webList = webSiteDao.findByParentId(900);
		for (WebsiteBean bean : webList) {
			
			List<WebsiteBean> subList = webSiteDao.findByParentId(bean.getId());
			for(WebsiteBean website:subList){
				List<Article> list = articleDao.findByWebId(website.getId(),"NED");
				System.out.println("文章列表:"+list.size());
				for (Article art : list) {
//					List<ImageBean> imgList = imageDao.findImage(art.getId());
//					if(imgList.size() == 0){
						if (getImage(art)) {
							art.setText("FD");
							if (articleDao.update(art)) {
								System.out
										.println("更新记录[" + art.getId()+"|"+art.getTitle() + "]成功");
							}
						}
//					}
//					else{
//						art.setText("NED");
//						if (articleDao.update(art)) {
//							System.out
//									.println("更新记录[" + art.getTitle() + "]成功");
//						}
//						
//					}
				}
			}
		}
	}


	static void imgDownload() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(900);
		for (WebsiteBean bean : webList) {
			List<WebsiteBean> subList = webSiteDao.findByParentId(bean.getId());
			for(WebsiteBean website:subList){
				List<Article> list = articleDao.findByWebId(website.getId(),"FD");
				System.out.println(">> 网站["+bean.getId()+"|"+bean.getName()+"|"+bean.getUrl()+"]\t下文章数量"+list.size());
				for (Article art : list) {
					List<ImageBean> imgList = imageDao.findImage(art.getId());
					System.out.println(">> 文章["+art.getId()+"|"+art.getTitle()+"]\t下的图片数量"+imgList.size());
//					ARTICLE_COM_URL = art.getArticleUrl();
					for (ImageBean img : imgList) {
						if(img.getStatus() != -1){
							if (download(img,art.getArticleUrl())) {
								img.setStatus(-1);
								img.setLink("FD");
								if (imageDao.update(img)) {
									System.out.println(">> 更新图片对象["+art.getTitle()+"|" + img.getId() + "]成功!");
								}
							}
						}
					}
//					ARTICLE_COM_URL = null;
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
	public static boolean getDownloadImage(LinkBean link, int artId) throws Exception {

		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		boolean resultB = true;
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(CompositeTag.class);
		NodeList list = parser
				.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(new HasAttributeFilter("class", "bizhiin"));
		if (null != list && list.size() > 0) {
			Parser p2 = null;
			String length = "0";
			int size = 0;
			for (int i = 0; i < list.size(); i++) {
				Div div = (Div) list.elementAt(i);
				p2 = new Parser();
				p2.setInputHTML(div.toHtml());

				NodeFilter filter2 = new NodeClassFilter(LinkTag.class);
				NodeList list2 = p2.extractAllNodesThatMatch(filter2);

				ImageBean imgBean = null;
				String url = null;
				String imgSrc = null;
				if (null != list2 && list2.size() > 0) {
					LinkTag ltmp = (LinkTag) list2.elementAt(0);
					int start = link.getLink().lastIndexOf("/")+1;
					String url_ = link.getLink().substring(0,start);
					if (!ltmp.getLink().startsWith("http://")) {
						url = url_ + ltmp.getLink();
					} else {
						url = ltmp.getLink();
					}
					
					imgSrc = getImageURL(url);
					if (null != imgSrc) {
						
					} else {
						resultB = false;
						System.err.println(">> 出现异常，文章ID["+imgBean.getArticleId()+"]\t返回False");
						break;
					}
				}

				if (null != p2)
					p2 = null;
			}
		}
		if(null != parser)
			parser = null;
		
		return resultB;
	}
	
	static boolean download(ImageBean imgBean,String url) {
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
			bean.setSmallName(File.separator
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ s_fileName);
			bean.setName(File.separator
					+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator
					+ fileName);
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
			return false;
		}
		return true;
	}

	/**
	 * 测试方法
	 * @throws Exception
	 */
	static void test() throws Exception{
		ResultBean result = hasPaging2("http://desk.tpzj.com/html/260/index.html");
		if (result.isBool()) {
			Iterator it = result.getMap().keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println("key:" + key);
				LinkBean link = (LinkBean) result.getMap().get(key);
				secondURL(link, 0);
			}
		}
		
	}
	
}