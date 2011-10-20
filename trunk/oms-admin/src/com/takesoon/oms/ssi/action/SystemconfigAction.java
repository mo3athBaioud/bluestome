package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.takesoon.oms.ssi.common.Constants;
import com.takesoon.oms.ssi.entity.SystemConfig;
import com.takesoon.oms.ssi.service.SystemConfigManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

@InterceptorRefs({
	@InterceptorRef("actionInterceptor"),
	@InterceptorRef("defaultStack")
})
@Namespace("/admin")
@Action("sysconfig")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/sysconfig.jsp"),
})
public class SystemconfigAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SystemConfigManager sysConfigManager;
	
	private SystemConfig entity;
	
	private Integer webId = 0;

	@Override
	public void delete() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
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
		request.setAttribute("webId", webId);
		return SUCCESS;
	}

	@Override
	public void list() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != sysConfigManager){
				 int c = sysConfigManager.getTotalBySql(entity);
				 if(c > 0){
					 List<SystemConfig> list = sysConfigManager.getListBySql(entity, start, limit);
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
	
	public void search() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != sysConfigManager){
				 int c = sysConfigManager.getTotalBySql(entity);
				 if(c > 0){
					 List<SystemConfig> list = sysConfigManager.getListBySql(entity, start, limit);
					 if(null != list && list.size() > 0){
						 String json = ExtUtil.getJsonFromList(c, list);
						 out.println(json);
					 }else{
						 out.println("{success:false,msg:'搜索数据为空!'}");
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
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();
		try{
			 sb.append(entity.getType()).append("|").append(entity.getKey()).append("|").append(entity.getValue());
			 out = getOut(response);
			 if(null != entity.getId() && entity.getId().intValue() > 0){
				 sysConfigManager.save(entity);
				 if(entity.getId().intValue() > 0 ){
					 out.println("{success:true,msg:'添加["+sb.toString()+"]成功!'}"); 
				 }else{
					 out.println("{success:false,msg:'该键值["+sb.toString()+"]失败!'}"); 
				 }
			 }else{
				 if(sysConfigManager.checkUnique(entity.getType(), entity.getKey(), entity.getValue())){
					 sysConfigManager.save(entity);
					 if(entity.getId().intValue() > 0 ){
						 out.println("{success:true,msg:'添加["+sb.toString()+"]成功!'}"); 
					 }else{
						 out.println("{success:false,msg:'该键值["+sb.toString()+"]失败!'}"); 
					 }
				 }else{
					 out.println("{success:false,msg:'该键值["+sb.toString()+"]已经存在,请输入其他键值!'}"); 
				 }
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
	public void update() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
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
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 for(Long id:ids){
				 //TODO 启用记录
				 logger.info(" >> id:"+id);
				 sysConfigManager.enabled(id.intValue());
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
	 * 禁用
	 * @throws IOException
	 */
	public void disable() throws IOException{
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 for(Long id:ids){
				 //TODO 禁用记录
				 sysConfigManager.disabled(id.intValue());
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
	
	public SystemConfig getEntity() {
		return entity;
	}

	public void setEntity(SystemConfig entity) {
		this.entity = entity;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

}
