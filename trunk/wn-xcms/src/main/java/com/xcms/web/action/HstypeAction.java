package com.xcms.web.action;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Hstype;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.HstypeService;

/**
 * 机型管理ACTION
 * @author bluestome
 *
 */
@IocBean
@InjectName
@At("/hstype")
public class HstypeAction  extends BaseAction{

	@Inject
	private HstypeService hstypeService;
	
	/**
	 * 查找厂商下的机型数据列表
	 * @param hsmanid
	 * @return
	 */
	@At("/list")
	@Ok("json")
	public JsonObject list(@Param("hid") Integer hsmanid ){
		json = new JsonObject();
		logger.debug(" >> hsmanid:"+hsmanid);
		List<Hstype> list = hstypeService.find(hsmanid);
		if(null != list && list.size() > 0){
			json.setCount(list.size());
			json.setObj(list);
			json.setMsg("查询厂商["+hsmanid+"]下机型列表成功!");
		}else{
			json.setSuccess(false);
			json.setMsg("未找到厂商["+hsmanid+"]下的机型数据!");
		}
		return json;
	}
	
	public HstypeService getHstypeService() {
		return hstypeService;
	}
	
	public void setHstypeService(HstypeService hstypeService) {
		this.hstypeService = hstypeService;
	}
	
}
