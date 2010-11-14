package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Article;

public interface IArticleDAO {

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return
	 */
	Article findById(Integer id);
	
	/**
	 * 添加记录
	 * @param article
	 * @return
	 */
	int insert(Article article);
	
	/**
	 * 更新方法
	 * @param article
	 * @return
	 */
	int update(Article article);
	
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
