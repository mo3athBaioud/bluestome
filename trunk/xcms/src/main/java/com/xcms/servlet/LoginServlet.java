package com.xcms.servlet;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	static String[][] USERS = {{"liyang","liyang123"},{"zhangxiao","zhangxiao123"},{"weinan","weinan123"}};
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
				logger.info(" User["+USERS[0][0]+"] login at "+getStringDate());
				response.getWriter().print("{success:true,msg:'登录成功',url:'"+request.getContextPath()+"index.html'}");
				return;
			}else{
				response.getWriter().print("{failure:true,errorType:'1',msg:'登录失败，["+USERS[0][0]+"]密码不正确'}");
				return;
			}
		}
		
		if(name.equals(USERS[1][0])){
			if(password.equals(USERS[1][1]) ){
				request.getSession().setAttribute("LOGIN_SESSION_NAME", USERS[1][0]);
				logger.info(" User["+USERS[1][0]+"] login at "+getStringDate());
				response.getWriter().print("{success:true,msg:'登录成功',url:'"+request.getContextPath()+"/index.html'}");
				return;
			}else{
				response.getWriter().print("{failure:true,errorType:'1',msg:'登录失败，["+USERS[1][0]+"]密码不正确'}");
				return;
			}
		}
		
		if(name.equals(USERS[2][0])){
			if(password.equals(USERS[2][1]) ){
				request.getSession().setAttribute("LOGIN_SESSION_NAME", USERS[2][0]);
				logger.info(" User["+USERS[2][0]+"] login at "+getStringDate());
				response.getWriter().print("{success:true,msg:'登录成功',url:'"+request.getContextPath()+"/weinan.html'}");
				return;
			}else{
				response.getWriter().print("{failure:true,errorType:'1',msg:'登录失败，["+USERS[2][0]+"]密码不正确'}");
				return;
			}
		}
		
		response.getWriter().print("{failure:true,errorType:'2',msg:'登录失败,无此用户["+name+"]'}");
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
	
	/**
	 * 获取现在时间
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

}
