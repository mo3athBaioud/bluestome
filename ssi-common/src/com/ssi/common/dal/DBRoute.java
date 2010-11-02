package com.ssi.common.dal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author wangqi
 * @version $Id:DBRoute.java Apr 28, 2009 11:16:55 AM wangqi $
 */
public class DBRoute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257554050781719469L;

	public static final String DEFAULT_DB = "reader";

	public static final String LOG_DB = "log";

	public static final String REPORT_DB = "report";

	private String xid = null;

	private DBRoute(String xid) {
		this.xid = xid;
	}

	/**
	 * key is xid, value is data source
	 */
	private static Map<String, DBRoute> routes = new HashMap<String, DBRoute>();

	public static DBRoute getDefaultRoute() {
		return create(DEFAULT_DB);
	}

	public static DBRoute getLogRoute() {
		return create(LOG_DB);
	}

	public static DBRoute getReportRoute() {
		return create(REPORT_DB);
	}

	public static DBRoute getRoute(String xid) {
		return create(xid);
	}

	public static DBRoute create(String xid) {
		if (routes.get(xid) == null) {
			routes.put(xid, new DBRoute(xid));
		}
		return routes.get(xid);
	}

	/**
	 * @return the xid
	 */
	public String getXid() {
		return xid;
	}

	/**
	 * @param xid
	 *            the xid to set
	 */
	public void setXid(String xid) {
		this.xid = xid;
	}

}
