package com.ssi.dal.dao;

import com.ssi.dal.domain.Article;

public interface IArticleDAO {

	/**
	 * 根据记录ID查找记录
	 * @param id
	 * @return
	 */
	Article findById(Integer id);
}
