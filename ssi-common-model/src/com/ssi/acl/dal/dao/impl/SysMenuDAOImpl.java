package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.ISysMenuDAO;
import com.ssi.acl.dal.domain.RoleSysMenu;
import com.ssi.acl.dal.domain.SysMenu;
import com.ssi.common.dal.BaseDAOImpl;

public class SysMenuDAOImpl extends BaseDAOImpl implements ISysMenuDAO {

	public int delete(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_SYSMENU_BY_ID",id, getRoute());
		return result;
	}

	public int deleteRoleSysMenu(Integer id) {
		int result = -1;
		result = getEntityDelegate().delete("DELETTE_TBL_ROLE_SYSMENU_BY_ID",id, getRoute());
		return result;
	}

	public List<SysMenu> find(HashMap map) {
		List<SysMenu> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_SYSMENU_BY_HASH", map, getRoute());
		return list;
	}

	public List<RoleSysMenu> findRoleSysMenu(HashMap map) {
		List<RoleSysMenu> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_ROLE_SYSMENU_BY_HASH", map, getRoute());
		return list;
	}

	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_SYSMENU_COUNT",map,getRoute());
		return count;
	}

	public int getCountRoleSysMenu(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_ROLE_SYSMENU_COUNT",map,getRoute());
		return count;
	}

	public int insert(SysMenu sysMenu) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_SYSMENU",sysMenu, getRoute());
		return result;
	}

	public int insertRoleSysMenu(RoleSysMenu roleSysMenu) {
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_ROLE_SYSMENU",roleSysMenu, getRoute());
		return result;
	}

	public int update(SysMenu sysMenu) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_SYSMENU", sysMenu, getRoute());
		return result;
	}

	public int updateRoleSysMenu(RoleSysMenu roleSysMenu) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_ROLE_SYSMENU", roleSysMenu, getRoute());
		return result;
	}

}
