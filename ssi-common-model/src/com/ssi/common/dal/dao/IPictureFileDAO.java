package com.ssi.common.dal.dao;

import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.domain.PictureFile;

public interface IPictureFileDAO {

	/**
	 * 根据文章ID和图片ID查找记录
	 * @param articleId
	 * @param imageId
	 * @return
	 */
	PictureFile find(Integer articleId,Integer imageId);
	
	/**
	 * 添加操作
	 * @param picture
	 * @return
	 */
	boolean insert(PictureFile picture);
	/**
	 * 更新操作
	 * @return
	 */
	int update(PictureFile picture);
	
	/**
	 * 查找最近多少条记录
	 * @param number 记录数
	 * @return
	 */
	List<PictureFile> findLastPictureFile(int number);
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	int getCount(HashMap map);
	
	/**
	 * 根据条件查询记录
	 * @param map
	 * @return
	 */
	List<PictureFile> find(HashMap map);
}
