package com.xcms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.BDistrict;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.BDistrictService;

@IocBean
@InjectName
@At("/bdistrict")
public class BDistrictAction extends BaseAction {

	@Inject
	private BDistrictService bDistrictService;

	/**
	 * 添加业务区数据
	 * @param bdistrict
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::bdistrict.") BDistrict bdistrict,HttpServletRequest request){
		json = new JsonObject();
		if(null != request.getParameter("action") && "edit".equals(request.getParameter("action"))){
			if(bDistrictService.update(bdistrict)){
				json.setSuccess(true);
				json.setMsg("编辑业务区数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("编辑业务区数据失败,是否存在相同业务区代码!");
			}
		}else{
			if(bDistrictService.add(bdistrict)){
				json.setSuccess(true);
				json.setMsg("添加业务区数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("添加业务区数据失败,是否存在相同业务区代码!");
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
		int count = bDistrictService.getCount(colName,value);
		List<BDistrict> list =  bDistrictService.search(colName,value,start, limit);
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
	@At("/bdlist")
	@Ok("json")
	public JsonObject bdlist(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = bDistrictService.getbdListCount(colName,value);
		List<BDistrict> list =  bDistrictService.bdlist(colName,value,start, limit);
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
	@At("/enablelist")
	@Ok("json")
	public JsonObject enableList(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = bDistrictService.getbdListCount(colName,value);
		List<BDistrict> list =  bDistrictService.bdlist(colName,value,start, limit);
		logger.debug(" >> count:"+count);
		if(null != list && list.size() > 0){
			for(int i=0;i<list.size();i++){
				BDistrict b = (BDistrict)list.get(i);
				if(b.getStatus() == 0){
					list.remove(i);
					count--;
				}
			}
			json.setCount(count);
			json.setObj(list);
		}else{
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 修改个人信息
	 * @param bdistrict
	 * @return
	 */
	@At("/update")
	@Ok("json")
	public JsonObject update(@Param("::bdistrict.") BDistrict bdistrict){
		json = new JsonObject();
		json.setSuccess(false);
		json.setMsg("对不起，暂不提供信息修改!");
		return json;
	}
	
	/**
	 * 根据业务区数据ID删除业务区数据
	 * @param ids
	 * @return
	 */
	@At("/delete")
	@Ok("json")
	public JsonObject delete(@Param("code") String[] codes){
		json = new JsonObject();
		boolean isOk = true;
		try{
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("删除业务区数据成功!");
			}else{
				json.setMsg("删除业务区数据失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	/**
	 * 启用业务区
	 * @param id
	 * @return
	 */
	@At("/enable")
	@Ok("json")
	public JsonObject enable(@Param("code") String[] codes,HttpServletRequest request){
		json = new JsonObject();
		boolean isOk = true;
		try{
			for(String code:codes){
				if(!bDistrictService.enable(code)){
					isOk = false;
					break;
				}
			}
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("启用渠道成功!");
			}else{
				json.setMsg("启用渠道失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	/**
	 * 禁用业务区
	 * @param id
	 * @return
	 */
	@At("/disable")
	@Ok("json")
	public JsonObject disable(@Param("code") String[] codes,HttpServletRequest request){
		json = new JsonObject();
		boolean isOk = true;
		try{
			for(String code:codes){
				if(!bDistrictService.disable(code)){
					isOk = false;
					break;
				}
			}
			json.setSuccess(isOk);
			if(isOk){
				json.setMsg("禁用渠道成功!");
			}else{
				json.setMsg("禁用渠道失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	public BDistrictService getBDistrictService() {
		return bDistrictService;
	}

	public void setBDistrictService(BDistrictService districtService) {
		bDistrictService = districtService;
	}
	
	
}
