package com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.dal.domain.Count;
import com.ssi.dal.domain.Website;
public interface IWebsiteDAO {

	
	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	Website findById(Integer id);
	
	/**
	 * 根据父ID查找网站子记录
	 * @param fatherId
	 * @return
	 */
	List<Website> findByFatherId(Integer fatherId);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 查找图片记录数列表
	 * @param webid
	 * @return
	 */
	List<Count> getImageCountByWebid(int webid);
}
