package com.ssi.web.services.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.common.dal.domain.Website;
import com.ssi.web.services.IWebsiteService;

public class WebsiteServiceImpl implements IWebsiteService {

	private IWebsiteDAO websiteDao;
	
	public List<Website> find(HashMap map) {
		return websiteDao.find(map);
	}

	public List<Website> findByFatherId(Integer fatherId) {
		return websiteDao.findByFatherId(fatherId);
	}

	public Website findById(Integer id) {
		return websiteDao.findById(id);
	}

	public int getCount(HashMap map) {
		return websiteDao.getCount(map);
	}

	public IWebsiteDAO getWebsiteDao() {
		return websiteDao;
	}

	public void setWebsiteDao(IWebsiteDAO websiteDao) {
		this.websiteDao = websiteDao;
	}
	
	
}
