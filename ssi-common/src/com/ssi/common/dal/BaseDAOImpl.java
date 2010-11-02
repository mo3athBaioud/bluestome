package com.ssi.common.dal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseDAOImpl {
	protected final Log logger = LogFactory.getLog(getClass());

	private QueryDelegate queryDelegate = new QueryDelegate();
	private EntityDelegate entityDelegate = new EntityDelegate();
	
	private DBRoute route = DBRoute.getDefaultRoute();

	public QueryDelegate getQueryDelegate() {
		return queryDelegate;
	}

	public void setQueryDelegate(QueryDelegate queryDelegate) {
		this.queryDelegate = queryDelegate;
	}

	public EntityDelegate getEntityDelegate() {
		return entityDelegate;
	}

	public void setEntityDelegate(EntityDelegate entityDelegate) {
		this.entityDelegate = entityDelegate;
	}

	public DBRoute getRoute() {
		return route;
	}

	public void setRoute(DBRoute route) {
		this.route = route;
	}

}
