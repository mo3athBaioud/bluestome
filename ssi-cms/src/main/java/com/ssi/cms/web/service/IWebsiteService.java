package com.ssi.cms.web.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ssi.common.dal.domain.Website;

public interface IWebsiteService {

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return Website
	 */
	Website findById(Integer id);
	
	/**
	 * 根据父ID查找网站子记录
	 * @param fatherId
	 * @return
	 */
	List<Website> findByFatherId(Integer fatherId);
	
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
	List<Website> find(HashMap map);
	
	/**
	 * 查询所有文章记录
	 * @return
	 */
	List<Website> findAll() throws Exception;

	/**
	 * 根据文章所属网站ID查询文章记录
	 * @param webId
	 * @return
	 */
	List<Website> findByParentId(Integer id) throws Exception;
	
	/**
	 * 获取根一级菜单
	 * @return
	 */
	List<Website> getRootWebSite() throws Exception;
	
	/**
	 * 获取根一级菜单
	 * @return
	 */
	List<Website> getRootWebSite(int webType) throws Exception;
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 */
	Website findWebById(Integer id) throws Exception;


	/**
	 * 分页方法
	 * @param pageNo 页码
	 * @param pageSize 每页显示的记录数
	 * @param asc 是否顺序排列
	 * @return List<T>
	 */
	List<Website> getPageList(String colName,String value,Integer startIndex,Integer pageSize,boolean asc) throws Exception;
	
	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	int getCount(String colName,String value);
	
	/**
	 * 构建树形菜单
	 * @param list
	 * @param request
	 * @return
	 * @throws Exception
	 */
	String tree(List<Website> list) throws Exception;	
	
	/**
	 * 构建第二种树形菜单
	 * @param list
	 * @param request
	 * @return
	 * @throws Exception
	 */
	String tree2(List<Website> list,HttpServletRequest request) throws Exception;
	
	/**
	 * 更新记录
	 * @param website
	 * @return
	 */
	boolean update(Website website);
	
	/**
	 * 添加记录
	 * @param website
	 * @return
	 */
	boolean insert(Website website);
	
	
	/**
	 * 根据站点id禁用站点
	 * @param id
	 * @return
	 */
	boolean disable(Integer id);

	/**
	 * 根据站点id启用站点
	 * @param id
	 * @return
	 */
	boolean enable(Integer id);
}
