package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.Dictionary;

public interface IDictionaryDAO {

	/**
	 * 根据条件查询数据
	 * @param map
	 * @return
	 */
	List<Dictionary> find(HashMap map);
	
	/**
	 * 添加记录
	 * @param dic
	 * @return
	 */
	int insert(Dictionary dic);
	
	/**
	 * 更新记录
	 * @param dic
	 * @return
	 */
	int update(Dictionary dic);
	
	/**
	 * 删除记录
	 * @param dic
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据条件获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
