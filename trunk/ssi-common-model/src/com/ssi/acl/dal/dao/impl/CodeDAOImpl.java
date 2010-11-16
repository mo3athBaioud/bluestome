package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.ICodeDAO;
import com.ssi.acl.dal.domain.Code;
import com.ssi.common.dal.BaseDAOImpl;

public class CodeDAOImpl extends BaseDAOImpl implements ICodeDAO {

	/**
	 * 根据HASHMAP查找记录
	 */
	public List<Code> find(HashMap map) {
		List<Code> list = null;
		list = getQueryDelegate().queryForList("GET_CODE_BY_HASH", map, getRoute());
		return list;
	}

	/**
	 * 添加记录
	 */
	public int insert(Code code) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_CODE",code,getRoute());
		return result;
	}

	/**
	 * 更新记录
	 */
	public int update(Code code) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_CODE", code, getRoute());
		return result;
	}
	
	/**
	 * 根据条件获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_CODE_COUNT", map, getRoute());
		return count;
	}

}
