package com.ssi.acl.dal.dao;

import java.util.*;

import com.ssi.acl.dal.domain.SpInfo;

public interface ISpInfoDAO {

	/**
	 * 根据条件查找SP
	 * @param map
	 * @return
	 */
	List<SpInfo> find(HashMap map);
	
	/**
	 * 添加记录
	 * @param spinfo
	 * @return
	 */
	int insert(SpInfo spinfo);
	
	/**
	 * 更新记录
	 * @param spinfo
	 * @return
	 */
	int update(SpInfo spinfo);
	
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
