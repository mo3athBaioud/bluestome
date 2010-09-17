package com.chinamilitary.htmlparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
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
import com.chinamilitary.util.HttpClientUtils;
import com.chinamilitary.util.IOUtil;
import com.chinamilitary.util.StringUtils;
import com.chinamilitary.xmlparser.XMLParser;
import com.common.Constants;

public class MilitaryParser {
	private static final String URL = "http://tuku.military.china.com/military/html/1_1.html"; // military.china.com

	private static final String URL_ = "http://military.china.com/";

	private ExecutorService responseExecutor = Executors.newFixedThreadPool(2);

	// http://tuku.military.china.com/military/

	private static final String TUKU_URL = "http://tuku.military.china.com/military/html/20"; // military.china.com

	private static final String HISTORY_TUKU_URL = "http://tuku.news.china.com/history/html/20";

	private static final String BBS_ARTICLE_URL = "http://military.china.com/zh_cn/bbs2/11053806/20";

	// START
	private static final String SOCIAL_TUKU_URL = "http://pic.news.china.com/social/html/20";

	// private static final String ENGLISH_TUKU_URL =
	// "http://pic.english.china.com/english/html/20";

	private static final String TECH_TUKU_URL = "http://tuku.tech.china.com/tech/html/20";

	// private static final String HEALTH_TUKU_URL =
	// "http://tuku.health.china.com/health/html/20";

	private static final String FUN_TUKU_URL = "http://tuku.fun.china.com/fun/html/20";

	private static final String GAME_TUKU_URL = "http://tuku.game.china.com/game/html/20";

	static String PIC_SAVE_PATH = Constants.FILE_SERVER;
	
	final static String FILE_SERVER = Constants.FILE_SERVER;

	private static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	private static List<Article> ARTICLELIST = new ArrayList<Article>();

	private static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	private static MemcacheClient client = MemcacheClient.getInstance();

	private static ArticleDao articleDao = DAOFactory.getInstance()
			.getArticleDao();

	private static WebSiteDao webSiteDao = DAOFactory.getInstance()
			.getWebSiteDao();

	private static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	private static PicFileDao picFiledao = DAOFactory.getInstance()
			.getPicFileDao();

	static void getLink() throws Exception {
		Parser p1 = new Parser();
		p1.setURL(URL);

		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		LinkBean bean = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				if (link.getLink().startsWith(TUKU_URL)) {
					if (link
							.getLink()
							.equals(
									"http://tuku.military.china.com/military/html/1_1.html")) {
						System.out.println("从中国武器装备图片大全页面中获取数据...");
						getLink(link.getLink());
					}
					bean = new LinkBean();
					bean.setLink(link.getLink());
					String name = StringUtils
							.illageString(link.getAttribute("title") == null ? (link
									.getLinkText() == null ? "无话题" : link
									.getLinkText())
									: link.getAttribute("title"));
					if (name.indexOf("“") != -1 && name.indexOf("”") != -1) {
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
		}
		p1 = null;
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
				if (link.getLink().startsWith(SOCIAL_TUKU_URL)
						|| link.getLink().startsWith(TUKU_URL)
						|| link.getLink().startsWith(HISTORY_TUKU_URL)
						|| link.getLink().startsWith(BBS_ARTICLE_URL)
						|| link.getLink().startsWith(TECH_TUKU_URL)
						|| link.getLink().startsWith(FUN_TUKU_URL)
						|| link.getLink().startsWith(GAME_TUKU_URL)) {
					if (null == client.get(link.getLink())) {
						bean = new LinkBean();
						bean.setLink(link.getLink());
						String name = StringUtils.illageString(link
								.getAttribute("title") == null ? (link
								.getLinkText() == null ? "无话题" : link
								.getLinkText()) : link.getAttribute("title"));
						if (name.indexOf("“") != -1 || name.indexOf("”") != -1) {
							name = name.replaceAll("“", "");
							name = name.replace("”", "");
						}
						// 判断连接中是否存在创建文件夹时的非法字符
						if (name.indexOf("\"") != -1
								&& name.indexOf("\"") != -1) {
							name = name.replace("\"", "");
						}

						bean.setName(name);
						LINKHASH.put(bean.getLink(), bean);
						// getLink(bean.getLink());
					} else {
						System.out.println(">> 已存在相同[" + link.getLink() + "]");
					}
				}
			}
		}
		p1 = null;
	}

	static List<LinkBean> getLinkList() {
		return LINKLIST;
	}

	static void add(LinkBean bean) {
		LINKLIST.add(bean);
	}

	static void clearHash() {
		if (LINKHASH.size() > 0) {
			LINKHASH.clear();
		}
	}

	static void clearList() {
		if (LINKLIST.size() > 0) {
			LINKLIST.clear();
		}
		if (ARTICLELIST.size() > 0)
			ARTICLELIST.clear();
	}

	/**
	 * 获得文章
	 * 
	 * @throws Exception
	 */
	static void getActicle(Integer parentId) throws Exception {
		List<WebsiteBean> list = webSiteDao.findByParentId(parentId);
		for (WebsiteBean webBean : list) {
			System.out.println("页面名称：" + webBean.getName() + "页面地址："
					+ webBean.getUrl());
			if(!HttpClientUtils.validationURL(webBean.getUrl())){
				System.err.println("地址不通["+webBean.getUrl()+"]");
				continue;
			}
			getLink(webBean.getUrl());
			if (LINKHASH.size() > 0) {
				System.out.println("LINKHASH.size():" + LINKHASH.size());
				Set<String> key = LINKHASH.keySet();
				Iterator<String> it = key.iterator();
				while (it.hasNext()) {
					LinkBean bean = (LinkBean) LINKHASH.get(it.next());
					Article acticle = null;
					try {
						if(null != client.get(bean.getLink())){
							System.err.println("缓存中已存在地址["+bean.getLink()+"]");
							continue;
						}
						if (HttpClientUtils.validationURL(bean.getLink())) {
							acticle = new Article();
							acticle.setArticleUrl(bean.getLink());
							acticle.setTitle(bean.getName());
							acticle.setWebId(webBean.getId());
							acticle.setText("NED");
							if (bean.getLink().startsWith(
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
									|| bean.getLink().startsWith("http://pic.news.china.com/news/")) {

								String html = bean.getLink().substring(
										bean.getLink().lastIndexOf("/") + 1,
										bean.getLink().lastIndexOf("."));
								if (bean.getLink().indexOf("_") == -1) { // &&
																			// html.length()
																			// > 6
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
								getImage(result);
							}else{
								System.out.println(">> 添加文章失败");
							}
						} else {
							System.err.println(">> getActicle 地址暂不可用 ["
									+ bean.getLink() + "]");
							continue;
						}
					} catch (Exception e) {
						LINKLIST.add(bean);
						e.printStackTrace();
						// break;
						continue;
					}
				}
				// }
			}
			webSiteDao.update(webBean);
			clearHash();
		}
	}

	static void pitchArticle() throws Exception {
		List<Article> list = articleDao.findAll();
		int count = 1;
		for (Article bean : list) {
			if (bean.getArticleUrl().startsWith("http://tuku.news.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.military.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.fun.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.game.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://pic.news.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.tech.china.com/")) {
				if (bean.getActicleXmlUrl() == null
						|| bean.getActicleRealUrl() == null
						|| bean.getActicleXmlUrl().equalsIgnoreCase("")
						|| bean.getActicleRealUrl().equalsIgnoreCase("")) {
					String realUrl = HTMLParser
							.getRealUrl(bean.getArticleUrl());
					String xmlUrl = HTMLParser.getXmlUrl(realUrl);
					bean.setActicleRealUrl(realUrl);
					bean.setActicleXmlUrl(xmlUrl);
					boolean result = articleDao.update(bean);
					if (result) {
						getImage(bean);
						count++;
					}
				}
			}
		}
		System.out.println("共有" + count + "条记录更新成功!!!");
	}

	static void pitchArticleUrl() throws Exception {
		List<Article> list = articleDao.findAll();
		int count = 1;
		for (Article bean : list) {
			if (bean.getArticleUrl().startsWith("http://tuku.news.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.military.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.fun.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.game.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://pic.news.china.com/")
					|| bean.getArticleUrl().startsWith(
							"http://tuku.tech.china.com/")) {
				if (bean.getActicleXmlUrl() == null
						|| bean.getActicleRealUrl() == null
						|| bean.getActicleXmlUrl().equalsIgnoreCase("")
						|| bean.getActicleRealUrl().equalsIgnoreCase("")) {
					String realUrl = HTMLParser
							.getRealUrl(bean.getArticleUrl());
					String xmlUrl = HTMLParser.getXmlUrl(realUrl);
					bean.setActicleRealUrl(realUrl);
					bean.setActicleXmlUrl(xmlUrl);
					boolean result = articleDao.update(bean);
					if (result) {
						getImage(bean);
						count++;
					}
				}
			}
		}
		System.out.println("共有" + count + "条记录更新成功!!!");
	}

	static void getImage() throws Exception {
		List<Article> list = articleDao.findAll();
		if (list.size() > 0) {
			for (Article article : list) {
				String realUrl = HTMLParser.getRealUrl(article.getArticleUrl());
				String xmlUrl = HTMLParser.getXmlUrl(realUrl);
				switch (article.getWebId()) {
				case 3:
					break;
				default:
					try {
						if (imageDao
								.getCount("select count(*) from tbl_image where d_article_id = "
										+ article.getId()) > 0) {
							System.out.println("跳过");
							continue;
						}
						List<ImageBean> imgList = XMLParser
								.readXmlFromURL(xmlUrl);
						for (ImageBean bean : imgList) {
							bean.setArticleId(article.getId());
							int result = imageDao.insert(bean);
							if (result == -1) {
								System.out.println("图片标题为：" + bean.getTitle()
										+ ",未添加到数据库");
							} else {
								System.out
										.println((bean.getTitle() == null ? "无标题"
												: bean.getTitle()));
							}
						}
					} catch (Exception e) {
						ARTICLELIST.add(article);
						e.printStackTrace();
						continue;
					}
					break;
				}
			}
		}
	}

	static void getImage(Article article) throws Exception {
		try {
			if (imageDao
					.getCount("select count(*) from tbl_image where d_article_id = "
							+ article.getId()) > 0) {
				System.out.println("跳过");
				return;
			}
			if (article.getActicleXmlUrl() != null
					&& !article.getActicleXmlUrl().equalsIgnoreCase("")) {
				List<ImageBean> imgList = XMLParser.readXmlFromURL(article
						.getActicleXmlUrl());
				for (ImageBean bean : imgList) {
					bean.setArticleId(article.getId());
					int result = imageDao.insert(bean);
					if (result == -1) {
						System.out.println("图片标题为：" + bean.getTitle()
								+ ",未添加到数据库");
					} else {
						bean.setId(result);
						imgDownload(bean, article.getWebId());
						System.out.println((bean.getTitle() == null ? "无标题"
								: bean.getTitle()));
					}
				}
			}
		} catch (Exception e) {
			ARTICLELIST.add(article);
			e.printStackTrace();
			return;
		}
	}

	static void getImage(Integer id) throws Exception {
		System.err.println(" >> IN GetImage Method");
		Article article = null;
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
				if (!HttpClientUtils.validationURL(article.getArticleUrl())) {
					System.err.println(">> getImage.地址不通["
							+ article.getArticleUrl() + "]");
					return;
				}
				if (article.getArticleUrl().startsWith(
						"http://tuku.news.china.com/")
						|| article.getArticleUrl().startsWith(
								"http://tuku.military.china.com/")
						|| article.getArticleUrl().startsWith(
								"http://tuku.fun.china.com/")
						|| article.getArticleUrl().startsWith(
								"http://tuku.game.china.com/")
						|| article.getArticleUrl().startsWith(
								"http://pic.news.china.com/")
						|| article.getArticleUrl().startsWith(
								"http://tuku.tech.china.com/")) {
					if (article.getActicleXmlUrl() != null
							|| !article.getActicleXmlUrl().equalsIgnoreCase("")) {
						List<ImageBean> imgList = XMLParser
								.readXmlFromURL(article.getActicleXmlUrl());
						if(null != imgList && imgList.size() > 0){
							for (ImageBean bean : imgList) {
								if (!HttpClientUtils
										.validationURL(bean.getImgUrl())
										|| !HttpClientUtils.validationURL(bean
												.getHttpUrl())) {
									System.err.println(">> 地址不通["
											+ bean.getHttpUrl() + "]");
									continue;
								}
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
												+ bean.getTitle() + ",未添加到数据库");
									} else {
										bean.setId(result);
										client.add(bean.getHttpUrl(), bean
												.getHttpUrl());
										client.add(CacheUtils.getImageKey(result),
												bean);
										imgDownload(bean, article.getWebId());
									}
								}
							}
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

	static void imgDownload(ImageBean imgBean, Integer webId) {
		System.err.println(" >> IN Download Image Method");
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		try{
			if(picFiledao.getCount("select count(*) from tbl_pic_file where d_image_id = "+imgBean.getId()) > 0){
				System.err.println(" >> 图片已经下载["+imgBean.getId()+"]完成");
				return;
			}
		}catch(Exception e){
			return;
		}
		
		try {
			if (null == client.get(CacheUtils.getDownloadSmallImageKey(imgBean
					.getId()))) {
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator
						+ s_fileName);
			}

			if (null == client.get(CacheUtils.getDownloadBigImageKey(imgBean
					.getId()))) {
				IOUtil.createPicFile(imgBean.getHttpUrl(), PIC_SAVE_PATH
						+ StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
						+ imgBean.getArticleId() + File.separator + fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			// webId+File.separator+
			bean.setSmallName(File.separator + StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + s_fileName);

			// webId+File.separator+
			bean.setName(File.separator + StringUtils.gerDir(String.valueOf(imgBean.getArticleId()))
					+ imgBean.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				boolean b = picFiledao.insert(bean);
				if (b) {
					client.add(CacheUtils.getDownloadSmallImageKey(imgBean
							.getId()), "SMALL_" + imgBean.getId());
					client.add(CacheUtils.getDownloadBigImageKey(imgBean
							.getId()), "BIG_" + imgBean.getId());
					if (imageDao.updateLinkStatus(imgBean.getId())) {
						// 更新图片地址
						if (null != client.get(imgBean.getHttpUrl())) {
							client.replace(imgBean.getHttpUrl(), imgBean
									.getHttpUrl());
						} else {
							client.add(imgBean.getHttpUrl(), imgBean
									.getHttpUrl());
						}
					}

				} else {
					System.out.println("失败");
				}
			} catch (Exception e) {
				System.out.println("数据库异常");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("网络连接，文件IO异常");
			e.printStackTrace();
		}
	}

	static void imgDownload() throws Exception {
		PicfileBean bean = null;
		List<ImageBean> list = imageDao.findAll();
		try {
			if (list.size() > 0) {
				System.out.println("list.size():" + list.size());
				for (ImageBean imgBean : list) {
					bean = new PicfileBean();
					String s_fileName = imgBean.getImgUrl().substring(
							imgBean.getImgUrl().lastIndexOf("/") + 1,
							imgBean.getImgUrl().length());
					String fileName = imgBean.getHttpUrl().substring(
							imgBean.getHttpUrl().lastIndexOf("/") + 1,
							imgBean.getHttpUrl().length());
					s_fileName = s_fileName.replace(".", "_s.");
					IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
							+ CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ fileName.replace(".", "_s."));
					IOUtil.createPicFile(imgBean.getHttpUrl(), PIC_SAVE_PATH
							+ CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ fileName);
					bean.setArticleId(imgBean.getArticleId());
					bean.setImageId(imgBean.getId());
					bean.setTitle(imgBean.getTitle());
					bean.setSmallName(CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ s_fileName);
					bean.setName(CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ fileName);
					bean.setUrl(PIC_SAVE_PATH);
					boolean b = picFiledao.insert(bean);
					if (b) {
						System.out.println("成功！");
					} else {
						System.out.println("失败");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片下载
	 * 
	 * @throws Exception
	 */
	static void imgDownloadTest() throws Exception {
		PicfileBean bean = null;
		List<ImageBean> list = imageDao.findImage(5, "NED");
		System.out.println("可以下载的图片记录为：" + list.size());
		try {
			if (list.size() > 0) {
				System.out.println("list.size():" + list.size());
				for (ImageBean imgBean : list) {
					bean = new PicfileBean();
					String s_fileName = imgBean.getImgUrl().substring(
							imgBean.getImgUrl().lastIndexOf("/") + 1,
							imgBean.getImgUrl().length());
					String fileName = imgBean.getHttpUrl().substring(
							imgBean.getHttpUrl().lastIndexOf("/") + 1,
							imgBean.getHttpUrl().length());
					s_fileName = s_fileName.replace(".", "_s.");
					IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
							+ CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ fileName.replace(".", "_s."));
					IOUtil.createPicFile(imgBean.getHttpUrl(), PIC_SAVE_PATH
							+ CommonUtil.getDate("") + File.separator
							+ imgBean.getArticleId() + File.separator
							+ fileName);
					// bean.setArticleId(imgBean.getArticleId());
					// bean.setImageId(imgBean.getId());
					// bean.setTitle(imgBean.getTitle());
					// bean.setSmallName(CommonUtil.getDate("") + File.separator
					// + imgBean.getArticleId() + File.separator
					// + s_fileName);
					// bean.setName(CommonUtil.getDate("") + File.separator
					// + imgBean.getArticleId() + File.separator
					// + fileName);
					// bean.setUrl(PIC_SAVE_PATH);
					// boolean b = picFiledao.insert(bean);
					// if (b) {
					// System.out.println("成功！");
					// } else {
					// System.out.println("失败");
					// }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取首页数据
	 * 
	 * @throws Exception
	 */
	static void index() throws Exception {
		Parser parser = new Parser();
		parser.setURL(URL_);

		// 获取指定ID的DIV内容
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList nodes = parser.extractAllNodesThatMatch(filter);

		if (nodes != null && nodes.size() > 0) {
			Article article = null;
			for (int i = 0; i < nodes.size(); i++) {
				try {
					LinkTag link = (LinkTag) nodes.elementAt(i);
					if (link.getLink().startsWith(SOCIAL_TUKU_URL)
							|| link.getLink().startsWith(TUKU_URL)
							|| link.getLink().startsWith(HISTORY_TUKU_URL)
							|| link.getLink().startsWith(BBS_ARTICLE_URL)
							|| link.getLink().startsWith(TECH_TUKU_URL)
							|| link.getLink().startsWith(FUN_TUKU_URL)
							|| link.getLink().startsWith(GAME_TUKU_URL)) {
						if (null == client.get(link.getLink())) {
							String name = StringUtils.illageString(link
									.getAttribute("title") == null ? (link
									.getLinkText() == null ? "无话题" : link
									.getLinkText()) : link
									.getAttribute("title"));
							if (name.indexOf("“") != -1
									&& name.indexOf("”") != -1) {
								name = name.replaceAll("“", "");
								name = name.replace("”", "");
							}
							// 判断连接中是否存在创建文件夹时的非法字符
							if (name.indexOf("\"") != -1
									&& name.indexOf("\"") != -1) {
								name = name.replace("\"", "");
							}
							System.out.println("name:" + name);
							article = new Article();
							article.setWebId(36);
							article.setArticleUrl(link.getLink());
							article.setTitle(name);
							article.setText("NED"); // No Execute Download
							int key = articleDao.insert(article);
							if (key > 0) {
								client.add(link.getLink(), link.getLink());
							} else {
								System.err.println(">> 数据库中已存在相同[" + link.getLink()
										+ "]");
							}
							Thread.sleep(50);
						} else {
							System.err.println(">> 缓存中已存在相同[" + link.getLink()
									+ "]");
						}
					}

				} catch (Exception e) {
					System.out.println(">> Index.Exception:" + e.getMessage());
					continue;
				}
			}
		}

	}

	public static void main(String args[]) {
		try {

			// imgDownloadTest();

//			 add2Cache();

//			 index();

			getActicle(5); // 5 , 143

			getActicle(143); // 5 , 143
			
//			WebsiteBean bean = webSiteDao.findById(301);
			
//			patchImag(bean);

//			 patch();

			// listUnHandleData();

			clearList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void patch() {
		try {
			// List<WebsiteBean> list = webSiteDao.findByParentId(143);
//			Integer[] webIds = { 1,3, 4,5,14,17,29,34,301,144,145 };
			Integer[] webIds = {144};
			// for(WebsiteBean webSite:list){
			// List<Article> articleList =
			// articleDao.findShowImg(webSite.getId(), "NED", 0);
			for (Integer webId : webIds) {
				List<Article> articleList = articleDao.findByWebId(webId);
				for (Article article : articleList) {
					// if(1 != article.getWebId() || 3 != article.getWebId()){
					getImage(article.getId());
					// }
				}
			}
			// }

		} catch (Exception e) {

		}
	}

	static void add2Cache() throws Exception {

		List<WebsiteBean> webList5 = webSiteDao.findByParentId(5);
		if (null != webList5) {
			client.add(CacheUtils.getWebListByParentID(5), webList5);
		}
		
		List<WebsiteBean> webList143 = webSiteDao.findByParentId(143);
		if (null != webList143) {
			client.add(CacheUtils.getWebListByParentID(143), webList143);
		}

		// 初始化中华军事网网址到缓存
		List<String> murls = articleDao.findAllArticleURL(5);
		for (String url : murls) {
			if (null == client.get(url)) {
				client.add(url, url);
			}
		}

		// 初始化中华军事网网址到缓存
//		List<String> imgUrls = imageDao.findImageURL(5);
//		for (String url : imgUrls) {
//			if (null == client.get(url)) {
//				client.add(url, url);
//			} else {
//				client.replace(url, url);
//			}
//		}

		// 初始化中华图库网文章到缓存
		List<String> tkurls = articleDao.findAllArticleURL(143);
		for (String url : tkurls) {
			if (null == client.get(url)) {
				client.add(url, url);
			}
		}

		// 初始化中华图库网文章图片到缓存
//		List<String> tkImgUrls = imageDao.findImageURL(143);
//		for (String url : tkImgUrls) {
//			if (null == client.get(url)) {
//				client.add(url, url);
//			} else {
//				client.replace(url, url);
//			}
//		}

		// 添加父站点为5的文章对象
		for (WebsiteBean wbean : webList5) {
			List<Article> list = articleDao.findByWebId(wbean.getId());
			for (Article bean : list) {
				client.add(CacheUtils.getArticleKey(bean.getId()), bean);
			}
		}

		// 添加父站点为143的文章对象
		for (WebsiteBean wbean : webList143) {
			List<Article> list = articleDao.findByWebId(wbean.getId());
			for (Article bean : list) {
				client.add(CacheUtils.getArticleKey(bean.getId()), bean);
			}
		}
	}

	static void listUnHandleData() throws Exception {
		System.out
				.println("**********************不能解析的数据连接***********************");

		if (ARTICLELIST.size() > 0) {
			for (Article acticle : ARTICLELIST) {
				System.out.println("未能解析的文章名称为：" + acticle.getTitle());
			}
		}

		if (LINKLIST.size() > 0) {
			for (int i = 0; i < LINKLIST.size(); i++) {
				LinkBean bean = (LinkBean) LINKLIST.get(i);
				System.out.println("连接地址：" + bean.getLink());
				System.out.println("name:" + bean.getName());
			}
		}
	}
	
	static void patchImag(WebsiteBean bean) {
		try{
			List<Article> artList = articleDao.findByWebId(bean.getId(),"FD");
			for(Article article:artList){
				List<ImageBean> imgList = imageDao.findImage(article.getId());
				for(ImageBean img:imgList){
					imgDownload(img, bean.getId());
				}
			}
		}catch(Exception e){
			System.err.println(">> error patch img");
		}
	}

}
