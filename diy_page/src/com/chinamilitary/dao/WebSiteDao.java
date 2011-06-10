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
	boolean insert(WebsiteBean bean) throws Exception;
	
	/**
	 * 修改网站记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	boolean update(WebsiteBean bean) throws Exception;
	
	/**
	 * 查找所有记录
	 * @return
	 * @throws Exception
	 */
	List<WebsiteBean> findAll() throws Exception;
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	WebsiteBean findById(Integer id) throws Exception;
	
	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<WebsiteBean> findByParentId(Integer id) throws Exception;
	
	/**
	 * 根据站点URL查找web对象
	 * @param url
	 * @return
	 * @throws Exception
	 */
	WebsiteBean findByUrl(String url) throws Exception;
}
