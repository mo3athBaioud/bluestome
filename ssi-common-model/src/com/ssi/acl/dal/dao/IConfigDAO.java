package com.ssi.acl.dal.dao;

import java.util.*;

import com.ssi.acl.dal.domain.Config;

public interface IConfigDAO {

	/**
	 * 
	 * @param map
	 * @return
	 */
	List<Config> find(HashMap map);
	
	/**
	 * 添加配置项记录
	 * @param config
	 * @return
	 */
	int insert(Config config);
	
	/**
	 * 修改配置项
	 * @param config
	 * @return
	 */
	int update(Config config);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
