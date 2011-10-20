package com.takesoon.oms.ssi.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddActionLog {
	
	private static Logger logger = LoggerFactory.getLogger(AddActionLog.class);

	public void before(JoinPoint jp){
//		Object[] objs = jp.getArgs();
		StringBuffer sb = new StringBuffer("do before");
//		for(Object obj:objs){
//			sb.append(obj).append("_");
//		}
		logger.info("before:"+sb.toString());
	}
	
	public void after(JoinPoint jp){
//		Object[] objs = jp.getArgs();
		StringBuffer sb = new StringBuffer("do after");
//		for(Object obj:objs){
//			sb.append(obj).append("_");
//		}
		logger.info("after:"+sb.toString());
	}
}
