package com.ssi.dal.dao.impl;

import java.util.HashMap;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.dal.dao.IImageDAO;
import com.ssi.dal.domain.Image;
import com.ssi.dal.domain.PictureFile;

public class ImageDAOImpl extends BaseDAOImpl implements IImageDAO {

	/**
	 * 根据ID查找记录
	 */
	public Image findById(Integer id) {
		return find(id,"FD");
	}
	
	/**
	 * 添加图片记录
	 * @param image
	 * @return
	 */
	public int insert(Image image){
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_IMAGE",image,getRoute());
		return result;
	}

	/**
	 * 更新图片记录
	 * @param image
	 * @return
	 */
	public int update(Image image){
		int result = -1;
		result = getEntityDelegate().update("UPDATE_IMAGE",image,getRoute());
		return result;
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
		if(null != obj){
			image = (Image)obj;
			HashMap temp = new HashMap();
			temp.put("articleid", image.getArticleId());
			temp.put("imageid", image.getId());
			PictureFile pictureFile = (PictureFile)getQueryDelegate().queryForObject("GET_PICTUREFILE_BY_MAP",temp,getRoute());
			if(null != pictureFile){
				image.setPictureFile(pictureFile);
			}
			temp.clear();
		}
		map.clear();
		return image;
	}
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = -1;
		count = getQueryDelegate().queryForCount("GET_IMAGE_COUNT",map,getRoute());
		return count;
	}

}
