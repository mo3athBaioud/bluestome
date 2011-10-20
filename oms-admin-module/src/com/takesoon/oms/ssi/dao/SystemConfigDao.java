package com.takesoon.oms.ssi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.skymobi.webframework.ssh3.core.orm.hibernate.CURDHibernateDao;
import com.takesoon.oms.ssi.entity.SystemConfig;

@Component
public class SystemConfigDao extends CURDHibernateDao<SystemConfig,Integer> {
	
	public int getTotalBySQL(String sql) throws HibernateException{
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addScalar("total", Hibernate.INTEGER);
		c = (Integer)query.uniqueResult();
		return c;
	}

	/**
	 * 根据SQL获取查询语句
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public List<SystemConfig> getListBySQL(String sql) throws HibernateException{
		List<SystemConfig> list = new ArrayList<SystemConfig>();
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addEntity(SystemConfig.class);
		list = query.list();
		return list;
	}
	
}
