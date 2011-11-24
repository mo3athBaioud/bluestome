package com.xian.support.web;

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
			 Staff staff = staffManager.getStaffByUsername(username);
			 if(null != staff){
			 	String md5 = MD5.getInstance().getMD5ofStr(password);
			 	if(md5.equals(staff.getPassword())){
			 		//TODO 设置SESSION属性
			 		 String token = UUID.randomUUID().toString();
			 		 request.getSession().setAttribute(token, staff);
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
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
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
	
	
}
