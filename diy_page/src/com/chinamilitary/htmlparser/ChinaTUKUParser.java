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
	
//	final static String FILE_SERVER = "O:\\fileserver\\image\\";
	
	final static String FILE_SERVER = "D:\\fileserver\\image\\";
	
	static int D_PARENT_ID = 301;

	// "http://tuku.tech.china.com/tech",
	static final String[] CATALOG_URL = {
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
			"http://tuku.auto.china.com/auto/",
			"http://tuku.culture.china.com/culture/"
		};

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();

	static HashMap<String, LinkBean> LINKHASH = new HashMap<String, LinkBean>();

	static final String PIC_SAVE_PATH = Constants.FILE_SERVER;

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
				System.out.println("Link:"+link.getLink());
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
				if (!HttpClientUtils.validationURL(article.getArticleUrl())) {
					System.err.println(">> getImage.地址不通["
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
											+ bean.getTitle() + ",地址:["+bean.getHttpUrl()+"],未添加到数据库");
								} else {
									bean.setId(result);
									client.add(bean.getHttpUrl(), bean
											.getHttpUrl());
									client.add(CacheUtils.getImageKey(result),
											bean);
								}
								if(imgDownload(bean, article.getWebId())){
									isDownload = true;
									bean.setLink("FD");
									if(imageDao.update(bean)){
										System.out.println(">> 保存图片["+bean.getHttpUrl()+"]成功");
									}
								}else{
									continue;
								}
							}
						}
						if(isDownload){
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
			big = HttpClientUtils.getResponseBodyAsByte(imgBean.getCommentshowurl(), null, imgBean.getHttpUrl());
			if(null == big)
				return false;
			length = String.valueOf(big.length);
			if(length.equalsIgnoreCase("20261") || length.equalsIgnoreCase("3267") || length.equalsIgnoreCase("4096")){
				System.out.println(">>> 尝试使用Referer来获取图片");
				big = HttpClientUtils.getResponseBodyAsByte(imgBean.getReferer(), null, imgBean.getHttpUrl());
				length = String.valueOf(big.length);
				if(length.equalsIgnoreCase("20261") || length.equalsIgnoreCase("3267") || length.equalsIgnoreCase("4096")){
					System.err.println("下载被屏蔽，未突破盗链系统...");
					return false;
				}
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
				if(null != bean.getImageId()){
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
			patch();
			update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void update() throws Exception{
		StringBuffer esb  = new StringBuffer("Exception:\n");
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
								|| bean.getLink().startsWith("http://tuku.auto.china.com/auto/html/")
								|| bean.getLink().startsWith("http://tuku.culture.china.com/")) {
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
					// break;
					esb.append("URL["+key+"]:\n"+e.getMessage()+"\n");
					continue;
				}
			}

			LINKHASH.clear();
		}
			
	}

	/**
	 * 更新记录
	 */
	public static void patch() throws Exception{
		List<Article> list = articleDao.findByWebId(301,"NED");
		if(null != list || list.size() > 0){
			for(Article article:list){
				try{
					getImage(article.getId());
					article.setText("FD");
					if(articleDao.update(article)){
						System.out.println(">> 更新文章成功["+article.getId()+"]");
						String aKey = CacheUtils.getArticleKey(article.getId());
						if (null == client.get(aKey)) {
							client.add(aKey, article);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					break;
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
	
	static void movefile() throws Exception{
		List<WebsiteBean> webList = webSiteDao.findByParentId(D_PARENT_ID);
		PicfileBean bean = null;
		for(WebsiteBean website:webList){
			System.out.println(website.getId()+"|"+website.getName()+"|"+website.getUrl());
			List<Article> artList = articleDao.findByWebId(website.getId(), "FD");
			System.out.println("文章数量:"+artList.size());
			for(Article article:artList){
				List<ImageBean> list = imageDao.findImage(article.getId());
				for(ImageBean img:list){
					bean = picFiledao.findByImgIdAndArticleId(img.getId(), article.getId());
					if(null != bean){
						if(moveFile(bean)){
							System.out.println(bean.getId()+"|"+bean.getArticleId()+"|"+bean.getImageId());
							System.out.println("after move file name:"+bean.getName());
							System.out.println("after move file smallName:"+bean.getSmallName());
							System.out.println("-------------------------------------------------------------");
						}
					}
//					break;
				}
//				break;
			}
//			break;
		}
	}
	
	static boolean moveFile(PicfileBean bean) {
		String tmpUrl = "P:\\share\\file\\zol\\";
		boolean isBig = false;
		boolean isSmall = false;
		int start = bean.getName().lastIndexOf(File.separator)+1;
		int smnStart = bean.getSmallName().lastIndexOf(File.separator)+1;
		String prx = StringUtils.gerDir(String.valueOf(bean.getArticleId()));
		String fileName = prx+bean.getArticleId()+File.separator+bean.getName().substring(start);
		String smallFileName = prx+bean.getArticleId()+File.separator+bean.getSmallName().substring(smnStart);
		String source = tmpUrl + bean.getName();
		String smgSource = tmpUrl + bean.getSmallName();
		String target = FILE_SERVER+fileName;
		String smgTarget = FILE_SERVER + smallFileName;
		bean.setUrl(FILE_SERVER);
		if(FileUtils.copyFile(source, target)){
			System.out.println(">> 大图成功!!!");
			if(FileUtils.deleteFile(source)){
				System.out.println(">> 删除源大图["+source+"]成功");
			}
			bean.setName(fileName);
			isBig = true;
		}
		
		if(FileUtils.copyFile(smgSource, smgTarget)){
			System.out.println(">> 小图成功!!!");
			if(FileUtils.deleteFile(smgSource)){
				System.out.println(">> 删除源小图["+smgSource+"]成功");
			}
			bean.setSmallName(smallFileName);
			isSmall = true;
		}
		if(isBig){
			if(isBig || isSmall){
				try{
					if(picFiledao.update(bean)){
						System.out.println(">> 更新图片文件["+bean.getId()+"]记录成功!");
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return isBig;
	}
}
