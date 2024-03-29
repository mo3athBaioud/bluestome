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
import com.takesoon.oms.ssi.entity.ArticleDoc;
import com.takesoon.oms.ssi.service.ArticleDocManager;
import com.takesoon.oms.ssi.service.ImageCacheManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

@Namespace("/admin")
@Action("articledoc")
@Results( {
	@Result(name = "success", location = "/WEB-INF/pages/admin/articledoc.jsp"),
	@Result(name = "alllist", location = "/WEB-INF/pages/admin/doclist.jsp"),
})
public class ArticleDocAction extends CRUDActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ArticleDocManager articleDocManager;
	
	@Autowired	
	private ImageCacheManager imageCacheManager;
	
	private ArticleDoc entity;
	
	private Integer webId = 0;
	
	public static String ALLLIST = "alllist";

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
	
	
	public String allList(){
		return ALLLIST;
	}

	@Override
	public void list() throws IOException {
		response.setCharacterEncoding(Constants.CHARSET);
		PrintWriter out = null;
		try{
			 out = getOut(response);
			 if(null != articleDocManager){
				 int c = articleDocManager.getTotalBySql(entity);
				 if(c > 0){
					 List<ArticleDoc> list = articleDocManager.getListBySql(entity, start, limit);
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
	 * 中转显示内容
	 * @throws IOException
	 */
	public void doc() throws IOException{
		//TODO 
		response.setCharacterEncoding(Constants.CHARSET);
		ServletOutputStream out = null;
		byte[] body = null;
		try{
			 out = response.getOutputStream();
			 entity = articleDocManager.get(id.intValue());
			 if(null != entity){
				 String url = entity.getUrl();
				 if(HttpClientUtils.validationURL(url)){
					 HashMap<String, Object> result = HttpClientUtils.getResponse(url);
					 response.setContentType((String)result.get("contentType"));
					//缓存图片对象
					if(null != imageCacheManager.getByte(url))
					{
						body = imageCacheManager.getByte(url);
						logger.info(" >> get body from cache by ["+url+"]");
					}else
					{
						body = (byte[])result.get("body");
						if(null != body && body.length > 0)
						{
							imageCacheManager.putByte(url, body);
							logger.info(" >> put body to cache by ["+url+"]");
						}
					}
					if(null != body && body.length > 0)
					{
						response.setHeader("Cache-Control", "max-age="+(3600*24*365));
						response.setContentLength(body==null?0:body.length);
						out.write(body);
						out.flush();
					}
				}
			 }
		}catch(Exception e){
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 清理所选文章的缓存
	 * @throws IOException
	 */
	public void clearCache() throws IOException{
		//TODO 
		response.setCharacterEncoding(Constants.CHARSET);
		ServletOutputStream out = null;
		int i = 0;
		try{
			 out = response.getOutputStream();
			 for(Long id:ids)
			 {
				 entity = articleDocManager.get(id.intValue());
				 if(null != entity){
					 String url = entity.getUrl();
					 imageCacheManager.removeByte(url);
					 i++;
				 }
			 }
			 if(i == ids.length)
			 {
				 out.println("{success:true,msg:'清理文章数为["+i+"]的缓存成功!'}");
			 }else{
				 out.println("{success:false,msg:'清理缓存失败,未找到文章ID["+id.intValue()+"]的数据'}");
			 }
		}catch(Exception e){
			 out.println("{success:false,msg:'清理缓存出现异常，异常【"+e.getMessage()+"】'}");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
			i = 0;
		}
	}

	public ArticleDoc getEntity() {
		return entity;
	}

	public void setEntity(ArticleDoc entity) {
		this.entity = entity;
	}

	public Integer getWebId() {
		return webId;
	}

	public void setWebId(Integer webId) {
		this.webId = webId;
	}

	public String getALLLIST() {
		return ALLLIST;
	}

	public void setALLLIST(String alllist) {
		ALLLIST = alllist;
	}

	
}
