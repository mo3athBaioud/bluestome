package com.ssi.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.dal.dao.IPictureFileDAO;
import com.ssi.dal.domain.PictureFile;

public class PictureFileDAOImpl extends BaseDAOImpl implements IPictureFileDAO {

	/**
	 * 根据文章ID和图片ID查找图片文件记录
	 */
	public PictureFile find(Integer articleId, Integer imageId) {
		List<PictureFile> list = findBy(articleId, imageId);
		if(null != list && list.size() == 1){
			return (PictureFile)list.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param articleId
	 * @param imageId
	 * @return
	 */
	private List<PictureFile> findBy(Integer articleId,Integer imageId){
		List<PictureFile> list = new ArrayList<PictureFile>();
		HashMap map = new HashMap();
		if(null != articleId && articleId > 0)
			map.put("articleid",articleId);
		if(null != imageId && imageId > 0)
			map.put("imageid",imageId);
		Object obj = getQueryDelegate().queryForList("GET_PICTUREFILE_BY_MAP", map, getRoute());
		if(null != obj)
			list = (List<PictureFile>)obj;
		return list;
	}
	
	/**
	 * 查找最近多少条记录
	 * @param number 记录数
	 * @return
	 */
	public List<PictureFile> findLastPictureFile(int number){
		List<PictureFile> list = new ArrayList<PictureFile>();
		HashMap map = new HashMap();
		if(number < 0){
			map.put("limit", 100);
		}else{
			map.put("limit", number);
		}
		Object obj = getQueryDelegate().queryForList("GET_LASTEST_PICTUREFILE", map, getRoute());
		if(null != obj)
			list = (List<PictureFile>)obj;
		return list;
	}

}
