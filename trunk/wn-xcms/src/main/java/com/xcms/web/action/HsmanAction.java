package com.xcms.web.action;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.mss.dal.domain.Hsman;
import com.mss.dal.domain.HsmanTree;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.HsmanService;

/**
 * 厂商管理ACTION
 * @author bluestome
 *
 */
@IocBean
@InjectName
@At("/hsman")
public class HsmanAction extends BaseAction{

	@Inject
	private HsmanService hsmanService;
	
	
	@At("/list")
	@Ok("Json")
	public JsonObject list(){
		json = new JsonObject();
		List<Hsman> list = hsmanService.findAll(1);
//		logger.debug("json:"+Json.toJson(list));
		if(null != list && list.size() > 0){
			json.setCount(list.size());
			json.setObj(list);
			json.setMsg("获取激活厂商数据成功!");
		}else{
			json.setSuccess(false);
			json.setMsg("获取激活厂商数据失败!");
		}
		return json;
	}
	
	@At("/tree")
	@Ok("Json")
	public List<HsmanTree> tree(){
		List<HsmanTree> list = hsmanService.tree(1);
//		logger.debug(" >> tree:"+Json.toJson(list));
		return list;
	}

	public HsmanService getHsmanService() {
		return hsmanService;
	}

	public void setHsmanService(HsmanService hsmanService) {
		this.hsmanService = hsmanService;
	}
	
	
}
