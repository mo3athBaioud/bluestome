package com.ssi.htmlparser.chinamilitary;

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

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.ssi.common.utils.FileUtils;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.common.utils.StringUtils;
import com.ssi.dal.domain.Article;
import com.ssi.dal.domain.Image;
import com.ssi.dal.domain.PictureFile;
import com.ssi.dal.domain.Website;
import com.ssi.htmlparser.BaseHtmlParser;
import com.ssi.htmlparser.bean.LinkBean;
import com.ssi.htmlparser.bean.WebsiteBean;
import com.ssi.htmlparser.utils.CacheUtils;
import com.ssi.htmlparser.utils.CommonUtil;
import com.ssi.htmlparser.utils.IOUtil;

public class MilitaryParser extends BaseHtmlParser {
	
	private final String URL = "http://tuku.military.china.com/military/html/1_1.html"; // military.china.com

	private final String URL_ = "http://military.china.com/";

	private final String TUKU_URL = "http://tuku.military.china.com/military/html/20"; // military.china.com

	private final String HISTORY_TUKU_URL = "http://tuku.news.china.com/history/html/20";

	private final String BBS_ARTICLE_URL = "http://military.china.com/zh_cn/bbs2/11053806/20";

	private final String SOCIAL_TUKU_URL = "http://pic.news.china.com/social/html/20";

	private final String TECH_TUKU_URL = "http://tuku.tech.china.com/tech/html/20";

	private final String FUN_TUKU_URL = "http://tuku.fun.china.com/fun/html/20";

	private final String GAME_TUKU_URL = "http://tuku.game.china.com/game/html/20";

	static int D_PARENT_ID = 301;

	String FILE_SERVER = "O:\\fileserver\\image\\";
	
	String tmpUrl = "P:\\share\\file\\military\\";

	private List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

	private List<Article> ARTICLELIST = new ArrayList<Article>();

	private HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	void getLink() throws Exception {
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
						logger.info("从中国武器装备图片大全页面中获取数据...");
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

	void getLink(String url) throws Exception {

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
						logger.info(">> 已存在相同[" + link.getLink() + "]");
					}
				}
			}
		}
		p1 = null;
	}

	List<LinkBean> getLinkList() {
		return LINKLIST;
	}

	void add(LinkBean bean) {
		LINKLIST.add(bean);
	}

	void clearHash() {
		if (LINKHASH.size() > 0) {
			LINKHASH.clear();
		}
	}

	void clearList() {
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
	void getActicle(Integer parentId) throws Exception {
		List<Website> list = websiteDao.findByFatherId(parentId);
		for (Website webBean : list) {
			logger.info("页面名称：" + webBean.getName() + "页面地址："
					+ webBean.getUrl());
			if (!HttpClientUtils.validationURL(webBean.getUrl())) {
				logger.error("地址不通[" + webBean.getUrl() + "]");
				continue;
			}
			getLink(webBean.getUrl());
			if (LINKHASH.size() > 0) {
				logger.info("LINKHASH.size():" + LINKHASH.size());
				Set<String> key = LINKHASH.keySet();
				Iterator<String> it = key.iterator();
				while (it.hasNext()) {
					LinkBean bean = (LinkBean) LINKHASH.get(it.next());
					Article acticle = null;
					try {
						if (null != client.get(bean.getLink())) {
							logger.error("缓存中已存在地址[" + bean.getLink() + "]");
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
									|| bean.getLink().startsWith(
											"http://pic.news.china.com/news/")) {

								String html = bean.getLink().substring(
										bean.getLink().lastIndexOf("/") + 1,
										bean.getLink().lastIndexOf("."));
								if (bean.getLink().indexOf("_") == -1) { // &&
									// html.length()
									// > 6
									// 老版本图片播放器解析方式
									String realUrl = htmlParser.getRealUrl(bean
											.getLink());
									String xmlUrl = htmlParser
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
								client.put(bean.getLink(), bean.getLink());
								acticle.setId(result);
								String aKey = CacheUtils.getArticleKey(result);
								if (null == articleCache.get(aKey)) {
									articleCache.put(aKey, acticle);
								}
								getImage(result);
							} else {
								logger.info(">> 添加文章失败");
							}
						} else {
							logger.error(">> getActicle 地址暂不可用 ["
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
			clearHash();
		}
	}

	void pitchArticle() throws Exception {
		List<Article> list = articleDao.find(new HashMap());
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
					String realUrl = htmlParser
							.getRealUrl(bean.getArticleUrl());
					String xmlUrl = htmlParser.getXmlUrl(realUrl);
					bean.setActicleRealUrl(realUrl);
					bean.setActicleXmlUrl(xmlUrl);
					int result = articleDao.update(bean);
					if (result > 0) {
						getImage(bean);
						count++;
					}
				}
			}
		}
		logger.info("共有" + count + "条记录更新成功!!!");
	}

	void pitchArticleUrl() throws Exception {
		List<Article> list = articleDao.find(new HashMap());
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
					String realUrl = htmlParser
							.getRealUrl(bean.getArticleUrl());
					String xmlUrl = htmlParser.getXmlUrl(realUrl);
					bean.setActicleRealUrl(realUrl);
					bean.setActicleXmlUrl(xmlUrl);
					int result = articleDao.update(bean);
					if (result > 0) {
						getImage(bean);
						count++;
					}
				}
			}
		}
		logger.info("共有" + count + "条记录更新成功!!!");
	}

	void getImage() throws Exception {
		List<Article> list = articleDao.find(new HashMap());
		if (list.size() > 0) {
			for (Article article : list) {
				String realUrl = htmlParser.getRealUrl(article.getArticleUrl());
				String xmlUrl = htmlParser.getXmlUrl(realUrl);
				switch (article.getWebId()) {
				case 3:
					break;
				default:
					try {
						HashMap map = new HashMap();
						map.put("articleId", article.getId());
						if (imageDao.getCount(map) > 0) {
							logger.info("跳过");
							continue;
						}
						List<Image> imgList = xmlParser.readXmlFromURL(xmlUrl);
						for (Image bean : imgList) {
							bean.setArticleId(article.getId());
							int result = imageDao.insert(bean);
							if (result == -1) {
								logger.info("图片标题为：" + bean.getTitle()
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

	void getImage(Article article) throws Exception {
		try {
			HashMap map = new HashMap();
			map.put("articleId", article.getId());
			if (imageDao.getCount(map) > 0) {
				logger.info("跳过");
				return;
			}
			if (article.getActicleXmlUrl() != null
					&& !article.getActicleXmlUrl().equalsIgnoreCase("")) {
				List<Image> imgList = xmlParser.readXmlFromURL(article
						.getActicleXmlUrl());
				for (Image bean : imgList) {
					bean.setArticleId(article.getId());
					int result = imageDao.insert(bean);
					if (result == -1) {
						logger.info("图片标题为：" + bean.getTitle() + ",未添加到数据库");
					} else {
						// bean.setId(result);
						// imgDownload(bean, article.getWebId());
						// logger.info((bean.getTitle() == null ? "无标题"
						// : bean.getTitle()));
					}
				}
			}
		} catch (Exception e) {
			ARTICLELIST.add(article);
			e.printStackTrace();
			return;
		}
	}

	void getImage(Integer id) throws Exception {
		logger.error(" >> IN GetImage Method");
		Article article = null;
		String aKey = CacheUtils.getArticleKey(id);
		try {
			if (null != articleCache.get(aKey)) {
				logger.info(">> 从缓存中获取文章对象[" + id + "] ");
				article = (Article) articleCache.get(aKey);
			}
			if (null == article) {
				logger.info(">> 从缓存中未获取文章对象，从数据库中获取文章对象[" + id + "] ");
				article = articleDao.findById(id);
				if (null != article)
					articleCache.put(aKey, article);
			}
			if (article != null) {
				if (!HttpClientUtils.validationURL(article.getArticleUrl())) {
					logger.error(">> getImage.地址不通[" + article.getArticleUrl()
							+ "]");
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
						List<Image> imgList = xmlParser.readXmlFromURL(article
								.getActicleXmlUrl());
						if (null != imgList && imgList.size() > 0) {
							for (Image bean : imgList) {
								if (!HttpClientUtils.validationURL(bean
										.getImgUrl())
										|| !HttpClientUtils.validationURL(bean
												.getHttpUrl())) {
									logger.error(">> 地址不通[" + bean.getHttpUrl()
											+ "]");
									continue;
								}
								String length = HttpClientUtils
										.getHttpHeaderResponse(bean
												.getHttpUrl(), "Content-Length");
								logger.info(">> Content-Length:" + length);
								if (null != length) {
									bean.setSize(Long.valueOf(length));
									bean.setStatus(1);
								}
								if (client.get(bean.getHttpUrl()) == null) {
									bean.setArticleId(article.getId());
									// HttpClientUtils
									int result = imageDao.insert(bean);
									if (result == -1) {
										logger.info("图片标题为：" + bean.getTitle()
												+ ",未添加到数据库");
									} else {
										bean.setId(result);
										client.put(bean.getHttpUrl(), bean
												.getHttpUrl());
										imageCache.put(CacheUtils
												.getImageKey(result), bean);
										// if(imgDownload(bean,
										// article.getWebId())){
										// bean.setLink("FD");
										// if(imageDao.update(bean)){
										// System.out
										// .println(">>
										// 图片下载成功，更新[tbl_image("+bean.getArticleId()+"|"+bean.getId()+")]图片记录");
										// }
										// }
									}
								}
							}
							article.setText("FD");
							articleDao.update(article);
						}
					}
				}
			} else {
				logger.info("未查询到ARTICLE记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	boolean imgDownload(Image img, Integer webId) {
		PictureFile bean = null;
		bean = new PictureFile();
		String s_fileName = img.getImgUrl().substring(
				img.getImgUrl().lastIndexOf("/") + 1, img.getImgUrl().length());
		String fileName = img.getHttpUrl().substring(
				img.getHttpUrl().lastIndexOf("/") + 1,
				img.getHttpUrl().length());
		String length = "0";
		try {
			byte[] big = null;
			big = HttpClientUtils.getResponseBodyAsByte(
					img.getCommentshowurl(), null, img.getHttpUrl());
			if (null == big)
				return false;
			length = String.valueOf(big.length);
			if (length.equalsIgnoreCase("20261")
					|| length.equalsIgnoreCase("3267")
					|| length.equalsIgnoreCase("4096")) {
				logger.info(">>> 尝试使用Referer来获取图片");
				big = HttpClientUtils.getResponseBodyAsByte(img.getReferer(),
						null, img.getHttpUrl());
				length = String.valueOf(big.length);
				if (length.equalsIgnoreCase("20261")
						|| length.equalsIgnoreCase("3267")
						|| length.equalsIgnoreCase("4096")) {
					logger.error("下载被屏蔽，未突破盗链系统...");
					return false;
				}
			}
			// 小图
			if (client.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + s_fileName)) == null) {
				IOUtil.createPicFile(img.getImgUrl(), PIC_SAVE_PATH
						+ StringUtils
								.gerDir(String.valueOf(img.getArticleId()))
						+ img.getArticleId() + File.separator + s_fileName);
			}

			// 大图
			if (client.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + fileName)) == null) {
				IOUtil.createFile(big, PIC_SAVE_PATH
						+ StringUtils
								.gerDir(String.valueOf(img.getArticleId()))
						+ img.getArticleId() + File.separator + fileName);
			}
			bean.setArticleId(img.getArticleId());
			bean.setImageId(img.getId());
			bean.setTitle(img.getTitle());
			bean.setSmallName(File.separator
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + s_fileName);
			bean.setName(File.separator
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + fileName);
			bean.setUrl(PIC_SAVE_PATH);
			try {
				boolean b = pictureFileDao.insert(bean);
				if (b) {
					pictureFileCache.put(CacheUtils.getBigPicFileKey(bean
							.getUrl()
							+ bean.getName()), bean);
					pictureFileCache.put(CacheUtils.getSmallPicFileKey(bean
							.getUrl()
							+ bean.getSmallName()), bean);
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				logger.info("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			logger.info("网络连接，文件IO异常");
			return false;
		}
	}

	void imgDownload() throws Exception {
		PictureFile bean = null;
		HashMap map = new HashMap();
		map.put("link", "NED");
		map.put("limit", 200);
		List<Image> list = imageDao.find(map);
		try {
			if (list.size() > 0) {
				logger.info("list.size():" + list.size());
				for (Image imgBean : list) {
					bean = new PictureFile();
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
					boolean b = pictureFileDao.insert(bean);
					if (b) {
						logger.info("成功！");
					} else {
						logger.info("失败");
					}
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
	void index() throws Exception {
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
							logger.info("name:" + name);
							article = new Article();
							article.setWebId(36);
							article.setArticleUrl(link.getLink());
							article.setTitle(name);
							article.setText("NED"); // No Execute Download
							int key = articleDao.insert(article);
							if (key > 0) {
								client.put(link.getLink(), link.getLink());
							} else {
								logger.error(">> 数据库中已存在相同[" + link.getLink()
										+ "]");
							}
							Thread.sleep(50);
						} else {
							logger.error(">> 缓存中已存在相同[" + link.getLink() + "]");
						}
					}

				} catch (Exception e) {
					logger.error(">> Index.Exception:" + e.getMessage());
					continue;
				}
			}
		}

	}

	void patch() {
		try {
			// Integer[] webIds = { 1,3, 4,5,14,17,29,34,301,144,145 };
			Integer[] webIds = { 144 };
			for (Integer webId : webIds) {
				HashMap map = new HashMap();
				map.put("webId", webId);
				List<Article> articleList = articleDao.find(map);
				for (Article article : articleList) {
					getImage(article.getId());
				}
			}

		} catch (Exception e) {

		}
	}

	void patchImag(WebsiteBean bean) {
		try {
			HashMap map = new HashMap();
			map.put("webId", bean.getId());
			map.put("text", "FD");
			List<Article> artList = articleDao.find(map);
			for (Article article : artList) {
				HashMap imgMap = new HashMap();
				imgMap.put("articleId", article.getId());
				List<Image> imgList = imageDao.find(imgMap);
				for (Image img : imgList) {
					imgDownload(img, bean.getId());
				}
				imgMap.clear();
			}
			map.clear();
		} catch (Exception e) {
			logger.error(">> error patch img");
		}
	}

	void movefile(int parentId) throws Exception {
		List<Website> webList = websiteDao.findByFatherId(parentId);
		PictureFile bean = null;
		for (Website website : webList) {
			logger.info(website.getId() + "|" + website.getName() + "|"
					+ website.getUrl());
			HashMap map = new HashMap();
			map.put("webId", bean.getId());
			map.put("text", "FD");
			List<Article> artList = articleDao.find(map);
			logger.info("文章数量:" + artList.size());
			for (Article article : artList) {
				HashMap imgMap = new HashMap();
				imgMap.put("articleId", article.getId());
				List<Image> imgList = imageDao.find(imgMap);
				for (Image img : imgList) {
					logger.info("ImageID:" + img.getId() + "\tArticleID:"
							+ article.getId());
					HashMap picMap = new HashMap();
					picMap.put("articleid", article.getId());
					picMap.put("imageid", img.getId());
					List<PictureFile> pList = pictureFileDao.find(picMap);
					if (pList.size() == 1) {
						bean = (PictureFile) pList.get(0);
						if (null != bean) {
							if (moveFile(bean)) {
								logger.info(bean.getId() + "|"
										+ bean.getArticleId() + "|"
										+ bean.getImageId());
								logger.info("after move file name:"
										+ bean.getName());
								logger.info("after move file smallName:"
										+ bean.getSmallName());
								logger
										.info("-------------------------------------------------------------");
							}
						}
					}
					break;
				}
				imgMap.clear();
			}
		}
	}

	boolean moveFile(PictureFile bean) {
		tmpUrl = "P:\\share\\file\\military\\";
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
		String smgSource = tmpUrl
				+ bean.getSmallName().replace(File.separator + "s_",
						File.separator);
		String target = FILE_SERVER + fileName;
		String smgTarget = FILE_SERVER
				+ smallFileName.replace(File.separator + "s_", File.separator);
		bean.setUrl(FILE_SERVER);
		if (FileUtils.copyFile(source, target)) {
			logger.info(">> 大图成功!!!");
			if (FileUtils.deleteFile(source)) {
				logger.info(">> 删除源大图[" + source + "]成功");
			}
			bean.setName(fileName);
			isBig = true;
		}

		if (FileUtils.copyFile(smgSource, smgTarget)) {
			logger.info(">> 小图成功!!!");
			if (FileUtils.deleteFile(smgSource)) {
				logger.info(">> 删除源小图[" + smgSource + "]成功");
			}
			bean.setSmallName(smallFileName);
			isSmall = true;
		}
		if (isBig) {
			if (isBig || isSmall) {
				try {
					if (pictureFileDao.update(bean) > 0) {
						logger.info(">> 更新图片文件[" + bean.getId() + "]记录成功!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return isBig;
	}

	
	public int getD_PARENT_ID() {
		return D_PARENT_ID;
	}

	public void setD_PARENT_ID(int d_parent_id) {
		D_PARENT_ID = d_parent_id;
	}

	public String getTmpUrl() {
		return tmpUrl;
	}

	public void setTmpUrl(String tmpUrl) {
		this.tmpUrl = tmpUrl;
	}
	
	public String getFILE_SERVER() {
		return FILE_SERVER;
	}

	public void setFILE_SERVER(String file_server) {
		FILE_SERVER = file_server;
	}

	@Override
	public void init() {
		try{
			getActicle(5);
			getActicle(5);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}

}
