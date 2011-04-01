package com.chinamilitary.htmlparser;

import java.io.File;
import java.io.IOException;
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
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.PicfileBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.CommonUtil;
import com.chinamilitary.util.IOUtil;
import com.chinamilitary.util.StringUtils;
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.xmlparser.XMLParser;
import com.common.Constants;
import com.utils.FileUtils;

public class ChinaTUKUParser {

	static final String URL_ = "http://tuku.china.com/";

	// final static String FILE_SERVER = "O:\\fileserver\\image\\";

	final static String FILE_SERVER = "D:\\fileserver\\image\\";

	static int D_PARENT_ID = 300;

	// "http://tuku.tech.china.com/tech",
	static final String[] CATALOG_URL = { "http://pic.news.china.com/news/",
			"http://tuku.kaiyun.china.com/kaiyun/",
			"http://tuku.ent.china.com/fun/",
			"http://tuku.military.china.com/military/",
			"http://tuku.game.china.com/game/",
			"http://tuku.fun.china.com/fun/",
			"http://pic.news.china.com/social/",
			"http://pic.sports.china.com/sports/",
			"http://tuku.travel.china.com/travel/",
			"http://tuku.auto.china.com/auto/",
			"http://tuku.culture.china.com/culture/" };

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static final String PIC_SAVE_PATH = Constants.FILE_SERVER;

	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	// http://rtuku.club.china.com/user/channelPageTypeIndexAjaxAction.do?channelId=baobao&pageNum=1&eachRowNum=4&eachPageNum=40&typeId=23670&tagId=0
	static String CATALOGS_URL = "http://rtuku.club.china.com/user/channelPageTypeIndexAjaxAction.do?channelId=baobao&pageNum=1&eachRowNum=4&eachPageNum=10000&typeId={id}&tagId=0";

	/**
	 * 验证地址是否为可以请求的地址
	 * 
	 * @param url
	 * @return
	 */
	static boolean validationURL(String url) {
		boolean b = false;
		for (String tmp : CATALOG_URL) {
			if (url.startsWith(tmp + "html/")) {
				b = true;
				break;
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
	 * 根据内容获取内容中的链接
	 * 
	 * @param content
	 * @throws Exception
	 */
	static void getLinkByContent(String content) throws Exception {
		Parser p1 = new Parser();
		p1.setInputHTML(content);
		p1.setEncoding("UTF-8");

		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		LinkBean bean = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				bean = new LinkBean();
				bean.setLink(link.getLink().replaceAll("\"","").replaceAll("\\\\","").replace("//html", "/html"));
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
//				System.out.println(" >> link:"+bean.getLink()+"|"+bean.getName());
				LINKHASH.put(bean.getLink(), bean);
			}
		}
		p1 = null;
	}

	static void getImage(Integer id, String url) throws Exception {
		Article article = null;
		boolean isDownload = false;
		String aKey = CacheUtils.getArticleKey(id);
		try {
			if (null != client.get(aKey)) {
				System.out.println(">> 从缓存中获取文章对象[" + id + "] ");
				article = (Article) client.get(aKey);
			}
			if (null == article) {
				System.out.println(">> 从缓存中未获取文章对象，从数据库中获取文章对象[" + id + "] ");
				article = articleDao.findById(id);
				if (null != article)
					client.add(aKey, article);
			}
			if (article != null) {
				// if (!HttpClientUtils.validationURL(article.getArticleUrl()))
				// {
				// System.err.println(">> getImage.地址不通["
				// + article.getArticleUrl() + "]");
				// return;
				// }
				// http://tuku.kaiyun.china.com/kaiyun/
				String tmpUrl = url.substring(0, url.lastIndexOf("/") + 1);
				if ((article.getArticleUrl().startsWith(tmpUrl))) {
					if (article.getActicleXmlUrl() != null
							|| !article.getActicleXmlUrl().equalsIgnoreCase("")) {
						List<ImageBean> imgList = XMLParser
								.readXmlFromURL(article.getActicleXmlUrl());
						for (ImageBean bean : imgList) {
							// if (!HttpClientUtils
							// .validationURL(bean.getImgUrl())
							// || !HttpClientUtils.validationURL(bean
							// .getHttpUrl())) {
							// System.err.println(">> 地址不通["
							// + bean.getHttpUrl() + "]");
							// continue;
							// }
							String length = HttpClientUtils
									.getHttpHeaderResponse(bean.getHttpUrl(),
											"Content-Length");
							System.out.println(">> Content-Length:" + length);
							if (null != length) {
								bean.setFileSize(Long.valueOf(length));
								bean.setStatus(1);
							}
							if (client.get(bean.getHttpUrl()) == null) {
								bean.setArticleId(article.getId());
								// HttpClientUtils
								int result = imageDao.insert(bean);
								if (result == -1) {
									System.out.println("图片标题为："
											+ bean.getTitle() + ",地址:["
											+ bean.getHttpUrl() + "],未添加到数据库");
								} else {
									bean.setId(result);
									client.add(bean.getHttpUrl(), bean
											.getHttpUrl());
									client.add(CacheUtils.getImageKey(result),
											bean);
								}
								if (imgDownload(bean, article.getWebId())) {
									isDownload = true;
									bean.setLink("FD");
									if (imageDao.update(bean)) {
										System.out.println(">> 保存图片["
												+ bean.getHttpUrl() + "]成功");
									}
								} else {
									continue;
								}
							}
						}
						if (isDownload) {
							article.setText("FD");
							articleDao.update(article);
						}
					}
				}
			} else {
				System.out.println("未查询到ARTICLE记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	static boolean imgDownload(ImageBean imgBean, Integer webId) {
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
			big = HttpClientUtils.getResponseBodyAsByte(imgBean
					.getCommentshowurl(), null, imgBean.getHttpUrl());
			if (null == big)
				return false;
			length = String.valueOf(big.length);
			if (length.equalsIgnoreCase("20261")
					|| length.equalsIgnoreCase("3267")
					|| length.equalsIgnoreCase("4096")) {
				System.out.println(">>> 尝试使用Referer来获取图片");
				big = HttpClientUtils.getResponseBodyAsByte(imgBean
						.getReferer(), null, imgBean.getHttpUrl());
				length = String.valueOf(big.length);
				if (length.equalsIgnoreCase("20261")
						|| length.equalsIgnoreCase("3267")
						|| length.equalsIgnoreCase("4096")) {
					System.err.println("下载被屏蔽，未突破盗链系统...");
					return false;
				}
			}
			// 小图
			if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ StringUtils
							.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + s_fileName)) == null) {
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean
								.getArticleId())) + imgBean.getArticleId()
						+ File.separator + s_fileName);
			}

			// 大图
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ StringUtils
							.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + fileName)) == null) {
				IOUtil.createFile(big, PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean
								.getArticleId())) + imgBean.getArticleId()
						+ File.separator + fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			bean.setSmallName(File.separator
					+ StringUtils
							.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + s_fileName);
			bean.setName(File.separator
					+ StringUtils
							.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				if (null != bean.getImageId()) {
					boolean b = picFiledao.insert(bean);
					if (b) {
						client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
								+ bean.getName()), bean);
						client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
								+ bean.getSmallName()), bean);
					} else {
						return false;
					}
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

	public static void main(String args[]) {
		try {
			// patch();
			// index();
			update2();
			// processC();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void index() {
		try {
			getLink("http://www.china.com");
			Iterator it = LINKHASH.keySet().iterator();
			Article acticle = null;
			while (it.hasNext()) {
				String key = (String) it.next();
				WebsiteBean tmp = webSiteDao.findByUrl(key);
				if (null != tmp) {
					LinkBean bean = LINKHASH.get(key);
					if (!bean.getLink().startsWith(tmp.getUrl())) {
						System.out.println("[" + bean.getLink() + "]不是以"
								+ tmp.getUrl() + "开始的!");
						continue;
					}
					System.out.println("title:" + bean.getTitle() + "\tname:"
							+ bean.getName() + "\tlink:" + bean.getLink());
					System.out.println(" >> :" + tmp.getId() + "\t"
							+ tmp.getName());

					try {
						acticle = new Article();
						acticle.setArticleUrl(bean.getLink());
						acticle.setTitle(bean.getName());
						acticle.setWebId(tmp.getId());
						acticle.setText("NED");
						if (bean.getLink().startsWith(tmp.getUrl())) {
							if (bean.getLink().indexOf("_") == -1) {
								if (tmp.getId() == 1633) {
									String xmlUrl = bean.getLink().replace(
											".htm", ".xml");
									if (xmlUrl != null) {
										acticle.setActicleXmlUrl(xmlUrl);
										acticle.setActicleRealUrl(bean
												.getLink());
									} else {
										acticle.setActicleXmlUrl(null);
										acticle.setActicleRealUrl(null);
									}
								} else {
									// 老版本图片播放器解析方式
									String realUrl = HTMLParser.getRealUrl(bean
											.getLink());
									String xmlUrl = HTMLParser
											.getXmlUrl(realUrl);
									if (xmlUrl != null) {
										acticle.setActicleXmlUrl(xmlUrl);
										acticle.setActicleRealUrl(realUrl);
									} else {
										acticle.setActicleXmlUrl(null);
										acticle.setActicleRealUrl(null);
									}
								}
							} else {
								// 新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，
								String xmlUrl = HTMLParser.getXmlUrl2(bean
										.getLink());
								if (xmlUrl != null) {
									acticle.setActicleXmlUrl(xmlUrl);
									acticle.setActicleRealUrl("2");
								} else {
									acticle.setActicleXmlUrl(null);
									acticle.setActicleRealUrl(null);
								}
							}
						} else {
							acticle.setActicleXmlUrl(null);
							acticle.setActicleRealUrl(null);
						}
						int result = articleDao.insert(acticle);
						if (result > 0) {
							client.add(bean.getLink(), bean.getLink());
							acticle.setId(result);
							String aKey = CacheUtils.getArticleKey(result);
							if (null == client.get(aKey)) {
								client.add(aKey, acticle);
							}
							getImage(result, tmp.getUrl());
						}
					} catch (Exception e) {
						System.err.println(" >> " + e.getMessage());
						continue;
					}
					System.out.println();
				}
				/**
				 **/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void processC() throws Exception {
		List<Article> list = articleDao.findByWebId(301);
		System.out.println(" >> list.size():" + list.size());
		for (Article article : list) {
			// System.out.println(" >>
			// title["+article.getTitle()+"]\t"+article.getArticleUrl()+"\t"+article.getText());
			if (article.getArticleUrl().startsWith(
					"http://tuku.news.china.com/history")) {
				article.setWebId(4);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.military.china.com/military")) {
				article.setWebId(5);
			}
			if (article.getArticleUrl().startsWith(
					"http://pic.news.china.com/social")) {
				article.setWebId(142);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.game.china.com/game")) {
				article.setWebId(144);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.tech.china.com/tech")) {
				article.setWebId(145);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.fun.china.com/fun")) {
				article.setWebId(146);
			}
			if (article.getArticleUrl().startsWith(
					"http://pic.news.china.com/news")) {
				article.setWebId(615);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.kaiyun.china.com/kaiyun")) {
				article.setWebId(1627);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.ent.china.com/fun")) {
				article.setWebId(1628);
			}
			if (article.getArticleUrl().startsWith(
					"http://pic.sports.china.com/sports")) {
				article.setWebId(1629);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.travel.china.com/travel")) {
				article.setWebId(1630);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.culture.china.com/culture")) {
				article.setWebId(1631);
			}
			if (article.getArticleUrl().startsWith(
					"http://tuku.auto.china.com/auto")) {
				article.setWebId(1633);
			}
			if (articleDao.update(article)) {
				System.out.println(" >> update article.webid"
						+ article.getWebId() + " success!");
			}
		}
	}

	public static void update2() throws Exception {
		List<WebsiteBean> list = webSiteDao.findByParentId(D_PARENT_ID);
		for (WebsiteBean bean : list) {
			System.out.println(" >> bean.id:"+bean.getId());
			if(bean.getId() != 1633 && bean.getId() != 1636 && bean.getId() != 1644){
				continue;
			}
			switch(bean.getId()){
				case 1633:
				case 1636:
				case 1644:
					List<WebsiteBean> child = webSiteDao.findByParentId(bean
							.getId());
					for (WebsiteBean tmp : child) {
						// 单独处理子项逻辑
						String id = getId(tmp.getUrl());
						String content = HttpClientUtils.getResponseBody(
								CATALOGS_URL.replace("{id}", id), "UTF-8");
						if(null != content && !"".equals(content)){
							doProcessSub(tmp,content);
						}
					}
					break;
				default:
					doProcess(bean);
					break;
			}
			
			// bean.getId() == 1636 ||
//			if (bean.getId() == 1644) {
//				List<WebsiteBean> child = webSiteDao.findByParentId(bean
//						.getId());
//				int count = child.size();
//				if (count > 0) {
//					for (WebsiteBean tmp : child) {
//						// 单独处理子项逻辑
//						String id = getId(tmp.getUrl());
//						String content = HttpClientUtils.getResponseBody(
//								CATALOGS_URL.replace("{id}", id), "UTF-8");
//						if(null != content && !"".equals(content)){
//							doProcessSub(tmp,content);
//						}
//						break;
//					}
//				} else {
//					doProcess(bean);
//				}
//			}
			
		}

	}

	private static String getId(String url) {
		String id = null;
		int start = url.lastIndexOf("/");
		int end = url.lastIndexOf("_");
		id = url.substring(start + 1, end);
		return id;
	}

	static List<String> getPage(String url) throws Exception {
		List<String> page = new ArrayList<String>();
		Parser parser = new Parser();
		parser.setURL(url);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = parser.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "pages borTop"));
		if (list != null && list.size() > 0) {
			Div div = (Div) list.elementAt(0);
			System.out.println(" >> " + div.toHtml());
		}
		page.add(url);
		if (null != parser)
			parser = null;
		return page;
	}

	private static void doProcess(WebsiteBean bean) throws Exception {
		System.out.println(" >> url.id[" + bean.getId() + "] url.url["
				+ bean.getUrl() + "]");
		try {
			getLink(bean.getUrl());
		} catch (Exception e) {
		}
		Iterator it = LINKHASH.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			try {
				String tmpUrl = bean.getUrl().substring(0,
						bean.getUrl().lastIndexOf("/") + 1);
				LinkBean link = (LinkBean) LINKHASH.get(key);
				if (!link.getLink().startsWith(tmpUrl)) {
					System.out.println(" >> [" + link.getLink() + "] 不是以["
							+ tmpUrl + "]开头的");
					continue;
				}
				Article acticle = null;
				if (null != client.get(link.getLink())) {
					System.err.println("缓存中已存在相同地址[" + link.getLink() + "]");
					continue;
				}
				acticle = new Article();
				acticle.setArticleUrl(link.getLink());
				acticle.setTitle(link.getName());
				acticle.setWebId(bean.getId());
				acticle.setText("NED");
				System.out.println(" >> tmpUrl:" + tmpUrl);
				if (link.getLink().startsWith(tmpUrl)) {
					if (link.getLink().indexOf("_") == -1) {
						if (bean.getId() == 1633 || bean.getParentId() == 1636
								|| bean.getParentId() == 1644) {
							String xmlUrl = link.getLink().replace(".htm",
									".xml");
							if (xmlUrl != null) {
								acticle.setActicleXmlUrl(xmlUrl);
								acticle.setActicleRealUrl(link.getLink());
							} else {
								acticle.setActicleXmlUrl(null);
								acticle.setActicleRealUrl(null);
							}
						} else {
							// 老版本图片播放器解析方式
							String realUrl = HTMLParser.getRealUrl(link
									.getLink());
							String xmlUrl = HTMLParser.getXmlUrl(realUrl);
							if (xmlUrl != null) {
								acticle.setActicleXmlUrl(xmlUrl);
								acticle.setActicleRealUrl(realUrl);
							} else {
								acticle.setActicleXmlUrl(null);
								acticle.setActicleRealUrl(null);
							}
						}
					} else {
						// 新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，
						String xmlUrl = HTMLParser.getXmlUrl2(link.getLink());
						if (xmlUrl != null) {
							acticle.setActicleXmlUrl(xmlUrl);
							acticle.setActicleRealUrl("2");
						} else {
							acticle.setActicleXmlUrl(null);
							acticle.setActicleRealUrl(null);
						}
					}
				} else {
					acticle.setActicleXmlUrl(null);
					acticle.setActicleRealUrl(null);
				}
				int result = articleDao.insert(acticle);
				if (result > 0) {
					 client.add(link.getLink(), link.getLink());
					acticle.setId(result);
					String aKey = CacheUtils.getArticleKey(result);
					 if (null == client.get(aKey)) {
						 client.add(aKey, acticle);
					 }
					getImage(result, bean.getUrl());
				}
			} catch (Exception e) {
				continue;
			}
		}

		LINKHASH.clear();
	}

	/**
	 * 处理有子菜单的页面
	 * @param bean
	 * @param content
	 * @throws Exception
	 */
	private static void doProcessSub(WebsiteBean bean,String content) throws Exception {
		System.out.println(" >> url.id[" + bean.getId() + "] url.url["
				+ bean.getUrl() + "]");
		try {
			getLinkByContent(content);
		} catch (Exception e) {
		}
		Iterator it = LINKHASH.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			try {
				String tmpUrl = bean.getUrl().substring(0,
						bean.getUrl().lastIndexOf("/") + 1);
				LinkBean link = (LinkBean) LINKHASH.get(key);
				if (!link.getLink().startsWith(tmpUrl)) {
					System.out.println(" >> [" + link.getLink() + "] 不是以["
							+ tmpUrl + "]开头的");
					continue;
				}
				Article acticle = null;
				if (null != client.get(link.getLink())) {
					System.err.println("缓存中已存在相同地址[" + link.getLink() + "]");
					continue;
				}
				acticle = new Article();
				acticle.setArticleUrl(link.getLink());
				acticle.setTitle(link.getName());
				acticle.setWebId(bean.getId());
				acticle.setText("NED");
				System.out.println(" >> tmpUrl:" + tmpUrl);
				if (link.getLink().startsWith(tmpUrl)) {
					if (link.getLink().indexOf("_") == -1) {
						if (bean.getId() == 1633 || bean.getParentId() == 1636
								|| bean.getParentId() == 1644) {
							String xmlUrl = link.getLink().replace(".htm",
									".xml");
							if (xmlUrl != null) {
								acticle.setActicleXmlUrl(xmlUrl);
								acticle.setActicleRealUrl(link.getLink());
							} else {
								acticle.setActicleXmlUrl(null);
								acticle.setActicleRealUrl(null);
							}
						} else {
							// 老版本图片播放器解析方式
							String realUrl = HTMLParser.getRealUrl(link
									.getLink());
							String xmlUrl = HTMLParser.getXmlUrl(realUrl);
							if (xmlUrl != null) {
								acticle.setActicleXmlUrl(xmlUrl);
								acticle.setActicleRealUrl(realUrl);
							} else {
								acticle.setActicleXmlUrl(null);
								acticle.setActicleRealUrl(null);
							}
						}
					} else {
						// 新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，
						String xmlUrl = HTMLParser.getXmlUrl2(link.getLink());
						if (xmlUrl != null) {
							acticle.setActicleXmlUrl(xmlUrl);
							acticle.setActicleRealUrl("2");
						} else {
							acticle.setActicleXmlUrl(null);
							acticle.setActicleRealUrl(null);
						}
					}
				} else {
					acticle.setActicleXmlUrl(null);
					acticle.setActicleRealUrl(null);
				}
				int result = articleDao.insert(acticle);
				if (result > 0) {
					 client.add(link.getLink(), link.getLink());
					acticle.setId(result);
					String aKey = CacheUtils.getArticleKey(result);
					 if (null == client.get(aKey)) {
						 client.add(aKey, acticle);
					 }
					getImage(result, bean.getUrl());
				}
			} catch (Exception e) {
				continue;
			}
		}

		LINKHASH.clear();
	}

	public static void parserAuto(String url) throws Exception {
		Parser p1 = new Parser();
		p1.setURL(url);
		p1.setEncoding("UTF-8");

		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = p1.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "ImgHolder"));
		if (null != list && list.size() > 0) {
			int count = list.size();
			System.out.println(" >> auto.size:" + count);
			for (int i = 0; i < count; i++) {
				Div div = (Div) list.elementAt(i);
				System.out.println(" >> " + div.toHtml());
			}
		}

		if (null != p1) {
			p1 = null;
		}

	}

	public static void parserSubContent(String content) throws Exception {
		Parser p1 = new Parser();
		p1.setInputHTML(content);
		p1.setEncoding("UTF-8");

		NodeFilter filter = new NodeClassFilter(Div.class);
		NodeList list = p1.extractAllNodesThatMatch(filter)
				.extractAllNodesThatMatch(
						new HasAttributeFilter("class", "ImgHolder"));
		if (null != list && list.size() > 0) {
			int count = list.size();
			for (int i = 0; i < count; i++) {
				Div div = (Div) list.elementAt(i);
				System.out.println(" >> " + div.toHtml());
			}
		}

		if (null != p1) {
			p1 = null;
		}

	}

	public static void update() throws Exception {
		StringBuffer esb = new StringBuffer("Exception:\n");
		for (String url : CATALOG_URL) {
			try {
				getLink(url);
			} catch (Exception e) {
				esb.append("URL[" + url + "]:\n" + e.getMessage() + "\n");
				continue;
			}
			Iterator it = LINKHASH.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println("key:" + key);
				try {
					LinkBean bean = (LinkBean) LINKHASH.get(key);
					Article acticle = null;
					if (null != client.get(bean.getLink())) {
						System.err
								.println("缓存中已存在相同地址[" + bean.getLink() + "]");
						continue;
					}
					if (HttpClientUtils.validationURL(bean.getLink())) {
						acticle = new Article();
						acticle.setArticleUrl(bean.getLink());
						acticle.setTitle(bean.getName());
						acticle.setWebId(301);
						acticle.setText("NED");
						if (bean.getLink().startsWith(
								"http://tuku.kaiyun.china.com/kaiyun/")
								|| bean.getLink().startsWith(
										"http://tuku.ent.china.com/fun/")
								|| bean.getLink().startsWith(
										"http://tuku.news.china.com/")
								|| bean.getLink().startsWith(
										"http://tuku.military.china.com/")
								|| bean.getLink().startsWith(
										"http://tuku.fun.china.com/")
								|| bean.getLink().startsWith(
										"http://tuku.game.china.com/")
								|| bean.getLink().startsWith(
										"http://pic.news.china.com/")
								|| bean.getLink().startsWith(
										"http://tuku.tech.china.com/")
								|| bean.getLink().startsWith(
										"http://pic.news.china.com/news/")
								|| bean.getLink().startsWith(
										"http://pic.sports.china.com/")
								|| bean.getLink().startsWith(
										"http://tuku.travel.china.com/")
								|| bean
										.getLink()
										.startsWith(
												"http://tuku.auto.china.com/auto/html/")
								|| bean.getLink().startsWith(
										"http://tuku.culture.china.com/")) {
							String html = bean.getLink().substring(
									bean.getLink().lastIndexOf("/") + 1,
									bean.getLink().lastIndexOf("."));
							if (bean.getLink().indexOf("_") == -1) { // &&
								// html.length()
								// > 6
								// 老版本图片播放器解析方式
								String realUrl = HTMLParser.getRealUrl(bean
										.getLink());
								System.out.println("老版本图片播放器解析：" + realUrl);
								String xmlUrl = HTMLParser.getXmlUrl(realUrl);
								if (xmlUrl != null) {
									acticle.setActicleXmlUrl(xmlUrl);
									acticle.setActicleRealUrl(realUrl);
								} else {
									acticle.setActicleXmlUrl(null);
									acticle.setActicleRealUrl(null);
								}
							} else {
								// 新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，
								String xmlUrl = HTMLParser.getXmlUrl2(bean
										.getLink());
								if (xmlUrl != null) {
									acticle.setActicleXmlUrl(xmlUrl);
									acticle.setActicleRealUrl("2");
								} else {
									acticle.setActicleXmlUrl(null);
									acticle.setActicleRealUrl(null);
								}
							}

						} else {
							acticle.setActicleXmlUrl(null);
							acticle.setActicleRealUrl(null);
						}
						int result = articleDao.insert(acticle);
						if (result > 0) {
							client.add(bean.getLink(), bean.getLink());
							acticle.setId(result);
							String aKey = CacheUtils.getArticleKey(result);
							if (null == client.get(aKey)) {
								client.add(aKey, acticle);
							}
							getImage(result, url);
						}
					} else {
						System.err.println(">> getActicle 地址暂不可用 ["
								+ bean.getLink() + "]");
						continue;
					}
				} catch (Exception e) {
					// LINKLIST.add(bean);
					// break;
					esb.append("URL[" + key + "]:\n" + e.getMessage() + "\n");
					continue;
				}
			}

			LINKHASH.clear();
		}

	}

	/**
	 * 更新记录
	 */
	public static void patch() throws Exception {
		List<WebsiteBean> wlist = webSiteDao.findByParentId(D_PARENT_ID);
		for (WebsiteBean bean : wlist) {
			List<Article> list = articleDao.findByWebId(bean.getId(), "NED");
			if (null != list || list.size() > 0) {
				for (Article article : list) {
					try {
						if (article.getArticleUrl().indexOf("_") == -1) { // &&
							// html.length()
							// > 6
							// 老版本图片播放器解析方式
							String realUrl = HTMLParser.getRealUrl(article
									.getArticleUrl());
							System.out.println("老版本图片播放器解析：" + realUrl);
							String xmlUrl = HTMLParser.getXmlUrl(realUrl);
							if (xmlUrl != null) {
								article.setActicleXmlUrl(xmlUrl);
								article.setActicleRealUrl(realUrl);
							} else {
								article.setActicleXmlUrl(null);
								article.setActicleRealUrl(null);
							}
						} else {
							// 新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，
							String xmlUrl = HTMLParser.getXmlUrl2(article
									.getArticleUrl());
							if (xmlUrl != null) {
								article.setActicleXmlUrl(xmlUrl);
								article.setActicleRealUrl("2");
							} else {
								article.setActicleXmlUrl(null);
								article.setActicleRealUrl(null);
							}
						}
						if (articleDao.update(article)) {
							getImage(article.getId(), bean.getUrl());
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}
	}

	/**
	 * 初始化文章缓存数据
	 * 
	 */
	public static void init() {
		try {
			List<Article> alist = articleDao.findAll();
			for (Article art : alist) {
				if (null == client.get(art.getArticleUrl())) {
					client.add(art.getArticleUrl(), art.getArticleUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void movefile() throws Exception {
		List<WebsiteBean> webList = webSiteDao.findByParentId(D_PARENT_ID);
		PicfileBean bean = null;
		for (WebsiteBean website : webList) {
			System.out.println(website.getId() + "|" + website.getName() + "|"
					+ website.getUrl());
			List<Article> artList = articleDao.findByWebId(website.getId(),
					"FD");
			System.out.println("文章数量:" + artList.size());
			for (Article article : artList) {
				List<ImageBean> list = imageDao.findImage(article.getId());
				for (ImageBean img : list) {
					bean = picFiledao.findByImgIdAndArticleId(img.getId(),
							article.getId());
					if (null != bean) {
						if (moveFile(bean)) {
							System.out.println(bean.getId() + "|"
									+ bean.getArticleId() + "|"
									+ bean.getImageId());
							System.out.println("after move file name:"
									+ bean.getName());
							System.out.println("after move file smallName:"
									+ bean.getSmallName());
							System.out
									.println("-------------------------------------------------------------");
						}
					}
					// break;
				}
				// break;
			}
			// break;
		}
	}

	static boolean moveFile(PicfileBean bean) {
		String tmpUrl = "P:\\share\\file\\zol\\";
		boolean isBig = false;
		boolean isSmall = false;
		int start = bean.getName().lastIndexOf(File.separator) + 1;
		int smnStart = bean.getSmallName().lastIndexOf(File.separator) + 1;
		String prx = StringUtils.gerDir(String.valueOf(bean.getArticleId()));
		String fileName = prx + bean.getArticleId() + File.separator
				+ bean.getName().substring(start);
		String smallFileName = prx + bean.getArticleId() + File.separator
				+ bean.getSmallName().substring(smnStart);
		String source = tmpUrl + bean.getName();
		String smgSource = tmpUrl + bean.getSmallName();
		String target = FILE_SERVER + fileName;
		String smgTarget = FILE_SERVER + smallFileName;
		bean.setUrl(FILE_SERVER);
		if (FileUtils.copyFile(source, target)) {
			System.out.println(">> 大图成功!!!");
			if (FileUtils.deleteFile(source)) {
				System.out.println(">> 删除源大图[" + source + "]成功");
			}
			bean.setName(fileName);
			isBig = true;
		}

		if (FileUtils.copyFile(smgSource, smgTarget)) {
			System.out.println(">> 小图成功!!!");
			if (FileUtils.deleteFile(smgSource)) {
				System.out.println(">> 删除源小图[" + smgSource + "]成功");
			}
			bean.setSmallName(smallFileName);
			isSmall = true;
		}
		if (isBig) {
			if (isBig || isSmall) {
				try {
					if (picFiledao.update(bean)) {
						System.out.println(">> 更新图片文件[" + bean.getId()
								+ "]记录成功!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return isBig;
	}
}
