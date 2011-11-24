package com.xian.support.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssi.common.utils.DateUtils;
import com.xian.support.dao.StaffDao;
import com.xian.support.entity.Staff;
import com.xian.support.entity.Staff;

@Component
@Transactional
public class StaffManager {


	@Autowired
	private StaffDao staffDao;
	
	public Staff get(Integer id) {
		return staffDao.getting(id);
	}
	
	public String getId(){
		return staffDao.getIdName();
	}
	
	public int getCount(Staff entity){
		Criteria criteria = getCriteria(entity);
		return staffDao.getAll(criteria).size();
	}
	
	public int getTotal(Staff entity) {
		Criteria criteria = getCriteria(entity);
		return staffDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Staff entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return staffDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Staff entity) {
		Criteria criteria = staffDao.createCriteria();
		if(null != entity.getStartDate() && null != entity.getEndDate()){
			criteria.add(Restrictions.between("createTime", entity.getStartDate(), entity.getEndDate()));
		}else{
			if(null != entity.getStartDate()){
				criteria.add(Restrictions.gt("createTime", entity.getStartDate()));
			}
			if(null != entity.getEndDate()){
				criteria.add(Restrictions.lt("createTime", entity.getEndDate()));
			}
		}
		if (entity.getId() != null && entity.getId() != 0) {
			criteria.add(Restrictions.eq("id", entity.getId()));
		}
		if (StringUtils.isNotBlank(entity.getChannelcode())) {
			criteria.add(Restrictions.like("title", "%"+entity.getId()+"%"));
		}
		if (null != entity.getStatus()) {
			criteria.add(Restrictions.eq("status", entity.getStatus()));
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(Staff entity){
		staffDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		staffDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Staff> getList(Staff entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.desc("id"));
		return staffDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Staff> getList(Staff entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.desc("id"));
		return staffDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Staff> getListBySql(Staff entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		return staffDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Staff entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_staff");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getChannelcode() && !entity.getChannelcode().equals("")){
				sql.append(" and a.d_channel_code = '").append(entity.getChannelcode()).append("'\n");
			}
			if(null != entity.getUsername() && !entity.getUsername().equals("")){
				sql.append(" and a.d_username like '%").append(entity.getUsername()).append("%'\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
				sql.append(" and a.d_createtime between '"+DateUtils.formatDate(entity.getStartDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
				sql.append(" and '"+DateUtils.formatDate(entity.getEndDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
				sql.append("\n");
			}else{
				if(null != entity.getStartDate()){
					sql.append(" and a.d_createtime > '"+DateUtils.formatDate(entity.getStartDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
					sql.append("\n");
				}
				if(null != entity.getEndDate()){
					sql.append(" and a.d_createtime < '"+DateUtils.formatDate(entity.getEndDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
					sql.append("\n");
				}
			}
		}
		return sql.toString();
	}

	public String buildSQL(Staff entity,Integer start,Integer limit){
		StringBuffer sql = new StringBuffer("select * from tbl_staff");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getChannelcode() && !entity.getChannelcode().equals("")){
				sql.append(" and a.d_channel_code = '").append(entity.getChannelcode()).append("'\n");
			}
			if(null != entity.getUsername() && !entity.getUsername().equals("")){
				sql.append(" and a.d_username like '%").append(entity.getUsername()).append("%'\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
				sql.append(" and a.d_createtime between '"+DateUtils.formatDate(entity.getStartDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
				sql.append(" and '"+DateUtils.formatDate(entity.getEndDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
				sql.append("\n");
			}else{
				if(null != entity.getStartDate()){
					sql.append(" and a.d_createtime > '"+DateUtils.formatDate(entity.getStartDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
					sql.append("\n");
				}
				if(null != entity.getEndDate()){
					sql.append(" and a.d_createtime < '"+DateUtils.formatDate(entity.getEndDate(), DateUtils.FULL_STANDARD_PATTERN_2)).append("'");
					sql.append("\n");
				}
			}
		}
		sql.append(" order by d_id desc");
		if(null != limit && null != start)
		{
			sql.append(" limit ").append(limit);
			sql.append(" offset ").append(start);
		}
//		else{
//			sql.append(" limit ").append(10);
//			sql.append(" offset ").append(0);
//		}
		return sql.toString();
	}

	/**
	 * 禁用数据记录
	 * @param code
	 * @return
	 */
	public boolean disabled(Integer id){
		Staff entity = get(id);
		if(null != entity)
		{
			if(entity.getStatus() != 0){
				entity.setStatus(0);
				save(entity);
			}
		}
		return true;
	}

	/**
	 * 启用数据记录
	 * @param code
	 * @return
	 */
	public boolean enabled(Integer id){
		Staff entity = get(id);
		if(null != entity)
		{
			if(entity.getStatus() != 1){
				entity.setStatus(1);
				save(entity);
			}
		}
		return true;
	}

}
