package com.ssi.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssi.common.dal.domain.Website;
import com.ssi.web.services.IWebsiteService;

public class IndexAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4231814370873773013L;
	private IWebsiteService websiteService;
	private Integer id;
	
	/**
	 * 首页
	 * @return
	 */
	public String index(){
		HashMap map = new HashMap();
		if(null == id || id < 1){
			map.put("parentId",0);
		}else{
			map.put("parentId",id);
		}
		List<Website> list = websiteService.find(map); 
		request.setAttribute("websiteList", list);
		return "main";
	}

	public IWebsiteService getWebsiteService() {
		return websiteService;
	}

	public void setWebsiteService(IWebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
