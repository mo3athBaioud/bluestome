package com.xcms.web.action;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.view.ViewTerminal;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.UtpService;

/**
 * 用户终端查询Action
 * @author bluestome
 *
 */
@IocBean
@InjectName
@At("/utp")
public class UtpAction extends BaseAction{

	@Inject
	private UtpService utpService;
	
	@At("/search")
	@Ok("json")
	public JsonObject search(@Param("phonenum") String phonenum ){
		json = new JsonObject();
		try{
		ViewTerminal v = utpService.search(phonenum);
		if(null != v){
			json.setCount(1);
			json.setObj(v);
			json.setMsg("已查找到对应的终端数据!");
		}else{
			json.setSuccess(false);
			json.setMsg("未查找到对应的终端数据!");
		}
		}catch(Exception e){
			json.setSuccess(false);
			json.setMsg("查询数据发生异常,异常为:"+e);
			e.printStackTrace();
		}
		logger.debug(">> "+Json.toJson(json));
		return json;
	}

	@At("/utp")
	@Ok("json")
	public JsonObject utp(@Param("phonenum") String phonenum,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		List<ViewTerminal> list = utpService.search(phonenum, start, limit);
		if(null != list && list.size() > 0){
			json.setCount(list.size());
			json.setObj(list);
			json.setMsg("已查找到对应的终端数据!");
		}else{
			json.setSuccess(false);
			json.setMsg("未查找到对应的终端数据!");
		}
		return json;
	}
	
	public UtpService getUtpService() {
		return utpService;
	}

	public void setUtpService(UtpService utpService) {
		this.utpService = utpService;
	}
	
	
}
