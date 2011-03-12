package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.Image;

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
	
	/**
	 * 按条件查找图片记录
	 * @param map
	 * @return
	 */
	List<Image> find(HashMap map);
}