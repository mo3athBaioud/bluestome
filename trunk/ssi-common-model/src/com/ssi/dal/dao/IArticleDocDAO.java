package com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.dal.domain.*;
public interface IArticleDocDAO {

	/**
	 * 添加记录
	 * @param articleDoc
	 * @return
	 */
	int insert(ArticleDoc articleDoc);
	
	/**
	 * 更新记录
	 * @param articleDoc
	 * @return
	 */
	int update(ArticleDoc articleDoc);
	
	/**
	 * 根据MAP查找记录
	 * @param map
	 * @return
	 */
	List<ArticleDoc> find(HashMap map);	
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
