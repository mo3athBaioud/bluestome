package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.domain.PictureFile;

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
	 * 根据条件查询记录
	 * @param map
	 * @return
	 */
	public List<PictureFile> find(HashMap map){
		List<PictureFile> list = new ArrayList<PictureFile>();
		Object obj = getQueryDelegate().queryForList("GET_PICTUREFILE_BY_MAP", map, getRoute());
		if(null != obj)
			list = (List<PictureFile>)obj;
		return list;
	}
	
	/**
	 * 添加操作
	 * @param picture
	 * @return
	 */
	public boolean insert(PictureFile picture){
		int result = -1;
		result = (Integer)getEntityDelegate().insert("INSERT_PICTUREFILE", picture,getRoute());
		if(result > 0){
			return true;
		}
		return false;
	}
	/**
	 * 更新操作
	 * @return
	 */
	public int update(PictureFile picture){
		int result = -1;
		result = getEntityDelegate().update("UPDATE_PICTUREFILE", picture,getRoute());
		return result;
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
		if(null != obj){
			list = (List<PictureFile>)obj;
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
		count = getQueryDelegate().queryForCount("GET_PICTUREFILE_COUNT",map,getRoute());
		return count;
	}
	
}
