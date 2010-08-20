package com.tuku.htmlparser;

import java.io.File;
import java.util.List;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.bean.PicfileBean;
import com.chinamilitary.bean.WebsiteBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.dao.PicFileDao;
import com.chinamilitary.dao.WebSiteDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.memcache.MemcacheClient;
import com.chinamilitary.util.CacheUtils;
import com.chinamilitary.util.CommonUtil;
import com.chinamilitary.util.IOUtil;

public class TUKUDownload {

	static String SAVE_PATH = "F:\\china\\tuku\\";

	static MemcacheClient client = MemcacheClient.getInstance();

	static ArticleDao articleDao = DAOFactory.getInstance().getArticleDao();

	static WebSiteDao webSiteDao = DAOFactory.getInstance().getWebSiteDao();

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	static PicFileDao picFiledao = DAOFactory.getInstance().getPicFileDao();

	/**
	 * 下载逻辑类 返回核心下载结果
	 * @param img
	 * @return
	 */
	static boolean downloadImage(ImageBean img) throws Exception{
		boolean b = false;
		if (picFiledao.checkExists(img.getId(), img.getArticleId())) {
			System.err.println(">> 该记录["+img.getId()+"]已经下载 ");
			return false;
		}
		imgDownload(img);
		b = true;
		return b;
	}

	/**
	 * 核心下载类 执行实际的文件下载
	 * @param imgBean
	 * @throws Exception
	 */
	static void imgDownload(ImageBean imgBean) throws Exception {
		System.err.println(" >> IN Download Image Method");
		PicfileBean bean = null;
		bean = new PicfileBean();
		String s_fileName = imgBean.getImgUrl().substring(
				imgBean.getImgUrl().lastIndexOf("/") + 1,
				imgBean.getImgUrl().length());
		String fileName = imgBean.getHttpUrl().substring(
				imgBean.getHttpUrl().lastIndexOf("/") + 1,
				imgBean.getHttpUrl().length());
		s_fileName = s_fileName.replace(".", "_s.");

		if (null == client.get(CacheUtils.getDownloadSmallImageKey(imgBean
				.getId()))) {
			IOUtil.createPicFile(imgBean.getImgUrl(), SAVE_PATH
					+ CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator
					+ fileName.replace(".", "_s."));
		}

		if (null == client.get(CacheUtils.getDownloadBigImageKey(imgBean
				.getId()))) {
			IOUtil.createPicFile(imgBean.getHttpUrl(), SAVE_PATH
					+ CommonUtil.getDate("") + File.separator
					+ imgBean.getArticleId() + File.separator + fileName);
		}
		bean.setArticleId(imgBean.getArticleId());
		bean.setImageId(imgBean.getId());
		bean.setTitle(imgBean.getTitle());
		bean.setSmallName(CommonUtil.getDate("") + File.separator
				+ imgBean.getArticleId() + File.separator + s_fileName);

		bean.setName(CommonUtil.getDate("") + File.separator
				+ imgBean.getArticleId() + File.separator + fileName);
		bean.setUrl(SAVE_PATH);

		boolean b = picFiledao.insert(bean);
		if (b) {
			client.add(CacheUtils.getDownloadSmallImageKey(imgBean.getId()),
					"SMALL_" + imgBean.getId());
			client.add(CacheUtils.getDownloadBigImageKey(imgBean.getId()),
					"BIG_" + imgBean.getId());
			if (imageDao.updateLinkStatus(imgBean.getId())) {
				// 更新图片地址
				if (null != client.get(imgBean.getHttpUrl())) {
					client.replace(imgBean.getHttpUrl(), imgBean.getHttpUrl());
				} else {
					client.add(imgBean.getHttpUrl(), imgBean.getHttpUrl());
				}
			}else{
				throw new Exception(">> 添加文件失败");
			}

		} else {
			System.err.println(">> 下载图片失败");
			throw new Exception(">> 添加文件保存记录失败");
		}
	}

	/**
	 * 下载方法
	 * @param bean
	 */
	static void patchImag(WebsiteBean bean) throws Exception {
		List<Article> artList = articleDao.findByWebId(bean.getId(), "FD");
		for (Article article : artList) {
			List<ImageBean> imgList = imageDao.findImage(article.getId());
			for (ImageBean img : imgList) {
				try {
					if(downloadImage(img)){
						img.setStatus(1);
						img.setLink("FD");
						if(!imageDao.update(img)){
							System.err.println(" >> 更新tbl_image对象记录["+img.getId()+"]失败!!!");
						}
					}
				} catch (Exception e) {
					System.err.println(">> error patch img");
					continue;
				}
			}
		}
	}
	
	public static void main(String args[]){
		try{
			List<WebsiteBean> list = webSiteDao.findByParentId(400);
			for(WebsiteBean bean:list){
				patchImag(bean);
			}
		}catch(Exception e){
			System.err.println(" >> Exception:"+e);
		}
	}

}
