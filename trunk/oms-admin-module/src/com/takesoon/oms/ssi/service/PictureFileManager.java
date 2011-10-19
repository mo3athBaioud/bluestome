package com.takesoon.oms.ssi.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.takesoon.oms.ssi.dao.PictureFileDao;
import com.takesoon.oms.ssi.entity.PictureFile;

@Component
@Transactional
public class PictureFileManager {

	@Autowired
	private PictureFileDao pictureFileDao;
	
	public PictureFile get(Integer id) {
		return pictureFileDao.getting(id);
	}
	
	public String getId(){
		return pictureFileDao.getIdName();
	}
	
	public int getCount(PictureFile entity){
		Criteria criteria = getCriteria(entity);
		return pictureFileDao.getAll(criteria).size();
	}
	
	public int getTotal(PictureFile entity) {
		Criteria criteria = getCriteria(entity);
		return pictureFileDao.getAll(criteria).size();
	}
	
	public int getTotalBySql(PictureFile entity) throws HibernateException{
		String sql = buildCountSQL(entity);
		return pictureFileDao.getTotalBySQL(sql);
	}
	
	public Criteria getCriteria(PictureFile entity) {
		Criteria criteria = pictureFileDao.createCriteria();
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
		if (null != entity.getImageId()) {
			criteria.add(Restrictions.eq("imageId", entity.getImageId()));
		}
		if (StringUtils.isNotBlank(entity.getName())) {
			criteria.add(Restrictions.like("name", "%"+entity.getName()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getTitle())) {
			criteria.add(Restrictions.like("title", "%"+entity.getId()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getSmallName())) {
			criteria.add(Restrictions.like("smallName", "%"+entity.getSmallName()+"%"));
		}
		if (StringUtils.isNotBlank(entity.getUrl())) {
			criteria.add(Restrictions.like("url", "%"+entity.getUrl()+"%"));
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
	public void save(PictureFile entity){
		pictureFileDao.save(entity);
	}
	
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(Integer id){
		pictureFileDao.delete(id);
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<PictureFile> getList(PictureFile entity,int start,int limit){
		return null;
	}
	
	/**
	 * 获取指定的列表数量
	 * @param entity
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<PictureFile> getListBySql(PictureFile entity,int start,int limit){
		if(null == entity.getStart() && start > 0)
			entity.setStart(start);
		if(null == entity.getLimit() && limit  > 0)
			entity.setLimit(limit);
		String sql = buildSQL(entity);
		return pictureFileDao.getListBySQL(sql);
	}
	
	public String buildCountSQL(PictureFile entity){
		StringBuffer sql = new StringBuffer("select count(*) as total from tbl_pic_file");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getArticleId()){
				sql.append(" and a.d_article_id = ").append(entity.getArticleId()).append("\n");
			}
			if(null != entity.getImageId()){
				sql.append(" and a.d_image_id = ").append(entity.getImageId()).append("\n");
			}
			if(null != entity.getUrl()){
				sql.append(" and a.d_file_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_file_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getName()){
				sql.append(" and a.d_file_name like '%").append(entity.getName()).append("%'").append("\n");
			}
			if(null != entity.getSmallName()){
				sql.append(" and a.d_file_small_name like '%").append(entity.getSmallName()).append("%'").append("\n");
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

	public String buildSQL(PictureFile entity){
		StringBuffer sql = new StringBuffer("select * from tbl_pic_file");
		if(null != entity){
			sql.append(" a");
			sql.append(" where 1 = 1").append("\n");
			if(null != entity.getId()){
				sql.append(" and a.d_id = ").append(entity.getId()).append("\n");
			}
			if(null != entity.getArticleId()){
				sql.append(" and a.d_article_id = ").append(entity.getArticleId()).append("\n");
			}
			if(null != entity.getImageId()){
				sql.append(" and a.d_image_id = ").append(entity.getImageId()).append("\n");
			}
			if(null != entity.getUrl()){
				sql.append(" and a.d_file_url like '%").append(entity.getUrl()).append("%'").append("\n");
			}
			if(null != entity.getTitle()){
				sql.append(" and a.d_file_title like '%").append(entity.getTitle()).append("%'").append("\n");
			}
			if(null != entity.getName()){
				sql.append(" and a.d_file_name like '%").append(entity.getName()).append("%'").append("\n");
			}
			if(null != entity.getSmallName()){
				sql.append(" and a.d_file_small_name like '%").append(entity.getSmallName()).append("%'").append("\n");
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
				sql.append(" limit ").append(entity.getLimit());
				sql.append(" offset ").append(entity.getStart());
			}
		}
		return sql.toString();
	}
}
