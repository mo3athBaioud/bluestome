package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.Code;

public interface ICodeDAO {

	/**
	 * 根据HASHMAP查找记录
	 * @param map
	 * @return
	 */
	List<Code> find(HashMap map);
	
	/**
	 * 添加记录
	 * @param code
	 * @return
	 */
	int insert(Code code);
	
	/**
	 * 更新记录
	 * @param code
	 * @return
	 */
	int update(Code code);
	
	/**
	 * 根据条件获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
