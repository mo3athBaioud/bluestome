package com.ssi.cms.web.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.cms.web.service.ISysconfigService;
import com.ssi.common.dal.domain.SysConfig;
import com.ssi.common.service.EntityServiceImpl;

public class SysconfigServiceImpl<SysConfig> extends EntityServiceImpl implements
		ISysconfigService<SysConfig> {
	/**
	 * 分页方法
	 * @param pageNo 页码
	 * @param pageSize 每页显示的记录数
	 * @param asc 是否顺序排列
	 * @return List<T>
	 */
	public List<SysConfig> getPageList(String colName,String value,Integer startIndex,Integer pageSize,boolean asc) throws Exception{
		HashMap map = new HashMap();
		if(null == colName || colName.equalsIgnoreCase("")){
			if(null != value && !"".equals(value)){
				map.put("name", value);
			}
		}else{
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}
		if(null == startIndex){
			startIndex = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		List<SysConfig>  list = entityDAO.find(map);
		return list;
	}
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	public int getCount(String colName,String value){
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			map.put(colName, value);
		}else{
			if(null != value && !"".equals(value)){
				map.put("name", value);
			}
		}
		return entityDAO.getCount(map);
		
	}

}
