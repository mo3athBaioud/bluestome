package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public interface IUsgsService {

	
	/**
	 * 根据参数MAP查找地震信息
	 * @param map
	 * @return
	 */
	List<EarthQuakeInfo> find(HashMap map);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	int getCount(String colName,String value);

	
	/**
	 * 分页方法
	 * @param pageNo 页码
	 * @param pageSize 每页显示的记录数
	 * @param asc 是否顺序排列
	 * @return List<T>
	 */
	List<EarthQuakeInfo> getPageList(String colName,String value,Integer startIndex,Integer pageSize,boolean asc) throws Exception;
	
}
