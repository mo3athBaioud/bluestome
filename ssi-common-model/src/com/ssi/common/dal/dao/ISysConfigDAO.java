package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.common.dal.domain.SysConfig;
import com.ssi.common.dal.domain.Website;

public interface ISysConfigDAO extends EntityDAO<SysConfig>{

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	SysConfig findById(Integer id);
	
	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	SysConfig findByName(String name);
	
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
	List<SysConfig> find(HashMap map);

}
