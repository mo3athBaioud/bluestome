package com.taksoon.bluestome.test.dao;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.service.ArticleDocManager;
import com.takesoon.oms.ssi.service.ArticleManager;
import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.oms.ssi.service.PictureFileManager;
import com.takesoon.oms.ssi.service.SystemConfigManager;
import com.takesoon.oms.ssi.service.WebsiteManager;

public class AbstractTestCase {
	
	protected ArticleDocManager articleDocManager;
	protected ArticleManager articleManager;
	protected ImageManager imageManager;
	protected PictureFileManager pictureFileManager;
	protected SystemConfigManager systemConfigManager;
	protected WebsiteManager websiteManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		articleDocManager = (ArticleDocManager)context.getBean("articleDocManager");
		articleManager = (ArticleManager)context.getBean("articleManager");
		imageManager = (ImageManager)context.getBean("imageManager");
		pictureFileManager = (PictureFileManager)context.getBean("pictureFileManager");
		systemConfigManager = (SystemConfigManager)context.getBean("systemConfigManager");
		websiteManager = (WebsiteManager)context.getBean("websiteManager");
	}
	
	@After
	public void destory(){
		if(null != articleDocManager)
			articleDocManager = null;
		if(null != articleManager)
			articleManager = null;
		if(null != imageManager)
			imageManager = null;
		if(null != pictureFileManager)
			pictureFileManager = null;
		if(null != systemConfigManager)
			systemConfigManager = null;
		if(null != websiteManager)
			websiteManager = null;
	}

}
