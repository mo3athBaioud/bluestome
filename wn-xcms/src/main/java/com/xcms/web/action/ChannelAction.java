package com.xcms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.mss.dal.domain.Channel;
import com.xcms.common.json.JsonObject;
import com.xcms.web.service.ChannelService;

@IocBean
@InjectName
@At("/channel")
public class ChannelAction extends BaseAction {

	@Inject
	private ChannelService channelService;

	/**
	 * 添加渠道数据
	 * @param channel
	 * @return
	 */
	@At("/add")
	@Ok("json")
	public JsonObject insert(@Param("::channel.") Channel channel,HttpServletRequest request){
		json = new JsonObject();
		if(null != request.getParameter("action") && "edit".equals(request.getParameter("action"))){
			if(channelService.update(channel)){
				json.setSuccess(true);
				json.setMsg("编辑渠道数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("编辑渠道数据失败,是否存在相同渠道代码!");
			}
		}else{
			if(channelService.add(channel)){
				json.setSuccess(true);
				json.setMsg("添加渠道数据成功");
			}else{
				json.setSuccess(false);
				json.setMsg("添加渠道数据失败,是否存在相同渠道代码!");
			}
		}
		return json;
	}
	
	/**
	 * 查询渠道数据
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
		int count = channelService.getCount(colName,value);
		List<Channel> list =  channelService.search(colName,value,start, limit);
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
	 * 查询渠道数据
	 * @param colName
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 */
	@At("/chlist")
	@Ok("json")
	public JsonObject chlist(@Param("colName") String colName,@Param("value") String value,@Param("start") int start,@Param("limit") int limit){
		json = new JsonObject();
		int count = channelService.getchListCount(colName,value);
		List<Channel> list =  channelService.chlist(colName,value,start, limit);
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
	 * 修改通道信息
	 * @param channel
	 * @return
	 */
	@At("/update")
	@Ok("json")
	public JsonObject update(@Param("::channel.") Channel channel){
		json = new JsonObject();
		json.setSuccess(false);
		json.setMsg("对不起，暂不提供信息修改!");
		return json;
	}
	
	/**
	 * 根据渠道数据ID删除渠道数据
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
				json.setMsg("删除渠道数据成功!");
			}else{
				json.setMsg("删除渠道数据失败!");
			}
		}catch(Exception e){
			logger.error(e);
		}
		return json;
	}
	
	/**
	 * 启用渠道
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
				if(!channelService.enable(code)){
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
	 * 禁用渠道
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
				if(!channelService.disable(code)){
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
	
	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}
	
}
