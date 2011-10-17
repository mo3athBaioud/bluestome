package com.takesoon.oms.ssi.service;

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

import com.takesoon.oms.ssi.dao.ArticleDao;
import com.takesoon.oms.ssi.entity.Article;

@Component
@Transactional
public class ArticleManager {

	@Autowired
	private ArticleDao articleDao;
	
	public Article get(Integer id) {
		return articleDao.getting(id);
	}
	
	public String getId(){
		return articleDao.getIdName();
	}
	
	public int getCount(Article entity){
		Criteria criteria = getCriteria(entity);
		return articleDao.getAll(criteria).size();
	}
	
	public int getTotal(Article entity) {
		Criteria criteria = getCriteria(entity);
		return articleDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Article entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return articleDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Article entity) {
		Criteria criteria = articleDao.createCriteria();
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
			if (StringUtils.isNotBlank(entity.getTitle())) {
				criteria.add(Restrictions.like("title", "%"+entity.getId()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getIntro())) {
				criteria.add(Restrictions.like("intro", "%"+entity.getIntro()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getText())) {
				criteria.add(Restrictions.like("text", "%"+entity.getText()+"%"));
			}
			if (StringUtils.isNotBlank(entity.getArticleUrl())) {
				criteria.add(Restrictions.like("articleUrl", "%"+entity.getArticleUrl()+"%"));
			}
			if (entity.getWebId() != null && entity.getWebId() != 0) {
				criteria.add(Restrictions.eq("webId", entity.getWebId()));
			}
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(Article entity){
		articleDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		articleDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Article> getList(Article entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("id"));
		return articleDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Article> getListBySql(Article entity,int start,int limit){
		String sql = buildSQL(entity,start,limit);
		System.out.println(" > sql:"+sql);
		return articleDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Article entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_article");
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
			if(null != entity.getArticleUrl()){
				sql.append(" and a.d_acticle_url like '%").append(entity.getArticleUrl()).append("%'").append("\n");
			}
			if(null != entity.getActicleRealUrl()){
				sql.append(" and a.d_article_real_url like '%").append(entity.getActicleRealUrl()).append("%'").append("\n");
			}
			if(null != entity.getActicleXmlUrl()){
				sql.append(" and a.d_article_xml_url like '%").append(entity.getActicleXmlUrl()).append("%'").append("\n");
			}
			if(null != entity.getText()){
				sql.append(" and a.d_text like '%").append(entity.getText()).append("%'").append("\n");
			}
			if(null != entity.getIntro()){
				sql.append(" and a.d_intro like '%").append(entity.getIntro()).append("%'").append("\n");
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

	public String buildSQL(Article entity,int start,int limit){
		StringBuffer sql = new StringBuffer("select * from tbl_article");
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
			if(null != entity.getArticleUrl()){
				sql.append(" and a.d_acticle_url like '%").append(entity.getArticleUrl()).append("%'").append("\n");
			}
			if(null != entity.getActicleRealUrl()){
				sql.append(" and a.d_article_real_url like '%").append(entity.getActicleRealUrl()).append("%'").append("\n");
			}
			if(null != entity.getActicleXmlUrl()){
				sql.append(" and a.d_article_xml_url like '%").append(entity.getActicleXmlUrl()).append("%'").append("\n");
			}
			if(null != entity.getText()){
				sql.append(" and a.d_text like '%").append(entity.getText()).append("%'").append("\n");
			}
			if(null != entity.getIntro()){
				sql.append(" and a.d_intro like '%").append(entity.getIntro()).append("%'").append("\n");
			}
			if(null != entity.getStartDate() && null != entity.getEndDate()){
//				sql.append(" and a.d_createtime between '").append()
			}else{
				if(null != entity.getStartDate()){
					
				}
				if(null != entity.getEndDate()){
					
				}
			}
			if(null != entity.getStart() && null != entity.getLimit()){
			}
		}
		sql.append(" order by d_id desc");
		sql.append(" limit ").append(limit);
		sql.append(" offset ").append(start);
		return sql.toString();
	}
}
