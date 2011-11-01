package com.takesoon.oms.ssi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssi.common.utils.HttpClientUtils;
import com.takesoon.oms.ssi.common.Constants;
import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.WebsiteManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

/**
 * 缓存清理类
 * @author Bluestome.Zhang
 *
 */
//@Namespace("/admin")
//@Action("cache")
//@Results( {
//	@Result(name = "success", location = "/WEB-INF/pages/admin/cache.jsp"),
//})
public class CacheAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	
}
