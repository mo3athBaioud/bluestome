package com.xcms.web.action;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Tac;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.TacService;

@IocBean
@InjectName
@At("/tac")
public class TacAction extends BaseAction{

	@Inject
	private TacService tacService;
	
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::tac.") Tac tac){
		json = new JsonObject();
		if(tacService.add(tac)){
			json.setSuccess(true);
			json.setMsg("添加TAC数据成功");
		}else{
			json.setSuccess(false);
			json.setMsg("添加TAC数据失败，可能存在相同的TAC,请检查!");
		}
		return json;
	}
	
	
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = tacService.getCount(colName,value);
		List<Tac> list =  tacService.search(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			json.setCount(count);
			json.setObj(list);
			json.setMsg("分页成功！");
		}else{
			json.setSuccess(false);
			json.setMsg("分页失败！");
		}
		return json;
	}

	@At("/update")
	@Ok("json")
	public JsonObject update(@Param("::tac.") Tac tac){
		json = new JsonObject();
		if(tacService.update(tac)){
			json.setMsg("分页成功！");
		}else{
			json.setSuccess(false);
			json.setMsg("修改失败!");
		}
		return json;
	}
	public TacService getTacService() {
		return tacService;
	}

	public void setTacService(TacService tacService) {
		this.tacService = tacService;
	}
	
	
}
