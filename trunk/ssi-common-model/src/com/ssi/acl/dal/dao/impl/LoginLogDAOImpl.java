package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.ILoginLogDAO;
import com.ssi.acl.dal.domain.LoginLog;
import com.ssi.common.dal.BaseDAOImpl;

public class LoginLogDAOImpl extends BaseDAOImpl implements ILoginLogDAO {

	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_LOGINLOG_BY_ID", id, getRoute());
		return result;
	}

	public List<LoginLog> find(HashMap map) {
		List<LoginLog> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_LOGINLOG_BY_HASH", map, getRoute());
		return list;
	}

	public int insert(LoginLog loginLog) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_LOGINLOG", loginLog, getRoute());
		return result;
	}

	public int update(LoginLog loginLog) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_LOGINLOG", loginLog, getRoute());
		return result;
	}
	
	/**
	 * 根据HASHMAP中的条件获取记录总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_LOGINLOG_COUNT", map,getRoute());
		return count;
	}


}
