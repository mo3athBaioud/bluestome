package com.takesoon.oms.ssi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.skymobi.webframework.ssh3.core.orm.hibernate.CURDHibernateDao;
import com.takesoon.oms.ssi.entity.PictureFile;

@Component
public class PictureFileDao extends CURDHibernateDao<PictureFile,Integer> {
	
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
	public List<PictureFile> getListBySQL(String sql) throws HibernateException{
		List<PictureFile> list = new ArrayList<PictureFile>();
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addEntity(PictureFile.class);
		list = query.list();
		return list;
	}

}
