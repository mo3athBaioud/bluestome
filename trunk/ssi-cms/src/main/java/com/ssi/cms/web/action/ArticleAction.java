package com.ssi.cms.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.ssi.cms.web.service.ArticleDocIService;
import com.ssi.cms.web.service.IArticleService;
import com.ssi.cms.web.service.ImageIService;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.ArticleDoc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ArticleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1406444074920735186L;

	private List<Article> articleList;

	private List<ArticleDoc> articleDocList;

	private IArticleService articleService;
	
	private ImageIService imageService;

	private ArticleDocIService articleDocService;
	
	private Article article;

	private Integer id;
	
	private Integer[]  ids;

	public String showArticle() {
		try {
			articleList = articleService.findByWebId(id);
		} catch (Exception e) {
			logger.error(">> ArticleAction.showArticle.Exception: " + e);
		}
		return SUCCESS;
	}

	/**
	 * 以response方式响应客户端请求
	 * 
	 */
	public void list() throws Exception {
		response.setCharacterEncoding("UTF-8");
		limit = (limit == null ? 10 : limit);
		start = (start == null ? 0 : start);
		try {
			articleList = articleService.getPageList(colName, value, start,
					limit, false, null);
			if (null != articleList && articleList.size() > 0) {
				int count = articleService.getCount(colName, value, null);
				logger.debug(">> ArticleAction.article 查询数据成功");
				toJson(response, articleList, count);
			} else {
				logger.debug(">> ArticleAction.article 未找到条件[" + colName
						+ "],关键字[" + value + "]的有效数据");
				response.getWriter().print(
						"{failure:true,msg:'未找到条件[" + colName + "],关键字["
								+ value + "]的有效数据'}");
			}
		} catch (Exception e) {
			logger.debug(">> ArticleAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}
	
	/**
	 * 以response方式响应客户端请求
	 * 
	 */
	public void article() throws Exception {
		response.setCharacterEncoding("UTF-8");
		limit = (limit == null ? 10 : limit);
		start = (start == null ? 0 : start);
		try {
			if (null == id) {
				articleList = articleService.getPageList(colName, value, start,
						limit, false, null);
				if (null != articleList && articleList.size() > 0) {
					int count = articleService.getCount(colName, value, null);
					logger.debug(">> ArticleAction.article 查询数据成功");
					toJson(response, articleList, count);
				} else {
					logger.debug(">> ArticleAction.article 未找到条件[" + colName
							+ "],关键字[" + value + "]的有效数据");
					response.getWriter().print(
							"{failure:true,msg:'未找到条件[" + colName + "],关键字["
									+ value + "]的有效数据'}");
				}
			} else {
				articleList = articleService.getPageList(colName, value, start,
						limit, false, id);
				if (null != articleList && articleList.size() > 0) {
					int count = articleService.getCount(colName, value, id);
					logger.debug(">> ArticleAction.article 查询数据成功 总数：" + count);
					toJson(response, articleList, count);
				} else {
					logger.debug(">> ArticleAction.article 未找到条件[" + colName
							+ "],关键字[" + value + "]的有效数据");
					response.getWriter().print(
							"{failure:true,msg:'未找到条件[" + colName + "],关键字["
									+ value + "]的有效数据'}");
				}
			}
		} catch (Exception e) {
			logger.debug(">> ArticleAction.article" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}

	/**
	 * 以response方式响应客户端请求
	 * 
	 */
	public void articledoc() throws Exception {
		response.setCharacterEncoding("UTF-8");
		limit = (limit == null ? 10 : limit);
		start = (start == null ? 0 : start);
		try {
			if (null == id) {
				articleDocList = articleDocService.getPageList(colName, value,
						page.getStart(), page.getLimit(), false, null);
				if (null != articleDocList && articleDocList.size() > 0) {
					int count = articleDocService.getCount(colName, value, id);
					logger.debug(">> ArticleAction.articleDoc 查询数据成功");
					toArticleDocJson(response, articleDocList, count);
				} else {
					logger.debug(">> ArticleAction.article 未找到条件[" + colName
							+ "],关键字[" + value + "]的有效数据");
					response.getWriter().print(
							"{failure:true,msg:'未找到条件[" + colName + "],关键字["
									+ value + "]的有效数据'}");
				}
			} else {
				articleDocList = articleDocService.getPageList(colName, value,
						start, limit, false, id);
				if (null != articleDocList && articleDocList.size() > 0) {
					int count = articleDocService.getCount(colName, value, id);
					logger.debug(">> ArticleAction.articleDoc 查询数据成功 总数："
							+ count);
					toArticleDocJson(response, articleDocList, count);
				} else {
					logger.debug(">> ArticleAction.articleDoc 未找到条件[" + colName
							+ "],关键字[" + value + "]的有效数据");
					response.getWriter().print(
							"{failure:true,msg:'未找到条件[" + colName + "],关键字["
									+ value + "]的有效数据'}");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(">> ArticleAction.articleDoc" + e);
			response.getWriter().print("{failure:true,msg:'查询文章发生异常'}");
		}
	}

	public void update() throws Exception {
		response.setCharacterEncoding("UTF-8");
		try {
			/**
			logger.debug("article.id:" + article.getId());
			logger.debug("article.title" + article.getTitle());
			logger.debug("article.getArticleRealUrl:"
					+ article.getActicleRealUrl());
			logger.debug("article.getArticleXmlUrl:"
					+ article.getActicleXmlUrl());
			logger.debug("article.getArticleUrl:" + article.getArticleUrl());
			logger.debug("article.getText:" + article.getText());
			logger.debug("article.getCreateTime:" + article.getCreateTime());
			logger.debug("article.getWebId:" + article.getWebId());
			logger.debug("article.getWebSiteBean().getId:"
					+ article.getWebsite().getId());
			**/
			articleService.update(article);
			response.getWriter().print(
					"{success:true,msg:'更新数据[" + article.getTitle() + "]成功!'}");
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(">> ArticleAction.update.Exception:" + e);
			response.getWriter().print("{failure:true,msg:'更新数据失败'}");
		}
	}
	
	/**
	 * 启用数据
	 * 
	 * @throws IOException
	 */
	public void enable() throws IOException {
		response.setCharacterEncoding("UTF-8");
		boolean b = true;
		try {
			for(Integer id:ids){
				b = articleService.enable(id);
				if(!b){
					break;
				}
			}
			if (b) {
				response.getWriter().print("{success:true,msg:'启用记录成功!'}");
			} else {
				response.getWriter().print("{failure:true,msg:'启用字典数据失败!'}");
			}
		} catch (Exception e) {
			logger.debug(">> RuleAction.enable" + e);
			response.getWriter().print("{failure:true,msg:'启用字典数据发生异常'}");
		}

	}

	/**
	 * 禁用数据
	 * 
	 * @throws IOException
	 */
	public void disable() throws IOException {
		response.setCharacterEncoding("UTF-8");
		boolean b = true;
		try {
			for(Integer id:ids){
				b = articleService.disable(id);
				if(!b){
					break;
				}
			}
			if (b) {
				response.getWriter().print("{success:true,msg:'禁用记录成功!'}");
			} else {
				response.getWriter().print(
						"{failure:true,msg:'禁用字典数据失败,请检查该地址是否已经被禁用！'}");
			}
		} catch (Exception e) {
			logger.debug(">> RuleAction.disable" + e);
			response.getWriter().print("{failure:true,msg:'禁用字典数据发生异常'}");
		}

	}
	/**
	 * 删除记录
	 */
	public void delete() throws Exception {
		response.setCharacterEncoding("UTF-8");
		boolean b = true;
		try {
			for(Integer id:ids){
				b = articleService.delete(id);
				if(!b){
					logger.debug(" >> 删除文章失败!");
					break;
				}
			}
			if(b){
				response.getWriter().print(
						"{success:true,msg:'删除数据成功!'}");
			}else{
				response.getWriter().print(
						"{failure:true,msg:'删除数据失败!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(">> ArticleAction.update.Exception:" + e);
			response.getWriter().print("{failure:true,msg:'更新数据失败'}");
		}
		
	}
	
	/**
	 *  
	 *设置文章缩略图
	 */
	public void previewIcon() throws Exception{
		response.setCharacterEncoding("UTF-8");
		try {
			article = articleService.findById(id);
			if(!article.getActicleXmlUrl().toLowerCase().endsWith(".xml")){
				String iconUrl = request.getParameter("icon");
				if(null != iconUrl && !"".equals(iconUrl)){
					article.setActicleXmlUrl(iconUrl);
					boolean b = articleService.update(article);
					if(b){
						response.getWriter().print(
								"{success:true,msg:'设置文章[" + article.getTitle() + "]缩略图成功!'}");
					}else{
						response.getWriter().print(
								"{failure:true,msg:'设置文章[" + article.getTitle() + "]缩略图失败!'}");
					}
				}else{
					response.getWriter().print(
							"{failure:true,msg:'设置文章[" + article.getTitle() + "]缩略图失败，未获取缩略图地址!'}");
				}
			}else{
				response.getWriter().print(
						"{failure:true,msg:'设置文章[" + article.getTitle() + "]缩略图失败，该文章不允许设置缩略图!'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(">> ArticleAction.update.Exception:" + e);
			response.getWriter().print("{failure:true,msg:'更新数据失败'}");
		}
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public List<ArticleDoc> getArticleDocList() {
		return articleDocList;
	}

	public void setArticleDocList(List<ArticleDoc> articleDocList) {
		this.articleDocList = articleDocList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArticleDocIService getArticleDocService() {
		return articleDocService;
	}

	public void setArticleDocService(ArticleDocIService articleDocService) {
		this.articleDocService = articleDocService;
	}

	public void toJson(HttpServletResponse response, List list, int count)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer = new StringBuffer();
		Iterator it = list.iterator();
		buffer.append("{count:").append(count).append(
				",success:true,msg:'操作成功',article:");
		while (it.hasNext()) {
			JSONObject json = new JSONObject();
			Article article = (Article) it.next();
			json.put("d_id", article.getId());
			json.put("d_article_real_url", article.getActicleRealUrl());
			json.put("d_article_xml_url", article.getActicleXmlUrl());
			json.put("d_acticle_url", article.getArticleUrl());
			String str = sdf.format(article.getCreateTime());
			json.put("d_createtime", str);
			json.put("d_intro", article.getIntro());
			json.put("d_text", article.getText());
			json.put("d_title", article.getTitle());
			json.put("d_web_id", article.getWebId());
			int imgCount = imageService.getCount(null, null, article.getId());
			json.put("d_img_count", imgCount);
			jsonArr.add(json);
		}
		buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}

	public void toArticleDocJson(HttpServletResponse response, List list,
			int count) throws Exception {
		PrintWriter out = response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		response.setCharacterEncoding("UTF-8");
		JSONArray jsonArr = new JSONArray();
		StringBuffer buffer = new StringBuffer();
		Iterator it = list.iterator();
		buffer.append("{count:").append(count).append(
				",success:true,msg:'操作成功',articledoc:");
		while (it.hasNext()) {
			JSONObject json = new JSONObject();
			ArticleDoc articledoc = (ArticleDoc) it.next();
			json.put("d_id", articledoc.getId());
			json.put("d_url", articledoc.getUrl());
			json.put("d_author", articledoc.getAuthor());
			json.put("d_title", articledoc.getTitle());
			String str = sdf.format(articledoc.getCreateTime());
//			logger.debug(" >> articledoc.createtime:"+str);
			json.put("d_createtime", str);
			json.put("d_grade", articledoc.getGrade());
			json.put("d_tag", articledoc.getTag());
			json.put("d_status", articledoc.getStatus());
			json.put("d_modifytime", articledoc.getModifyTime());
			json.put("d_publish_time", articledoc.getPublishTime());
			json.put("d_web_id", articledoc.getWebId());
			jsonArr.add(json);
		}
		buffer.append(jsonArr.toString()).append("}");
		out.print(buffer.toString());
		out.close();
	}
	
	
	

	public IArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public ImageIService getImageService() {
		return imageService;
	}

	public void setImageService(ImageIService imageService) {
		this.imageService = imageService;
	}

	
	
}
