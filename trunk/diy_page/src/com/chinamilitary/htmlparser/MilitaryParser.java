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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
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
import com.chinamilitary.xmlparser.XMLParser;

public class MilitaryParser {
	private static final String URL = "http://tuku.military.china.com/military/html/1_1.html"; // military.china.com
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

	private static final String PIC_SAVE_PATH = "F:\\china\\military\\";

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
			getLink(webBean.getUrl());
			if (LINKHASH.size() > 0) {
				System.out.println("LINKHASH.size():" + LINKHASH.size());
				Set<String> key = LINKHASH.keySet();
				Iterator<String> it = key.iterator();
				while (it.hasNext()) {
					LinkBean bean = (LinkBean) LINKHASH.get(it.next());
					System.out.println("分类名称:" + webBean.getName() + ",链接名称："
							+ bean.getName() + ",链接地址：" + bean.getLink());
					Article acticle = null;
//					if (client.get(CacheUtils.getHTMLKey(bean.getLink())) == null) {
						try {
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
											"http://tuku.tech.china.com/")) {
								
								String html = bean.getLink().substring(bean.getLink().lastIndexOf("/")+1,bean.getLink().lastIndexOf("."));
								if(bean.getLink().indexOf("_") == -1){ // && html.length() > 6
//									 老版本图片播放器解析方式
									String realUrl = HTMLParser.getRealUrl(bean
											.getLink());
									System.out.println("老版本图片播放器解析："+realUrl);
									String xmlUrl = HTMLParser.getXmlUrl(realUrl);
									if(xmlUrl != null){
										acticle.setActicleXmlUrl(xmlUrl);
										acticle.setActicleRealUrl(realUrl);
									}else{
										acticle.setActicleXmlUrl(null);
										acticle.setActicleRealUrl(null);
									}
								}else{
//									新版本图片播放器解析方式，不需要在解析真实的URL地址，直接将地址中的XML文件解析并且从中获取必要的数据即可，								
									String xmlUrl = HTMLParser.getXmlUrl2(bean.getLink());
									if(xmlUrl != null){
										acticle.setActicleXmlUrl(xmlUrl);
										acticle.setActicleRealUrl("2");
									}else{
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
								System.out.println("数据库操作结果："
										+ (result == -1 ? "添加失败" : "添加成功"));
								System.out.println("连接地址：" + bean.getLink());
								System.out.println("name:" + bean.getName());
								acticle.setId(result);
								client.add(CacheUtils.getHTMLKey(acticle
										.getArticleUrl()), acticle);
								client.add(CacheUtils.getArticleKey(result),acticle);
//								client.remove("127.0.0.1:11211:ARTICLE:LIST_BY_WEBID:"+acticle.getWebId());
								getImage(result);
							}
						} catch (Exception e) {
							LINKLIST.add(bean);
							e.printStackTrace();
//							break;
							continue;
						}
					}
//				}
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
		try {
			if (imageDao
					.getCount("select count(*) from tbl_image where d_article_id = "
							+ id) > 0) {
				System.out.println("跳过");
				return;
			}
			Article article = articleDao.findById(id);
			if (article != null) {
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
						for (ImageBean bean : imgList) {
							if (client.get(CacheUtils.getImgKey(bean
									.getHttpUrl())) == null) {
								bean.setArticleId(article.getId());
								int result = imageDao.insert(bean);
								if (result == -1) {
									System.out.println("图片标题为："
											+ bean.getTitle() + ",未添加到数据库");
								} else {
									bean.setId(result);
									client.add(CacheUtils.getImgKey(bean
											.getHttpUrl()), bean);
									client.add(CacheUtils.getImageKey(result), bean);
									imgDownload(bean, article.getWebId());
									System.out
											.println((bean.getTitle() == null ? "无标题"
													: bean.getTitle()));
								}
							}
						}
						article.setText("FD");
						articleDao.update(article);
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
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		s_fileName = s_fileName.replace(".", "_s.");
		try {
			// webId+File.separator+
			if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator
					+ fileName.replace(".", "_s."))) == null) {
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ CommonUtil.getDate("") + File.separator
						+ imgBean.getArticleId() + File.separator
						+ fileName.replace(".", "_s."));
			}

			// webId+File.separator+
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator + fileName)) == null) {
				IOUtil.createPicFile(imgBean.getHttpUrl(), PIC_SAVE_PATH
						+ CommonUtil.getDate("") + File.separator
						+ imgBean.getArticleId() + File.separator + fileName);
			}
			bean.setArticleId(imgBean.getArticleId());
			bean.setImageId(imgBean.getId());
			bean.setTitle(imgBean.getTitle());
			// webId+File.separator+
			bean.setSmallName(CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator + s_fileName);

			// webId+File.separator+
			bean.setName(CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				boolean b = picFiledao.insert(bean);
				if (b) {
					client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
							+ bean.getName()), bean);
					client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
							+ bean.getSmallName()), bean);
					System.out.println("成功！");
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

	public static void main(String args[]) {
		try {
			if (2 > 3) {
				String str = "我们?不是";
				System.out.println(StringUtils.construct(str));
				return;
			}
			// add2Cache();

			// clearList();
			getActicle(143); //5 , 143

			// pitchArticle();
			// imgDownload();
			// pitchArticle();

			// getLink("http://tuku.military.china.com/military/html/8_1.html");
			// Iterator it = LINKHASH.keySet().iterator();
			// while(it.hasNext()){
			// String key = (String)it.next();
			// LinkBean bean = (LinkBean)LINKHASH.get(key);
			// System.out.println("链接名称："+bean.getName()+"\n链接地址："+bean.getLink());
			// }

			System.out
					.println("**********************************************");
			listUnHandleData();
			clearList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void add2Cache() throws Exception {

		List<Article> list = articleDao.findAll();
		for (Article bean : list) {
			client.add(CacheUtils.getHTMLKey(bean.getArticleUrl()), bean);
		}

		List<ImageBean> list1 = imageDao.findAll();
		for (ImageBean bean : list1) {
			client.add(CacheUtils.getImgKey(bean.getHttpUrl()), bean);
		}

		List<PicfileBean> list2 = picFiledao.findAll();
		for (PicfileBean bean : list2) {
			client.add(CacheUtils.getBigPicFileKey(bean.getUrl()
					+ bean.getName()), bean.getName());
			client.add(CacheUtils.getSmallPicFileKey(bean.getUrl()
					+ bean.getSmallName()), bean.getSmallName());
		}

	}

	static void listUnHandleData() throws Exception {
		System.out.println("**********************不能解析的数据连接***********************");

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

}
