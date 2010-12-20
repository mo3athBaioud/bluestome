package com.sky.commons.spring.factory;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
/*
 * bean����ȡ����
 */
public class SkyBeanFactory {

	private static final Logger logger = Logger.getLogger(SkyBeanFactory.class);


	/**
	 * @param path:�����ļ���·��
	 * @parem beanId:bean����
	 * @return bean ʵ��
	 * desc������beanidȡ��bean
	 */
	public static Object getBean(String path,String beanID){
		if(StringUtils.isEmpty(path) || StringUtils.isEmpty(beanID)){
			logger.error("bad path parameter or beanId parameter");
		}
		ApplicationContext ctx = ApplicationContextFactory.getApplicationContextByClassPath(path);
		return ctx.getBean(beanID);
	}
	
	/*
	 * ����beanidȡ��bean
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

