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
import com.xian.support.dao.NoplogDao;
import com.xian.support.entity.Noplog;

@Component
@Transactional
public class NoplogManager {


	@Autowired
	private NoplogDao noplogDao;
	
	public Noplog get(Integer id) {
		return noplogDao.getting(id);
	}
	
	public String getId(){
		return noplogDao.getIdName();
	}
	
	public int getCount(Noplog entity){
		Criteria criteria = getCriteria(entity);
		return noplogDao.getAll(criteria).size();
	}
	
	public int getTotal(Noplog entity) {
		Criteria criteria = getCriteria(entity);
		return noplogDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Noplog entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return noplogDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Noplog entity) {
		Criteria criteria = noplogDao.createCriteria();
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
		if (StringUtils.isNotBlank(entity.getLoginname())) {
			criteria.add(Restrictions.eq("loginname",entity.getLoginname()));
		}
		if (null != entity.getBtype()) {
			criteria.add(Restrictions.eq("btype", entity.getBtype()));
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(Noplog entity){
		noplogDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		noplogDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Noplog> getList(Noplog entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.desc("id"));
		return noplogDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Noplog> getList(Noplog entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.desc("id"));
		return noplogDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start 起始ID
	 * @param limit 限制ID
	 * @return
	 */
	public List<Noplog> getListBySql(Noplog entity,Integer start,Integer limit){
		String sql = buildSQL(entity,start,limit);
		return noplogDao.getListBySQL(sql);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @return
	 */
	public List<Noplog> getListBySql(Noplog entity){
		String sql = buildSQL(entity,null,null);
		return noplogDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Noplog entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_noplog");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getUid()){
				sql.append(" and a.d_uid = ").append(entity.getUid()).append("\n");
			}
			if(null != entity.getBtype()){
				sql.append(" and a.d_btype = ").append(entity.getBtype()).append("\n");
			}
			if(null != entity.getPhonenum() && !entity.getPhonenum().equals("")){
				sql.append(" and a.d_phonenum = '").append(entity.getPhonenum()).append("'\n");
			}
			if(null != entity.getLoginname() && !entity.getLoginname().equals("")){
				sql.append(" and a.d_loginname = '").append(entity.getLoginname()).append("'\n");
			}
			if(null != entity.getPhonenumBDistrict() && !entity.getPhonenumBDistrict().equals("")){
				sql.append(" and a.d_phonenum_bdistrict = '").append(entity.getPhonenumBDistrict()).append("'\n");
			}
			if(null != entity.getLoginnameBDistrict() && !entity.getLoginnameBDistrict().equals("")){
				sql.append(" and a.d_loginname_bdistrict = '").append(entity.getLoginnameBDistrict()).append("'\n");
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
			if(null != entity.getResult()){
				sql.append(" and a.d_result = ").append(entity.getResult()).append("\n");
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

	public String buildSQL(Noplog entity,Integer start,Integer limit){
		StringBuffer sql = new StringBuffer("select * from tbl_noplog");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getUid()){
				sql.append(" and a.d_uid = ").append(entity.getUid()).append("\n");
			}
			if(null != entity.getBtype()){
				sql.append(" and a.d_btype = ").append(entity.getBtype()).append("\n");
			}
			if(null != entity.getPhonenum() && !entity.getPhonenum().equals("")){
				sql.append(" and a.d_phonenum = '").append(entity.getPhonenum()).append("'\n");
			}
			if(null != entity.getLoginname() && !entity.getLoginname().equals("")){
				sql.append(" and a.d_loginname = '").append(entity.getLoginname()).append("'\n");
			}
			if(null != entity.getPhonenumBDistrict() && !entity.getPhonenumBDistrict().equals("")){
				sql.append(" and a.d_phonenum_bdistrict = '").append(entity.getPhonenumBDistrict()).append("'\n");
			}
			if(null != entity.getLoginnameBDistrict() && !entity.getLoginnameBDistrict().equals("")){
				sql.append(" and a.d_loginname_bdistrict = '").append(entity.getLoginnameBDistrict()).append("'\n");
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
			if(null != entity.getResult()){
				sql.append(" and a.d_result = ").append(entity.getResult()).append("\n");
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
