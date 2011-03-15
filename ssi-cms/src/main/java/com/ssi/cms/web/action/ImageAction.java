package com.ssi.cms.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.ssi.cms.web.service.ImageIService;
import com.ssi.common.dal.domain.Image;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ImageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1602909887059048406L;

	private List<Image> imageList;
	
	private ImageIService imageService;

	private Image image;

	private Integer id;
	
	final static String URL_PREFIX = "http://192.168.0.100:8080/image";

	public String showImage() {
		try{
			imageList = imageService.findByArticleId(id);
		}catch(Exception e){
			logger.debug(">> ImageAction.showImage Exception:"+e);
		}
		return SUCCESS;
	}
	
	/**
	 * 重定向图片地址
	 * @throws IOException
	 */
	public void showImg() throws IOException{
		//展现的图片类型 1：大图 0：小图
		response.setCharacterEncoding("UTF-8");
		String type = "1";
		try{
			type = request.getParameter("type") == null ? "1":request.getParameter("type");
			image = imageService.findById(id);
			if(null != image){
				if(type.equals("1")){
					logger.info(request.getRemoteAddr()+"访问了["+image.getTitle()+"]大图片");
					if(null != image.getPictureFile()){
						response.sendRedirect(URL_PREFIX+image.getPictureFile().getName());
					}else{
						response.sendRedirect(image.getHttpUrl());
					}
				}else{
					logger.info(request.getRemoteAddr()+"访问了["+image.getTitle()+"]小图片");
					if(null != image.getPictureFile()){
						response.sendRedirect(URL_PREFIX+image.getPictureFile().getSmallName());
					}else{
						response.sendRedirect(image.getImgUrl());
					}
				}
			}else{
				logger.debug(">> ImageAction.showImg 未找到图片["+image.getTitle()+"]");
				response.sendRedirect(request.getContextPath()+"/images/nopic.jpg");
			}
		}catch(Exception e){
			logger.error(">> ImageAction.showImg 未找图片["+id+"]");
			response.sendRedirect(request.getContextPath()+"/images/404.gif");
		}
	}
	
	public void image() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try{
			imageList = imageService.getPageList(colName, value, start, limit,id, false);
			if(null  != imageList && imageList.size() > 0){
				int count = imageService.getCount(colName, value,id);
				logger.debug(">> ImageAction.image 查询数据成功");
				toJson(response, imageList,count);
			}else{
				logger.debug(">> ImageAction.image 未找到条件["+colName+"],关键字["+value+"]的有效数据");
				response.getWriter().print("{failure:true,msg:'未找到有效数据'}");
			}
		}catch(Exception e){
			logger.debug(">> ImageAction.image" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public ImageIService getImageService() {
		return imageService;
	}

	public void setImageService(ImageIService imageService) {
		this.imageService = imageService;
	}

	public void toJson(HttpServletResponse response, List list,int num) throws Exception {
		PrintWriter  out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer =new StringBuffer();
		Iterator it=list.iterator();
		buffer.append("{count:").append(num).append(",success:true,msg:'查询数据成功',image:");
		while(it.hasNext()){
			JSONObject  json=new JSONObject();
			Image image= (Image)it.next();
			json.put("d_id", image.getId());
			json.put("d_article_id",image.getArticleId());
			json.put("d_commentshowurl", image.getCommentshowurl());
			json.put("d_commentsuburl", image.getCommentsuburl());
			String str = sdf.format(image.getCreatetime());
			json.put("d_createtime", str);
			if(null != image.getPictureFile()){
				json.put("d_httpurl", URL_PREFIX+image.getPictureFile().getName());
				json.put("d_imgurl", URL_PREFIX+image.getPictureFile().getSmallName());
			}else{
				json.put("d_httpurl", image.getHttpUrl());
				json.put("d_imgurl", image.getImgUrl());
			}
			json.put("d_src_httpurl", image.getHttpUrl());
			json.put("d_src_imgurl", image.getImgUrl());
			json.put("d_intro", image.getIntro());
			json.put("d_link", image.getLink());
			json.put("d_name", image.getName());
			json.put("d_orderid", image.getOrderId());
			json.put("d_time", image.getTime());
			json.put("d_title", image.getTitle());
			jsonArr.add(json);
		}
    	buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}

	
}
