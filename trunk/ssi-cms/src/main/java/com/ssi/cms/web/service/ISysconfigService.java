package com.ssi.cms.web.service;

import java.util.List;

import com.ssi.common.service.IEntityService;

public interface ISysconfigService<SysConfig> extends IEntityService{

	/**
	 * 分页方法
	 * @param pageNo 页码
	 * @param pageSize 每页显示的记录数
	 * @param asc 是否顺序排列
	 * @return List<T>
	 */
	List<SysConfig> getPageList(String colName,String value,Integer startIndex,Integer pageSize,boolean asc) throws Exception;
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	int getCount(String colName,String value);
	
}
