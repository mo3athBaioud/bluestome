package com.takesoon.oms.ssi.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.takesoon.oms.ssi.dao.ImageDao;
import com.takesoon.oms.ssi.entity.Image;

@Component
@Transactional
public class ImageManager {

	@Autowired
	private ImageDao imageDao;
	
	public Image get(Integer id) {
		return imageDao.getting(id);
	}
	
	public String getId(){
		return imageDao.getIdName();
	}
	
	public int getCount(Image entity){
		Criteria criteria = getCriteria(entity);
		return imageDao.getAll(criteria).size();
	}
	
	public int getTotal(Image entity) {
		Criteria criteria = getCriteria(entity);
		return imageDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(Image entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return imageDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(Image entity) {
		Criteria criteria = imageDao.createCriteria();
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
		if (null != entity.getId()) {
			criteria.add(Restrictions.eq("id", entity.getId()));
		}
		if (null != entity.getArticleId()) {
			criteria.add(Restrictions.eq("articleId", entity.getArticleId()));
		}
		if (StringUtils.isNotBlank(entity.getName())) {
			criteria.add(Restrictions.like("name", "%"+entity.getName()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getTitle())) {
			criteria.add(Restrictions.like("title", "%"+entity.getId()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getHttpUrl())) {
			criteria.add(Restrictions.like("httpUrl", "%"+entity.getHttpUrl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getImgUrl())) {
			criteria.add(Restrictions.like("imageUrl", "%"+entity.getImgUrl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getCommentshowurl())) {
			criteria.add(Restrictions.like("commentshowurl", "%"+entity.getCommentshowurl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getCommentsuburl())) {
			criteria.add(Restrictions.like("commentsuburl", "%"+entity.getCommentsuburl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getHttpUrl())) {
			criteria.add(Restrictions.like("httpUrl", "%"+entity.getHttpUrl()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getTime())) {
			criteria.add(Restrictions.like("timer", "%"+entity.getTime()+"%"));
		}
		
		if (StringUtils.isNotBlank(entity.getIntro())) {
			criteria.add(Restrictions.like("intro", "%"+entity.getIntro()+"%"));
		}
		
		if (StringUtils.isNotBlank(entity.getLink())) {
			criteria.add(Restrictions.like("link", "%"+entity.getLink()+"%"));
		}
		if (null != entity.getStatus()) {
			criteria.add(Restrictions.eq("status", entity.getStatus()));
		}
		if (null != entity.getSize()) {
			criteria.add(Restrictions.eq("size", entity.getSize()));
		}
		if (null != entity.getOrderId()) {
			criteria.add(Restrictions.eq("orderId", entity.getOrderId()));
		}
		return criteria;
	}
	
	/**
	 * 保存记录
	 * @param entity
	 */
	public void save(Image entity){
		imageDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		imageDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Image> getList(Image entity,int start,int limit){
		Criteria criteria = getCriteria(entity);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.addOrder(Order.asc("id"));
		return imageDao.getAll(criteria);
	}
	
	/**
	 * 获取列表数量
	 * @param entity
	 * @return
	 */
	public List<Image> getList(Image entity){
		Criteria criteria = getCriteria(entity);
		criteria.addOrder(Order.asc("id"));
		return imageDao.getAll(criteria);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Image> getListBySql(Image entity,int start,int limit){
		if(null == entity.getStart())
			entity.setStart(start);
		if(null == entity.getLimit())
			entity.setLimit(limit);
		String sql = buildSQL(entity);
		return imageDao.getListBySQL(sql);
	}
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @return
	 */
	public List<Image> getListBySql(Image entity){
		if(null == entity.getStart())
			entity.setStart(null);
		if(null == entity.getLimit())
			entity.setLimit(null);
		String sql = buildSQL(entity);
		return imageDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(Image entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_image");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getArticleId()){
				sql.append(" and a.d_article_id = ").append(entity.getArticleId()).append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getCommentshowurl()){
				sql.append(" and a.d_commentshowurl like '%").append(entity.getCommentshowurl()).append("%'").append("\n");
			}
			if(null != entity.getCommentsuburl()){
				sql.append(" and a.d_commentsuburl like '%").append(entity.getCommentsuburl()).append("%'").append("\n");
			}
			if(null != entity.getHttpUrl()){
				sql.append(" and a.d_httpurl like '%").append(entity.getHttpUrl()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getSize()){
				sql.append(" and a.d_filesize = ").append(entity.getSize()).append("\n");
			}
			if(null != entity.getOrderId()){
				sql.append(" and a.d_orderid = ").append(entity.getOrderId()).append("\n");
			}
			if(null != entity.getTime()){
				sql.append(" and a.d_time like '%").append(entity.getTime()).append("%'").append("\n");
			}
			if(null != entity.getIntro()){
				sql.append(" and a.d_intro like '%").append(entity.getIntro()).append("%'").append("\n");
			}
			if(null != entity.getLink()){
				sql.append(" and a.d_link like '%").append(entity.getLink()).append("%'").append("\n");
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

	public String buildSQL(Image entity){
		StringBuffer sql = new StringBuffer("select * from tbl_image");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getArticleId()){
				sql.append(" and a.d_article_id = ").append(entity.getArticleId()).append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getCommentshowurl()){
				sql.append(" and a.d_commentshowurl like '%").append(entity.getCommentshowurl()).append("%'").append("\n");
			}
			if(null != entity.getCommentsuburl()){
				sql.append(" and a.d_commentsuburl like '%").append(entity.getCommentsuburl()).append("%'").append("\n");
			}
			if(null != entity.getHttpUrl()){
				sql.append(" and a.d_httpurl like '%").append(entity.getHttpUrl()).append("%'").append("\n");
			}
			if(null != entity.getStatus()){
				sql.append(" and a.d_status = ").append(entity.getStatus()).append("\n");
			}
			if(null != entity.getSize()){
				sql.append(" and a.d_filesize = ").append(entity.getSize()).append("\n");
			}
			if(null != entity.getOrderId()){
				sql.append(" and a.d_orderid = ").append(entity.getOrderId()).append("\n");
			}
			if(null != entity.getTime()){
				sql.append(" and a.d_time like '%").append(entity.getTime()).append("%'").append("\n");
			}
			if(null != entity.getIntro()){
				sql.append(" and a.d_intro like '%").append(entity.getIntro()).append("%'").append("\n");
			}
			if(null != entity.getLink()){
				sql.append(" and a.d_link like '%").append(entity.getLink()).append("%'").append("\n");
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
