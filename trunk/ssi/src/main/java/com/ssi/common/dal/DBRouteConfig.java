package com.ssi.common.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;

/**
 * 数据库路由配置（考虑扩展路由规则，路由策略等）
 * 
 * @author wangqi
 * @version $Id:DBRouteManager.java Apr 28, 2009 1:33:49 PM wangqi $
 */
public class DBRouteConfig implements InitializingBean {
	private static Log logger = LogFactory.getLog(DBRouteConfig.class);

	private List<String> allNodeNameList = new ArrayList<String>();

	public void init() {
		for (String dbKey : allNodeNameList) {
			DBRoute.create(dbKey);
		}
	}

	/**
	 * 根据xid返回该路由指定的数据库 根据客户端指定的DBRoute决定应该被查询的数据库
	 * 
	 * @param dbRoute
	 * @return
	 */
	public String routingDB(DBRoute dbRoute) {
		if (null == dbRoute) {
			throw new RuntimeException("无法确定路由，请检查参数！");
		}

		String xid = dbRoute.getXid();

		if (!StringUtils.isEmpty(xid)) {
			if (allNodeNameList.contains(xid)) {
				return xid;
			}
		}

		if (logger.isErrorEnabled()) {
			logger.error("Database [" + xid + "] undefined");
		}
		return null;
	}

	/**
	 * @return the allNodeNameList
	 */
	public List<String> getAllNodeNameList() {
		return allNodeNameList;
	}

	/**
	 * @param allNodeNameList
	 *            the allNodeNameList to set
	 */
	public void setAllNodeNameList(List<String> allNodeNameList) {
		this.allNodeNameList = allNodeNameList;
	}

	public void afterPropertiesSet() throws Exception {
		if (allNodeNameList == null || allNodeNameList.isEmpty()) {
			throw new BeanInitializationException("node need to be set.");
		}
	}

}
