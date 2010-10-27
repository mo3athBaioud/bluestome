package com.ssi.dal.dao;

import java.util.List;

import com.ssi.dal.domain.PictureFile;

public interface IPictureFileDAO {

	/**
	 * 根据文章ID和图片ID查找记录
	 * @param articleId
	 * @param imageId
	 * @return
	 */
	PictureFile find(Integer articleId,Integer imageId);
	
	/**
	 * 查找最近多少条记录
	 * @param number 记录数
	 * @return
	 */
	List<PictureFile> findLastPictureFile(int number);
}
