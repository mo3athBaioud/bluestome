package com.ssi.common.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * @ClassName: LogUtilAOP  
 * @author : bluestome.zhang@sky-mobi.com
 * @date   : 2010-7-3  
 * @version: 1.0
 */
public class LogUtilAOP {
	private static Logger logger = Logger.getLogger(LogUtilAOP.class);
 
	


	@SuppressWarnings("unused")
	private void logging(JoinPoint joinPoint) {
		// 得到参数信息
		Object[] args = joinPoint.getArgs();
        
		Object param=null;
		if(args.length!=0){
			param=args[0];
		}

		// 得到方法 信息
		String info = joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName();

		if (logger.isDebugEnabled()) {
			logger.debug("Starting " + info + " :"+param);
		}
	}
	

}