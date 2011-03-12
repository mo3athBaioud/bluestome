package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.common.dal.domain.Article;

public interface IArticleDAO extends EntityDAO<Article>{

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return
	 */
	Article findById(Integer id);
	
	/**
	 * 根据对象查找记录
	 * @param map
	 * @return
	 */
	List<Article> find(HashMap map);	
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
}
