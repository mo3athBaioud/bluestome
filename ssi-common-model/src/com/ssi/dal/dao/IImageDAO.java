package com.ssi.dal.dao;

import java.util.HashMap;

import com.ssi.dal.domain.Image;

public interface IImageDAO {

	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 */
	Image findById(Integer id);
	
	/**
	 * 添加图片记录
	 * @param image
	 * @return
	 */
	int insert(Image image);
	
	/**
	 * 更新图片记录
	 * @param image
	 * @return
	 */
	int update(Image image);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
}
