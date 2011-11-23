package com.xian.support.cms.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 用户拦截用户操作相关的方法
 * @author Bluestome.Zhang
 *
 */
public class ActionInterceptor extends AbstractInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(ActionInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		StringBuffer sb = new StringBuffer();
		sb.append(name).append("|");
		sb.append(request.getRemoteAddr()).append("|").append(request.getRequestURI());
		logger.info(request.getQueryString());
		if(null == request.getQueryString()){
			sb.append("|").append("无条件查询");
		}
		logger.info(" > "+sb.toString());
		if(sb != null)
			sb = null;
		return arg0.invoke();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
