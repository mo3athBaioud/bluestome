package com.xcms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.TerminalCore;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.TerminalCoreService;

@IocBean
@InjectName
@At("/tereminalcore")
public class TerminalCoreAction extends BaseAction {

	@Inject
	private TerminalCoreService terminalCoreService;
	
	/**
	 * 添加终端核心数据数据
	 * @param terminalCore
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::terminalcore.") TerminalCore terminalCore,HttpServletRequest request){
		json = new JsonObject();
		if(null != request.getParameter("action") && "edit".equals(request.getParameter("action"))){
			if(terminalCoreService.update(terminalCore)){
				json.setSuccess(true);
				json.setMsg("编辑终端核心数据数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("编辑终端核心数据数据失败,是否存在相同终端TAC代码["+terminalCore.getTac()+"]!");
			}
		}else{
			if(terminalCoreService.add(terminalCore)){
				json.setSuccess(true);
				json.setMsg("添加终端核心数据数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("添加终端核心数据数据失败,是否存在相同终端核心数据代码!");
			}
		}
		return json;
	}
	
	/**
	 * 查询业务区数据
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
		int count = terminalCoreService.getCount(colName,value);
		List<TerminalCore> list =  terminalCoreService.search(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			json.setCount(count);
			json.setObj(list);
		}else{
			json.setSuccess(false);
		}
		return json;
	}
	
	public TerminalCoreService getTerminalCoreService() {
		return terminalCoreService;
	}

	public void setTerminalCoreService(TerminalCoreService terminalCoreService) {
		this.terminalCoreService = terminalCoreService;
	}
	
	
}
