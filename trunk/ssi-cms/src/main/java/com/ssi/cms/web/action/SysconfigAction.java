package com.ssi.cms.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ssi.cms.web.service.ISysconfigService;
import com.ssi.common.dal.domain.SysConfig;

public class SysconfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ISysconfigService sysconfigService;

	private List<SysConfig> sysconfigList;

	public void list() throws IOException {
		response.setCharacterEncoding("UTF-8");
		try {
			sysconfigList = sysconfigService.getPageList(colName, value, start, limit, false);
			if(null != sysconfigList && sysconfigList.size() > 0){
				int count = sysconfigService.getCount(colName, value);
				logger.debug(">> WebsiteAction.article 查询数据成功");
				toJson(response, sysconfigList,count);
			}else{
				logger.debug(">> SysconfigAction.list 未查询到有效数据");
				response.getWriter().print("{failure:true,msg:'未找到数据!'}");
			}
		} catch (Exception e) {
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询系统配置数据发生异常'}");
		}
	}

	public void insert() throws IOException {
		response.setCharacterEncoding("UTF-8");
		try {

		} catch (Exception e) {
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'添加系统配置数据发生异常'}");
		}
	}

	public void delete() throws IOException {
		response.setCharacterEncoding("UTF-8");
		try {

		} catch (Exception e) {
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'删除系统配置数据发生异常'}");
		}
	}

	public void update() throws IOException {
		response.setCharacterEncoding("UTF-8");
		try {

		} catch (Exception e) {
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'更新系统配置数据发生异常'}");
		}
	}
	
	/**
	 * 输出JSON格式数据
	 * @param response
	 * @param list
	 * @param count
	 * @throws IOException
	 */
	private void toJson(HttpServletResponse response, List<SysConfig> list, int count) throws IOException {
		PrintWriter out = response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer =new StringBuffer();
		JSONObject  json=null;
		Iterator it=list.iterator();
		buffer.append("{count:").append(count).append(",success:true,sysconfig:");
		while(it.hasNext()){
			json=new JSONObject();
			SysConfig bean = (SysConfig)it.next();
			json.put("d_id", bean.getId());
			json.put("d_name",bean.getName());
			json.put("d_type",bean.getType());
			json.put("d_value",bean.getValue());
			json.put("d_description",bean.getDescription());
			json.put("d_groupid",bean.getGroupid());
			json.put("d_create_dt",sdf.format(bean.getCreatetime()));
			json.put("d_modify_dt",sdf.format(bean.getModifytime()));
			jsonArr.add(json);
		}
    	buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}

	public ISysconfigService getSysconfigService() {
		return sysconfigService;
	}

	public void setSysconfigService(ISysconfigService sysconfigService) {
		this.sysconfigService = sysconfigService;
	}

	public List<SysConfig> getSysconfigList() {
		return sysconfigList;
	}

	public void setSysconfigList(List<SysConfig> sysconfigList) {
		this.sysconfigList = sysconfigList;
	}

}
