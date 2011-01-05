package com.chinamilitary.dao;

import java.util.List;

import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.db.ICommonDao;

public interface WebSiteDao extends ICommonDao{

	/**
	 * 添加网站记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public boolean insert(WebsiteBean bean) throws Exception;
	
	/**
	 * 修改网站记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public boolean update(WebsiteBean bean) throws Exception;
	
	/**
	 * 查找所有记录
	 * @return
	 * @throws Exception
	 */
	public List<WebsiteBean> findAll() throws Exception;
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WebsiteBean findById(Integer id) throws Exception;
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<WebsiteBean> findByParentId(Integer id) throws Exception;
}