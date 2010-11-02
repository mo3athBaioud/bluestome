package com.ssi.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.dal.dao.IWebsiteDAO;
import com.ssi.dal.domain.Count;
import com.ssi.dal.domain.Website;

public class WebsiteDAOImpl extends BaseDAOImpl implements IWebsiteDAO {

	/**
	 * 根据父ID查找网站子记录
	 * @param fatherId
	 * @return
	 */
	public List<Website> findByFatherId(Integer fatherId) {
		List<Website> list = new ArrayList<Website>();
		Object obj = getQueryDelegate().queryForList("GET_CHILD_WEBSITE_BY_FATHER_ID", fatherId,getRoute());
		if(null != obj)
			list = (List<Website>)obj;
		return list;
	}

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	public Website findById(Integer id) {
		Website website = null;
		Object obj = getQueryDelegate().queryForObject("GET_WEBSITE_BY_ID", id,getRoute());
		if(null != obj)
			website = (Website)obj;
		return website;
	}

	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = -1;
		count = getQueryDelegate().queryForCount("GET_WEBSITE_COUNT",map,getRoute());
		return count;
	}

	/**
	 * 查找图片记录数
	 * @param webid
	 * @return
	 */
	public List<Count> getImageCountByWebid(int webid){
		List<Count> list = new ArrayList<Count>();
		Object obj = getQueryDelegate().queryForList("GET_COUNT_BY_WEB_ID", webid,getRoute());
		if(null != obj)
			list = (List<Count>)obj;
		return list;
	}

}
