package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.ArticleDoc;

public interface ArticleDocIService{

	/**
	 * 查询所有文章记录
	 * @return
	 */
	List<ArticleDoc> findAll() throws Exception;

	/**
	 * 根据文章所属网站ID查询文章记录
	 * @param webId
	 * @return
	 */
	List<ArticleDoc> findByWebId(Integer webId) throws Exception;
	
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
	public List<ArticleDoc> getPageList(String colName, String value,
			Integer startIndex, Integer pageSize, boolean asc,Integer webId) throws Exception;	
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	int getCount(String colName,String value,Integer webId);
	
	/**
	 * 更新文章
	 * @param articleDoc
	 * @return
	 * @throws Exception
	 */
	boolean update(ArticleDoc articleDoc) throws Exception;	
	
	public List<ArticleDoc> find(HashMap map);

	public ArticleDoc findById(Integer id);	
	
}
