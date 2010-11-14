package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.dao.IArticleDocDAO;
import com.ssi.common.dal.domain.ArticleDoc;

public class ArticleDocDAOImpl extends BaseDAOImpl implements IArticleDocDAO {

	/**
	 * 添加记录
	 */
	public int insert(ArticleDoc articleDoc) {
		int result = -1 ;
		HashMap map = new HashMap();
		map.put("title", articleDoc.getTitle());
		int count = getCount(map);
		logger.info(">> url["+articleDoc.getUrl()+"],title["+articleDoc.getTitle()+"] 下的数量为:"+count);
		map.clear();
		if(count > 0){
			logger.info(">> PCPOP EXISTS THE SAME TITLE["+articleDoc.getTitle()+"]");
			return result;
		}
		map.put("url", articleDoc.getUrl());
		if(getCount(map) > 0){
			logger.info(">> PCPOP EXISTS THE SAME URL["+articleDoc.getUrl()+"]");
			return result;
		}
		map.clear();
		result = (Integer)getEntityDelegate().insert("INSERT_ARTICLEDOC", articleDoc, getRoute());
		return result;
	}

	/**
	 * 更新记录
	 */
	public int update(ArticleDoc articleDoc) {
		int result = -1;
		result = getEntityDelegate().update("UPDATE_ARTICLEDOC",articleDoc, getRoute());
		return result;
	}
	
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
