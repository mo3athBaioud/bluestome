package com.xian.support.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssi.common.utils.MD5;
import com.xian.support.cms.common.Constants;
import com.xian.support.entity.Staff;
import com.xian.support.service.StaffManager;

@Namespace("/admin")
@Action("login")
@Results( {
	@Result(name = "success", location = "/index.jsp"),
})
public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private String checkcode;
	
	@Autowired
	private StaffManager staffManager;
	
	/**
	 * 登录主要方法
	 *
	 */
	public void exec(){
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 logger.info(" > username:"+username);
			 logger.info(" > password:"+password);
			 out = getOut(response);
			 String code = (String)request.getSession().getAttribute("rand");
			 if(!code.equals(checkcode)){
				 out.println("{success:false,msg:'验证码错误!',errorType:4}"); 
			 }else{
				 Staff staff = staffManager.getStaffByUsername(username);
				 if(null != staff){
				 	String md5 = MD5.getInstance().getMD5ofStr(password);
				 	if(md5.equals(staff.getPassword())){
				 		//移除验证码SESSION
				 		request.getSession().removeAttribute("rand");
				 		//移除原有的用户会话
				 		request.getSession().removeAttribute(Constants.USER_SESSION);
				 		
				 		//TODO 设置SESSION属性
				 		 String token = UUID.randomUUID().toString();
				 		 request.getSession().setAttribute(Constants.USER_SESSION, staff);
				 		 logger.info(" > token:{}",token);
						 out.println("{success:true,msg:'登录成功!',token:'"+token+"'}"); 
				 	}else{
				 		//TODO 密码不正确
						 out.println("{success:false,msg:'密码不正确!',errorType:1}"); 
				 	}
				 }else{
					 //TODO 用户名不存在
					 out.println("{success:false,msg:'用户名不存在!',errorType:1}"); 
				 }
			 }
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 退出系统
	 * @throws IOException
	 */
	public void logout() throws IOException{
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			out = getOut(response);
			Object obj = request.getSession().getAttribute(Constants.USER_SESSION);
			if(null != obj){
				request.getSession().removeAttribute(Constants.USER_SESSION);
			}
			out.print("{success:true,msg:'退出成功',url:'"+request.getContextPath()+"/login3.jsp'}");
		}catch(Exception e){
			out.println("{success:false,msg:'退出系统出现异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
			
		}
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

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
}
