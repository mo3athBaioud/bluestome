package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.PrivilegeObject;
import com.ssi.acl.dal.domain.RolePriv;

public interface IPrivilegeObjectDAO {

	/**
	 * 根据HASHMAP查找权限对象记录
	 * @param map
	 * @return
	 */
	List<PrivilegeObject> find(HashMap map);
	
	/**
	 * 添加权限对象
	 * @param obj
	 * @return
	 */
	int insert(PrivilegeObject obj);
	
	/**
	 * 更新权限对象
	 * @param obj
	 * @return
	 */
	int update(PrivilegeObject obj);
	
	/**
	 * 删除权限对象
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据记录查询记录总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);


	/**
	 * 根据HASHMAP查找权限对象记录
	 * @param map
	 * @return
	 */
	List<RolePriv> findRolePriv(HashMap map);
	
	/**
	 * 添加权限对象
	 * @param obj
	 * @return
	 */
	int insertRolePriv(RolePriv obj);
	
	/**
	 * 更新权限对象
	 * @param obj
	 * @return
	 */
	int updateRolePriv(RolePriv obj);
	
	/**
	 * 删除权限对象
	 * @param id
	 * @return
	 */
	int deleteRolePriv(Integer id);
	
	/**
	 * 根据记录查询记录总数
	 * @param map
	 * @return
	 */
	int getCountRolePriv(HashMap map);
}
