package com.ssi.cms.web.action;

import java.io.IOException;

import com.ssi.common.Constants;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5619515921066981143L;
	
	private String username;
	
	private String password;
	
	/**
	 * 登陆方法
	 * @return
	 * @throws IOException 
	 */
	public void login() throws IOException{
		response.setCharacterEncoding("UTF-8");
		if(null != username){
			if(null != password){
				if("bluestome".equals(username)){
					if("qaz123654".equals(password)){
						request.getSession().setAttribute(Constants.USERSESSION, "bluestome");
						response.getWriter().print("{success:true,msg:'登录成功!',url:'"+request.getContextPath()+"/index.jsp'}");
					}else{
						response.getWriter().print("{failure:true,msg:'密码不正确'}");
					}
				}else{
					response.getWriter().print("{failure:true,msg:'不存在该用户["+username+"]!'}");
				}
			}else{
				response.getWriter().print("{failure:true,msg:'请输入密码!'}");
			}
		}else{
			response.getWriter().print("{failure:true,msg:'请输入用户名!'}");
		}
		return;
	}
	
	/**
	 * 退出系统
	 * @throws IOException
	 */
	public void logout() throws IOException{
		response.setCharacterEncoding("UTF-8");
		Object obj = request.getSession().getAttribute(Constants.USERSESSION);
		if(null != obj){
			request.getSession().removeAttribute(Constants.USERSESSION);
		}
		response.getWriter().print("{success:true,msg:'退出成功',url:'"+request.getContextPath()+"/login3.jsp'}");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
