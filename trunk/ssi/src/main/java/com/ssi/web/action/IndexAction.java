package com.ssi.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

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
		int pageSize = 10;
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		int tmpPageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0:Integer.parseInt(request.getParameter(pageIndexName))-1;
		System.out.println("pageName:"+pageIndexName+"\tpageValue:"+tmpPageIndex);
		HashMap map = new HashMap();
		if(null == id || id < 1){
			map.put("parentId",0);
		}else{
			map.put("parentId",id);
		}
		int count =  websiteService.getCount(map);
		map.put("limit", pageSize);
		map.put("offset", pageSize*tmpPageIndex);
		List<Website> list = websiteService.find(map); 
		request.setAttribute("resultSize", count);
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
