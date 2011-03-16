package com.ssi.htmlparser.chinamilitary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.PictureFile;
import com.ssi.common.memcache.MemcacheClient;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.common.utils.StringUtils;
import com.ssi.htmlparser.BaseHtmlParser;
import com.ssi.htmlparser.bean.LinkBean;
import com.ssi.htmlparser.cache.ArticleCache;
import com.ssi.htmlparser.cache.CommonCache;
import com.ssi.htmlparser.cache.ImageCache;
import com.ssi.htmlparser.cache.PictureFileCache;
import com.ssi.htmlparser.utils.CacheUtils;
import com.ssi.htmlparser.utils.IOUtil;

public class ChinaTUKUParser extends BaseHtmlParser{
	
	final Log logger = LogFactory.getLog(ChinaTUKUParser.class);
			
	boolean isRunning = true;
	
	static HashMap<String,LinkBean> LINKHASH = new HashMap<String,LinkBean>();
	
	int count = 0;

	// "http://tuku.tech.china.com/tech",
	private String[] CATALOG_URL = {
			"http://tuku.kaiyun.china.com/kaiyun/",
			"http://tuku.ent.china.com/fun/",
			"http://tuku.military.china.com/military/",
			"http://tuku.game.china.com/game/",
			"http://tuku.fun.china.com/fun/",
			"http://tuku.news.china.com/history/",
			"http://pic.news.china.com/social/",
			"http://pic.news.china.com/news/",
			"http://pic.sports.china.com/sports/",
			"http://tuku.travel.china.com/travel/",
			"http://auto.china.com/autopic/",
			"http://tuku.culture.china.com/culture/"};

	/**
	 * 验证地址是否为可以请求的地址
	 * 
	 * @param url
	 * @return
	 */
	boolean validationURL(String url) {
		boolean b = false;
		for (String tmp : CATALOG_URL) {
			if (url.startsWith(tmp + "html/")) {
				b = true;
				break;
			}
		}
		return b;
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
							logger.error(">> 缓存中已存在相同[" + link.getLink()
									+ "]");
						}
					} else {
						logger.error("地址[" + link.getLink() + "]不可用");
					}
				}
			}
		}
		p1 = null;
	}

	void getImage(Integer id) throws Exception {
		logger.error(" >> IN GetImage Method");
		Article article = null;
		String aKey = String.valueOf(id);
		try {
			if (null != articleCache.get(aKey)) {
				logger.debug(">> 从缓存中获取文章对象[" + id + "] ");
				article = articleCache.get(aKey);
			}
			if (null == article) {
				logger.debug(">> 从缓存中未获取文章对象，从数据库中获取文章对象[" + id + "] ");
				article = articleDao.findById(id);
				if (null != article)
					articleCache.put(aKey, article);
			}
			if (article != null) {
				if (!HttpClientUtils.validationURL(article.getArticleUrl())) {
					logger.error(">> getImage.地址不通["
							+ article.getArticleUrl() + "]");
					return;
				}
				//http://tuku.kaiyun.china.com/kaiyun/
				if ((article.getArticleUrl().startsWith(
						"http://tuku.kaiyun.china.com/kaiyun/")
						||article.getArticleUrl().startsWith(
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
								"http://tuku.tech.china.com/")
						|| article.getArticleUrl().startsWith("http://pic.sports.china.com/")
						|| article.getArticleUrl().startsWith("http://tuku.travel.china.com/")
						|| article.getArticleUrl().startsWith("http://tuku.auto.china.com/")
						|| article.getArticleUrl().startsWith("http://tuku.culture.china.com/"))) {
					if (article.getActicleXmlUrl() != null
							|| !article.getActicleXmlUrl().equalsIgnoreCase("")) {
						List<Image> imgList = xmlParser
								.readXmlFromURL(article.getActicleXmlUrl());
						for (Image bean : imgList) {
							if (!HttpClientUtils
									.validationURL(bean.getImgUrl())
									|| !HttpClientUtils.validationURL(bean
											.getHttpUrl())) {
								logger.error(">> 地址不通["
										+ bean.getHttpUrl() + "]");
								continue;
							}
							String length = HttpClientUtils
									.getHttpHeaderResponse(bean.getHttpUrl(),
											"Content-Length");
							logger.debug(">> Content-Length:" + length);
							if (null != length) {
								bean.setSize(Long.valueOf(length));
								bean.setStatus(1);
							}
							if (client.get(bean.getHttpUrl()) == null) {
								bean.setArticleId(article.getId());
								int result = imageDao.insert(bean);
								if (result == -1) {
									logger.debug("图片标题为："
											+ bean.getTitle() + ",未添加到数据库");
								} else {
									bean.setId(result);
									client.put(bean.getHttpUrl(), bean
											.getHttpUrl());
									imageCache.put(String.valueOf(result),
											bean);
//									if(imgDownload(bean, article.getWebId())){
//										logger.debug(">> 保存图片["+bean.getHttpUrl()+"]成功");
//									}
								}
							}
						}
						article.setText("FD");
						articleDao.update(article);
					}
				}
			} else {
				logger.debug("未查询到ARTICLE记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	boolean imgDownload(Image imgBean, Integer webId) {
		PictureFile bean = null;
		bean = new PictureFile();
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
				Integer b = pictureFileDao.insert(bean);
				if (b > 0) {
					pictureFileCache.put(CacheUtils.getBigPicFileKey(bean.getUrl()
							+ bean.getName()), bean);
					pictureFileCache.put(CacheUtils.getSmallPicFileKey(bean.getUrl()
							+ bean.getSmallName()), bean);
				} else {
					return false;
				}
			} catch (Exception e) {
				logger.debug("数据库异常");
				e.printStackTrace();
				return false;
			}
		} catch (IOException e) {
			logger.debug("网络连接，文件IO异常");
			return false;
		}
		return true;
	}
	
	public void init(){
		logger.debug(">> 开始解析CHINA图库网");
		StringBuffer esb  = new StringBuffer("Exception:\n");
		try {
			for (String url : CATALOG_URL) {
				try{
					getLink(url);
				}catch(Exception e){
					esb.append("URL["+url+"]:\n"+e.getMessage()+"\n");
					continue;
				}
				Iterator it = LINKHASH.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					logger.info("key:" + key);
					try {
						LinkBean bean = (LinkBean) LINKHASH.get(key);
						Article acticle = null;
						if (null != client.get(bean.getLink())) {
							logger.error("缓存中已存在相同地址[" + bean.getLink()
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
									"http://tuku.kaiyun.china.com/kaiyun/")
								    ||bean.getLink().startsWith(
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
									|| bean.getLink().startsWith("http://pic.sports.china.com/")
									|| bean.getLink().startsWith("http://tuku.travel.china.com/")
									|| bean.getLink().startsWith("http://tuku.auto.china.com/")
									|| bean.getLink().startsWith("http://tuku.culture.china.com/")) {
								String html = bean.getLink().substring(
										bean.getLink().lastIndexOf("/") + 1,
										bean.getLink().lastIndexOf("."));
								if (bean.getLink().indexOf("_") == -1) { // &&
																			// html.length()
																			// > 6
								// 老版本图片播放器解析方式
									String realUrl = htmlParser.getRealUrl(bean
											.getLink());
									logger.info("老版本图片播放器解析：" + realUrl);
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
								count ++;
								client.put(bean.getLink(), bean.getLink());
								acticle.setId(result);
								String aKey = CacheUtils.getArticleKey(result);
								if (null == articleCache.get(aKey)) {
									articleCache.put(aKey, acticle);
								}
								getImage(result);
								logger.info(">> 已处理的图片数量为:"+count);
							}
						} else {
							logger.error(">> getActicle 地址暂不可用 ["
									+ bean.getLink() + "]");
							continue;
						}
					} catch (Exception e) {
						// LINKLIST.add(bean);
						// break;
						esb.append("URL["+key+"]:\n"+e.getMessage()+"\n");
						logger.error("URL["+key+"]:\n"+e.getMessage()+"\n");
						continue;
					}
				}

				LINKHASH.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ChinaTUKUParser.Exception:"+e.getMessage()+"\n");
		}
		logger.debug("ERROR:\n"+esb.toString());
		}


	public String[] getCATALOG_URL() {
		return CATALOG_URL;
	}


	public void setCATALOG_URL(String[] catalog_url) {
		CATALOG_URL = catalog_url;
	}

	public void setCount(int count){
		this.count = count;
	}
	
	public int getCount(){
		return count;
	}
}
