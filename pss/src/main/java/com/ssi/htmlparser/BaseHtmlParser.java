package com.ssi.htmlparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IArticleDocDAO;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.PictureFile;
import com.ssi.common.dal.domain.Website;
import com.ssi.common.utils.HttpClientUtils;
import com.ssi.common.utils.StringUtils;
import com.ssi.htmlparser.cache.ArticleCache;
import com.ssi.htmlparser.cache.ArticleDocCache;
import com.ssi.htmlparser.cache.CommonCache;
import com.ssi.htmlparser.cache.ImageCache;
import com.ssi.htmlparser.cache.PictureFileCache;
import com.ssi.htmlparser.chinamilitary.HTMLParser;
import com.ssi.htmlparser.chinamilitary.XMLParser;
import com.ssi.htmlparser.utils.CacheUtils;
import com.ssi.htmlparser.utils.CommonUtil;
import com.ssi.htmlparser.utils.IOUtil;

public abstract class BaseHtmlParser {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	//TODO Configure in spring.xml
	protected CommonCache client;
	
	//TODO Configure in spring.xml
	protected ArticleDocCache articleDocCache;
	
	//TODO Configure in spring.xml
	protected ArticleCache articleCache;
	
	//TODO Configure in spring.xml
	protected ImageCache imageCache;
	
	//TODO Configure in spring.xml
	protected PictureFileCache pictureFileCache;
	
	protected IArticleDocDAO articleDocDao;
	
	//TODO Configure in spring.xml
	protected IArticleDAO articleDao;
	
	//TODO Configure in spring.xml
	protected IImageDAO imageDao;
	
	//TODO Configure in spring.xml
	protected IPictureFileDAO pictureFileDao;
	
	//TODO Configure in spring.xml
	protected IWebsiteDAO websiteDao;
	
	//TODO Configure in spring.xml
	protected String PIC_SAVE_PATH = "/";
	
	//TODO Configure in spring.xml
	protected XMLParser xmlParser;
	
	//TODO Configure in spring.xml
	protected HTMLParser htmlParser;

	protected Integer downloadCount = 30;
	
	public ArticleCache getArticleCache() {
		return articleCache;
	}

	public void setArticleCache(ArticleCache articleCache) {
		this.articleCache = articleCache;
	}

	public IArticleDAO getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(IArticleDAO articleDao) {
		this.articleDao = articleDao;
	}

	public CommonCache getClient() {
		return client;
	}

	public void setClient(CommonCache client) {
		this.client = client;
	}

	public HTMLParser getHtmlParser() {
		return htmlParser;
	}

	public void setHtmlParser(HTMLParser htmlParser) {
		this.htmlParser = htmlParser;
	}

	public ImageCache getImageCache() {
		return imageCache;
	}

	public void setImageCache(ImageCache imageCache) {
		this.imageCache = imageCache;
	}

	public IImageDAO getImageDao() {
		return imageDao;
	}

	public void setImageDao(IImageDAO imageDao) {
		this.imageDao = imageDao;
	}

	public String getPIC_SAVE_PATH() {
		return PIC_SAVE_PATH;
	}

	public void setPIC_SAVE_PATH(String pic_save_path) {
		PIC_SAVE_PATH = pic_save_path;
	}

	public PictureFileCache getPictureFileCache() {
		return pictureFileCache;
	}

	public void setPictureFileCache(PictureFileCache pictureFileCache) {
		this.pictureFileCache = pictureFileCache;
	}

	public IPictureFileDAO getPictureFileDao() {
		return pictureFileDao;
	}

	public void setPictureFileDao(IPictureFileDAO pictureFileDao) {
		this.pictureFileDao = pictureFileDao;
	}

	public IWebsiteDAO getWebsiteDao() {
		return websiteDao;
	}

	public void setWebsiteDao(IWebsiteDAO websiteDao) {
		this.websiteDao = websiteDao;
	}

	public XMLParser getXmlParser() {
		return xmlParser;
	}

	public void setXmlParser(XMLParser xmlParser) {
		this.xmlParser = xmlParser;
	}

	public IArticleDocDAO getArticleDocDao() {
		return articleDocDao;
	}

	public void setArticleDocDao(IArticleDocDAO articleDocDao) {
		this.articleDocDao = articleDocDao;
	}

	public ArticleDocCache getArticleDocCache() {
		return articleDocCache;
	}

	public void setArticleDocCache(ArticleDocCache articleDocCache) {
		this.articleDocCache = articleDocCache;
	}

	
	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	/**
	 * 初始化方法
	 *
	 */
	public abstract void init();
	
	/**
	 * 图片下载补丁
	 * @param bean
	 */
	public synchronized void patchImag() {
		int count = 0;
		try {
				HashMap imgMap = new HashMap();
				imgMap.put("link", "NED");
//				imgMap.put("status", -1);
				imgMap.put("limit", downloadCount);
				List<Image> imgList = imageDao.find(imgMap);
				logger.info(">> download imageList.size:"+imgList.size());
				for (Image img : imgList) {
					logger.info(">> image.articleid:"+img.getArticleId()+"\t image.imageid:"+img.getId());
					try{
						if(imgDownload(img)){
							img.setLink("FD");
							img.setStatus(1);
							count ++;
						}else{
							img.setLink("ENED");
							img.setStatus(-1); //未添加成功
						}
					}catch(Exception e){
						img.setLink("ENED");
						img.setStatus(-2); //异常情况下的状态代码
						logger.error("Exception:"+e);
					}finally{
						logger.debug(">> download success records ["+count+"]");
						if(imageDao.update(img) > 0){
							imageCache.remove(CacheUtils
									.getImageKey(img.getId()));
							logger.info(">> download image and update image success");
						}
					}
				}
				count = 0;
				imgMap.clear();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">> error patch img");
		}
	}
	
	/**
	 * 图片下载补丁
	 * @param bean
	 */
	public void patchENEDImag() {
		int count = 0;
		try {
				HashMap imgMap = new HashMap();
				imgMap.put("link", "ENED");
//				imgMap.put("status", -1);
				imgMap.put("limit", downloadCount);
				List<Image> imgList = imageDao.find(imgMap);
				logger.info(">> download imageList.size:"+imgList.size());
				for (Image img : imgList) {
					logger.info(">> image.articleid:"+img.getArticleId()+"\t image.imageid:"+img.getId());
					try{
						if(imgDownload(img)){
							img.setLink("FD");
							img.setStatus(1);
							count ++;
						}else{
							img.setLink("ENED");
							img.setStatus(-1); //未添加成功
						}
					}catch(Exception e){
						img.setLink("ENED");
						img.setStatus(-2); //异常情况下的状态代码
						logger.error("Exception:"+e);
					}finally{
						logger.debug(">> download success records ["+count+"]");
						if(imageDao.update(img) > 0){
							imageCache.remove(CacheUtils
									.getImageKey(img.getId()));
							logger.info(">> download image and update image success");
						}
					}
				}
				count = 0;
				imgMap.clear();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">> error patch img");
		}
	}
	
	/**
	 * 图片下载方法
	 * @param img
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean imgDownload(Image img) throws InterruptedException {
		PictureFile bean = null;
		bean = new PictureFile();
		String s_fileName = img.getImgUrl().substring(
				img.getImgUrl().lastIndexOf("/") + 1, img.getImgUrl().length());
		String fileName = img.getHttpUrl().substring(
				img.getHttpUrl().lastIndexOf("/") + 1,
				img.getHttpUrl().length());
		if(s_fileName.equalsIgnoreCase(fileName)){
			logger.info(">> 文件名相同，将修改小图标文件名");
			s_fileName = s_fileName.replace(".", "_s.");
		}
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
			// 小图下载
			if (pictureFileCache.get(CacheUtils.getShowImgKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + s_fileName)) == null) {
				IOUtil.createPicFile(img.getImgUrl(), PIC_SAVE_PATH
						+ StringUtils
								.gerDir(String.valueOf(img.getArticleId()))
						+ img.getArticleId() + File.separator + s_fileName);
			}

			// 大图下载
			if (pictureFileCache.get(CacheUtils.getBigPicFileKey(PIC_SAVE_PATH
					+ StringUtils.gerDir(String.valueOf(img.getArticleId()))
					+ img.getArticleId() + File.separator + fileName)) == null) {
				Thread.sleep(1000);
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
				Integer b = pictureFileDao.insert(bean);
				if (b > 0) {
					pictureFileCache.put(CacheUtils.getBigPicFileKey(bean
							.getUrl()
							+ bean.getName().replaceAll(" ", "%20")), bean);
					pictureFileCache.put(CacheUtils.getSmallPicFileKey(bean
							.getUrl()
							+ bean.getSmallName().replaceAll(" ", "%20")), bean);
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				logger.error("数据库异常:"+e.getMessage());
				return false;
			}
		} catch (IOException e) {
			logger.error("网络连接，文件IO异常");
			return false;
		}
	}
	
}
