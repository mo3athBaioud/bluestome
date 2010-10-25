package com.ssi.common.dal;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import com.ssi.common.utils.SpringServiceUtils;

public class JdbcUtils implements InitializingBean {
	private JdbcUtils() {
	}

	private static JdbcUtils instance;

	public static JdbcUtils getInstance() {
		if (instance == null) {
			instance = (JdbcUtils) SpringServiceUtils.getService("jdbcUtils");
		}
		return instance;
	}
	
	private String defaultDb = DBRoute.DEFAULT_DB;

	private DBRouteConfig dbRouteConfig;
	private Map<String, JdbcTemplate> jdbcTemplateList = new HashMap<String, JdbcTemplate>();

	/**
	 * jdbcTemplateList接受spring注入
	 */
	public void afterPropertiesSet() throws Exception {
		if (jdbcTemplateList == null || jdbcTemplateList.isEmpty()) {
			throw new BeanInitializationException(
					"jdbc templates need to be set.");
		}
	}

	public void setJdbcTemplateList(Map<String, JdbcTemplate> jdbcTemplateList) {
		this.jdbcTemplateList = jdbcTemplateList;
	}

	public void setDefaultDb(String defaultDb) {
		this.defaultDb = defaultDb;
	}

	/**
	 * 
	 * @param dbKey
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate(String dbKey) {
		return jdbcTemplateList.get(dbKey);
	}

	public JdbcTemplate getJdbcTemplate() {
		return getJdbcTemplate(defaultDb);
	}

	public static boolean checkExsit(final String sql) {
		return (Boolean) getInstance().getJdbcTemplate().execute(
				new StatementCallback() {

					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						boolean ret = false;
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next())
							ret = true;

						return ret;
					}

				});
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getHashValue(final String sql) {
		return (HashMap<String, String>) getInstance().getJdbcTemplate()
				.execute(new StatementCallback() {

					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						HashMap<String, String> hp = new HashMap<String, String>();
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							int cols = rs.getMetaData().getColumnCount();
							for (int i = 0; i < cols; i++) {
								hp.put(rs.getMetaData().getColumnName(i + 1)
										.toLowerCase(), rs.getString(i + 1));
							}
						}
						return hp;
					}

				});
	}

	/**
	 * 取得指定查询结果的第一行的第一列的内容，并且将内容保存为String类型
	 * 
	 * @param sql
	 * @param con
	 * @return @
	 */
	public static String getValue(final String sql) {
		return (String) getInstance().getJdbcTemplate().execute(
				new StatementCallback() {

					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						String ret = "";
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next())
							ret = rs.getString(1);

						return ret;
					}

				});
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getValues(final String sql) {
		return (ArrayList<String>) getInstance().getJdbcTemplate().execute(
				new StatementCallback() {

					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						ArrayList<String> values = new ArrayList();
						ResultSet rs = stmt.executeQuery(sql);
						while (rs.next())
							values.add(rs.getString(1));

						return values;
					}

				});
	}

	/**
	 * 根据SQL语句，返回结果集的第一列为数组
	 * 
	 * @param sql
	 * @return
	 * @throws java.lang.Exception
	 */
	public static String[] getValueArray(String sql) {
		ArrayList<String> list = JdbcUtils.getValues(sql);
		String ret[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = (String) list.get(i);
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static String[][] getResultSetArray(final String sql) {
		return (String[][]) getInstance().getJdbcTemplate().execute(
				new StatementCallback() {

					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						String[][] ret = null;

						ArrayList values = new ArrayList();
						ResultSet rs = stmt.executeQuery(sql);
						int cols = rs.getMetaData().getColumnCount();
						while (rs.next()) {
							String[] row = new String[cols];
							for (int i = 0; i < cols; i++) {
								row[i] = rs.getString(i + 1);
							}
							values.add(row);
						}
						ret = new String[values.size()][cols];
						for (int i = 0; i < values.size(); i++) {
							String[] row = (String[]) (values.get(i));
							for (int j = 0; j < cols; j++) {
								ret[i][j] = row[j];
							}
						}

						return ret;
					}

				});
	}

	public static String[] getFirstResultSetArray(final String sql) {

		return (String[]) getInstance().getJdbcTemplate().execute(
				new StatementCallback() {
					public Object doInStatement(Statement stmt)
							throws SQLException, DataAccessException {
						String[] results = null;

						ResultSet rs = stmt.executeQuery(sql);
						int cols = rs.getMetaData().getColumnCount();

						while (rs.next()) {
							results = new String[cols];
							for (int i = 0; i < cols; i++) {
								results[i] = rs.getString(i + 1);
							}
						}

						return results;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public static List<HashMap> executeQueryForList(
			final String sql) {
		return (List<HashMap>) getInstance().getJdbcTemplate()
				.execute(new StatementCallback() {
					public Object doInStatement(Statement st)
							throws SQLException, DataAccessException {
						ResultSet rs = st.executeQuery(sql);

						List<HashMap> resultList = new ArrayList<HashMap>();

						// 检索此 ResultSet 对象的列的编号、类型和属性。
						ResultSetMetaData rsmd = rs.getMetaData();
						// 得到当前的列数
						int colCount = rsmd.getColumnCount();

						while (rs.next()) { // while控制行数
							HashMap<String, String> result = new HashMap<String, String>();
							for (int i = 1; i <= colCount; i++) {// for循环控制列数

								// 得到当前列的列名
								String name = rsmd.getColumnName(i);
								// 得到当前列的值
								String value = rs.getString(i);
								result.put(name, value);

							}
							resultList.add(result);
						}
						return resultList;
					}
				});
	}

	public DBRouteConfig getDbRouteConfig() {
		return dbRouteConfig;
	}

	public void setDbRouteConfig(DBRouteConfig dbRouteConfig) {
		this.dbRouteConfig = dbRouteConfig;
	}
}
