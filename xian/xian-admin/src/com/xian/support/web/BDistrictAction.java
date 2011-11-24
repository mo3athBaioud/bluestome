package com.xian.support.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.xian.support.cms.common.Constants;
import com.xian.support.cms.utils.ExtUtil;
import com.xian.support.entity.BDistrict;
import com.xian.support.service.BDistrictManager;

@Namespace("/admin")
@Action("bdistrict")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/bdistrict.jsp"),
})
public class BDistrictAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BDistrictManager BDistrictManager;
	
	private BDistrict entity;

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
		return SUCCESS;
	}
	

	@Override
	public void list() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != BDistrictManager){
				 int c = BDistrictManager.getTotalBySql(entity);
				 if(c > 0){
					 List<BDistrict> list = BDistrictManager.getListBySql(entity, start, limit);
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
	 * 显示所有区域数据
	 * @throws IOException
	 */
	public void combo() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			if(null == entity)
			{
				//设置当前记录状态可用
				entity = new BDistrict();
				entity.setStart(1);
			}
			 out = getOut(response);
			 if(null != BDistrictManager){
				 int c = BDistrictManager.getTotalBySql(entity);
				 if(c > 0){
					 List<BDistrict> list = BDistrictManager.getListBySql(entity);
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
			 if(null != BDistrictManager){
				 int c = BDistrictManager.getTotalBySql(entity);
				 if(c > 0){
					 List<BDistrict> list = BDistrictManager.getListBySql(entity, start, limit);
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
			 if(null == entity){
				 out.println("{success:false,msg:'未获取相关参数!'}");
				 return;
			 }
			 if(null != mtype){
				 if(mtype.equals("add")){
					 //TODO 新增数据
					 BDistrictManager.save(entity);
					 b = true;
				 }else if(mtype.equals("edit")){
					 //TODO　修改数据
					 BDistrictManager.save(entity);
					 b = true;
				 }
			 }else{
				 //TODO 未获取操作类型
				 message = "未获取操作类型";
			 }
			 if(b){
				 //TODO
				 out.println("{success:true,msg:'保存成功!'}"); 
			 }else{
				 //TODO　
				 out.println("{success:false,msg:'保存失败,原因:["+message+"]!'}"); 
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
		int i = 0;
		try{
			 out = getOut(response);
			 for(String code:codes){
				 //TODO 启用记录
				 if(BDistrictManager.enabled(code)){
					 i++;
				 }
			 }
			 if(i == codes.length){
				 out.println("{success:true,msg:'启用成功!'}");
			 }else{
				 out.println("{success:true,msg:'部分启用成功!'}");
			 }
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			i = 0;
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
		int i = 0;
		try{
			 out = getOut(response);
			 for(String code:codes){
				 //TODO 禁用记录
				 if(BDistrictManager.disabled(code)){
					 i ++;
				 }
			 }
			 if(i == codes.length){
				 out.println("{success:true,msg:'禁用成功!'}"); 
			 }else{
				 out.println("{success:true,msg:'部分禁用成功!'}"); 
			 }
		}catch(Exception e){
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally{
			i = 0;
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}

	public BDistrict getEntity() {
		return entity;
	}

	public void setEntity(BDistrict entity) {
		this.entity = entity;
	}

}
