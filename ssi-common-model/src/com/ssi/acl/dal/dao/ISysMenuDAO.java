package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.RoleSysMenu;
import com.ssi.acl.dal.domain.SysMenu;

public interface ISysMenuDAO {

	/**
	 * 根据条件查找菜单
	 * @param map
	 * @return
	 */
	List<SysMenu> find(HashMap map);
	
	/**
	 * 添加菜单记录
	 * @param sysMenu
	 * @return
	 */
	int insert(SysMenu sysMenu);
	
	/**
	 * 更新菜单记录
	 * @param sysMenu
	 * @return
	 */
	int update(SysMenu sysMenu);
	
	/**
	 * 根据ID删除菜单记录
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据条件获取记录总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);



	/**
	 * 根据条件查找菜单
	 * @param map
	 * @return
	 */
	List<RoleSysMenu> findRoleSysMenu(HashMap map);
	
	/**
	 * 添加菜单记录
	 * @param sysMenu
	 * @return
	 */
	int insertRoleSysMenu(RoleSysMenu sysMenu);
	
	/**
	 * 更新菜单记录
	 * @param sysMenu
	 * @return
	 */
	int updateRoleSysMenu(RoleSysMenu sysMenu);
	
	/**
	 * 根据ID删除菜单记录
	 * @param id
	 * @return
	 */
	int deleteRoleSysMenu(Integer id);
	
	/**
	 * 根据条件获取记录总数
	 * @param map
	 * @return
	 */
	int getCountRoleSysMenu(HashMap map);
}
