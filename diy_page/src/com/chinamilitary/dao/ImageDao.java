package com.chinamilitary.dao;

import java.util.HashMap;
import java.util.List;

import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.db.ICommonDao;

public interface ImageDao extends ICommonDao {

	/**
	 * 添加记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	int insert(ImageBean bean) throws Exception;
	
	/**
	 * 查找所有记录
	 * @return
	 * @throws Exception
	 */
	List<ImageBean> findAll() throws Exception;
	
	/**
	 * 查找某站点下的所有数据
	 * @param webParentId
	 * @return
	 * @throws Exception
	 */
	List<String> findImageURL(int webParentId) throws Exception;
	
	/**
	 * 根据父站点
	 */
	List<ImageBean> findImage(int webParentId) throws Exception;
	
	/**
	 * 查找某站点下的所有数据
	 * @param webParentId
	 * @return
	 * @throws Exception
	 */
	List<ImageBean> findImage(int webParentId,String status) throws Exception;
	/**
	 * 
	 * @param link
	 * @return
	 * @throws Exception
	 */
	ImageBean findByLink(String link,Integer articleid) throws Exception;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean updateLinkStatus(Integer id) throws Exception;
	
	/**
	 * 
	 * @param link
	 * @return
	 * @throws Exception
	 */
	ImageBean findByHttpUrl(String httpurl) throws Exception;
	
	/**
	 * 根据文章ID查找图片
	 * @param articleId
	 * @return
	 * @throws Exception
	 */
	List<HashMap<String,String>> findImageByArticle(Integer articleId) throws Exception;	
	
	/**
	 * 根据文章ID查找图片
	 * @param articleId
	 * @return
	 * @throws Exception
	 */
	List<ImageBean> findImage(Integer articleId) throws Exception;	
	
	/**
	 * 根据文章ID查找图片
	 * @param articleId
	 * @param text 资源状态
	 * @return
	 * @throws Exception
	 */
	List<ImageBean> findImage(Integer articleId,String text) throws Exception;	
	
	/**
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	boolean update(ImageBean bean) throws Exception;
	
	/**
	 * 根据图片大小分页获取数据
	 * @param x 文件大小
	 * @param offset 偏移量
	 * @param limit 最大数量
	 * @return
	 * @throws Exception
	 */
	List<ImageBean> findImageByFilesizeEqualX(Integer x,Integer offset,Integer limit) throws Exception;	

}
