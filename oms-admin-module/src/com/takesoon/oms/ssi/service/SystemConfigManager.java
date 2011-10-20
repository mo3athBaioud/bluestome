package com.takesoon.oms.ssi.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.takesoon.oms.ssi.dao.SystemConfigDao;
import com.takesoon.oms.ssi.entity.SystemConfig;

@Component
@Transactional
public class SystemConfigManager {

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	public SystemConfig get(Integer id) {
		return systemConfigDao.getting(id);
	}
	
	public String getId(){
		return systemConfigDao.getIdName();
	}
	
	public int getCount(SystemConfig entity){
		Criteria criteria = getCriteria(entity);
		return systemConfigDao.getAll(criteria).size();
	}
	
	public int getTotal(SystemConfig entity) {
		Criteria criteria = getCriteria(entity);
		return systemConfigDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(SystemConfig entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return systemConfigDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(SystemConfig entity) {
		Criteria criteria = systemConfigDao.createCriteria();
		if(null != entity){
			if(null != entity.getStartDate() && null != entity.getEndDate()){
				criteria.add(Restrictions.between("createtime", entity.getStartDate(), entity.getEndDate()));
			}else{
				if(null != entity.getStartDate()){
					criteria.add(Restrictions.gt("createtime", entity.getStartDate()));
				}
				if(null != entity.getEndDate()){
					criteria.add(Restrictions.lt("createtime", entity.getEndDate()));
				}
			}
			if (entity.getId() != null && entity.getId() != 0) {
				criteria.add(Restrictions.eq("id", entity.getId()));
			}
			if (StringUtils.isNotBlank(entity.getKey())) {
				criteria.add(Restrictions.like("key", "%"+entity.getKey()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getType())) {
				criteria.add(Restrictions.like("type", "%"+entity.getType()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getValue())) {
				criteria.add(Restrictions.like("value", "%"+entity.getValue()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getRemarks())) {
				criteria.add(Restrictions.like("remarks", "%"+entity.getRemarks()+"%"));
			}
			if (entity.getStatus() != null) {
				criteria.add(Restrictions.eq("status", entity.getStatus()));
			}
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(SystemConfig entity){
		if(null != entity.getId() && entity.getId().intValue() > 0){
			entity.setModifytime(new Date());
		}else{
			entity.setCreatetime(new Date());
			entity.setStatus(1);
		}
		systemConfigDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		systemConfigDao.delete(id);
	}
	
	/**
	 * 启用记录
	 * @param id
	 */
	public void enabled(Integer id){
		SystemConfig entity = null;
		try{
			entity = get(id);
			entity.setStatus(1);
			save(entity);
		}catch(Exception e){
		}
	}
	
	/**
	 * 禁用记录
	 * @param id
	 */
	public void disabled(Integer id){
		SystemConfig entity = null;
		try{
			entity = get(id);
			entity.setStatus(0);
			save(entity);
		}catch(Exception e){
		}
	}
	
	/**
	 * 检查约束
	 * @param entity
	 * @return
	 */
	public boolean checkUnique(String type,String key,String value){
		SystemConfig entity = new SystemConfig();
		entity.setType(type);
		entity.setKey(key);
		entity.setValue(value);
		int c = getTotalBySql(entity);
		if(c > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<SystemConfig> getList(SystemConfig entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("id"));
		return systemConfigDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<SystemConfig> getList(SystemConfig entity){
		Criteria criteria = getCriteria(entity);
		return systemConfigDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<SystemConfig> getListBySql(SystemConfig entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		System.out.println(" > sql:"+sql);
		return systemConfigDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(SystemConfig entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_system_config");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getKey()){
				sql.append(" and a.d_key like '%").append(entity.getKey()).append("%'").append("\n");
			}
			if(null != entity.getType()){
				sql.append(" and a.d_type like '%").append(entity.getType()).append("%'").append("\n");
			}
			if(null != entity.getValue()){
				sql.append(" and a.d_value like '%").append(entity.getValue()).append("%'").append("\n");
			}
			if(null != entity.getRemarks()){
				sql.append(" and a.d_remarks like '%").append(entity.getRemarks()).append("%'").append("\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
//				sql.append(" and a.d_createtime between '").append()
			}else{
				if(null != entity.getStartDate()){
					
				}
				if(null != entity.getEndDate()){
					
				}
			}
		}
		return sql.toString();
	}

	public String buildSQL(SystemConfig entity,int start,int limit){
		StringBuffer sql = new StringBuffer("select * from tbl_system_config");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getKey()){
				sql.append(" and a.d_key like '%").append(entity.getKey()).append("%'").append("\n");
			}
			if(null != entity.getType()){
				sql.append(" and a.d_type like '%").append(entity.getType()).append("%'").append("\n");
			}
			if(null != entity.getValue()){
				sql.append(" and a.d_value like '%").append(entity.getValue()).append("%'").append("\n");
			}
			if(null != entity.getRemarks()){
				sql.append(" and a.d_remarks like '%").append(entity.getRemarks()).append("%'").append("\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
//				sql.append(" and a.d_createtime between '").append()
			}else{
				if(null != entity.getStartDate()){
					
				}
				if(null != entity.getEndDate()){
					
				}
			}
		}
		sql.append(" order by d_id desc");
		sql.append(" limit ").append(limit);
		sql.append(" offset ").append(start);
		return sql.toString();
	}
}
