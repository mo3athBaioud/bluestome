package com.ssi.cms.core.aop.advice;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 前通知返回
 * 
 * @author bluestome
 * 
 */
public class CacheMethodAroundAdvice implements MethodInterceptor,
		MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	Logger logger = LoggerFactory.getLogger(CacheMethodAroundAdvice.class);

	private long beforeRunTime;

	private long afterRunTime;

	private static final String CACHENAME_SPLIT_CHAR = "#";

	static Map<String, Object> map = new HashMap<String, Object>();

	private Cache cache;

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// 当前时间
		beforeRunTime = System.currentTimeMillis();

	}

	public void afterReturning(Object arg0, Method method, Object[] args,
			Object target) throws Throwable {
		// 记录当前时间
		afterRunTime = System.currentTimeMillis();
		// 取得该方法运行所消耗的时间
		long durationTimes = afterRunTime - beforeRunTime;
		
		List list = cache.getKeys();
		logger.info("cache.keys.size:"+list.size());
		/**
		String className = target.getClass().getName();
		for (int i = 0; i < list.size(); i++) {
			String cacheKey = String.valueOf(list.get(i));
			if (cacheKey.startsWith(className)) {
				cache.remove(cacheKey);
				logger.debug("remove cache " + cacheKey);
			}
		}
		**/
		logger.info("running and spendtimes is " + durationTimes);
	}

	// 抛出Exception之后被调用
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		String clazzName = target.getClass().getName();
		String methodName = method.getName();
		String exceptionClazz = ex.getClass().getName();
		String exceptionMessage = ex.getMessage();
		logger.error(clazzName + CACHENAME_SPLIT_CHAR + methodName
				+ " have a error cause by:  \n" + exceptionClazz + ", "
				+ exceptionMessage);
	}

	// 缓存相关数据
	public Object invoke(MethodInvocation arg0) throws Throwable {
		String targetName = arg0.getThis().getClass().getName();
		String methodName = arg0.getMethod().getName();
		Object[] arguments = arg0.getArguments();
		Object result;
		String key = getCacheKey(targetName, methodName, arguments);
		logger.info(" >> cache.key:" + key);
		Element element = cache.get(key);
		if (null == element) {
			result = arg0.proceed();
			element = new Element(key, (Serializable) result);
			cache.put(element);
		}
		if (null != key) {
			key = null;
		}
		return element.getValue();
	}

	/**
	 * 组装缓存KEY
	 * 
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 * @return
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(CACHENAME_SPLIT_CHAR).append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(CACHENAME_SPLIT_CHAR).append(arguments[i]);
			}
		}
		return sb.toString();
	}
}
