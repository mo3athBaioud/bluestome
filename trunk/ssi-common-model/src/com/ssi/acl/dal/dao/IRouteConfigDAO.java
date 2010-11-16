package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.RouteConfig;

public interface IRouteConfigDAO {

	/**
	 * 根据条件查找记录
	 * @param map
	 * @return
	 */
	List<RouteConfig> find(HashMap map);
	
	/**
	 * 添加记录
	 * @param routeConfig
	 * @return
	 */
	int insert(RouteConfig routeConfig);
	
	/**
	 * 更新记录
	 * @param routeConfig
	 * @return
	 */
	int update(RouteConfig routeConfig);
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据条件获取记录总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
