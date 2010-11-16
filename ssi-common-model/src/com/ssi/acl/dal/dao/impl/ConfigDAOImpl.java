package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IConfigDAO;
import com.ssi.acl.dal.domain.Config;
import com.ssi.common.dal.BaseDAOImpl;

public class ConfigDAOImpl extends BaseDAOImpl implements IConfigDAO {

	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<Config> find(HashMap map){
		List<Config> list = null;
		list = getQueryDelegate().queryForList("GET_CONFIG_BY_HASH", map, getRoute());
		return list;
	}
	
	/**
	 * 添加配置项记录
	 * @param config
	 * @return
	 */
	public int insert(Config config){
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_CONFIG", config, getRoute());
		return result;
	}
	
	/**
	 * 修改配置项
	 * @param config
	 * @return
	 */
	public int update(Config config){
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_CONFIG",config,getRoute());
		return result;
	}

	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_CONFIG_COUNT",map,getRoute());
		return count;
	}

}
