package com.xcms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	static String[][] USERS = {{"liyang","liyang123"},{"zhangxiao","zhangxiao123"}};
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("username") == null ? "admin" : request.getParameter("username");
		String password = request.getParameter("password") == null ? "admin" :request.getParameter("password");
		if(name.equals(USERS[0][0]) ){
			if(password.equals(USERS[0][1])){
				request.getSession().setAttribute("LOGIN_SESSION_NAME", USERS[0][0]);
				response.getWriter().print("{success:true,msg:'登录成功',url:'"+request.getContextPath()+"index.html'}");
				return;
			}else{
				response.getWriter().print("{failure:true,msg:'登录失败，["+USERS[0][0]+"]密码不正确'}");
				return;
			}
		}
		
		if(name.equals(USERS[1][0])){
			if(password.equals(USERS[1][1]) ){
				request.getSession().setAttribute("LOGIN_SESSION_NAME", USERS[1][0]);
				response.getWriter().print("{success:true,msg:'登录成功',url:'"+request.getContextPath()+"/index.html'}");
				return;
			}else{
				response.getWriter().print("{failure:true,msg:'登录失败，["+USERS[1][0]+"]密码不正确'}");
				return;
			}
		}
		
		response.getWriter().print("{failure:true,msg:'登录失败,无此用户["+name+"]'}");
		return;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
