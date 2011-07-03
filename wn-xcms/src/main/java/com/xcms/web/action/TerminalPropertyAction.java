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
import com.mss.dal.domain.TerminalProperty;
import com.xcms.UserSession;
import com.xcms.common.Constants;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.StaffService;
import com.xcms.web.service.TerminalPropertyService;

@IocBean
@InjectName
@At("/terminalproperty")
public class TerminalPropertyAction extends BaseAction {

	@Inject
	private TerminalPropertyService terminalPropertyService;
	
	/**
	 * 添加员工
	 * @param staff
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::object.") TerminalProperty terminalproperty){
		json = new JsonObject();
		try{
			if(terminalPropertyService.add(terminalproperty)){
				json.setSuccess(true);
				json.setMsg("终端属性信息保存成功");
			}else{
				json.setSuccess(false);
				json.setMsg("添加终端属性信息工失败,是否存在相同终端属性信息!");
			}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("添加终端属性信息失败,提示信息["+e.getMessage()+"]!");
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
		int count = terminalPropertyService.getCount(colName,value);
		if(count > 0){
			List<TerminalProperty> list =  terminalPropertyService.search(colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("未查询到["+colName+"]为["+value+"]的数据");
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
	public JsonObject update(@Param("::object.") Staff staff){
		json = new JsonObject();
		json.setSuccess(false);
		json.setMsg("对不起，暂不提供信息修改!");
		return json;
	}

	public TerminalPropertyService getTerminalPropertyService() {
		return terminalPropertyService;
	}

	public void setTerminalPropertyService(
			TerminalPropertyService terminalPropertyService) {
		this.terminalPropertyService = terminalPropertyService;
	}
	
	
}
