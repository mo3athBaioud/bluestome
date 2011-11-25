package com.xian.support.cms.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xian.support.cms.common.Constants;

public class SessionFilter implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	String[] limitUrls = new String[] { "/login3.jsp","/checkcode.cgi"};
	
	List<String> extsList = new ArrayList<String>(); 
	
	FilterConfig config;

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		/**
		 * 1,doFilter方法的第一个参数为ServletRequest对象。此对象给过滤器提供了对进入的信息（包括
		 * 表单数据、cookie和HTTP请求头）的完全访问。第二个参数为ServletResponse，通常在简单的过
		 * 滤器中忽略此参数。最后一个参数为FilterChain，此参数用来调用servlet或JSP页。
		 */

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		/**
		 * 如果处理HTTP请求，并且需要访问诸如getHeader或getCookies等在ServletRequest中
		 * 无法得到的方法，就要把此request对象构造成HttpServletRequest
		 */
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getRequestURI(); 
		// 取得根目录所对应的绝对路径:
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1),
				currentURL.length()); // 截取到当前文件名用于比较
		logger.info(" > targetURL:" + targetURL);
		if (!"/login3.jsp".equals(targetURL) && !"/checkcode.cgi".equals(targetURL)) {
			//获取当前访问资源的扩展名
			if(currentURL.indexOf(".") != -1){
				String targetExt = currentURL.substring(currentURL.indexOf(".", 1),currentURL.length());
				// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
				if (request.getSession().getAttribute(Constants.USER_SESSION) == null) {
					logger.debug("session is null,pls login!");
					// *用户登录以后需手动添加session
					response.getWriter().write(
							"<script>parent.location.href='"
									+ request.getContextPath()
									+ "/login3.jsp'</script>");
					response.getWriter().flush();
					response.getWriter().close();
					// 如果session为空表示用户没有登录就重定向到login.jsp页面
					return;
				}
			logger.info(" > targetExt:" + targetExt);
			}
		}
		// 加入filter链继续向下执行
		filterChain.doFilter(request, response);
		/**
		 * 调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作 为它
		 * 的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另
		 * 一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
		 */
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String headersStr = config.getInitParameter("exts");
		String[] headers = headersStr.split(",");
		for (int i = 0; i < headers.length; i++) {
			String temp = headers[i];
			extsList.add(temp);
		}
	}
}
