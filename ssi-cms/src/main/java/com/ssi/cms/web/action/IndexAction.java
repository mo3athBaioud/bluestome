package com.ssi.cms.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ssi.cms.web.service.IWebsiteService;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Image;
import com.ssi.common.dal.domain.Website;

public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4950621369694627816L;
	private String showImgUrl;
	private String militaryChina;
	private String message;
	private Integer id;
	
	private List<Article>  articleList;
	private List<Website> websiteList;
	private List<Image> imageList;
	private IWebsiteService websiteService;
	
	public String doExe() throws Exception {
		logger.debug("showImgUrl:"+showImgUrl);
		logger.debug("militaryChina:"+militaryChina);
		if(null == id){
			websiteList = websiteService.getRootWebSite();
		}else{
			websiteList = websiteService.findByParentId(id);
		}
		return SUCCESS;
	}
	
	public String ftl() throws Exception{
		message = "this is a freemark page";
		if(null == id){
			websiteList = websiteService.getRootWebSite();
		}else{
			websiteList = websiteService.findByParentId(id);
		}
		return SUCCESS;
	}
	
//	public String showArticle(){
//		articleList = articleDAO.findByWebId(id);
//		return SUCCESS;
//	}
//	
//	public String showImage(){
//		imageList = imageDAO.findByArticleId(id);
//		return SUCCESS;
//	}

	public String getMilitaryChina() {
		return militaryChina;
	}

	public void setMilitaryChina(String militaryChina) {
		this.militaryChina = militaryChina;
	}

	public String getShowImgUrl() {
		return showImgUrl;
	}

	public void setShowImgUrl(String showImgUrl) {
		this.showImgUrl = showImgUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

	public List<Website> getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(List<Website> websiteList) {
		this.websiteList = websiteList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public void toJson(HttpServletResponse response, List list) throws Exception {
		// TODO Auto-generated method stub
	}

	public IWebsiteService getWebsiteService() {
		return websiteService;
	}

	public void setWebsiteService(IWebsiteService websiteService) {
		this.websiteService = websiteService;
	}
	
	
}