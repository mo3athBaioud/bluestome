package com.xcms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Staff;
import com.xcms.UserSession;
import com.xcms.common.Constants;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.StaffService;

@IocBean
@InjectName
@At("/staff")
public class StaffAction extends BaseAction {

	@Inject
	private StaffService staffService;
	
	/**
	 * 添加员工
	 * @param staff
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::staff.") Staff staff,HttpServletRequest request){
		UserSession us = null;
		json = new JsonObject();
		try{
			String ip = request.getRemoteAddr();
			String sessionName = ip + "_" + Constants.USERSESSION;
			Object obj  = request.getSession().getAttribute(sessionName);
			if(null != obj){
				us = (UserSession)obj;
				if(staff.getUsername().equals("admin")){
					//判断登录用户是否为admin,
					if(us.getStaff().getUsername().equals("admin")){
						if(staffService.update(staff)){
							json.setSuccess(true);
							json.setMsg("保存员工成功");
						}else{
							json.setSuccess(false);
							json.setMsg("保存员工失败!");
						}
					}else{
						json.setSuccess(false);
						json.setMsg("您无权修改[admin]用户信息!");
						return json;
					}
				}else{
					if(staffService.add(staff)){
						json.setSuccess(true);
						json.setMsg("保存员工成功");
					}else{
						json.setSuccess(false);
						json.setMsg("保存员工失败,是否存在相同用户名!");
					}
				}
			}else{
				json.setSuccess(false);
				json.setMsg("您还没有登录，请先登录!");
			}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("添加员工失败,提示信息["+e.getMessage()+"]!");
		}
		return json;
	}
	
	/**
	 * 查询员工
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = staffService.getCount(colName,value);
		if(count > 0){
			List<Staff> list =  staffService.search(colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("未查询到用户名为["+value+"]的用户");
		}
		return json;
	}
	
	/**
	 * 修改个人信息
	 * @param staff
	 * @return
	 */
	@At("/update")
	@Ok("json")
	public JsonObject update(@Param("::staff.") Staff staff){
		json = new JsonObject();
		json.setSuccess(false);
		json.setMsg("对不起，暂不提供信息修改!");
		return json;
	}
	
	/**
	 * 根据员工ID删除员工
	 * @param ids
	 * @return
	 */
	@At("/delete")
	@Ok("json")
	public JsonObject delete(@Param("id") Integer[] ids){
		json = new JsonObject();
		boolean isOk = true;
		try{
			for(Integer id:ids){
				if(!staffService.deleteStaff(id)){
					isOk = false;
					break;
				}
			}
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("删除员工成功!");
			}else{
				json.setMsg("删除员工失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@At("/mp")
	@Ok("json")
	public JsonObject modifyPassword(@Param("oldPwd") String oldpwd,@Param("newPwd") String newPwd,HttpServletRequest request){
		json = new JsonObject();
		String ip = request.getRemoteAddr();
		String sessionName = ip + "_" + Constants.USERSESSION;
		Object obj = request.getSession().getAttribute(sessionName);
		if(null == staffService){
			json.setSuccess(false);
			json.setMsg("修改密码失败，系统异常");
			return json;
		}
		if(null != obj){
			UserSession us = (UserSession)obj;
			Staff staff = us.getStaff();
			if(null != staff){
				if(staff.getPassword().equals(oldpwd)){
					staff.setPassword(newPwd);
					if(staffService.update(staff)){
						json.setSuccess(true);
						json.setMsg("修改密码成功，请重新登录！");
					}else{
						json.setSuccess(false);
						json.setMsg("修改密码失败，请确认您已经登录到平台！");
					}
				}else{
					json.setSuccess(false);
					json.setMsg("修改密码失败，原始密码不正确，请重新输入原始密码！");
				}
			}else{
				json.setSuccess(false);
				json.setMsg("您还未登录，请重新登录!");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("您还未登录，请重新登录!");
		}
		return json;
	}

	/**
	 * 启用员工
	 * @param id
	 * @return
	 */
	@At("/enable")
	@Ok("json")
	public JsonObject enable(@Param("id") Integer[] ids,HttpServletRequest request){
		json = new JsonObject();
		boolean isOk = true;
		try{
			for(Integer id:ids){
				if(!staffService.enable(id)){
					isOk = false;
					break;
				}
			}
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("启用员工成功!");
			}else{
				json.setMsg("启用员工失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	/**
	 * 禁用员工
	 * @param id
	 * @return
	 */
	@At("/disable")
	@Ok("json")
	public JsonObject disable(@Param("id") Integer[] ids,HttpServletRequest request){
		json = new JsonObject();
		boolean isOk = true;
		try{
			for(Integer id:ids){
				if(!staffService.disable(id)){
					isOk = false;
					break;
				}
			}
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("禁用员工成功!");
			}else{
				json.setMsg("禁用员工失败!");
			}
		}catch(Exception e){
			logger.error(e);
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
