package com.ssi.common.dal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.Assert;

public class IbatisGenericDao extends BaseDAOImpl {

	public static final String POSTFIX_INSERT = ".insert";

	public static final String POSTFIX_UPDATE = ".update";

	public static final String POSTFIX_DELETE = ".delete";

	public static final String POSTFIX_DELETE_PRIAMARYKEY = ".deleteByPrimaryKey";

	public static final String POSTFIX_SELECT = ".select";

	public static final String POSTFIX_GETALL = ".getAll";

	public static final String POSTFIX_SELECTMAP = ".selectByMap";

	public static final String POSTFIX_SELECTSQL = ".selectBySql";

	public static final String POSTFIX_COUNT = ".count";

	public static final String POSTFIX_QUERY = ".query";

	/**
	 * 根据ID获取对象
	 */
	public <T> T get(Class<T> entityClass, Serializable id) {
		T o = (T)getQueryDelegate().queryForObject(
				getStatementId(entityClass, IbatisGenericDao.POSTFIX_SELECT),
				id,getRoute());
		return o;
	}

	/**
	 * 新增对象
	 */
	public Integer _insert(Object o) {
		Integer result = -1;
		result = (Integer)getEntityDelegate().insert(
				getStatementId(o.getClass(), IbatisGenericDao.POSTFIX_INSERT),
				o,getRoute());
		return result;
	}

	/**
	 * 保存对象
	 */
	public Integer _update(Object o) {
		Integer result = -1;
		result = (Integer)getEntityDelegate().update(
				getStatementId(o.getClass(), IbatisGenericDao.POSTFIX_UPDATE),
				o,getRoute());
		return result;
	}

	/**
	 * 获取总数
	 * @param entityClass
	 * @param map
	 * @return
	 */
	public Integer _getCount(Class entityClass,HashMap map){
		Integer totalCount = (Integer) getQueryDelegate()
		.queryForObject(
				getStatementId(entityClass,
						IbatisGenericDao.POSTFIX_COUNT),
						map,getRoute());
		return totalCount;
	}
	
	/**
	 * 根据ID删除对象
	 */
	public <T> Integer removeById(Class<T> entityClass, Serializable id) {
		Integer result = getEntityDelegate().delete(
				getStatementId(entityClass,
						IbatisGenericDao.POSTFIX_DELETE_PRIAMARYKEY), id,getRoute());
		return result;
	}

	/**
	 * 分页查询函数，使用PaginatedList.
	 * 
	 * @param start
	 * @param limit
	 * @return 含17117717记录数和当前页数据的Page对象.
	 */
	public Page pagedQuery(Class entityClass, Map parameterObject, int start,
			int limit) {

		Assert.isTrue(start >= 0, "pageNo should start from 0");

		// 计算总数
		Integer totalCount = (Integer) getQueryDelegate()
				.queryForObject(
						getStatementId(entityClass,
								IbatisGenericDao.POSTFIX_COUNT),
						parameterObject,getRoute());

		// 如果没有数据则返回Empty Page
		Assert.notNull(totalCount, "totalCount Error");

		if (totalCount.intValue() == 0) {
			return new Page();
		}

		List list;
		int totalPageCount = 0;
		int startIndex = 0;

		// 如果pageSize小于0,则返回所有数捄1177,等同于getAll
		if (limit > 0) {

			// 计算页数
			totalPageCount = (totalCount / limit);
			totalPageCount += ((totalCount % limit) > 0) ? 1 : 0;

			// 计算skip数量
			if (totalCount > start) {
				startIndex = start;
			} else {
				startIndex = (totalPageCount - 1) * limit;
			}

			if (parameterObject == null)
				parameterObject = new HashMap();

			parameterObject.put("startIndex", startIndex);
			parameterObject.put("endIndex", limit);

			list = getQueryDelegate()
					.queryForList(
							getStatementId(entityClass,
									IbatisGenericDao.POSTFIX_QUERY),
							parameterObject,getRoute());

		} else {
			list = getQueryDelegate()
					.queryForList(
							getStatementId(entityClass,
									IbatisGenericDao.POSTFIX_QUERY),
							parameterObject,getRoute());
		}
		return new Page(startIndex, totalCount, limit, list);
	}

	/**
	 * 分页查询函数，使用PaginatedList.
	 * 
	 * @param start
	 * @param limit
	 * @return 含17117717记录数和当前页数据的Page对象.
	 */
	public Page pagedQuery(Class entityClass, Map parameterObject, int start,
			int limit, String countSqlId, String pageQuerySqlId) {

		Assert.isTrue(start >= 0, "pageNo should start from 0");

		// 计算总数
		Integer totalCount = (Integer) getQueryDelegate()
				.queryForObject(getStatementId(entityClass, countSqlId),
						parameterObject,getRoute());

		// 如果没有数据则返回Empty Page
		Assert.notNull(totalCount, "totalCount Error");

		if (totalCount.intValue() == 0) {
			return new Page();
		}

		List list;
		int totalPageCount = 0;
		int startIndex = 0;

		// 如果pageSize小于0,则返回所有数捄1177,等同于getAll
		if (limit > 0) {

			// 计算页数
			totalPageCount = (totalCount / limit);
			totalPageCount += ((totalCount % limit) > 0) ? 1 : 0;

			// 计算skip数量
			if (totalCount >= start) {
				startIndex = start;
			} else {
				startIndex = (totalPageCount - 1) * limit;
			}

			if (parameterObject == null)
				parameterObject = new HashMap();

			parameterObject.put("startIndex", startIndex);
			parameterObject.put("endIndex", limit);

			list = getQueryDelegate().queryForList(
					getStatementId(entityClass, pageQuerySqlId),
					parameterObject,getRoute());

		} else {
			list = getQueryDelegate().queryForList(
					getStatementId(entityClass, pageQuerySqlId),
					parameterObject,getRoute());
		}
		return new Page(startIndex, totalCount, limit, list);
	}

	/**
	 * get statement id in SQL Map file
	 * 
	 * @param entityClass
	 *            entity class
	 * @param suffix
	 *            suffix
	 * @return statement id
	 */
	private String getStatementId(Class entityClass, String suffix) {
		String className = entityClass.getName();
		String shortName = className.replace(entityClass.getPackage().getName()
				+ ".", "");
		return shortName.toLowerCase() + suffix;
	}

	public <T> List<T> queryForList(Class<T> entityClass, String statementId,
			Object parameters) {
		return getQueryDelegate().queryForList(
				getStatementId(entityClass, statementId), parameters,getRoute());
	}

	public <T> T queryForObject(Class<T> entityClass, String statementId,
			Object parameters) {
		return (T) getQueryDelegate().queryForObject(
				getStatementId(entityClass, statementId), parameters,getRoute());
	}

	public Integer update(Class entityClass, String statementId, Object parameters) {
		Integer result = -1;
		result = (Integer)getEntityDelegate().update(
				getStatementId(entityClass, statementId), parameters,getRoute());
		return result;
	}
}
