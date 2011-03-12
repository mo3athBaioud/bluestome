package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.common.dal.dao.IArticleDocDAO;
import com.ssi.common.dal.domain.ArticleDoc;

public class ArticleDocDAOImpl extends IbatisEntityDao<ArticleDoc> implements IArticleDocDAO {

	/**
	 * 根据MAP查找记录
	 * @param map
	 * @return
	 */
	public List<ArticleDoc> find(HashMap map){
		List<ArticleDoc> list = new ArrayList<ArticleDoc>();
		Object obj = getQueryDelegate().queryForList("FIND_ARTICLEDOC", map, getRoute());
		if(null != obj){
			list = (List<ArticleDoc>)obj;
		}
		return list;
	}
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = -1;
		count = getQueryDelegate().queryForCount("GET_ARTICLEDOC_COUNT",map,getRoute());
		return count;
	}

}
