package com.ssi.cms.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.ssi.cms.web.service.IWebsiteService;
import com.ssi.common.dal.domain.Website;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WebsiteAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018433911260161002L;

	private List<Website> websiteList;

	private IWebsiteService websiteService;

	private Website website;

	private Integer id;
	
	//ID数组
	private Integer[] ids;
	
	private Integer webType = 1;
	
	public String showSubWeb() throws Exception{
		return SUCCESS;
	}
	
	public void toTree() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try{
			if(null == id){
				websiteList = websiteService.getRootWebSite();
				String tree = websiteService.tree(websiteList);
				response.getWriter().print(tree);
			}else{
				websiteList = websiteService.findByParentId(id);
				String tree = websiteService.tree(websiteList);
				response.getWriter().print(tree);
			}
		}catch(Exception e){
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}
	
	public String sub() throws Exception{
		if(null == id){
			websiteList = websiteService.getRootWebSite();
		}else{
			websiteList = websiteService.findByParentId(id);
		}
		return SUCCESS;
	}

	public void tree2() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try{
			String node = request.getParameter("node");
			if(null != node && !"".equals(node)){
				id = Integer.valueOf(node);
			}
			long start = System.currentTimeMillis();
			if(null == id){
				websiteList = websiteService.getRootWebSite(webType);
				String tree2 = websiteService.tree2(websiteList,request);
				long end = System.currentTimeMillis();
				logger.info("根目录执行时间为:"+(end-start)/1000);
				response.getWriter().print(tree2);
			}else{
				websiteList = websiteService.findByParentId(id);
				String tree2 = websiteService.tree2(websiteList,request);
				long end = System.currentTimeMillis();
				logger.info("父ID["+id+"],执行时间为:"+(end-start)/1000);
				response.getWriter().print(tree2);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}
	
	public void root2() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try{
			if(null == id){
				websiteList = websiteService.getRootWebSite(webType);
			}else{
				websiteList = websiteService.findByParentId(id);
			}
			int count = (websiteList == null?0:websiteList.size());
			toJson(response, websiteList,count);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(">> WebsiteAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}
	
	public String showUpWeb() throws Exception{
		if(null == id){
			websiteList = websiteService.getRootWebSite();
		}else{
			logger.debug(">> WebsiteAction.showUpWeb.id:"+id);
			website = websiteService.findWebById(id);
			if(null != website)
				websiteList = websiteService.findByParentId(website.getParentId());
			else
				websiteList = websiteService.getRootWebSite();
			logger.debug(">> showUpWeb.website ["+website+"]");
			logger.debug(">> showUpWeb.websiteList.size"+websiteList.size());
		}
		return SUCCESS;
	}
	
	public void website() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try{
			websiteList = websiteService.getPageList(colName, value, start, limit, true);
			if(null  != websiteList && websiteList.size() > 0){
				int count = websiteService.getCount(colName, value);
				logger.debug(">> WebsiteAction.article 查询数据成功");
				toJson(response, websiteList,count);
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
	 * 获取根节点数据
	 * @throws IOException 
	 *
	 */
	public void root() throws IOException{
		response.setCharacterEncoding("UTF-8");
		Website website = null;
		try{
			website = new Website();
			website.setId(0);
			website.setName("根网站(逻辑上的数据)");
			website.setParentId(0);
			website.setStatus(1);
			website.setType(1);
			websiteList = websiteService.findAll();
			websiteList.add(website);
			toTreeJson(response, websiteList, websiteList.size());
		}catch(Exception e){
			logger.debug(">> WebsiteAction.update" + e);
			response.getWriter().print("{failure:true,msg:'更新站点发生异常'}");
		}
	}
	
	/**
	 * 添加网站
	 * @throws IOException 
	 */
	public void insert() throws IOException{
		response.setCharacterEncoding("UTF-8");
		try{
			boolean b = websiteService.insert(website);
			if(b){
				response.getWriter().print("{success:true,msg:'添加站点["+website.getName()+"]信息成功!'}");
			}else{
				response.getWriter().print("{failure:true,msg:'添加站点失败，可能存在相同的站点,请检查!'}");
			}
		}catch(Exception e){
			logger.debug(">> WebsiteAction.update" + e);
			response.getWriter().print("{failure:true,msg:'添加站点发生异常,异常信息为:'"+e.getMessage()+"}");
		}
	}
	
	/**
	 * 更新站点内容
	 * @throws IOException 
	 */
	public void update() throws IOException{
		response.setCharacterEncoding("UTF-8");
		try{
			boolean b = websiteService.update(website);
			if(b){
				response.getWriter().print("{success:true,msg:'更新站点["+website.getName()+"]信息成功!'}");
			}else{
				response.getWriter().print("{failure:true,msg:'更新站点发生异常'}");
			}
		}catch(Exception e){
			logger.debug(">> WebsiteAction.update" + e);
			response.getWriter().print("{failure:true,msg:'更新站点发生异常'}");
		}
	}
	
	/**
	 * 批量修改站点为禁用状态
	 * @throws IOException
	 */
	public void disable() throws IOException{
		response.setCharacterEncoding("UTF-8");
		try{
			StringBuffer sb = new StringBuffer();
			boolean b = true;
			sb.append("如下站点ID，未禁用成功[");
			for(Integer id:ids){
				if(!websiteService.disable(id)){
					sb.append(id).append(",");
				}
			}
			sb.append("]\r");
			if(b){
				logger.debug(" >> 禁用结果"+sb.toString());
				response.getWriter().print("{success:true,msg:'禁用站点成功!'}");
			}else{
				response.getWriter().print("{failure:true,msg:'禁用站点发生异常'}");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(">> WebsiteAction.update:" + e);
			response.getWriter().print("{failure:true,msg:'禁用站点发生异常'}");
		}
	}
	
	/**
	 * 批量修改站点为启用状态
	 * @throws IOException
	 */
	public void enable() throws IOException{
		response.setCharacterEncoding("UTF-8");
		try{
			StringBuffer sb = new StringBuffer();
			boolean b = true;
			sb.append("如下站点ID，未启用成功[");
			for(Integer id:ids){
				logger.info("webid:["+id+"]");
				if(!websiteService.enable(id)){
					sb.append(id).append(",");
				}
			}
			sb.append("]\r");
			if(b){
				System.out.println(" >> 启用结果"+sb.toString());
				response.getWriter().print("{success:true,msg:'启用站点成功!'}");
			}else{
				response.getWriter().print("{failure:true,msg:'启用站点发生异常'}");
			}
		}catch(Exception e){
			logger.debug(">> WebsiteAction.enable" + e);
			response.getWriter().print("{failure:true,msg:'启用站点发生异常'}");
		}
	}
	
	/**
	 * 删除记录
	 * @throws IOException
	 */
	public void delete() throws IOException {
		response.setCharacterEncoding("UTF-8");
		try{
			StringBuffer sb = new StringBuffer();
			boolean b = true;
			for(Integer id:ids){
				Website tmp = websiteService.findById(id);
				//站点下必须没有子站点数量
				int size = tmp.getChildren().size();
				if(size > 0){
					sb.append("站点["+tmp.getId()+"|"+tmp.getName()+"]存在["+size+"]子站点,请确认!");
					b = false;
					break;
				}
				
				//站点下的文章数必须为0 ? 是否需要？考虑，预留
//				if(tmp.getCount() > 0){
//					sb.append("站点["+tmp.getId()+"|"+tmp.getName()+"]存在["+tmp.getCount()+"]文章,请确认!");
//					b = false;
//					break;
//				}
				
				//查找记录是否已被禁用
				if(tmp.getStatus() != 0){
					sb.append("站点["+tmp.getId()+"|"+tmp.getName()+"]未被禁用,请确认!");
					b = false;
					break;
				}
				
				//删除站点
				if(!websiteService.delete((id))){
					sb.append("站点["+tmp.getId()+"|"+tmp.getName()+"]删除失败,可能存在关联关系!");
					b = false;
					break;
				}
			}
			if(b){
				logger.debug(" >> 删除站点成功!");
				response.getWriter().print("{success:true,msg:'删除站点成功!'}");
			}else{
				response.getWriter().print("{failure:true,msg:'删除站点失败，失败原因为:"+sb.toString()+"'}");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(">> WebsiteAction.update:" + e);
			response.getWriter().print("{failure:true,msg:'删除站点发生异常'}");
		}
	}
	
	public Integer getWebType() {
		return webType;
	}

	public void setWebType(Integer webType) {
		this.webType = webType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public List<Website> getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(List<Website> websiteList) {
		this.websiteList = websiteList;
	}

	/**
	 * 转换成JSON格式的数据
	 * @param response
	 * @param list
	 * @param count
	 * @throws Exception
	 */
	public void toTreeJson(HttpServletResponse response, List list,int count) throws Exception {
		PrintWriter out = response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer =new StringBuffer();
		Iterator it=list.iterator();
		buffer.append("{count:").append(count).append(",success:true,website:");
		while(it.hasNext()){
			JSONObject  json=new JSONObject();
			Website bean= (Website)it.next();
			json.put("d_id", bean.getId());
			json.put("d_parent_id", bean.getParentId());
			if(bean.getParentId() == 0){
				json.put("d_web_name", bean.getName());
			}else{
				json.put("d_web_name", "\t|--"+bean.getId()+"|"+bean.getName());
			}
			json.put("d_web_type", bean.getType());
			json.put("d_web_url", bean.getUrl());
			json.put("d_status", bean.getStatus());
			json.put("d_count", bean.getCount());
			json.put("d_sub_count", bean.getChildren().size());
			if( null == bean.getRemarks() || "".equals(bean.getRemarks())){
				json.put("d_remarks", "暂无");
			}else{
				json.put("d_remarks", bean.getRemarks());
			}
			String str = sdf.format(bean.getCreatetime());
			json.put("d_createtime", str);
			if(null !=  bean.getModifytime()){
				String modifyTime = sdf.format(bean.getModifytime());
				json.put("d_modify_time", modifyTime);
			}else{
				String modifyTime = sdf.format(new Date());
				json.put("d_modify_time", modifyTime);
			}
			if(null != bean.getFather()){
				json.put("d_parent_name", bean.getFather().getName());
			}
			jsonArr.add(json);
		}
    	buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
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
		buffer.append("{count:").append(count).append(",success:true,website:");
		while(it.hasNext()){
			JSONObject  json=new JSONObject();
			Website bean= (Website)it.next();
			json.put("d_id", bean.getId());
			json.put("d_parent_id", bean.getParentId());
			json.put("d_web_name", bean.getName());
			json.put("d_web_type", bean.getType());
			json.put("d_web_url", bean.getUrl());
			json.put("d_status", bean.getStatus());
			json.put("d_count", bean.getCount());
			json.put("d_sub_count", bean.getChildren().size());
			if( null == bean.getRemarks() || "".equals(bean.getRemarks())){
				json.put("d_remarks", "暂无");
			}else{
				json.put("d_remarks", bean.getRemarks());
			}
			String str = sdf.format(bean.getCreatetime());
			json.put("d_createtime", str);
			if(null !=  bean.getModifytime()){
				String modifyTime = sdf.format(bean.getModifytime());
				json.put("d_modify_time", modifyTime);
			}else{
				String modifyTime = sdf.format(new Date());
				json.put("d_modify_time", modifyTime);
			}
			if(null != bean.getFather()){
				json.put("d_parent_name", bean.getFather().getName());
			}
			jsonArr.add(json);
		}
    	buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}

	public IWebsiteService getWebsiteService() {
		return websiteService;
	}

	public void setWebsiteService(IWebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	
}
