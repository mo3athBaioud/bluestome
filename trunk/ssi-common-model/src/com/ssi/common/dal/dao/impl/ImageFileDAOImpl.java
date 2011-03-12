package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.common.dal.dao.IImageFileDAO;
import com.ssi.common.dal.domain.ImageFile;

public class ImageFileDAOImpl extends IbatisEntityDao<ImageFile> implements IImageFileDAO {

	/**
	 * 根据文章ID和图片ID查找图片文件记录
	 */
	public ImageFile find(Integer articleId, Integer imageId) {
		List<ImageFile> list = findBy(articleId, imageId);
		if(null != list && list.size() == 1){
			return (ImageFile)list.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param articleId
	 * @param imageId
	 * @return
	 */
	private List<ImageFile> findBy(Integer articleId,Integer imageId){
		List<ImageFile> list = new ArrayList<ImageFile>();
		HashMap map = new HashMap();
		if(null != articleId && articleId > 0)
			map.put("articleid",articleId);
		if(null != imageId && imageId > 0)
			map.put("imageid",imageId);
		Object obj = getQueryDelegate().queryForList("GET_ImageFile_BY_MAP", map, getRoute());
		if(null != obj)
			list = (List<ImageFile>)obj;
		return list;
	}
	
	/**
	 * 根据条件查询记录
	 * @param map
	 * @return
	 */
	public List<ImageFile> find(HashMap map){
		List<ImageFile> list = new ArrayList<ImageFile>();
		Object obj = getQueryDelegate().queryForList("GET_ImageFile_BY_MAP", map, getRoute());
		if(null != obj)
			list = (List<ImageFile>)obj;
		return list;
	}
	
	/**
	 * 查找最近多少条记录
	 * @param number 记录数
	 * @return
	 */
	public List<ImageFile> findLastImageFile(int number){
		List<ImageFile> list = new ArrayList<ImageFile>();
		HashMap map = new HashMap();
		if(number < 0){
			map.put("limit", 100);
		}else{
			map.put("limit", number);
		}
		Object obj = getQueryDelegate().queryForList("GET_LASTEST_ImageFile", map, getRoute());
		if(null != obj){
			list = (List<ImageFile>)obj;
		}
		if(list.size()>0){
			return list;
		}
		return null;
	}
	
	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = -1;
		count = getQueryDelegate().queryForCount("GET_ImageFile_COUNT",map,getRoute());
		return count;
	}
	
}
