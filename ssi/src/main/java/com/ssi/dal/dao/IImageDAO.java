package com.ssi.dal.dao;

import com.ssi.dal.domain.Image;

public interface IImageDAO {

	/**
	 * 根据ID查找记录
	 * @param id
	 * @return
	 */
	Image findById(Integer id);
}
