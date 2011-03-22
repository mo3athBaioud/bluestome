package com.ssi.cms.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.cms.web.service.ImageIService;
import com.ssi.common.dal.dao.IImageDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.ArticleDoc;
import com.ssi.common.dal.domain.Image;

public class ImageServiceImpl implements ImageIService {
	private IImageDAO imageDAO;
	private static Log log = LogFactory.getLog(ImageServiceImpl.class);

	/**
	 * 根据ArticleId查询图片列表
	 * 
	 * @param articleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Image> findByArticleId(Integer articleId) throws Exception {
		HashMap map = new HashMap();
		map.put("articleId", articleId);
		List<Image> list = new ArrayList<Image>();
		list = imageDAO.find(map);
		return list;
		
	}

	/**
	 * 根据列名和值来查询数据
	 * @param colName
	 * @param value
	 * @return
	 */
	public int getCount(String colName, String value,Integer articleId) {
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}else{
			map.put("title", value);	
		}
		map.put("title", value);
		map.put("articleId", articleId);
		return imageDAO.getCount(map);
	}

	/**
	 * 分页方法
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页显示的记录数
	 * @param asc
	 *            是否顺序排列
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<Image> getPageList(String colName, String value, Integer startIndex, Integer pageSize,Integer articleId,boolean asc) throws Exception {
		HashMap map = new HashMap();
		if(null != colName && !"".equals(colName)){
			if(null != value && !"".equals(value)){
				map.put(colName, value);
			}
		}else{
			map.put("title", value);	
		}
		if(null == startIndex){
			startIndex = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}
		if(asc){
			map.put("asc", "asc");
		}else{
			map.put("asc", "desc");
		}
		map.put("limit", pageSize);
		map.put("offset", startIndex);
		map.put("articleId", articleId);
		List<Image>  list = imageDAO.find(map);
		return list;
	}

	public IImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(IImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

	public boolean insert(Image image) {
		Integer result = imageDAO.insert(image);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public List<Image> find(HashMap map) {
		return imageDAO.find(map);
	}

	public Image findById(Integer id) {
		return imageDAO.findById(id);
	}
	
}
