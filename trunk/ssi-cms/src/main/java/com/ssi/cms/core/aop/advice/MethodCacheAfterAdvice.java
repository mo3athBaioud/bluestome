package com.ssi.cms.core.aop.advice;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 后通知
 * @author bluestome
 *
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean
{
	private static final Log logger = LogFactory.getLog(MethodCacheAfterAdvice.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public MethodCacheAfterAdvice() {
		super();
	}

	/**
	 * 清理缓存,根据缓存名称
	 */
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		String className = arg3.getClass().getName();
		List list = cache.getKeys();
		logger.debug(" >> cache.key.size:"+list.size());
		for(int i = 0;i<list.size();i++){
			String cacheKey = String.valueOf(list.get(i));
			if(cacheKey.startsWith(className)){
				cache.remove(cacheKey);
				logger.debug("remove cache " + cacheKey);
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");
	}

}


