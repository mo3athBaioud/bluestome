/**
 * �ļ�com.sky.spirit.basic.cache.interceptor.MethodCacheInterceptor.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.cache.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.sky.spirit.basic.cache.Cache;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����12:08:03
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {

	private static final Log logger = LogFactory
			.getLog(MethodCacheInterceptor.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,
				"A cache is required. Use setCache(Cache) to provide one.");
	}


	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result = null;

		logger.debug("looking for method result in cache");
		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Object element = cache.get(cacheKey);
		if (element == null) {
			logger.debug("calling intercepted method");
			result = invocation.proceed();
			logger.debug("caching result");
			element = result;
			cache.put(cacheKey, element);
		}
		return result;
	}

	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}

		return sb.toString();
	}

}
