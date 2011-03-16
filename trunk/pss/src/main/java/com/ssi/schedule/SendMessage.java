package com.ssi.schedule;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.common.cache.CacheException;
import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.dao.IWebsiteDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.Count;
import com.ssi.common.dal.domain.PictureFile;
import com.ssi.common.dal.domain.Website;
import com.ssi.common.utils.DateUtils;
import com.ssi.htmlparser.cache.PictureFileCache;

public class SendMessage extends BaseSchedule{

	private Integer id;
	
	private Integer count = 30;

	public void process() {
		logger.info(">> process");
		long start = System.currentTimeMillis();
		List<PictureFile> list = pictureFileDao.findLastPictureFile(count);
		if (null != list && list.size() > 0) {
			logger.info("---------------------------" + DateUtils.getNow()
					+ "---------------------------");
			int i = 0;
			for (PictureFile pic : list) {
				try {
					if (!pictureFileCache.containsKey(pic.getName())) {
						logger.info(">> No.[" + i + "] 添加图片记录到缓存中");
						pictureFileCache.put(pic.getName(), pic);
						logger.info("path:" + pic.getName());
					}
					i++;
				} catch (CacheException e) {
					logger.error(">> 缓存异常");
				}
			}
			long end = System.currentTimeMillis();
			logger.info("----------------------------耗时:" + (end - start)
					+ "ms----------------------------\n");
		} else {
			logger.info(">> 暂未查询当日最新图片文件");
		}
	}

	public void showStatus() {
		List<Website> list = websiteDao.findByFatherId(0);
		if (null != list && list.size() > 0) {
			for (Website website : list) {
				List<Count> countList = websiteDao.getImageCountByWebid(website
						.getId());
				if (null != countList && countList.size() > 0) {
					System.out.println("**********************["
							+ website.getName() + "|" + website.getId()
							+ "]*************************");
					for (Count count : countList) {
						System.out.println(count.getName() + ":"
								+ count.getTotal());
					}
					System.out
							.println("**********************End*****************************");
				}
			}
		}
	}

	public void showMsg() {
		logger.info(">> ShowMsg:Hello");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
