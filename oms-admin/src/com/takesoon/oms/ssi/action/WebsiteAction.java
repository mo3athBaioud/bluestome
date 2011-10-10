package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.WebsiteManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

@Namespace("/admin")
@Action("website")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/website.jsp"),
})
public class WebsiteAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WebsiteManager websiteManager;
	
	private Website entity;
	
	
	//访问地址
	private String turl;

	@Override
	public void delete() throws IOException {
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 out.println("{success:false,msg:'该方法[delete]还未启用!'}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	@Override
	public String execute(){
		return SUCCESS;
	}

	@Override
	public void list() throws IOException {
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != websiteManager){
				 int c = websiteManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Website> list = websiteManager.getListBySql(entity, start, limit);
					 if(null != list && list.size() > 0){
						 String json = ExtUtil.getJsonFromList(c, list);
						 out.println(json);
					 }else{
						 out.println("{success:false,msg:'列表数据为空!'}");
					 }
				 }else{
					 out.println("{success:false,msg:'没有数据!'}");
				 }
			 }else{
				 out.println("{success:false,msg:'业务类获取失败，请检查!'}"); 
			 }
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	@Override
	public void save() throws IOException {
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 out.println("{success:false,msg:'该方法[save]还未启用!'}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	@Override
	public void update() throws IOException {
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 out.println("{success:false,msg:'该方法[update]还未启用!'}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 启用
	 * @throws IOException
	 */
	public void enable() throws IOException{
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 for(Long id:ids){
				 //TODO 启用记录
				 logger.info(" >> id:"+id);
				 websiteManager.enabled(id.intValue());
			 }
			 out.println("{success:true,msg:'启用成功!'}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 页面测试
	 * @throws IOException
	 */
	public void debug() throws IOException{
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 logger.info(" > id:" + id);
			 logger.info(" > turl:" + turl);
			 out.println("{success:true,msg:'测试成功!',plength:65432,times:12345}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 禁用
	 * @throws IOException
	 */
	public void disable() throws IOException{
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 for(Long id:ids){
				 //TODO 禁用记录
				 websiteManager.disabled(id.intValue());
			 }
			 out.println("{success:true,msg:'禁用成功!'}"); 
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	public Website getEntity() {
		return entity;
	}

	public void setEntity(Website entity) {
		this.entity = entity;
	}

	public String getTurl() {
		return turl;
	}

	public void setTurl(String turl) {
		this.turl = turl;
	}

	
}
