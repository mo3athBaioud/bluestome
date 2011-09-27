package com.takesoon.oms.ssi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.skymobi.webframework.ssh3.core.orm.hibernate.CURDHibernateDao;
import com.takesoon.oms.ssi.entity.Image;

@Component
public class ImageDao extends CURDHibernateDao<Image,Integer> {
	
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
	public List<Image> getListBySQL(String sql) throws HibernateException{
		List<Image> list = new ArrayList<Image>();
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addEntity(Image.class);
		list = query.list();
		return list;
	}

}
