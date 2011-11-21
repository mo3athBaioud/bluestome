package com.xian.support.service;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssi.common.utils.DateUtils;
import com.xian.support.dao.BDistrictDao;
import com.xian.support.entity.BDistrict;

@Component
@Transactional
public class BDistrictManager {


	@Autowired
	private BDistrictDao BDistrictDao;
	
	public BDistrict get(Integer id) {
		return BDistrictDao.getting(id);
	}
	
	public String getId(){
		return BDistrictDao.getIdName();
	}
	
	public int getCount(BDistrict entity){
		Criteria criteria = getCriteria(entity);
		return BDistrictDao.getAll(criteria).size();
	}
	
	public int getTotal(BDistrict entity) {
		Criteria criteria = getCriteria(entity);
		return BDistrictDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(BDistrict entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return BDistrictDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(BDistrict entity) {
		Criteria criteria = BDistrictDao.createCriteria();
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
		if (entity.getCode() != null && !entity.getCode().equals("")) {
			criteria.add(Restrictions.eq("code", entity.getCode()));
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
	public void save(BDistrict entity){
		BDistrictDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		BDistrictDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BDistrict> getList(BDistrict entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.desc("id"));
		return BDistrictDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BDistrict> getList(BDistrict entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.desc("id"));
		return BDistrictDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BDistrict> getListBySql(BDistrict entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		return BDistrictDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(BDistrict entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_bdistrict");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getCode() && !entity.getCode().equals("")){
				sql.append(" and a.d_code = ").append(entity.getCode()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
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

	public String buildSQL(BDistrict entity,Integer start,Integer limit){
		StringBuffer sql = new StringBuffer("select * from tbl_bdistrict");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getCode() && !entity.getCode().equals("")){
				sql.append(" and a.d_code = ").append(entity.getCode()).append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
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
		sql.append(" order by d_code desc");
		if(null != limit && null != start)
		{
			sql.append(" limit ").append(limit);
			sql.append(" offset ").append(start);
		}else{
			sql.append(" limit ").append(10);
			sql.append(" offset ").append(0);
		}
		return sql.toString();
	}

}
