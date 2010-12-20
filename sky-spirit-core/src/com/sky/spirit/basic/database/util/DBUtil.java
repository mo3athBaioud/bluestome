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
 * Description: ʵ�������ݿ�������ص�һЩ���� <br>
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
	 * enableLocalDebug: �Ƿ��ڱ��ص��ԡ�<br>
	 * ֵΪtrueʱ�����������Դʧ����ʹ��DriverManager�����ݿ⽨�����ӣ� ���Ϊfalse��ֻ��������Դ�������ݿ����ӡ�
	 * Ĭ��Ϊfalse��<br>
	 * ��ͨ��ϵͳ����jdbc.enable_local_debug=true����enableLocalDebugΪtrue�����ñ��ص��ԣ�<br>
	 * ����JVM parameter�� -Djdbc.enable_local_debug=true
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
	 * ����Ӧ�÷���������Դ��������Դ�л�����ݿ����ӡ�<br>
	 * <br>
	 * �ڱ��ص���ʱ�����������Դʧ�ܲ���enableLocalDebug==true
	 * �����ϵͳ����ʹ��java.sql.DriverManager�������ӡ�<br>
	 * ���ص���ʱ�����õ�ϵͳ�������£�<br>
	 * <p>
	 * #jdbc���������� <br>
	 * jdbc.driver=<i>oracle.jdbc.driver.OracleDriver</i> <br>
	 * <br>
	 * #���ݿ����Ӵ�<br>
	 * jdbc.url=<i>jdbc:oracle:thin:@10.2.33.1:1521:xyz1</i> <br>
	 * <br>
	 * #���ݿ��û���<br>
	 * jdbc.username=<i>taipinglifetest</i> <br>
	 * <br>
	 * #���ݿ��û�����<br>
	 * jdbc.password=<i>ftp123</i> <br>
	 * </p>
	 * ��ͨ��JVM������������ϵͳ���ԣ�<br>
	 * -Djdbc.driver=oracle.jdbc.driver.OracleDriver
	 * -Djdbc.url=jdbc:oracle:thin:@10.2.33.1:1521:xyz1
	 * -Djdbc.username=taipinglifetest -Djdbc.password=ftp123
	 * 
	 * @return Connection
	 * @throws NamingException
	 *             �������Դ����ʧ��
	 * @throws SQLException
	 *             ����������ݿ�����ʧ��
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
	 * ����ѯ�����װ��List��<br>
	 * List��Ԫ������Ϊ��װһ�����ݵ�Map��Map keyΪ�ֶ�������д����valueΪ��Ӧ�ֶ�ֵ
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
	 * �ر�ResultSet��Statement��Connection
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
	 * ������sql�е� <b><font color="red">'</font></b> �滻�� <b><font
	 * color="blue">'</font><font color="red">'</font></b>
	 * 
	 * @param sql
	 *            �������sql���
	 * @return �滻�˵����ź��sql���
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
