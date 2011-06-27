package com.xcms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginFilter implements Filter {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	FilterConfig config;

	String[] limitUrls = new String[] { "/login.html","/login2.html","/servlet/LoginServlet","/servlet/LogoutServlet"};

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		logger.info(" >> Client:"+request.getRemoteAddr()+":"+request.getRemotePort());
		String back = "login2.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(back);
		try {
			String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:
			String targetURL = currentURL.substring(currentURL.lastIndexOf("/",
					1), currentURL.length()); // 截取到当前文件名用于比较
			if (session.getAttribute("LOGIN_SESSION_NAME") != null) {
				String name = (String)request.getSession().getAttribute("LOGIN_SESSION_NAME");
				if(null != name){
					logger.info(" >> User["+name+"].Visit.URL:"+request.getRequestURL());
				}
				chain.doFilter(request, response);
			} else {
				if (!checkUrl(targetURL)) {
					logger.warn(" >> No Login ,return to login page");
//					dispatcher.forward(request, response);
					chain.doFilter(request, response);
				} else {
					logger.info(" >> Client.RequestURL:"+request.getRequestURL());
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			dispatcher.forward(request, response);
		}
	}

	/**
	 * 比较请求的URL是否为可以访问的URL
	 * 
	 * @param targetURL
	 *            目标URL
	 * @return
	 */
	boolean checkUrl(String targetURL) {
		boolean b = false;
		for (int i = 0; i < limitUrls.length; i++) {
			if (limitUrls[i].equals(targetURL)) {
				b = true;
				break;
			}
		}
		return b;
	}

	public void destroy() {
	}
}
