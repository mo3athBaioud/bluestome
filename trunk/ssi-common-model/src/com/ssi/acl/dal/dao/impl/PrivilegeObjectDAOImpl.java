package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IPrivilegeObjectDAO;
import com.ssi.acl.dal.domain.PrivilegeObject;
import com.ssi.acl.dal.domain.RolePriv;
import com.ssi.common.dal.BaseDAOImpl;

public class PrivilegeObjectDAOImpl extends BaseDAOImpl implements
		IPrivilegeObjectDAO {

	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_PRIVILEGEOBJECT_BY_ID", id, getRoute());
		return result;
	}

	public int deleteRolePriv(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_ROLE_PRIV_BY_ID", id, getRoute());
		return result;
	}

	public List<PrivilegeObject> find(HashMap map) {
		List<PrivilegeObject> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_PRIVILEGEOBJECT_BY_HASH", map, getRoute());
		return list;
	}

	public List<RolePriv> findRolePriv(HashMap map) {
		List<RolePriv> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_ROLE_PRIV_BY_HASH", map, getRoute());
		return list;
	}

	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_PRIVILEGEOBJECT_COUNT",map,getRoute());
		return count;
	}

	public int getCountRolePriv(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_ROLE_PRIV_COUNT",map,getRoute());
		return count;
	}

	public int insert(PrivilegeObject obj) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_PRIVILEGEOBJECT",obj,getRoute());
		return result;
	}

	public int insertRolePriv(RolePriv obj) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_ROLE_PRIV",obj,getRoute());
		return result;
	}

	public int update(PrivilegeObject obj) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_PRIVILEGEOBJECT",obj,getRoute());
		return result;
	}

	public int updateRolePriv(RolePriv obj) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_ROLE_PRIV",obj,getRoute());
		return result;
	}

}
