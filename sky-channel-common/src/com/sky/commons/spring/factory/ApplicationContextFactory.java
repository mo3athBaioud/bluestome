package com.sky.commons.spring.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SuppressWarnings("unchecked")
public class ApplicationContextFactory {

//	private static final Logger logger = Logger.getLogger(ApplicationContextFactory.class);
	
	public static Map contextMap = new ConcurrentHashMap();
	
	/*
	 * 根据文件路径取得bean工厂并缓存
	 */
	public static ApplicationContext getApplicationContext(String path){
		if(StringUtils.isEmpty(path)){
			throw new IllegalArgumentException("application context path is not configured correctly");
		}
		if(contextMap.get(path) != null){
			return (ApplicationContext)contextMap.get(path);
		}
		ApplicationContext ctx = new FileSystemXmlApplicationContext("file:" + path);
		contextMap.put(path, ctx);
		return ctx;
	}

	/*
	 * 根据类路径取得bean工厂并缓存
	 */
	public static ApplicationContext getApplicationContextByClassPath(String path){
		if(StringUtils.isEmpty(path)){
			throw new IllegalArgumentException("application context path is not configured correctly");
		}
		if(contextMap.get(path) != null){
			return (ApplicationContext)contextMap.get(path);
		}
		ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
		contextMap.put(path, ctx);
		return ctx;
	}
}
