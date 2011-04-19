package com.xcms.filter;

import java.io.IOException;
import java.util.Enumeration;

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

public class LoginFilter implements Filter {
	FilterConfig config;

	String[] limitUrls = new String[] { "login.html","/servlet/LoginServlet","/servlet/LogoutServlet"};

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
		System.out.println(" >> Client:"+request.getRemoteAddr()+":"+request.getRemotePort()+"|"+request.getRequestURL());
		String back = "login.html";
		RequestDispatcher dispatcher = request.getRequestDispatcher(back);
		try {
			String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:
			String targetURL = currentURL.substring(currentURL.lastIndexOf("/",
					1), currentURL.length()); // 截取到当前文件名用于比较
			if (session.getAttribute("LOGIN_SESSION_NAME") != null) {
				chain.doFilter(request, response);
			} else {
				if (!checkUrl(targetURL)) {
					dispatcher.forward(request, response);
				} else {
					System.out.println("other URL:");
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
