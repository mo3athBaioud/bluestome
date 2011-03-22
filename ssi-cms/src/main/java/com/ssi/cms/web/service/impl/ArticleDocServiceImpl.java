package com.ssi.cms.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.cms.web.service.ArticleDocIService;
import com.ssi.common.dal.dao.IArticleDocDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.ArticleDoc;

public class ArticleDocServiceImpl implements ArticleDocIService {
	
	private IArticleDocDAO articleDocDAO;
	private static Log log = LogFactory.getLog(ArticleDocServiceImpl.class);
	/**
	 * 查询所有文章记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleDoc> findAll()  throws Exception{
		HashMap map = new HashMap();
		List<ArticleDoc> list = new ArrayList<ArticleDoc>();
		list = articleDocDAO.find(map);
		return list;
	}

	/**
	 * 根据文章所属网站ID查询文章记录
	 * @param webId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleDoc> findByWebId(Integer webId)  throws Exception{
		HashMap map = new HashMap();
		map.put("webId", webId);
		List<ArticleDoc> list = new ArrayList<ArticleDoc>();
		list = articleDocDAO.find(map);
		return list;
	}

	/**
	 * 分页方法
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示的记录数
	 * @param asc
	 *            是否顺序排列
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<ArticleDoc> getPageList(String colName, String value, Integer startIndex, Integer pageSize, boolean asc,Integer webId) throws Exception {
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
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		map.put("webId", webId);
		List<ArticleDoc>  list = articleDocDAO.find(map);
		return list;
	}
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	public int getCount(String colName,String value,Integer webId){
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}else{
			map.put("title", value);	
		}
		map.put("webId", webId);
		return articleDocDAO.getCount(map);
	}

	
	/**
	 * 更新文章
	 * @param articleDoc
	 * @return
	 * @throws Exception
	 */
	public boolean update(ArticleDoc articleDoc) throws Exception {
		Integer result = articleDocDAO.update(articleDoc);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	public List<ArticleDoc> find(HashMap map) {
		return articleDocDAO.find(map);
	}

	public ArticleDoc findById(Integer id) {
//		return articleDocDAO.findByPrimarykey(id);
		return null;
	}
	
	public IArticleDocDAO getArticleDocDAO() {
		return articleDocDAO;
	}

	public void setArticleDocDAO(IArticleDocDAO articleDocDAO) {
		this.articleDocDAO = articleDocDAO;
	}


	
}
