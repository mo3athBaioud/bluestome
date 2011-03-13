package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Image;

public interface ImageIService {

	/**
	 * 根据ArticleId查询图片列表
	 * @param articleId
	 * @return
	 */
	List<Image> findByArticleId(Integer articleId) throws Exception;

	/**
	 * 分页方法
	 * @param pageNo 页码
	 * @param pageSize 每页显示的记录数
	 * @param asc 是否顺序排列
	 * @return List<T>
	 */
	List<Image> getPageList(String colName,String value,Integer startIndex,Integer pageSize,Integer articleId,boolean asc) throws Exception;
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	int getCount(String colName,String value,Integer articleId);
	
	
	/**
	 * 添加记录
	 * @param image
	 * @return
	 */
	boolean insert(Image image);
	
	/**
	 * 根据条件查找记录
	 * @param map
	 * @return
	 */
	public List<Image> find(HashMap map);

	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 */
	public Image findById(Integer id);	
}
