package com.takesoon.oms.ssi.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssi.common.utils.HttpClientUtils;
import com.takesoon.oms.ssi.dao.WebsiteDao;
import com.takesoon.oms.ssi.entity.Website;

@Component
@Transactional
public class WebsiteManager {

	@Autowired
	private WebsiteDao websiteDao;
	
	public Website get(Integer id) {
		return websiteDao.getting(id);
	}
	
	public String getId(){
		return websiteDao.getIdName();
	}
	
	public int getCount(Website entity){
		Criteria criteria = getCriteria(entity);
		return websiteDao.getAll(criteria).size();
	}
	
	public int getTotal(Website entity) {
		Criteria criteria = getCriteria(entity);
		return websiteDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Website entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return websiteDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Website entity) {
		Criteria criteria = websiteDao.createCriteria();
		if(null != entity){
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
			if (StringUtils.isNotBlank(entity.getName())) {
				criteria.add(Restrictions.like("name", "%"+entity.getId()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getUrl())) {
				criteria.add(Restrictions.like("url", "%"+entity.getUrl()+"%"));
			}
			if (entity.getType() != null) {
				criteria.add(Restrictions.eq("type", entity.getType()));
			}
			if (entity.getParentId() != null) {
				criteria.add(Restrictions.eq("parentId", entity.getParentId()));
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
	public void save(Website entity){
		websiteDao.save(entity);
	}
	
	/**
	 * 启用记录
	 * @param id
	 */
	public void enabled(Integer id){
		Website entity = null;
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
		Website entity = null;
		try{
			entity = get(id);
			entity.setStatus(0);
			save(entity);
		}catch(Exception e){
		}
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		websiteDao.delete(id);
	}
	
	/**
	 * 测试URL，并返回响应信息
	 * @param url
	 * @return
	 */
	public HashMap<String,String> debugURL(String url){
		HashMap<String,String> pmap = new HashMap<String,String>();
		HashMap<String,String> tmap = HttpClientUtils.getHttpHeaderResponse(url);
		Iterator it = tmap.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			String value = tmap.get(key);
			//重新构建KEY 例如：contentType,contentLength
			int pos = key.lastIndexOf("-");
			if(pos != -1){
				String prex = key.substring(0,pos).toLowerCase();
				String ends = key.substring(pos+1);
				key = prex + ends;
			}else{
				key = key.toLowerCase();
			}
			pmap.put(key, value);
		}
		return pmap;
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Website> getList(Website entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("id"));
		return websiteDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Website> getListBySql(Website entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		return websiteDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Website entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_web_site");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getType()){
				sql.append(" and a.d_web_type = ").append(entity.getType()).append("\n");
			}
			if(null != entity.getParentId()){
				sql.append(" and a.d_parent_id = ").append(entity.getParentId()).append("\n");
			}
			if(null != entity.getUrl() && !entity.getUrl().equals("")){
				sql.append(" and a.d_web_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getName() && !entity.getName().equals("")){
				sql.append(" and a.d_web_name like '%").append(entity.getName()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
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

	public String buildSQL(Website entity,int start,int limit){
		StringBuffer sql = new StringBuffer("select * from tbl_web_site");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getType()){
				sql.append(" and a.d_web_type = ").append(entity.getType()).append("\n");
			}
			if(null != entity.getParentId()){
				sql.append(" and a.d_parent_id = ").append(entity.getParentId()).append("\n");
			}
			if(null != entity.getUrl() && !entity.getUrl().equals("")){
				sql.append(" and a.d_web_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getName() && !entity.getName().equals("")){
				sql.append(" and a.d_web_name like '%").append(entity.getName()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
//				sql.append(" and a.d_createtime between '").append()
			}else{
				if(null != entity.getStartDate()){
					
				}
				if(null != entity.getEndDate()){
					
				}
			}
			sql.append(" order by d_id asc");
			if(null != entity.getStart() && null != entity.getLimit()){
			}
		}
		sql.append(" limit ").append(limit);
		sql.append(" offset ").append(start);
		return sql.toString();
	}
}
