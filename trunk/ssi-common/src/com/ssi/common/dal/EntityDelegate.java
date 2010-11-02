package com.ssi.common.dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

public class EntityDelegate implements InitializingBean {
	private final Log logger = LogFactory.getLog(EntityDelegate.class);

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

	public Object insert(String statementName, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		Object returnObject = st.insert(statementName);
		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return returnObject;
	}

	public Object insert(String statementName, Object parameterObject,
			DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		Object returnObject = st.insert(statementName, parameterObject);
		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return returnObject;
	}

	@SuppressWarnings("unchecked")
	public void insertBatch(final String statementName, final List memberList,
			DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();

		st.execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {

				executor.startBatch();
				for (Object tObject : memberList) {
					executor.insert(statementName, tObject);
				}

				executor.executeBatch();
				return null;
			}
		});

		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return;
	}

	public int update(String statementName, Object parameterObject, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		int affectSize = st.update(statementName, parameterObject);
		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return affectSize;
	}

	public int update(String statementName, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		int affectSize = st.update(statementName);
		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return affectSize;
	}

	public int delete(String statementName, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		int affectSize = st.delete(statementName);
		long endTime = System.currentTimeMillis();

		logRunTime(statementName, dbName, endTime - startTime);

		return affectSize;
	}

	public int delete(String statementName, Object parameterObject, DBRoute dr) {
		String dbName = getDbRouteConfig().routingDB(dr);
		SqlMapClientTemplate st = sqlMapTemplateList.get(dbName);

		long startTime = System.currentTimeMillis();
		int affectSize = st.delete(statementName, parameterObject);
		long endTime = System.currentTimeMillis();
		logRunTime(statementName, dbName, endTime - startTime);

		return affectSize;
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
