package com.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.factory.DAOFactory;
import com.chinamilitary.util.HttpClientUtils;
import com.zhuoku.ZHUOKUParser;

public class DataProcess {

	static Logger logger = LoggerFactory.getLogger(ZHUOKUParser.class);

	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<ImageBean> list = null;
		do {
			try {
				list = imageDao.findImageByFilesizeEqualX(0, 0, 200);
				for (ImageBean b : list) {
					String len = HttpClientUtils.getHttpConentLength(b
							.getHttpUrl(), b.getHttpUrl(), b.getHttpUrl());
					if (null != len && !len.equals("0") && b.getStatus() != -3) {
						int length = Integer.valueOf(len);
						if (length > 0) {
							b.setFileSize(Long.valueOf(length));
							if (imageDao.update(b)) {
								System.out.println(b.getId() + "|"
										+ b.getArticleId() + "|"
										+ b.getHttpUrl() + "|\t获取图片大小成功，大小为:"
										+ length + "!");
							}
						}
					} else {
						logger.info(b.getId() + "|" + b.getArticleId() + "|"
								+ b.getHttpUrl() + "|\t获取图片大小失败!");
						b.setFileSize(-1L);
						//经过处理发现图片大小获取不到
						b.setStatus(-3);
						imageDao.update(b);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} while (null != list && list.size() > 0);
	}

}
