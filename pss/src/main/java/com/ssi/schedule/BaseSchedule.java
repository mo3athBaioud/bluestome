package com.ssi.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IArticleDocDAO;
import com.ssi.common.dal.dao.IChartDAO;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.htmlparser.cache.ArticleCache;
import com.ssi.htmlparser.cache.ArticleDocCache;
import com.ssi.htmlparser.cache.CommonCache;
import com.ssi.htmlparser.cache.ImageCache;
import com.ssi.htmlparser.cache.PictureFileCache;

public abstract class BaseSchedule {

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
	protected IChartDAO chartDao;
	
	//TODO
	public abstract void process();

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

	public ArticleDocCache getArticleDocCache() {
		return articleDocCache;
	}

	public void setArticleDocCache(ArticleDocCache articleDocCache) {
		this.articleDocCache = articleDocCache;
	}

	public IArticleDocDAO getArticleDocDao() {
		return articleDocDao;
	}

	public void setArticleDocDao(IArticleDocDAO articleDocDao) {
		this.articleDocDao = articleDocDao;
	}

	public CommonCache getClient() {
		return client;
	}

	public void setClient(CommonCache client) {
		this.client = client;
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

	public IChartDAO getChartDao() {
		return chartDao;
	}

	public void setChartDao(IChartDAO chartDao) {
		this.chartDao = chartDao;
	}
	
	

}
