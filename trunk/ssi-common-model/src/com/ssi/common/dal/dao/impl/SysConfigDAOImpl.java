package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.common.dal.dao.ISysConfigDAO;
import com.ssi.common.dal.domain.SysConfig;

public class SysConfigDAOImpl  extends IbatisEntityDao<SysConfig> implements ISysConfigDAO {

	public List<SysConfig> find(HashMap map) {
		List<SysConfig> list = new ArrayList<SysConfig>();
		list = getQueryDelegate().queryForList("QUERY_SYSCONFIG_BY_MAP", map, getRoute());
		return list;
	}

	public SysConfig findById(Integer id) {
		SysConfig config = null;
		config = (SysConfig)getQueryDelegate().queryForObject("GET_SYSCONFIG_BY_ID", id, getRoute());
		return config;
	}

	public SysConfig findByName(String name) {
		SysConfig config = null;
		config = (SysConfig)getQueryDelegate().queryForObject("GET_SYSCONFIG_BY_NAME", name, getRoute());
		return config;
	}

	public int getCount(HashMap map) {
		int result = -1;
		result = (Integer)getQueryDelegate().queryForObject("GET_SYSCONFIG_COUNT", map, getRoute());
		return result;
	}

}
