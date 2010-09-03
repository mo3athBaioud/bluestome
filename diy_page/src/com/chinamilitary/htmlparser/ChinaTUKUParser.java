package com.chinamilitary.htmlparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.LinkBean;
import com.chinamilitary.bean.PicfileBean;
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


public class ChinaTUKUParser {

	static final String URL_ = "http://tuku.china.com/";

	// "http://tuku.tech.china.com/tech",
	static final String[] CATALOG_URL = { "http://tuku.ent.china.com/fun/",
			"http://tuku.military.china.com/military/",
			"http://tuku.game.china.com/game/",
			"http://tuku.fun.china.com/fun/",
			"http://tuku.news.china.com/history/",
			"http://pic.news.china.com/social/",
			"http://pic.news.china.com/news/" };

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static final String PIC_SAVE_PATH = "F:\\china\\military\\";

	static List<LinkBean> LINKLIST = new ArrayList<LinkBean>();

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
		System.out.println("*******************************************");
		System.out.println(url);
		System.out.println("*******************************************");
		Parser p1 = new Parser();
		p1.setURL(url);
		p1.setEncoding("UTF-8");

		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList list = p1.extractAllNodesThatMatch(filter);
		LinkBean bean = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LinkTag link = (LinkTag) list.elementAt(i);
				if (validationURL(link.getLink())) {
					if (HttpClientUtils.validationURL(link.getLink())) {
						if (null == client.get(link.getLink())) {
							bean = new LinkBean();
							bean.setLink(link.getLink());
							String name = StringUtils.illageString(link
									.getAttribute("title") == null ? (link
									.getLinkText() == null ? "无话题" : link
									.getLinkText()) : link
									.getAttribute("title"));
							if (name.indexOf("“") != -1
									|| name.indexOf("”") != -1) {
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
						} else {
							System.err.println(">> 缓存中已存在相同[" + link.getLink()
									+ "]");
						}
					} else {
						System.err.println("地址[" + link.getLink() + "]不可用");
					}
				}
			}
		}
		p1 = null;
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
		s_fileName = s_fileName.replace(".", "_s.");
		try {
			if (null == client.get(CacheUtils.getDownloadSmallImageKey(imgBean
					.getId()))) {
				IOUtil.createPicFile(imgBean.getImgUrl(), PIC_SAVE_PATH
						+ CommonUtil.getDate("") + File.separator
						+ imgBean.getArticleId() + File.separator
						+ fileName.replace(".", "_s."));
			}

			if (null == client.get(CacheUtils.getDownloadBigImageKey(imgBean
					.getId()))) {
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

	public static void main(String args[]) {

		// init();

		try {
			for (String url : CATALOG_URL) {
				getLink(url);
				Iterator it = LINKHASH.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					System.out.println("key:" + key);
					try {
						LinkBean bean = (LinkBean) LINKHASH.get(key);
						Article acticle = null;
						if (null != client.get(bean.getLink())) {
							System.err.println("缓存中已存在相同地址[" + bean.getLink()
									+ "]");
							continue;
						}
						if (HttpClientUtils.validationURL(bean.getLink())) {
							acticle = new Article();
							acticle.setArticleUrl(bean.getLink());
							acticle.setTitle(bean.getName());
							acticle.setWebId(301);
							acticle.setText("NED");
							if (bean.getLink().startsWith(
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
											"http://pic.news.china.com/news/")) {
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
							}
						} else {
							System.err.println(">> getActicle 地址暂不可用 ["
									+ bean.getLink() + "]");
							continue;
						}
					} catch (Exception e) {
						// LINKLIST.add(bean);
						e.printStackTrace();
						// break;
						continue;
					}
				}

				LINKHASH.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}
