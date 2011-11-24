package com.xian.support.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssi.common.utils.MD5;
import com.xian.support.cms.common.Constants;
import com.xian.support.cms.utils.ExtUtil;
import com.xian.support.entity.Staff;
import com.xian.support.service.StaffManager;

@Namespace("/admin")
@Action("staff")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/staff.jsp"),
})
public class StaffAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StaffManager staffManager;
	
	private Staff entity;
	
	public StaffAction(){
		logger.info("StaffAction init ");
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
			 if(null != staffManager){
				 int c = staffManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Staff> list = staffManager.getListBySql(entity, start, limit);
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
			 if(null != staffManager){
				 int c = staffManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Staff> list = staffManager.getListBySql(entity, start, limit);
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
				out.println("{success:false,msg:'未获取到前台参数!'}");
				return;
			 }
			 if(null != mtype && mtype.equals("add"))
			 {
				 //TODO　新增数据 密码是否需要进行MD5加密？
				 String md5 = MD5.getInstance().getMD5ofStr(entity.getPassword());
				 entity.setPassword(md5);
				 staffManager.save(entity);
				 b = true;
			 }else{
				 //TODO 编辑数据
				 /*
				  * 1.验证密码是否输入正确
				  */
				 Staff staff = staffManager.get(entity.getId());
				 if(staff.getPassword().equals(entity.getPassword())){
					 //TODO 验证用户密码是否一致
					 staffManager.save(entity);
					 b = true;
				 }else{
					 message = "密码不正确，请重新输入";
				 }
			 }
			 if(b)
			 {
				 out.println("{success:true,msg:'保存成功!'}"); 
			 }else{
				 out.println("{success:false,msg:'"+message+"!'}"); 
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
			 for(Long id:ids){
				 //TODO 启用记录
				 if(staffManager.enabled(id.intValue())){
					 i++;
				 }
			 }
			 if(i == ids.length){
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
			 for(Long id:ids){
				 //TODO 禁用记录
				 if(staffManager.disabled(id.intValue())){
					 i ++;
				 }
			 }
			 if(i == ids.length){
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

	public Staff getEntity() {
		return entity;
	}

	public void setEntity(Staff entity) {
		this.entity = entity;
	}

}
