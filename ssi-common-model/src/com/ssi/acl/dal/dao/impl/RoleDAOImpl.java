package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IRoleDAO;
import com.ssi.acl.dal.domain.Role;
import com.ssi.common.dal.BaseDAOImpl;

public class RoleDAOImpl extends BaseDAOImpl implements IRoleDAO {

	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_ROLE_BY_ID",id,getRoute());
		return result;
	}

	public List<Role> find(HashMap map) {
		List<Role> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_ROLE_BY_HASH", map, getRoute());
		return list;
	}

	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_ROLE_COUNT", map, getRoute());
		return count;
	}

	public int insert(Role role) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_ROLE",role, getRoute());
		return result;
	}

	public int update(Role role) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_ROLE",role,getRoute());
		return result;
	}

}
