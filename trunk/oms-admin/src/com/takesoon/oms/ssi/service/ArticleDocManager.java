package com.takesoon.oms.ssi.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.takesoon.oms.ssi.dao.ArticleDocDao;
import com.takesoon.oms.ssi.entity.ArticleDoc;

@Component
@Transactional
public class ArticleDocManager {

	@Autowired
	private ArticleDocDao articleDocDao;
	
	public ArticleDoc get(Integer id) {
		return articleDocDao.getting(id);
	}
	
	public String getId(){
		return articleDocDao.getIdName();
	}
	
	public int getCount(ArticleDoc entity){
		Criteria criteria = getCriteria(entity);
		return articleDocDao.getAll(criteria).size();
	}
	
	public int getTotal(ArticleDoc entity) {
		Criteria criteria = getCriteria(entity);
		return articleDocDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(ArticleDoc entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return articleDocDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(ArticleDoc entity) {
		Criteria criteria = articleDocDao.createCriteria();
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
		if (StringUtils.isNotBlank(entity.getTitle())) {
			criteria.add(Restrictions.like("title", "%"+entity.getId()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getUrl())) {
			criteria.add(Restrictions.like("url", "%"+entity.getUrl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getAuthor())) {
			criteria.add(Restrictions.like("author", "%"+entity.getUrl()+"%"));
		}
		
		if (StringUtils.isNotBlank(entity.getTag())) {
			criteria.add(Restrictions.like("tag", "%"+entity.getTag()+"%"));
		}
		
		if (StringUtils.isNotBlank(entity.getPublishTime())) {
			criteria.add(Restrictions.like("publishTime", "%"+entity.getPublishTime()+"%"));
		}
		if (entity.getWebId() != null && entity.getWebId() != 0) {
			criteria.add(Restrictions.eq("webId", entity.getWebId()));
		}
		if (null != entity.getStatus()) {
			criteria.add(Restrictions.eq("status", entity.getStatus()));
		}
		
		if (null != entity.getGrade()) {
			criteria.add(Restrictions.eq("grade", entity.getGrade()));
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(ArticleDoc entity){
		articleDocDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		articleDocDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ArticleDoc> getList(ArticleDoc entity,int start,int limit){
		return null;
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ArticleDoc> getListBySql(ArticleDoc entity,int start,int limit){
		if(null == entity.getStart() && start > 0)
			entity.setStart(start);
		if(null == entity.getLimit() && limit  > 0)
			entity.setLimit(limit);
		String sql = buildSQL(entity);
		return articleDocDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(ArticleDoc entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_article_doc");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getWebId()){
				sql.append(" and a.d_web_id = ").append(entity.getWebId()).append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getUrl()){
				sql.append(" and a.d_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getAuthor()){
				sql.append(" and a.d_author like '%").append(entity.getAuthor()).append("%'").append("\n");
			}
			if(null != entity.getTag()){
				sql.append(" and a.d_tag like '%").append(entity.getTag()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getGrade()){
				sql.append(" and a.d_grade = ").append(entity.getGrade()).append("\n");
			}
			if(null != entity.getPublishTime()){
				sql.append(" and a.d_publishtime like '%").append(entity.getPublishTime()).append("%'").append("\n");
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

	public String buildSQL(ArticleDoc entity){
		StringBuffer sql = new StringBuffer("select * from tbl_article_doc");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getWebId()){
				sql.append(" and a.d_web_id = ").append(entity.getWebId()).append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getUrl()){
				sql.append(" and a.d_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getAuthor()){
				sql.append(" and a.d_author like '%").append(entity.getAuthor()).append("%'").append("\n");
			}
			if(null != entity.getTag()){
				sql.append(" and a.d_tag like '%").append(entity.getTag()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getGrade()){
				sql.append(" and a.d_grade = ").append(entity.getGrade()).append("\n");
			}
			if(null != entity.getPublishTime()){
				sql.append(" and a.d_publishtime like '%").append(entity.getPublishTime()).append("%'").append("\n");
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
				sql.append(" limit ").append(entity.getLimit());
				sql.append(" offset ").append(entity.getStart());
			}
		}
		return sql.toString();
	}
}
