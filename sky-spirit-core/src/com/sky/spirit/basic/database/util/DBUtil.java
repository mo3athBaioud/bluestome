package com.sky.spirit.basic.database.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.common.util.spring.SpringBeanUtils;

//import oracle.sql.*;

/**
 * Title: DBUtil <br>
 * Description: 实现了数据库连接相关的一些功能 <br>
 * Copyright: Copyright (c) 2005 <br>
 * Company: Taiping <br>
 * 
 * @author wangtao <br>
 * @version 1.0 <br>
 */
public class DBUtil {

	private static Log log = LogFactory.getLog(DBUtil.class);

	private static Connection connectionFlag = null;

	/**
	 * enableLocalDebug: 是否在本地调试。<br>
	 * 值为true时如果查找数据源失败则使用DriverManager与数据库建立连接； 如果为false则只查找数据源建立数据库连接。
	 * 默认为false。<br>
	 * 可通过系统属性jdbc.enable_local_debug=true设置enableLocalDebug为true，启用本地调试：<br>
	 * 增加JVM parameter： -Djdbc.enable_local_debug=true
	 */
	@SuppressWarnings("unused")
	private static boolean enableLocalDebug = false;

	static {
		enableLocalDebug = Boolean.getBoolean("jdbc.enable_local_debug");
	}
	private static javax.sql.DataSource ds = null;

	private static void initDataSource() throws Exception {
		ds = (DataSource) SpringBeanUtils.getBean("dataSource");
	}

	/**
	 * 查找应用服务器数据源，从数据源中获得数据库连接。<br>
	 * <br>
	 * 在本地调试时如果查找数据源失败并且enableLocalDebug==true
	 * 则根据系统属性使用java.sql.DriverManager建立连接。<br>
	 * 本地调试时可配置的系统属性如下：<br>
	 * <p>
	 * #jdbc驱动程序名 <br>
	 * jdbc.driver=<i>oracle.jdbc.driver.OracleDriver</i> <br>
	 * <br>
	 * #数据库连接串<br>
	 * jdbc.url=<i>jdbc:oracle:thin:@10.2.33.1:1521:xyz1</i> <br>
	 * <br>
	 * #数据库用户名<br>
	 * jdbc.username=<i>taipinglifetest</i> <br>
	 * <br>
	 * #数据库用户密码<br>
	 * jdbc.password=<i>ftp123</i> <br>
	 * </p>
	 * 可通过JVM参数设置上述系统属性：<br>
	 * -Djdbc.driver=oracle.jdbc.driver.OracleDriver
	 * -Djdbc.url=jdbc:oracle:thin:@10.2.33.1:1521:xyz1
	 * -Djdbc.username=taipinglifetest -Djdbc.password=ftp123
	 * 
	 * @return Connection
	 * @throws NamingException
	 *             如果数据源查找失败
	 * @throws SQLException
	 *             如果建立数据库连接失败
	 */
	public static Connection getConnection() throws SQLException {
		try {
			initDataSource();

			connectionFlag = ds.getConnection();
			log.debug("reading Connection is open ......");

			return connectionFlag;
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			log.error(e);
		}
		return connectionFlag;
	}

	public static void warn() {
		// log.debug("current connection is closed?
		// "+connectionFlag.isClosed());
	}

	/**
	 * 将查询结果封装成List。<br>
	 * List中元素类型为封装一行数据的Map，Map key为字段名（大写），value为相应字段值
	 * 
	 * @param rs
	 *            ResultSet
	 * @return List
	 * @throws java.sql.SQLException
	 */
	public static List<Map<String, Object>> resultSetToList(ResultSet rs)
			throws java.sql.SQLException {

		List<Map<String, Object>> empty = Collections.emptyList();
		if (rs == null)
			return empty;

		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowData;
		while (rs.next()) {
			rowData = new HashMap<String, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}

	/**
	 * 关闭ResultSet、Statement和Connection
	 * 
	 * @param rs
	 *            ResultSet to be closed
	 * @param stmt
	 *            Statement or PreparedStatement to be closed
	 * @param conn
	 *            Connection to be closed
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (java.sql.SQLException ex) {
				ex.printStackTrace();
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (java.sql.SQLException ex) {
				ex.printStackTrace();
			}
		if (conn != null)
			try {
				if (!conn.isClosed()) {
					conn.close();
					log.debug("Connection is closed ......");
				}
			} catch (java.sql.SQLException ex) {
				ex.printStackTrace();
			}
	}

	/**
	 * 将参数sql中的 <b><font color="red">'</font></b> 替换成 <b><font
	 * color="blue">'</font><font color="red">'</font></b>
	 * 
	 * @param sql
	 *            待处理的sql语句
	 * @return 替换了单引号后的sql语句
	 */
	public static String sqlEscape(String sql) {
		if (sql == null || sql.indexOf("\'") < 0)
			return sql;

		StringBuffer tmp = new StringBuffer();
		int index = -1;
		int lastIndex = -1;
		while ((index = sql.indexOf("\'", lastIndex + 1)) > -1) {
			tmp.append(sql.substring(lastIndex + 1, index)).append("\'\'");
			lastIndex = index;
		}
		return tmp.toString();
	}
}
