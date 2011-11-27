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
import com.xian.support.dao.BussinessSimplifyDao;
import com.xian.support.entity.BussinessSimplify;

@Component
@Transactional
public class BussinessSimplifyManager {


	@Autowired
	private BussinessSimplifyDao bussinessSimplifyDao;
	
	public BussinessSimplify get(Integer id) {
		return bussinessSimplifyDao.getting(id);
	}
	
	public String getId(){
		return bussinessSimplifyDao.getIdName();
	}
	
	public int getCount(BussinessSimplify entity){
		Criteria criteria = getCriteria(entity);
		return bussinessSimplifyDao.getAll(criteria).size();
	}
	
	public int getTotal(BussinessSimplify entity) {
		Criteria criteria = getCriteria(entity);
		return bussinessSimplifyDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(BussinessSimplify entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return bussinessSimplifyDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(BussinessSimplify entity) {
		Criteria criteria = bussinessSimplifyDao.createCriteria();
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
		if (StringUtils.isNotBlank(entity.getPhonenum())) {
			criteria.add(Restrictions.eq("phonenum", entity.getPhonenum()));
		}
		if (StringUtils.isNotBlank(entity.getBdistrict())) {
			criteria.add(Restrictions.eq("bdistrict", entity.getBdistrict()));
		}
		if (null != entity.getBtype()) {
			criteria.add(Restrictions.eq("btype", entity.getBtype()));
		}
		if (null != entity.getSupport()) {
			criteria.add(Restrictions.eq("support", entity.getSupport()));
		}
		if (null != entity.getSuuporttype()) {
			criteria.add(Restrictions.eq("suuporttype", entity.getSuuporttype()));
		}
		if (null != entity.getMSuccess()) {
			criteria.add(Restrictions.eq("mSuccess", entity.getMSuccess()));
		}
		if (null != entity.getIsMarketing()) {
			criteria.add(Restrictions.eq("isMarketing", entity.getIsMarketing()));
		}
		if (null != entity.getPlatsell()) {
			criteria.add(Restrictions.eq("platsell", entity.getPlatsell()));
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(BussinessSimplify entity){
		bussinessSimplifyDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		bussinessSimplifyDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BussinessSimplify> getList(BussinessSimplify entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.desc("id"));
		return bussinessSimplifyDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BussinessSimplify> getList(BussinessSimplify entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.desc("id"));
		return bussinessSimplifyDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<BussinessSimplify> getListBySql(BussinessSimplify entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		return bussinessSimplifyDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(BussinessSimplify entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_bussiness_simplify");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getBtype()){
				sql.append(" and a.d_btype = ").append(entity.getBtype()).append("\n");
			}
			if(null != entity.getBdistrict() && !entity.getBdistrict().equals("")){
				sql.append(" and a.d_bdistrict = '").append(entity.getBdistrict()).append("'\n");
			}
			if(null != entity.getPhonenum() && !entity.getPhonenum().equals("")){
				sql.append(" and a.d_phonenum = '").append(entity.getPhonenum()).append("'\n");
			}

			if(null != entity.getSupport()){
				sql.append(" and a.d_support = ").append(entity.getSupport()).append("\n");
			}
			if(null != entity.getSuuporttype()){
				sql.append(" and a.d_support_type = ").append(entity.getSuuporttype()).append("\n");
			}
			if(null != entity.getMSuccess()){
				sql.append(" and a.d_msuccess = ").append(entity.getMSuccess()).append("\n");
			}
			if(null != entity.getIsMarketing()){
				sql.append(" and a.d_ismarketing = ").append(entity.getIsMarketing()).append("\n");
			}
			if(null != entity.getPlatsell()){
				sql.append(" and a.d_platsell = ").append(entity.getPlatsell()).append("\n");
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

	public String buildSQL(BussinessSimplify entity,Integer start,Integer limit){
		StringBuffer sql = new StringBuffer("select * from tbl_bussiness_simplify");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getBtype()){
				sql.append(" and a.d_btype = ").append(entity.getBtype()).append("\n");
			}
			if(null != entity.getBdistrict() && !entity.getBdistrict().equals("")){
				sql.append(" and a.d_bdistrict = '").append(entity.getBdistrict()).append("'\n");
			}
			if(null != entity.getPhonenum() && !entity.getPhonenum().equals("")){
				sql.append(" and a.d_phonenum = '").append(entity.getPhonenum()).append("'\n");
			}

			if(null != entity.getSupport()){
				sql.append(" and a.d_support = ").append(entity.getSupport()).append("\n");
			}
			if(null != entity.getSuuporttype()){
				sql.append(" and a.d_support_type = ").append(entity.getSuuporttype()).append("\n");
			}
			if(null != entity.getMSuccess()){
				sql.append(" and a.d_msuccess = ").append(entity.getMSuccess()).append("\n");
			}
			if(null != entity.getIsMarketing()){
				sql.append(" and a.d_ismarketing = ").append(entity.getIsMarketing()).append("\n");
			}
			if(null != entity.getPlatsell()){
				sql.append(" and a.d_platsell = ").append(entity.getPlatsell()).append("\n");
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
	
}
