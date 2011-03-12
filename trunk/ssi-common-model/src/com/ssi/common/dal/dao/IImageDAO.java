package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.EntityDAO;
import com.ssi.common.dal.domain.Image;

public interface IImageDAO extends EntityDAO<Image>{

	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 */
	Image findById(Integer id);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 按条件查找图片记录
	 * @param map
	 * @return
	 */
	List<Image> find(HashMap map);
}
