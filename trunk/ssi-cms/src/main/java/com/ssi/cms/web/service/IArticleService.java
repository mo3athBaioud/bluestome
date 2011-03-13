package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Article;

public interface IArticleService {
	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	Article findById(Integer id);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 根据哈希表查找数据
	 * @param map
	 * @return
	 */
	List<Article> find(HashMap map);
	
	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	boolean insert(Article article);

	/**
	 * 查询所有文章记录
	 * @return
	 */
	List<Article> findAll() throws Exception;

	/**
	 * 根据文章所属网站ID查询文章记录
	 * @param webId
	 * @return
	 */
	List<Article> findByWebId(Integer webId) throws Exception;
	
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
	public List<Article> getPageList(String colName, String value,
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
	 * @param article
	 * @return
	 * @throws Exception
	 */
	boolean update(Article article) throws Exception;	
}
