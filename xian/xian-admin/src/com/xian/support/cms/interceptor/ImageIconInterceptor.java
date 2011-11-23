package com.xian.support.cms.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 图片缩略图方法拦截
 * @author Bluestome.Zhang
 *
 */
public class ImageIconInterceptor extends MethodFilterInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(ImageIconInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Integer COUNT = 0;
	
	private String name;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		//TODO  可配置
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
			logger.info(" > "+name+"不允许查看图片缩略图");
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
