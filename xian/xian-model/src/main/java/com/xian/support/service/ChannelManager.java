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
import com.xian.support.dao.ChannelDao;
import com.xian.support.entity.Channel;

@Component
@Transactional
public class ChannelManager {


	@Autowired
	private ChannelDao channelDao;
	
	public Channel get(String id) {
		return channelDao.getting(id);
	}
	
	public String getId(){
		return channelDao.getIdName();
	}
	
	public int getCount(Channel entity){
		Criteria criteria = getCriteria(entity);
		return channelDao.getAll(criteria).size();
	}
	
	public int getTotal(Channel entity) {
		Criteria criteria = getCriteria(entity);
		return channelDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Channel entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return channelDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Channel entity) {
		Criteria criteria = channelDao.createCriteria();
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
		if (entity.getChannelcode() != null && !entity.getChannelcode().equals("")) {
			criteria.add(Restrictions.eq("channelcode", entity.getChannelcode()));
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
	public void save(Channel entity){
		channelDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(String id){
		channelDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> getList(Channel entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.desc("channelcode"));
		return channelDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> getList(Channel entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.desc("channelcode"));
		return channelDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> getListBySql(Channel entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		return channelDao.getListBySQL(sql);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Channel> getListBySql(Channel entity){
		String sql = buildSQL(entity,null,null);
		return channelDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Channel entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_channel");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getChannelcode() && !entity.getChannelcode().equals("")){
				sql.append(" and a.d_channel_code = '").append(entity.getChannelcode()).append("'\n");
			}
			if(null != entity.getChannelname() && !entity.getChannelname().equals("")){
				sql.append(" and a.d_channel_name like '%").append(entity.getChannelname()).append("%'\n");
			}
			if(null != entity.getBdcode() && !entity.getBdcode().equals("")){
				sql.append(" and a.d_bdcode = '").append(entity.getBdcode()).append("'\n");
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

	public String buildSQL(Channel entity,Integer start,Integer limit){
		StringBuffer sql = new StringBuffer("select * from tbl_channel");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getChannelcode() && !entity.getChannelcode().equals("")){
				sql.append(" and a.d_channel_code = '").append(entity.getChannelcode()).append("'\n");
			}
			if(null != entity.getChannelname() && !entity.getChannelname().equals("")){
				sql.append(" and a.d_channel_name like '%").append(entity.getChannelname()).append("%'\n");
			}
			if(null != entity.getBdcode() && !entity.getBdcode().equals("")){
				sql.append(" and a.d_bdcode = '").append(entity.getBdcode()).append("'\n");
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
		sql.append(" order by d_channel_code desc");
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
	public boolean disabled(String code){
		Channel entity = get(code);
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
	public boolean enabled(String code){
		Channel entity = get(code);
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
