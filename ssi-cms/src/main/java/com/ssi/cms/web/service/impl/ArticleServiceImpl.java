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
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}else{
			map.put("title", value);
		}
		if(null != webId){
			map.put("webId", webId);
		}
		return articleDAO.getCount(map);
	}

	public List<Article> getPageList(String colName, String value, Integer startIndex, Integer pageSize, boolean asc, Integer webId) throws Exception {
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}else{
			map.put("title", value);
		}
		if(null == startIndex){
			startIndex = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		if(null != webId){
			map.put("webId", webId);
		}
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

	/**
	 * 删除记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Integer id) throws Exception{
		boolean b = false;
		Article article = null;
		Integer result = -1;
		try{
			article = articleDAO.findById(id);
			if(null != article){
				if(article.getText().equals("NED")){
					result = articleDAO.deleteByPrimarykey(id);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != article)
				article = null;
		}
		if(result > 0){
			b = true;
		}
		return b;
	}

	/**
	 * 禁用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean enable(Integer id) throws Exception{
		int result = -1;
		Article article = findById(id);
		if(null != article){
			if(!article.getText().equals("FD")){
				article.setText("FD");
				result = articleDAO.update(article);
			}
		}
		if(result > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 启用
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean disable(Integer id) throws Exception{
		int result = -1;
		Article article = findById(id);
		if(null != article){
			if(!article.getText().equals("NED")){
				article.setText("NED");
				result = articleDAO.update(article);
			}
		}
		if(result > 0){
			return true;
		}
		return false;
	}
	
	public IArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(IArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
	
	
}
