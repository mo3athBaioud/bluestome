package com.sky.commons.spring.factory;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
/*
 * bean对象取得类
 */
public class SkyBeanFactory {

	private static final Logger logger = Logger.getLogger(SkyBeanFactory.class);


	/**
	 * @param path:配置文件类路径
	 * @parem beanId:bean名称
	 * @return bean 实例
	 * desc：根据beanid取得bean
	 */
	public static Object getBean(String path,String beanID){
		if(StringUtils.isEmpty(path) || StringUtils.isEmpty(beanID)){
			logger.error("bad path parameter or beanId parameter");
		}
		ApplicationContext ctx = ApplicationContextFactory.getApplicationContextByClassPath(path);
		return ctx.getBean(beanID);
	}
	
	/*
	 * 根据beanid取得bean
	 */
	@SuppressWarnings("unchecked")
	public static Object getBean(String beanID){
		if(StringUtils.isEmpty(beanID)){
			logger.error("bad beanId parameter");
		}		
		Iterator iter = ApplicationContextFactory.contextMap.keySet().iterator();
		while(iter.hasNext()){
			String path = (String)iter.next();
			ApplicationContext ctx = ApplicationContextFactory.getApplicationContext(path);
			if(ctx.getBean(beanID) != null)
				return ctx.getBean(beanID);
		}
		return null;
	}

}

