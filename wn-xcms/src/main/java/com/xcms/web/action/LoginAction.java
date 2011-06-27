package com.xcms.web.action;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.BDistrict;
import com.mss.dal.domain.Channel;
import com.mss.dal.domain.Staff;
import com.xcms.UserSession;
import com.xcms.common.Constants;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.StaffService;

@IocBean
@InjectName
public class LoginAction extends BaseAction {

	@Inject
	private StaffService staffService;
	
	private UserSession userSession;
	
	/**
	 * 用户登录方法
	 *
	 */
	@At("/login")
	@Ok("json")
	public JsonObject login(@Param("username") String username,@Param("password") String password,HttpServletRequest request){
		Staff sta = staffService.findByName(username);
		json = new JsonObject();
		if(null != sta){
			if(sta.getPassword().equals(password)){
				String ip = request.getRemoteAddr();
				String sessionName = ip + "_" + Constants.USERSESSION;
				userSession = new UserSession();
				userSession.setUsid(UUID.randomUUID().toString());
				userSession.setStaff(sta);
				Channel tmp = staffService.findByChannelCode(sta.getChannelcode());
				if(null != tmp){
					userSession.setChannel(tmp);
					BDistrict bd = staffService.findByCode(tmp.getBdcode());
					if(null != bd){
						userSession.setBdistrict(bd);
						request.getSession().setAttribute(sessionName, userSession);
						logger.debug(" >> Session Set");
					}
				}
				//TODO 获取区局数据
				json.setSuccess(true);
				//设置用户属性到SESSION
			}else{
				json.setSuccess(false);
				//TODO 密码不正确
				json.setMsg("您输入的密码不正确!");
			}
		}else{
			json.setSuccess(false);
			//TODO 用户不存在
			json.setMsg("您输入的用户["+username+"]不存在!");
		}
		return json;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
}
