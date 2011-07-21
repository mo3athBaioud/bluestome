package com.ssi.cms.core.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.ssi.cms.core.annotation.MethodCache;
import com.ssi.cms.core.annotation.ObjectCache;
import com.ssi.common.utils.DateUtils;

/**
 * @author ahuaxuan(aaron zhang)
 * @since 2008-5-13
 * @version $Id: MethodCacheInterceptor.java 1879 2008-11-07 02:58:36Z aaron $
 */
@Component("methodCacheInterceptor")
public class MethodCacheInterceptor implements MethodInterceptor {

	private static transient Log logger = LogFactory.getLog(MethodCacheInterceptor.class);
	
	private static final String CACHENAME_SPLIT_CHAR = "#";
	
	private Cache methodCache;

	public void setMethodCache(Cache methodCache) {
		this.methodCache = methodCache;
	}

	/**
	 * Make sure that every bean which has been intercepted must has a interface,
	 * pls do not use CGLIB to PROXY class.
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		String targetName = invocation.getThis().getClass().getInterfaces()[0].getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Class[] cs = new Class[arguments.length];
		for (int k = 0; k < arguments.length; k++) {
			cs[k] = arguments[k].getClass();
		}
		
		if (invocation.getThis().getClass().getCanonicalName().contains("$Proxy")) {
			if (logger.isWarnEnabled()) {
				logger.warn("----- The object has been proxyed and method " +
						"cache interceptor can't get the target, " +
						"so the method result can't be cached which name is ------" + methodName);
			}
			
			return invocation.proceed();
		} else {
			if (invocation.getThis().getClass().isAnnotationPresent(ObjectCache.class)) {
				ObjectCache oc = invocation.getThis().getClass().getAnnotation(ObjectCache.class);
				return getResult(targetName, methodName, arguments, invocation, oc.expire());
			} else {
				
				Method[] mss = invocation.getThis().getClass().getMethods();
				Method ms = null;
				for (Method m : mss) {
					if (m.getName().equals(methodName)) {
						boolean argMatch = true;
						Class[] tmpCs = m.getParameterTypes();
						if (tmpCs.length != cs.length) {
							argMatch = false;
							continue;
						}
						for (int k = 0; k < cs.length; k++) {
							if (!cs[k].equals(tmpCs[k])) {
								argMatch = false;
								break;
							}
						}
						
						if (argMatch) {
							ms = m;
							break;
						}
					}
				}
				
				if (ms != null && ms.isAnnotationPresent(MethodCache.class)) {
					MethodCache mc = ms.getAnnotation(MethodCache.class);
					return getResult(targetName, methodName, arguments, invocation, mc.expire());
				} else {
					return invocation.proceed();
				}
			}
		}
	}
	
	private Object getResult(String targetName, String methodName, Object[] arguments,
			MethodInvocation invocation, int expire) throws Throwable {
		Object result;
		
		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = methodCache.get(cacheKey);
		if (element == null) {
			synchronized (this) {
				element = methodCache.get(cacheKey);
					if (element == null) {
					result = invocation.proceed();
	
					element = new Element(cacheKey, (Serializable) result);
					
					//annotation没有设expire值则使用ehcache.xml中自定义值
					if (expire > 0) {
						element.setTimeToIdle(expire);
						element.setTimeToLive(expire);
					}
					methodCache.put(element);
				}
			}
		}
		
		return element.getValue();
	}

	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
	 	StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(CACHENAME_SPLIT_CHAR).append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				if (arguments[i] instanceof Date) {
					sb.append(CACHENAME_SPLIT_CHAR).append(DateUtils.formatDate((Date) arguments[i]));
				} else {
					sb.append(CACHENAME_SPLIT_CHAR).append(arguments[i]);
				}
			}
		}

		return sb.toString();
	}

}
