package com.xcms.web.action;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Noplog;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BDistrictService;
import com.xcms.web.service.NOplogService;

@IocBean
@InjectName
@At("/noplog")
public class NoplogAction extends BaseAction {

	@Inject
	private NOplogService nOplogService;

	/**
	 * 查询业务区数据
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/alist")
	@Ok("json")
	public JsonObject alist(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = nOplogService.getCount(colName,value);
		List<Noplog> list =  nOplogService.search(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			json.setCount(count);
			json.setObj(list);
		}else{
			json.setSuccess(false);
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
	public JsonObject list(@Param("loginName") String loginName,@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		List<Noplog> list =  null;
		if(loginName.equals("admin")){
			int count = nOplogService.getCount(null,value);
			list =  nOplogService.search(null,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}else{
			int count = nOplogService.getCount(loginName,colName,value);
			list =  nOplogService.search(loginName,colName,value,start, limit);
			logger.debug(" >> count:"+count);
			if(null != list && list.size() > 0){
				json.setCount(count);
				json.setObj(list);
			}else{
				json.setSuccess(false);
			}
		}
		return json;
	}
	
	public NOplogService getNOplogService() {
		return nOplogService;
	}

	public void setNOplogService(NOplogService oplogService) {
		nOplogService = oplogService;
	}

	
	
}
