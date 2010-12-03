package com.ssi.common.log;

import java.lang.reflect.Field;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * 日志切片
 * @author bluestome
 *
 */
public class LogMethodInterceptor implements MethodInterceptor {
	private static Logger logger = Logger.getLogger(LogMethodInterceptor.class);

	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 得到参数信息
		Object[] args = invocation.getArguments();
		
		Object param=null;
		if(args.length!=0){
			param=args[0];
		}

		// 得到方法 信息
		String info = invocation.getThis().getClass().getName() + "."
				+ invocation.getMethod().getName();
		
		long start = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			if(null == param){
				logger.info("Starting " + info);
			}else{
				logger.info("Starting " + info + " :"+param);
			}
		}
		
		Object obj = invocation.proceed();
		
		long end = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			if(null == param){
				logger.info("Ending speed times ["+(end-start)+"]" + info);
			}else{
				logger.info("Ending speed times ["+(end-start)+"]");
			}
		}
		return obj;
	}

}
