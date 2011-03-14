package com.ssi.common.dal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class IbatisEntityDao<T> extends IbatisGenericDao implements
		EntityDAO<T> {
	/**
	 * DAO所管理的Entity类型.
	 */
	protected Class<T> entityClass;

	protected String primaryKeyName;

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public IbatisEntityDao() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 根据ID获取对象.
	 */
	public T findByPrimarykey(Serializable id) {
		return get(getEntityClass(), id);
	}

	/**
	 * 取得entityClass. <p/> JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public String getIdName(Class clazz) {
		return "id";
	}

	/**
	 * 分页查询.
	 */
	public Page pagedQuery(Map parameterObject, int start, int limit) {
		return pagedQuery(getEntityClass(), parameterObject, start, limit);
	}

	/**
	 * 分页查询.
	 */
	public Page pagedQuery(Map parameterObject, int start, int limit,
			String countSqlId, String pageQuerySqlId) {
		if (StringUtils.isNotBlank(pageQuerySqlId))
			return pagedQuery(getEntityClass(), parameterObject, start, limit,
					countSqlId, pageQuerySqlId);
		else {
			return pagedQuery(getEntityClass(), parameterObject, start, limit);
		}
	}

	/**
	 * 根据ID移除对象.
	 */
	public void deleteByPrimarykey(Serializable id) {
		removeById(getEntityClass(), id);
	}

	/**
	 * 保存对象. 为了实现IEntityDao 我在内部使用了insert和upate 2个方法.
	 */
	public Integer insert(T o) {
		return super._insert(o);
	}

	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}

	public List<T> queryForList(String statementId, Object parameters) {
		return super.queryForList(getEntityClass(), statementId, parameters);

	}

	public T queryForObject(String statementId, Object parameters) {
		return super.queryForObject(getEntityClass(), statementId, parameters);
	}

	public Integer update(String statementId, Object parameters) {
		return super.update(getEntityClass(), statementId, parameters);

	}

	public Integer update(T o) {
		return super._update(o);
	}
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		return _getCount(entityClass, map);
	}
	
}
