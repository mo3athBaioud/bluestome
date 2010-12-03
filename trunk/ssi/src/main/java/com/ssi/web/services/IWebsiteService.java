package com.ssi.web.services;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Website;

public interface IWebsiteService {

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
	 * 根据哈希表查找数据
	 * @param map
	 * @return
	 */
	List<Website> find(HashMap map);

}
