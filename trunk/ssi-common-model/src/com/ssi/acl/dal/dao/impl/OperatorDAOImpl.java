package com.ssi.acl.dal.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.acl.dal.dao.IOperatorDAO;
import com.ssi.acl.dal.domain.Operator;
import com.ssi.acl.dal.domain.OperatorRole;
import com.ssi.common.dal.BaseDAOImpl;

public class OperatorDAOImpl extends BaseDAOImpl implements IOperatorDAO {

	/**
	 * 根据HASHMAP查找操作员列表
	 * @param map
	 * @return
	 */
	public List<Operator> find(HashMap map) {
		List<Operator> list = null;
		list = getQueryDelegate().queryForList("FIND_OPERATOR_BY_HASH", map, getRoute());
		return list;
	}

	/**
	 * 获取指定条件下的记录总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map) {
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("GET_TBL_OPERATOR_COUNT", map, getRoute());
		return count;
	}

	/**
	 * 添加操作员
	 * @param operator
	 * @return
	 */
	public int insert(Operator operator){
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_OPERATOR", operator, getRoute());
		return result;
	}
	
	/**
	 * 更新操作员
	 * @param operator
	 * @return
	 */
	public int update(Operator operator){
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_OPERATOR", operator, getRoute());
		return result;
	}

	/**
	 * 删除操作员
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
		int result = -1;
		result = getEntityDelegate().delete("DELETE_OPERATOR_BY_ID", id, getRoute());
		return result;
	}
	
	/**
	 * 根据条件查询操作员角色
	 * @param map
	 * @return
	 */
	public List<OperatorRole> findOperatorRole(HashMap map){
		List<OperatorRole> list = null;
		list = getQueryDelegate().queryForList("FIND_TBL_OPERATOR_ROLE_BY_HASH", map, getRoute());
		return list;
	}
	
	
	/**
	 * 添加记录权限对象
	 * @param or
	 * @return
	 */
	public int insertOR(OperatorRole or){
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_TBL_OPERATOR_ROLE", or, getRoute());
		return result;
	}
	
	/**
	 * 更新操作员角色
	 * @param or
	 * @return
	 */
	public int updateOR(OperatorRole or){
		int result = -1;
		result = getEntityDelegate().update("UPDATE_TBL_OPERATOR_ROLE", or, getRoute());
		return result;
	}
	
	/**
	 * 删除操作员橘色
	 * @param or
	 * @return
	 */
	public int deleteOR(Integer id){
		int result = -1;
		result = getEntityDelegate().delete("DELETE_TBL_OPERATOR_ROLE_BY_ID",id,getRoute());
		return result;
	}
	
	/**
	 * 根据条件查找操作员角色数量
	 * @param map
	 * @return
	 */
	public int getORCount(HashMap map){
		int count = 0;
		count = (Integer)getQueryDelegate().queryForObject("FIND_TBL_OPERATOR_ROLE_COUNT_BY_HASH", map, getRoute());
		return count;
	}
	
}
