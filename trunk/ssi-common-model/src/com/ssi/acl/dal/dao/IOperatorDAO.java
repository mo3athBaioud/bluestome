package com.ssi.acl.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.domain.Operator;
import com.ssi.acl.dal.domain.OperatorRole;

public interface IOperatorDAO {

	/**
	 * 根据HASHMAP查找操作员列表
	 * @param map
	 * @return
	 */
	List<Operator> find(HashMap map);
	
	/**
	 * 获取指定条件下的记录总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 添加操作员
	 * @param operator
	 * @return
	 */
	int insert(Operator operator);
	
	/**
	 * 更新操作员
	 * @param operator
	 * @return
	 */
	int update(Operator operator);
	
	/**
	 * 删除操作员
	 * @param id
	 * @return
	 */
	int delete(Integer id);
	
	/**
	 * 根据条件查询操作员角色
	 * @param map
	 * @return
	 */
	List<OperatorRole> findOperatorRole(HashMap map);
	
	/**
	 * 添加记录权限对象
	 * @param or
	 * @return
	 */
	int insertOR(OperatorRole or);
	
	/**
	 * 更新操作员角色
	 * @param or
	 * @return
	 */
	int updateOR(OperatorRole or);
	
	/**
	 * 删除操作员橘色
	 * @param or
	 * @return
	 */
	int deleteOR(Integer id);
	
	/**
	 * 根据条件查找操作员角色数量
	 * @param map
	 * @return
	 */
	int getORCount(HashMap map);
}
