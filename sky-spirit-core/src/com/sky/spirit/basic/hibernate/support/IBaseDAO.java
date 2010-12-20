/**
 * �ļ�com.sky.spirit.basic.hibernate.support.IBaseDAO.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */


package com.sky.spirit.basic.hibernate.support;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.sky.spirit.basic.database.page.RowSetPage;
import com.sky.spirit.basic.exception.SkyException;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:17:25
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 * @param <T>
 */
public interface IBaseDAO<T> {
	public abstract Object get(Serializable serializable);

	public abstract List<T> getAll();

	public abstract Criteria createCriteria(Criterion acriterion[]);

	public abstract Criteria createCriteria(String s, boolean flag,
			Criterion acriterion[]);

	public abstract Serializable save(BaseEntity baseentity);

	public abstract void update(BaseEntity baseentity);

	public abstract void saveOrUpdate(BaseEntity baseentity);

	public abstract void remove(BaseEntity baseentity);

	public abstract void delete(BaseEntity baseentity);

	public abstract void removeById(Serializable serializable);

	public abstract Query createQuery(String s, Object aobj[]);

	public abstract List<T> find(String s, Object aobj[]);

	public abstract List<T> findBy(String s, Object obj);

	public abstract List<T> findBy(String s, Object obj, String s1, boolean flag);

	public abstract Object findUniqueBy(String s, Object obj);

	public abstract Page pagedQuery(String s, int i, int j, Object aobj[]);

	public abstract Page pagedQuery(Example example, int i, int j);

	public abstract Page pagedQuery(Criteria criteria, int i, int j);

	public abstract Page pagedQuery(DetachedCriteria detachedcriteria, int i,
			int j);

	public abstract Page pagedQuery(int i, int j, String s, boolean flag,
			Criterion acriterion[]);

	public abstract Page pagedQuery(int i, int j, Criterion acriterion[]);

	public abstract void batchDeleteByIds(List<T> list);

	public abstract void deleteById(Serializable serializable);

	public abstract boolean isUnique(Object obj, String s);

	public abstract void evit(Object obj);

	public abstract int executeUpdate(String s);

	public abstract Serializable insert(Object obj);

	public abstract List<T> findByExample(Object obj);

	public abstract List<T> findByExample(Object obj, int i, int j);

	public abstract List<T> findByCriteria(DetachedCriteria detachedcriteria);

	RowSetPage getRowSetPage(String sql, Integer pageNo)
			throws SkyException;
}
