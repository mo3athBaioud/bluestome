package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.Role;

public interface IRoleDAO {

	/**
	 * 根据HASH条件查找数据
	 * @param map
	 * @return
	 */
	List<Role> find(HashMap map);
	
	/**
	 * 添加角色数据
	 * @param role
	 * @return
	 */
	int insert(Role role);
	
	/**
	 * 更新角色对象
	 * @param role
	 * @return
	 */
	int update(Role role);
	
	/**
	 * 根据ID删除角色
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据HASH条件获取总记录数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
