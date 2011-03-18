package com.ssi.common.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;

public class EntityServiceImpl<T> implements IEntityService<T> {
	
	private EntityDAO entityDAO;

	public EntityDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	public Integer deleteByPrimarykey(Serializable id) {
		// TODO Auto-generated method stub
		return entityDAO.deleteByPrimarykey(id);
	}

	public List<T> find(HashMap map) {
		// TODO Auto-generated method stub
		return entityDAO.find(map);
	}

	public T findByPrimarykey(Serializable id) {
		// TODO Auto-generated method stub
		return (T) entityDAO.findByPrimarykey(id);
	}

	public int getCount(HashMap map) {
		// TODO Auto-generated method stub
		return entityDAO.getCount(map);
	}

	public Integer insert(T o) {
		// TODO Auto-generated method stub
		return entityDAO.insert(o);
	}

	public List<T> queryForList(String statementId, Object parameters) {
		// TODO Auto-generated method stub
		return entityDAO.queryForList(statementId, parameters);
	}

	public T queryForObject(String statementId, Object parameters) {
		// TODO Auto-generated method stub
		return (T)entityDAO.queryForObject(statementId, parameters);
	}

	public Integer update(T o) {
		// TODO Auto-generated method stub
		return entityDAO.update(o);
	}

	public Integer update(String statementId, Object parameters) {
		// TODO Auto-generated method stub
		return entityDAO.update(statementId, parameters);
	}

	
}
