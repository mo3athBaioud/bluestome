package com.takesoon.oms.ssi.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.skymobi.webframework.ssh3.core.orm.hibernate.CURDHibernateDao;
import com.takesoon.oms.ssi.entity.Article;
import com.takesoon.oms.ssi.entity.ArticleDoc;

@Component
public class ArticleDocDao extends CURDHibernateDao<ArticleDoc,Integer> {
	
	private static Logger logger = LoggerFactory.getLogger(ArticleDocDao.class);
	
	public int getTotalBySQL(String sql) throws HibernateException{
		long start = System.currentTimeMillis();
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addScalar("total", Hibernate.INTEGER);
		c = (Integer)query.uniqueResult();
		long end = System.currentTimeMillis();
		System.out.println("> ArticleDocDao.getTotalBySQL 耗时:{"+(end-start)+"} ms");
		return c;
	}

	/**
	 * 根据SQL获取查询语句
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public List<ArticleDoc> getListBySQL(String sql) throws HibernateException{
		long start = System.currentTimeMillis();
		List<ArticleDoc> list = new ArrayList<ArticleDoc>();
		int c = 0;
		Query query = getSession().createSQLQuery(sql).addEntity(ArticleDoc.class);
		list = query.list();
		long end = System.currentTimeMillis();
		System.out.println("> ArticleDocDao.getListBySQL 耗时:{"+(end-start)+"} ms");
		return list;
	}

}
