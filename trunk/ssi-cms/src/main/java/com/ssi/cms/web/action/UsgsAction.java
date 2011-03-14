package com.ssi.cms.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ssi.common.utils.JSONUtils;

import com.ssi.cms.web.service.IUsgsService;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public class UsgsAction extends BaseAction {

	private List<EarthQuakeInfo> infoList;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUsgsService usgsService;

	public void list() throws IOException{
		response.setCharacterEncoding("UTF-8");
		HashMap map = new HashMap();
		map.put("offset", 0);
		map.put("limit",20);
		try{
			infoList = usgsService.find(map);
			if(null != infoList && infoList.size() > 0){
				int count = usgsService.getCount(map);
				toJson(response, infoList, count);
			}else{
				logger.debug(">> WebsiteAction.article 未查询到有效数据");
				response.getWriter().print("{failure:true,msg:'未找到数据!'}");
			}
		
		}catch(Exception e){
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}
	
	/**
	 * 转换成JSON格式的数据
	 * @param response
	 * @param list
	 * @param count
	 * @throws Exception
	 */
	public void toJson(HttpServletResponse response, List list,int count) throws Exception {
		PrintWriter out = response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer =new StringBuffer();
		Iterator it=list.iterator();
		buffer.append("{count:").append(count).append(",success:true,usgs:");
		while(it.hasNext()){
			JSONObject  json=new JSONObject();
			EarthQuakeInfo bean= (EarthQuakeInfo)it.next();
			json.put("d_id", bean.getId());
			json.put("d_date", bean.getDate());
			json.put("d_latitude", bean.getLatitude());
			json.put("d_longitude", bean.getLongitude());
			json.put("d_depth", bean.getDepth());
			json.put("d_magnitude", bean.getMagnitude());
			json.put("d_comments", bean.getComments());
			json.put("d_url", bean.getUrl());
			String str = sdf.format(bean.getCreatetime());
			json.put("d_createtime", str);
			String mtr = sdf.format(bean.getModifytime());
			json.put("d_modifytime", mtr);
			if(null != bean.getDetail()){
				json.put("d_eventId", bean.getDetail().getEventId());
				json.put("d_distinces", bean.getDetail().getDistinces());
			}
			jsonArr.add(json);
		}
    	buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}
	
	
	public IUsgsService getUsgsService() {
		return usgsService;
	}

	public void setUsgsService(IUsgsService usgsService) {
		this.usgsService = usgsService;
	}

	public List<EarthQuakeInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<EarthQuakeInfo> infoList) {
		this.infoList = infoList;
	}
	
	
	
}
