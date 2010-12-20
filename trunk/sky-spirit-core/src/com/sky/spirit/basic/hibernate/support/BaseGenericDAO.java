/**
 * 文件com.sky.spirit.basic.hibernate.support.BaseGenericDAO.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */

package com.sky.spirit.basic.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.sky.spirit.common.util.BeanUtils;

/**
 * 
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:17:01
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 * @param <T>
 */
public class BaseGenericDAO<T> extends HibernateDaoSupport {
	public Object get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.desc(orderBy)));
	}

	public Serializable save(Object o) {
		return getHibernateTemplate().save(o);
	}

	public void update(Object o) {
		getHibernateTemplate().update(o);
	}

	public void saveOrUpdate(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	public void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public Query createQuery(String hql, Object values[]) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++)
			query.setParameter(i, values[i]);

		return query;
	}

	public Criteria createCriteria(Class<T> entityClass, Criterion criterions[]) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Criterion acriterion[];
		int j = (acriterion = criterions).length;
		for (int i = 0; i < j; i++) {
			Criterion c = acriterion[i];
			criteria.add(c);
		}

		return criteria;
	}

	public Criteria createCriteria(Class<T> entityClass, String orderBy,
			boolean isAsc, Criterion criterions[]) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List find(String hql, Object values[]) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entityClass, orderBy, isAsc,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.list();
	}

	public Object findUniqueBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Page pagedQuery(String hql, int pageNo, int pageSize,
			Object values[]) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		String countQueryString = (new StringBuilder(" select count (*) "))
				.append(removeSelect(removeOrders(hql))).toString();
		List<T> countlist = getHibernateTemplate().find(countQueryString,
				values);
		long totalCount = ((Long) countlist.get(0)).longValue();
		if (totalCount < 1L) {
			return new Page();
		} else {
			int startIndex = Page.getStartOfPage(pageNo, pageSize);
			Query query = createQuery(hql, values);
			List list = query.setFirstResult(startIndex)
					.setMaxResults(pageSize).list();
			return new Page(startIndex, totalCount, pageSize, list);
		}
	}

	@SuppressWarnings("unchecked")
	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;
		org.hibernate.criterion.Projection projection = impl.getProjection();
		List<T> orderEntries;
		try {
			orderEntries = (List<T>) BeanUtils.forceGetProperty(impl,
					"orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		int totalCount = ((Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(projection);
		if (projection == null)
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}
		if (totalCount < 1) {
			return new Page();
		} else {
			int startIndex = Page.getStartOfPage(pageNo, pageSize);
			List<T> list = criteria.setFirstResult(startIndex).setMaxResults(
					pageSize).list();
			return new Page(startIndex, totalCount, pageSize, list);
		}
	}

	public Page pagedQuery(Class<T> entityClass, int pageNo, int pageSize,
			Criterion criterions[]) {
		Criteria criteria = createCriteria(entityClass, criterions);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	public Page pagedQuery(Class<T> entityClass, int pageNo, int pageSize,
			String orderBy, boolean isAsc, Criterion criterions[]) {
		Criteria criteria = createCriteria(entityClass, orderBy, isAsc,
				criterions);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	public boolean isUnique(Class<T> entityClass, Object entity,
			String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = createCriteria(entityClass, new Criterion[0])
				.setProjection(Projections.rowCount());
		String nameList[] = uniquePropertyNames.split(",");
		try {
			String as[];
			int j = (as = nameList).length;
			for (int i = 0; i < j; i++) {
				String name = as[i];
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(
						entity, name)));
			}

			String idName = getIdName(entityClass);
			Serializable id = getId(entityClass, entity);
			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return ((Integer) criteria.uniqueResult()).intValue() == 0;
	}

	public Serializable getId(Class<T> entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity,
				getIdName(entityClass));
	}

	@SuppressWarnings("unchecked")
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert
				.notNull(meta, (new StringBuilder("Class ")).append(clazz)
						.append(" not define in hibernate session factory.")
						.toString());
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, (new StringBuilder(String.valueOf(clazz
				.getSimpleName()))).append(
				" has no identifier property define.").toString());
		return idName;
	}

	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, (new StringBuilder(" hql : "))
				.append(hql).append(" must has a keyword 'from'").toString());
		return hql.substring(beginPos);
	}

	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		for (; m.find(); m.appendReplacement(sb, ""))
			;
		m.appendTail(sb);
		return sb.toString();
	}

}
