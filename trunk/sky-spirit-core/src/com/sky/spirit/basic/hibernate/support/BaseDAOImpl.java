/**
 * �ļ�com.sky.spirit.basic.hibernate.support.BaseDAOImpl.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */


package com.sky.spirit.basic.hibernate.support;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.sky.spirit.basic.database.page.PagedStatement;
import com.sky.spirit.basic.database.page.PagedStatementOracleImpl;
import com.sky.spirit.basic.database.page.RowSetPage;
import com.sky.spirit.basic.exception.SkyException;
import com.sky.spirit.common.util.GenericsUtils;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:15:56
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDAOImpl<T> extends BaseGenericDAO<T> implements IBaseDAO<T> {
	protected Class<T> entityClass;

	public BaseDAOImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	protected Class<T> getEntityClass() {
		return entityClass;
	}

	public Object get(Serializable id) {
		return get(getEntityClass(), id);
	}

	public List<T> getAll() {
		return getAll(getEntityClass());
	}

	public List<T> getAll(String orderBy, boolean isAsc) {
		return getAll(getEntityClass(), orderBy, isAsc);
	}

	public void removeById(Serializable id) {
		removeById(getEntityClass(), id);
	}

	public void deleteById(Serializable id) {
		removeById(getEntityClass(), id);
	}

	public Criteria createCriteria(Criterion criterions[]) {
		return createCriteria(getEntityClass(), criterions);
	}

	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion criterions[]) {
		return createCriteria(getEntityClass(), orderBy, isAsc, criterions);
	}

	public List<T> findBy(String propertyName, Object value) {
		return findBy(getEntityClass(), propertyName, value);
	}

	public List<T> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		return findBy(getEntityClass(), propertyName, value, orderBy, isAsc);
	}

	public Object findUniqueBy(String propertyName, Object value) {
		return findUniqueBy(getEntityClass(), propertyName, value);
	}

	public boolean isUnique(Object entity, String uniquePropertyNames) {
		return isUnique(getEntityClass(), entity, uniquePropertyNames);
	}

	public void evit(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	public void remove(BaseEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public Page pagedQuery(int pageNo, int pageSize, String orderBy,
			boolean isAsc, Criterion criterions[]) {
		return pagedQuery(getEntityClass(), pageNo, pageSize, orderBy, isAsc,
				criterions);
	}

	public Page pagedQuery(int pageNo, int pageSize, Criterion criterions[]) {
		return pagedQuery(getEntityClass(), pageNo, pageSize, criterions);
	}

	public Page pagedQuery(Example example, int pageNo, int pageSize) {
		return pagedQuery(DetachedCriteria.forClass(getEntityClass()).add(
				example), pageNo, pageSize);
	}

	public Page pagedQuery(DetachedCriteria criteria, int pageNo, int pageSize) {
		return pagedQuery(criteria.getExecutableCriteria(getSession()), pageNo,
				pageSize);
	}

	public void batchDeleteByIds(List<T> ids) {
		String idName = getIdName(getEntityClass());
		getHibernateTemplate().deleteAll(findBy(idName, ids));
	}

	public int executeUpdate(String hql) {
		return createQuery(hql, new Object[0]).executeUpdate();
	}

	public Serializable insert(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	public List<T> findByExample(Object entity) {
		return getHibernateTemplate().findByExample(Example.create(entity));
	}

	public List<T> findByExample(Object entity, int offset, int pageSize) {
		return getHibernateTemplate().findByExample(Example.create(entity),
				offset, pageSize);
	}

	public List<T> findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public void delete(BaseEntity entity) {
		remove(entity);
	}

	public String getIdName(BaseEntity entity) {
		return getIdName(entity.getClass());
	}

	public Serializable save(BaseEntity entity) {
		return getHibernateTemplate().save(entity);
	}

	public void update(BaseEntity entity) {
		super.update(entity);
	}

	public void saveOrUpdate(BaseEntity entity) {
		super.saveOrUpdate(entity);
	}

	public RowSetPage getRowSetPage(String sql, Integer pageNo)
			throws SkyException {
		PagedStatement pst = null;
		RowSetPage returnSet = null;
		try {
			pst = new PagedStatementOracleImpl(sql, pageNo);
			returnSet = pst.executeQuery();
		} catch (SQLException e) {
			throw new SkyException(e.getMessage());
		} finally {
			pst.close();
		}
		return returnSet;
	}

}
