package com.ssi.dal.dao;

import com.ssi.dal.domain.PictureFile;

public interface IPictureFileDAO {

	/**
	 * 根据文章ID和图片ID查找记录
	 * @param articleId
	 * @param imageId
	 * @return
	 */
	PictureFile find(Integer articleId,Integer imageId);
}
