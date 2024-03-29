package com.xian.support.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.xian.support.cms.common.Constants;
import com.xian.support.cms.utils.ExtUtil;
import com.xian.support.entity.Noplog;
import com.xian.support.service.NoplogManager;

@Namespace("/admin")
@Action("noplog")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/noplog.jsp"),
})
public class NoplogAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private NoplogManager noplogManager;
	
	private Noplog entity;
	
	public NoplogAction(){
		logger.info("BussinessSimplifyAction init ");
	}

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
		if(null != response)
		{
			logger.info(" >　HttpServletResponse is not null!");
		}else{
			logger.info(" >　HttpServletResponse is null!");
		}
		if(null != request)
		{
			logger.info(" >　HttpServletRequest is not null!");
		}else{
			logger.info(" >　HttpServletRequest is null!");
		}
		return SUCCESS;
	}
	

	@Override
	public void list() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != noplogManager){
				 int c = noplogManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Noplog> list = noplogManager.getListBySql(entity, start, limit);
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

	/**
	 * 查询记录
	 * @throws IOException
	 */
	public void search() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != noplogManager){
				 int c = noplogManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Noplog> list = noplogManager.getListBySql(entity, start, limit);
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
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		boolean b = false;
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

	public Noplog getEntity() {
		return entity;
	}

	public void setEntity(Noplog entity) {
		this.entity = entity;
	}

}
