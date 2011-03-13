package com.ssi.cms.web.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ssi.cms.web.service.IArticleService;
import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.domain.Article;

public class ArticleServiceImpl implements IArticleService {
	
	private IArticleDAO articleDAO;

	public List<Article> find(HashMap map) {
		return articleDAO.find(map);
	}

	public Article findById(Integer id) {
		return articleDAO.findById(id);
	}

	public int getCount(HashMap map) {
		return articleDAO.getCount(map);
	}

	public boolean insert(Article article) {
		Integer result = articleDAO.insert(article);
		if(result > 0){
			return true; 
		}else{
			return false;
		}
	}

	public List<Article> findAll() throws Exception {
		return articleDAO.find(new HashMap());
	}

	public List<Article> findByWebId(Integer webId) throws Exception {
		HashMap map = new HashMap();
		map.put("webId", webId);
		return articleDAO.find(map);
	}

	public int getCount(String colName, String value, Integer webId) {
		HashMap map = new HashMap();
		if(null != colName || !"".equals("")){
			map.put(colName, value);
		}
		map.put("webId", webId);
		return articleDAO.getCount(map);
	}

	public List<Article> getPageList(String colName, String value, Integer startIndex, Integer pageSize, boolean asc, Integer webId) throws Exception {
		HashMap map = new HashMap();
		if(null == colName || colName.equalsIgnoreCase("")){
			map.put("title", value);
		}
		if(null == startIndex){
			startIndex = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}
//		map.put(colName, value);
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		map.put("webId", webId);
		List<Article>  list = articleDAO.find(map);
		return list;
	}

	public boolean update(Article article) throws Exception {
		Integer result = articleDAO.update(article); 
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	public IArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(IArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
	
	
}
