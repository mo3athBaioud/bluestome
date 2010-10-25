package com.ssi.dal.dao.impl;

import java.util.HashMap;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.dal.dao.IArticleDAO;
import com.ssi.dal.domain.Article;

public class ArticleDAOImpl extends BaseDAOImpl implements IArticleDAO {

	/**
	 * 根据ID查找记录
	 * @param id 记录ID
	 */
	public Article findById(Integer id) {
		return find(id,"FD");
	}
	
	/**
	 * 根据指定ID以及文字说明获取文章
	 * @param id
	 * @param text
	 * @return
	 */
	private Article find(Integer id,String text){
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("text", text);
		Article article = null;
		Object obj = getQueryDelegate().queryForObject("GET_ARTICLE_BY_MAP",map,getRoute());
		if(null != obj)
			article = (Article)obj;
		return article;
	}

}
