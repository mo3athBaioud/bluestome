package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.ISpInfoDAO;
import com.ssi.acl.dal.domain.SpInfo;
import com.ssi.common.dal.BaseDAOImpl;

public class SpInfoDAOImpl extends BaseDAOImpl implements ISpInfoDAO {

	/**
	 * 根据ID删除记录
	 */
	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETE_SPINFO_BY_ID",id,getRoute());
		return result;
	}

	/**
	 * 根据条件查找记录
	 */
	public List<SpInfo> find(HashMap map) {
		List<SpInfo> list = null;
		list = getQueryDelegate().queryForList("FIND_SP_BY_HASH",map,getRoute());
		return list;
	}

	/**
	 * 根据条件获取记录总数
	 */
	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_SPINFO_COUNT", map,getRoute());
		return 0;
	}

	/**
	 * 添加记录
	 */
	public int insert(SpInfo spinfo) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_SPINFO", spinfo, getRoute());
		return result;
	}

	/**
	 * 更新记录
	 */
	public int update(SpInfo spinfo) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_SPINFO", spinfo, getRoute());
		return result;
	}

}
