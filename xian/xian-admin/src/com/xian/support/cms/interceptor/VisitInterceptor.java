package com.xian.support.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xian.support.cms.common.Constants;

/**
 * 访问拦截
 * @author Bluestome.Zhang
 *
 */
public class VisitInterceptor extends AbstractInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(VisitInterceptor.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String LOGIN_PAGE = "/admin/login!logout.cgi";

	private String name;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext
		.get(StrutsStatics.HTTP_RESPONSE);
		String currentURL = request.getRequestURI(); 
		// 取得根目录所对应的绝对路径:
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1),
				currentURL.length()); // 截取到当前文件名用于比较
		logger.info(" > targetURL:" + targetURL);
		if (!"/login3.jsp".equals(targetURL) && !"/checkcode.cgi".equals(targetURL) && !"/admin/login!exec.cgi".equals(targetURL)) {
			//获取当前访问资源的扩展名
			if(currentURL.indexOf(".") != -1){
				String targetExt = currentURL.substring(currentURL.indexOf(".", 1),currentURL.length());
				//对扩展名进行判断
				if(targetExt.toLowerCase().endsWith(".cgi") || targetExt.toLowerCase().endsWith(".action")){
					// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
					HttpSession session = request.getSession();
					if (null == session || session.getAttribute(Constants.USER_SESSION) == null) {
						logger.debug("session is null,pls login!");
						response.setContentType("text/html");
						response.setCharacterEncoding("utf-8");
						// *用户登录以后需手动添加session
						response.getWriter().write(
								"<script>alert('您还没有登录,请登录!');parent.location.href='"
										+ request.getContextPath()
										+ "/login3.jsp'</script>");
						response.getWriter().flush();
						response.getWriter().close();
						// 如果session为空表示用户没有登录就重定向到login.jsp页面
						return null;
					}
				}
			}
		}
		return arg0.invoke();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
