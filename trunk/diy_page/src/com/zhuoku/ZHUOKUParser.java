package com.zhuoku;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.Article;
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
import com.zol.zoldesk.ZOLDESKParser;

public class ZHUOKUParser {

	static Log log = LogFactory.getLog(ZHUOKUParser.class);
	
	private static final String PIC_SAVE_PATH = Constants.FILE_SERVER;//"d:\\share\\zhuoku\\";//"d:\\share\\zhuoku\\";

	static String URL_ = "http://www.zhuoku.com/";
	
	static String URL = "http://www.zhuoku.com";
	
	static String IMAGE_URL = "http://image6.tuku.cn/";
	
	static String ARTICLE_COM_URL = "";
	
	static int D_PARENT_ID = 900;
	
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
					log.debug(link.getLinkText()+"|"+link.getLink());
					if (link.getLink().endsWith(".htm") && !link.getLink().equalsIgnoreCase("http://www.05sun.com")
							&&!link.getLink().equalsIgnoreCase("http://desk.zhuoku.com")
							&&!link.getLink().equalsIgnoreCase("http://www.letget.com")
							&&!link.getLink().equalsIgnoreCase("http://www.wbtheme.com")) { // 
						tmp = new WebsiteBean();
						tmp.setName(link.getLinkText());
						if (!link.getLink().startsWith("http://")) {
							log.debug(URL + link.getLink() + "\n");
							tmp.setUrl(URL + link.getLink());
						} else {
							log.debug(link.getLink() + "\n");
							tmp.setUrl(link.getLink());
						}
						tmp.setParentId(900);
						boolean b = webSiteDao.insert(tmp);
						if (b) {
							client.add(tmp.getUrl(), tmp.getUrl());
							log.debug("成功");
						} else {
							log.debug("失败");
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
								log.debug(link.getLinkText()+"|"+URL + link.getLink() + "\n");
								tmp.setUrl(URL + link.getLink());
							} else {
								log.debug(link.getLinkText()+"|"+link.getLink() + "\n");
								tmp.setUrl(link.getLink());
							}
							tmp.setParentId(webid);
							boolean b = webSiteDao.insert(tmp);
							if (b) {
								client.add(tmp.getUrl(), tmp.getUrl());
								log.debug("成功");
							} else {
								log.debug("失败");
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
					log.debug("单条耗时:" + (end1 - start1) + "长度："
							+ content.getBytes().length);
				}
			} catch (Exception e) {
				log.debug("Exception:" + e.getMessage());
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
							if(imgTag.getImageURL().startsWith("http://")){
								article.setActicleXmlUrl(imgTag.getImageURL());
							}else{
								article.setActicleXmlUrl(URL+imgTag.getImageURL());
							}
							article.setTitle(imgTag.getAttribute("alt") == null ? "NT" : imgTag.getAttribute("alt"));
						}
//						log.debug("*****************Start***************");
//						log.debug("ArticleUrl:"+article.getArticleUrl());
//						log.debug("ActicleXmlUrl:"+article.getActicleXmlUrl());
//						log.debug("Title:"+article.getTitle());
//						log.debug("Text:"+article.getText());
//						log.debug("*****************End***************\n");
						int key = articleDao.insert(article);
						if (key > 0) {
							log.info(" >> 文章:"+article.toString());
							client.add(url, url);
							getImage(article);
						}
					} else {
						log.error(">> 已存在相同的内容 [" + ltmp.getLinkText()
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
			log.debug(link.getLinkText() + "|" + link.getLink());
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
				if(null != link){
					try{
						b = getImage(link, article.getId());
					}catch(Exception e){
						log.error(e);
						continue;
					}
				}else{
					log.info(" >> key:"+key);
				}
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

		boolean resultB = true;
		Parser parser = new Parser();
		parser.setURL(link.getLink());
		parser.setEncoding("UTF-8");
		// 获取指定ID的TableTag内容
		NodeFilter filter = new NodeClassFilter(Div.class);
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
						imgBean = new ImageBean();
						imgBean.setArticleId(artId);
						imgBean.setHttpUrl(imgSrc);
						if (null == client.get(imgSrc)) {
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
								log.error(">> IMAGE SIZE ERROR");
								size = 0;
								imgBean.setFileSize(0l);
								imgBean.setStatus(1);
							}
//							log.debug("Title:"+imgBean.getTitle());
//							log.debug("ArticleId:"+imgBean.getArticleId());
//							log.debug("大图地址:"+imgBean.getHttpUrl());
//							log.debug("小图地址:"+imgBean.getImgUrl());
							int key = imageDao.insert(imgBean);
							if (key > 0) {
								log.info("添加图片记录:["+imgBean.getTitle() + "]\t|" + url+"\n");
								client.add(imgSrc, imgSrc);
							}else{
								ImageBean tmpImg = imageDao.findByHttpUrl(imgBean.getHttpUrl());
								if(null != tmpImg){
									tmpImg.setCommentshowurl(url);
									if(imageDao.update(tmpImg)){
										log.info("更新图片记录:["+tmpImg.getArticleId()+"|"+tmpImg.getTitle() + "]\t|" + url+"\t成功");
										client.add(imgSrc, imgSrc);
									}
								}
							}
						} else {
							ImageBean tmpImg = imageDao.findByHttpUrl(imgBean.getHttpUrl());
							if(null != tmpImg){
								tmpImg.setCommentshowurl(url);
								if(imageDao.update(tmpImg)){
									log.info("更新图片记录:["+tmpImg.getArticleId()+"|"+tmpImg.getTitle() + "]\t|" + url+"\t成功");
									client.add(imgSrc, imgSrc);
								}
							}
						}
					} else {
						resultB = false;
						log.error(">> 出现异常，文章ID["+imgBean.getArticleId()+"|"+imgBean.getArticleId()+"]\t返回False");
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
										log.debug(">> add article["
												+ articleId + "] image id["
												+ result + "] to DB");
										imgBean.setId(result);
										client.add(url, url);
									} else {
										log.error(">> 未添加[" + url
												+ "]到数据库中");
									}
								} else {
									log.error(">> 缓存中已存在相同的内容 ["
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
		List<WebsiteBean> webList = webSiteDao.findByParentId(D_PARENT_ID);
		boolean b = false;
		for (WebsiteBean bean : webList) {
			String lastModify = HttpClientUtils.getLastModifiedByUrl(bean.getUrl());
//			if(null != bean.getLastModifyTime() && !"".equals(bean.getLastModifyTime()) && bean.getLastModifyTime().equals(lastModify)){
//				log.debug(" >> 不需要更新:"+lastModify);
//				continue;
//			}
			List<WebsiteBean> subList = webSiteDao.findByParentId(bean.getId());
			for(WebsiteBean website:subList){
				String slastModify = HttpClientUtils.getLastModifiedByUrl(website.getUrl());
//				if(null != website.getLastModifyTime() && !"".equals(website.getLastModifyTime()) && website.getLastModifyTime().equals(slastModify)){
//					continue;
//				}
				
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
							log.error("key:" + key);
							continue;
						}
					}
					
					if(slastModify != null && !"".equals(slastModify) ){
						if(null == website.getLastModifyTime() || "".equals(website.getLastModifyTime()) || !website.getLastModifyTime().equals(slastModify)){
							website.setLastModifyTime(slastModify);
							if(webSiteDao.update(website)){
								log.info(" >> 更新网站["+bean.getName()+"|"+bean.getUrl()+"]最后时间["+lastModify+"]成功!");
							}
						}
					}
				}
			}
			
		}
	}

	public static void main(String[] args) {
		// init();
		try {
//			long start = System.currentTimeMillis();
//			update();
//			long end = System.currentTimeMillis();
//			log.debug(" >> 耗时:"+(end-start)/1000+1);
//			 new Thread(new Runnable(){
//				private boolean isRun = true;
//				public void run() {
//					while(isRun){
//						try{
//							test();
//							index();
							update();
							loadImg();
//							imgDownload();
//							Thread.sleep(86400000);
//						}catch(Exception e){
//							e.printStackTrace();
//							isRun = false;
//						}
//					}
//				}
//			 }).start();
			/**
			 **/
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				log.debug(link.getLinkText() + "\t" + link.getLink());
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
	
	private static void index(){
		try {
			getLink(URL);
			Iterator it = LINKHASH.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if(key.equals("http://www.zhuoku.com/") || key.equals("http://www.zhuoku.com/#")){
					continue;
				}
				Map<String,WebsiteBean> tmps = webSiteDao.findUrlList(key);
				tmps.remove("http://www.zhuoku.com");
				String[] keys = toStringArray(tmps);
				sort(keys);
				
//				if(keys.length > 0){
//					System.out.println("key:"+key+"|keys[0]:"+keys[0]);
//				}
				for(String tk:keys)
				{
					System.out.println(" > index.tk:\t"+tk);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符长度从大到小排序
	 * @param s1
	 */
	static void sort(String[] s1){
		String tmp;
		for(int i=0;i<s1.length;i++){
			for(int j=0;j<s1.length-i-1;j++){
				if(s1[j].length() < s1[j+1].length() ){
					tmp = s1[j];
					s1[j] = s1[j+1];
					s1[j+1] = tmp;
				}
			}
		}
	}
	
	/**
	 * 将Map中的键取出放入数组中
	 * @param map
	 * @return
	 */
	static String[] toStringArray(Map map){
		String[] keys = new String[map.size()];
		Iterator it = map.keySet().iterator();
		int i=0;
		while(it.hasNext()){
			String key = (String)it.next();
			keys[i] = key;
			i++;
		}
		return keys;
	}
	
	/**
	 * 获取二级菜单地址 基本上是第一次使用而已
	 * @throws Exception
	 */
	static void loadlevel2() throws Exception {
		// WebsiteBean bean = webSiteDao.findById(702);
		List<WebsiteBean> webList = webSiteDao.findByParentId(900);
		for (WebsiteBean bean : webList) {
			log.debug(bean.getName()+"|"+bean.getUrl());
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
				log.debug("文章列表:"+list.size());
				for (Article art : list) {
					log.debug(" :"+art.getTitle()+"|"+art.getText());
//					List<ImageBean> imgList = imageDao.findImage(art.getId());
//					if(imgList.size() == 0){
						if (getImage(art)) {
							art.setText("FD");
							if (articleDao.update(art)) {
								log.info("更新记录[" + art.getId()+"|"+art.getTitle() + "]成功");
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
				log.debug(">> 网站["+bean.getId()+"|"+bean.getName()+"|"+bean.getUrl()+"]\t下文章数量"+list.size());
				for (Article art : list) {
					List<ImageBean> imgList = imageDao.findImage(art.getId());
					log.debug(">> 文章["+art.getId()+"|"+art.getTitle()+"]\t下的图片数量"+imgList.size());
//					ARTICLE_COM_URL = art.getArticleUrl();
					for (ImageBean img : imgList) {
						if(img.getStatus() != -1 || img.getLink().equals("NED")){
							String url = art.getArticleUrl().replace(URLEncoder.encode("[", "utf-8"), "[").replace(URLEncoder.encode(" ", "utf-8"), " ")
															.replace(URLEncoder.encode("]", "utf-8"), "]");
							if (download(img,url)) {
								img.setStatus(-1);
								img.setLink("FD");
								if (imageDao.update(img)) {
									log.info(">> 更新图片对象["+art.getTitle()+"|" + img.getId() + "]成功!");
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
						log.error(">> 出现异常，文章ID["+imgBean.getArticleId()+"]\t返回False");
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
			String imgUrl = imgBean.getHttpUrl().replace("[", "%5B").replace(" ","%20").replace("]","%5D");
			log.info(" > URLEncoder.encode(\"[\", \"utf-8\"):"+URLEncoder.encode("[", "utf-8"));
			log.info(" > URLEncoder.encode(\"]\", \"utf-8\"):"+URLEncoder.encode("]", "utf-8"));
			log.info(" > IMGURL:"+imgUrl);
			big = HttpClientUtils.getResponseBodyAsByte(imgBean.getCommentshowurl(), null, imgUrl);
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
				}else{
					return false;
				}
			} catch (Exception e) {
				log.error("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			log.error("网络连接，文件IO异常");
			return false;
		}
		return true;
	}

	/**
	 * 测试方法
	 * @throws Exception
	 */
	static void test() throws Exception{
		List<WebsiteBean> list = webSiteDao.findByParentId(D_PARENT_ID);
		if(null != list && list.size() > 0){
			for(WebsiteBean web:list){
				System.out.println("--"+web.getName()+"|"+web.getUrl());
				List<WebsiteBean> slist = webSiteDao.findByParentId(web.getId());
				if(null != slist && slist.size() > 0){
					for(WebsiteBean ws:slist){
//						String url = ws.getUrl().replace("index-1.htm", "");
						String url = ws.getUrl()+"index-1.htm";
						ws.setUrl(url);
						System.out.println("\t--|"+web.getId()+"|"+ws.getName()+"|"+ws.getUrl());
//						if(webSiteDao.update(ws)){
//							System.out.println("修改成功!");
//						}
					}
				}
			}
		}
	}
	
}
