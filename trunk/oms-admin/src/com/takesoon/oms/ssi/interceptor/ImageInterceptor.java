package com.takesoon.oms.ssi.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 图片下载方法拦截
 * @author Bluestome.Zhang
 *
 */
public class ImageInterceptor extends MethodFilterInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(ImageInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	static Integer COUNT = 0;

	@Override
	public String doIntercept(ActionInvocation arg0) throws Exception {
		if(true){
			ActionContext actionContext = arg0.getInvocationContext();
			HttpServletRequest request = (HttpServletRequest) actionContext
					.get(StrutsStatics.HTTP_REQUEST);
			COUNT++;
			StringBuffer sb = new StringBuffer();
			sb.append(name).append("|");
			sb.append(request.getRemoteAddr()).append("|").append(arg0.getAction().getClass().getName()).append("|").append(request.getRequestURI()).append("|");
			sb.append(COUNT);
			logger.info(" > "+sb.toString());
			if(sb != null)
				sb = null;
			return arg0.invoke();
		}else{
			//TODO 需要返回默认不允许查看的图片
			logger.info(" > "+name+"不允许查看大图");
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
