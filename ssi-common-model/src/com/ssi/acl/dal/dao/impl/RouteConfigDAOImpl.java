package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IRouteConfigDAO;
import com.ssi.acl.dal.domain.RouteConfig;
import com.ssi.common.dal.BaseDAOImpl;

public class RouteConfigDAOImpl extends BaseDAOImpl implements IRouteConfigDAO {

	
	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETE_TBL_ROUTE_CONFIG_BY_ID", id,getRoute());
		return result;
	}

	public List<RouteConfig> find(HashMap map) {
		List<RouteConfig> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_ROUTECONFIG_BY_HASH", map, getRoute());
		return list;
	}

	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_ROUTE_CONFIG_COUNT", map, getRoute());
		return count;
	}

	public int insert(RouteConfig routeConfig) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_ROUTECONFIG", routeConfig, getRoute());
		return result;
	}

	public int update(RouteConfig routeConfig) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_ROUTE_CONFIG", routeConfig, getRoute());
		return result;
	}

}
