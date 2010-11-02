package com.ssi.common.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ssi.common.Paginator;
import com.ssi.common.utils.MapUtils;

public class QueryDelegate implements InitializingBean {
	private final Log logger = LogFactory.getLog(QueryDelegate.class);

	private DBRouteConfig dbRouteConfig;
	private Map<String, SqlMapClient> sqlMapList;
	private Map<String, SqlMapClientTemplate> sqlMapTemplateList = new HashMap<String, SqlMapClientTemplate>();

	public void afterPropertiesSet() throws Exception {
		if (sqlMapList == null || sqlMapList.isEmpty()) {
			throw new BeanInitializationException(
					"sql map client need to be set.");
		}
	}

	public void init() {
		if (null == sqlMapList) {
			throw new RuntimeException("数据库映射文件必须指定！");
		}

		// 创建SqlMapTemplate列表
		for (Iterator<String> it = sqlMapList.keySet().iterator(); it.hasNext();) {
			String dbKey = it.next();
			SqlMapClientTemplate sqlMT = new SqlMapClientTemplate();
			sqlMT.setSqlMapClient((SqlMapClient) sqlMapList.get(dbKey));
			sqlMapTemplateList.put(dbKey, sqlMT);
		}
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return getSqlMapClientTemplate(DBRoute.DEFAULT_DB);
	}

	public SqlMapClientTemplate getSqlMapClientTemplate(String dbKey) {
		return sqlMapTemplateList.get(dbKey);
	}

	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);

		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);
		if (st == null) {
			return new ArrayList();
		}

		long startTime = System.currentTimeMillis();

		List returnList = st.queryForList(statementName);

		long endTime = System.currentTimeMillis();

		logRunTime(statementName, dbName, endTime - startTime);

		return returnList;
	}

	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object parameterObject,
			DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);

		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);
		if (st == null) {
			return new ArrayList();
		}

		long startTime = System.currentTimeMillis();

		List returnList = st.queryForList(statementName, parameterObject);

		long endTime = System.currentTimeMillis();

		logRunTime(statementName, dbName, endTime - startTime);

		return returnList;
	}

	public Object queryForObject(String statementName, Object parameterObject,
			DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);

		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);
		if (st == null) {
			return null;
		}

		long startTime = System.currentTimeMillis();

		Object returnObject = st.queryForObject(statementName, parameterObject);

		long endTime = System.currentTimeMillis();

		logRunTime(statementName, dbName, endTime - startTime);

		return returnObject;
	}

	public Object queryForObject(String statementName, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);

		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);
		if (st == null) {
			return null;
		}

		long startTime = System.currentTimeMillis();

		Object returnObject = st.queryForObject(statementName);

		long endTime = System.currentTimeMillis();

		logRunTime(statementName, dbName, endTime - startTime);

		return returnObject;
	}

	/**
	 * 分页查询记录
	 * 
	 * @param countStatement
	 *            查询总条数SQL ID
	 * @param listStatement
	 *            查询SQL ID
	 * @param param
	 *            查询参数
	 * @param paginator
	 *            分页器
	 * @param dr
	 *            数据库路由
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryForPagedList(String countStatement, String listStatement,
			Object param, Paginator paginator, DBRoute dr) {
		Integer totalItem = queryForCount(countStatement, param, dr);
		int total = totalItem.intValue();

		paginator.setItems(total);

		List resultList = new ArrayList();

		if (total > 0) {
			resultList = queryForList(listStatement, param, paginator, dr);
		}

		return resultList;
	}

	/**
	 * 查询记录总数
	 * 
	 * @param countStatement
	 *            查询记录总数 SQL ID
	 * @param param
	 *            查询参数
	 * @param dr
	 *            数据库路由
	 * @return
	 */
	public Integer queryForCount(String countStatement, Object param, DBRoute dr) {
		Map paramsMap = MapUtils.objectToMap(param);
		return (Integer) queryForObject(countStatement, paramsMap, dr);
	}

	/**
	 * 查询记录集
	 * 
	 * @param listStatement
	 *            查询SQL ID
	 * @param param
	 *            查询参数
	 * @param paginator
	 *            分页器
	 * @param dr
	 *            数据库路由
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List queryForList(String listStatement, Object param,
			Paginator paginator, DBRoute dr) {
		Map paramsMap = MapUtils.objectToMap(param);

		paramsMap.put("_paging_", "y");
		paramsMap.put("_paging_index_ge_", paginator.getBeginIndex());
		paramsMap.put("_paging_index_lt_", paginator.getEndIndex());
		paramsMap.put("_paging_size_", paginator.getItemsPerPage());

		List result = queryForList(listStatement, paramsMap, dr);

		return result;
	}

	/**
	 * 用于调试SQL语句的执行时间.
	 */
	private void logRunTime(String statementName, String dbName, long runTime) {
		if (logger.isDebugEnabled()) {
			logger.debug("Sql " + statementName + " executed on " + dbName
					+ " databases. Run time estimated: " + runTime + "ms");
		}
	}

	/**
	 * @return the sqlMapList
	 */
	public Map<String, SqlMapClient> getSqlMapList() {
		return sqlMapList;
	}

	/**
	 * @param sqlMapList
	 *            the sqlMapList to set
	 */
	public void setSqlMapList(Map<String, SqlMapClient> sqlMapList) {
		this.sqlMapList = sqlMapList;
	}

	/**
	 * @return the sqlMapTemplateList
	 */
	public Map<String, SqlMapClientTemplate> getSqlMapTemplateList() {
		return sqlMapTemplateList;
	}

	/**
	 * @return the dbRouteConfig
	 */
	public DBRouteConfig getDbRouteConfig() {
		return dbRouteConfig;
	}

	/**
	 * @param dbRouteConfig
	 *            the dbRouteConfig to set
	 */
	public void setDbRouteConfig(DBRouteConfig dbRouteConfig) {
		this.dbRouteConfig = dbRouteConfig;
	}

	/**
	 * @param sqlMapTemplateList
	 *            the sqlMapTemplateList to set
	 */
	public void setSqlMapTemplateList(
			Map<String, SqlMapClientTemplate> sqlMapTemplateList) {
		this.sqlMapTemplateList = sqlMapTemplateList;
	}

}
