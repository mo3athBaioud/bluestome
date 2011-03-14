package com.ssi.common.dal;

import java.io.Serializable;
import java.util.List;

/**
 * GenericDao DAO层泛型接口，定义基本的DAO功能
 * 
 * @author wl
 * @since 0.1
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类，必须实现Serializable接口
 * 
 * @see com.thinkon.commons.dao.GenericIbatisDao
 */
public abstract interface IGenericDao<T> {
	/**
	 * 根据主键查找对象
	 * 
	 * @param id
	 *            主键值
	 * @return 对象实体
	 */
	T findByPrimarykey(Serializable id);

	/**
	 * 新增对象到数据库
	 * 
	 * @param o
	 *            对象实体
	 */
	Integer insert(T o);

	/**
	 * 更新对象实体到数据库
	 * 
	 * @param o
	 *            对象实体
	 */
	Integer update(T o);

	/**
	 * 根据主键删除对象
	 * 
	 * @param id
	 *            主键值
	 */
	Integer deleteByPrimarykey(Serializable id);

	/**
	 * 更新对象信息
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 */
	Integer update(String statementId, Object parameters);

	/**
	 * sql查询单个对象
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 * @return 查询结果
	 */
	T queryForObject(String statementId, Object parameters);

	/**
	 * sql查询列表
	 * 
	 * @param statementId
	 *            sql语句名称后缀
	 * @param parameters
	 *            sql参数
	 * @return 查询结果
	 */
	List<T> queryForList(String statementId, Object parameters);
}
