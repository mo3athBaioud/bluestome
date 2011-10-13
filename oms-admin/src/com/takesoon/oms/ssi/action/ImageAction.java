package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssi.common.utils.HttpClientUtils;
import com.takesoon.oms.ssi.common.Constants;
import com.takesoon.oms.ssi.common.JMagickScale;
import com.takesoon.oms.ssi.entity.Article;
import com.takesoon.oms.ssi.entity.Image;
import com.takesoon.oms.ssi.service.ArticleManager;
import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

@Namespace("/admin")
@Action("images")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/images.jsp"),
})
public class ImageAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ImageManager imageManager;
	
		@Autowired
	private ArticleManager articleManager;
	
	private Image entity;
	
	//图片处理类
	private JMagickScale tp = JMagickScale.getInstance();
	
	
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
			 if(null != imageManager){
				 int c = imageManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Image> list = imageManager.getListBySql(entity, start, limit);
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

	public void xmllist() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != imageManager){
				 int c = imageManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Image> list = imageManager.getListBySql(entity);
					 if(null != list && list.size() > 0){
						 String xml = ExtUtil.getXmlFromList(c, list);
						 logger.info(" >> xml:" + xml);
						 out.println(xml);
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
	
	public void root() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != imageManager){
				 int c = imageManager.getTotalBySql(entity);
				 if(c > 0){
					 List<Image> list = imageManager.getListBySql(entity, start, limit);
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
	
	/**
	 * 获取图片的宽高
	 * @throws IOException
	 */
	public void wh() throws IOException{
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 Article art = articleManager.get(entity.getArticleId());
			 if(null != art)
			 {
				 art.getArticleUrl();
				 if(null != tp)
				 {
					 tp.process(art.getArticleUrl(),entity.getHttpUrl());
					 out.println("{success:true,msg:'获取宽高成功!',width:"+tp.getWidth().intValue()+",height:"+tp.getHeight().intValue()+"}");
				 }else{
					 out.println("{success:false,msg:'获取图片处理对象失败!'}"); 
				 }
			 }
			 else
			 {
				 out.println("{success:false,msg:'获取宽高失败!'}"); 
			 }
		}catch(Exception e)
		{
			 out.println("{success:false,msg:'异常【"+e.getMessage()+"】'}");
		}finally
		{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 间接将图片发送给前台
	 *
	 */
	public void image() throws IOException{
		response.setCharacterEncoding(Constants.CHARSET);
		ServletOutputStream out = null;
		//TODO可以使用缓存方式存储已经下载的图片
		try{
			 out = response.getOutputStream();
			 Article art = articleManager.get(entity.getArticleId());
			 if(null != art)
			 {
				 entity = imageManager.get(entity.getId());
				 //TODO 设置图片类型
			 	String url = entity.getHttpUrl();
				if (url.toLowerCase().endsWith(".gif")) {
					response.setContentType("image/gif;");
				} else if (url.toLowerCase().endsWith(".png")) {
					response.setContentType("image/png;");
				} else if (url.toLowerCase().endsWith(".jpg")){
					response.setContentType("image/jpg;");
				}else{
					response.setContentType("image/jpg;");
				}
				byte[] body = HttpClientUtils.getResponseBodyAsByte(art.getArticleUrl(), null, url);
				if(null != body && body.length > 0)
				{
					response.setContentLength(body==null?0:body.length);
					out.write(body);
				}
			 }
			 //TODO 图片类型
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 间接将图片发送给前台
	 *
	 */
	public void icon() throws IOException{
		ServletOutputStream out = null;
		//TODO可以使用缓存方式存储已经下载的图片
		try{
			 out = response.getOutputStream();
			 Article art = articleManager.get(entity.getArticleId());
			 if(null != art)
			 {
				 entity = imageManager.get(entity.getId());
				 //TODO 设置图片类型
			 	String url = entity.getImgUrl();
				if (url.toLowerCase().endsWith(".gif")) {
					response.setContentType("image/gif;");
				} else if (url.toLowerCase().endsWith(".png")) {
					response.setContentType("image/png;");
				} else if (url.toLowerCase().endsWith(".jpg")){
					response.setContentType("image/jpg;");
				}else{
					response.setContentType("image/jpg;");
				}
				byte[] body = HttpClientUtils.getResponseBodyAsByte(art.getArticleUrl(), null, url);
				if(null != body && body.length > 0)
				{
					response.setContentLength(body==null?0:body.length);
					out.write(body);
					out.flush();
				}
			 }
			 Thread.sleep(1500);
			 //TODO 图片类型
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != out){
				out.close();
			}
		}
		return;
	}
	
	public JMagickScale getTp() {
		return tp;
	}

	public void setTp(JMagickScale tp) {
		this.tp = tp;
	}

	public Image getEntity() {
		return entity;
	}

	public void setEntity(Image entity) {
		this.entity = entity;
	}

}
