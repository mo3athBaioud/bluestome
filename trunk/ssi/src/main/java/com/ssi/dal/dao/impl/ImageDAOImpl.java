package com.ssi.dal.dao.impl;

import java.util.HashMap;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.dal.dao.IImageDAO;
import com.ssi.dal.domain.Image;

public class ImageDAOImpl extends BaseDAOImpl implements IImageDAO {

	/**
	 * 根据ID查找记录
	 */
	public Image findById(Integer id) {
		return find(id,"FD");
	}
	
	/**
	 * 
	 * @param id
	 * @param link
	 * @return
	 */
	private Image find(Integer id,String link){
		Image image = null;
		HashMap map = new HashMap();
		map.put("id",id);
		map.put("link",link);
		Object obj = getQueryDelegate().queryForObject("GET_IMAGE_BY_MAP", map, getRoute());
		if(null != obj)
			image = (Image)obj;
		return image;
	}

}
