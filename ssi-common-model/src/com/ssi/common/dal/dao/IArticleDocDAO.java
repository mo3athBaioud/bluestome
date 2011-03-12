package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.common.dal.domain.*;
public interface IArticleDocDAO extends EntityDAO<ArticleDoc>{

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
