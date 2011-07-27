package com.ssi.schedule;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.dao.IPictureFileDAO;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.PictureFile;
import com.ssi.common.utils.DateUtils;

public class SendMessage {

	private final Log logger = LogFactory.getLog(SendMessage.class);

	private IArticleDAO articleDAO;
	private IPictureFileDAO pictureFileDAO;
	private Integer id;

//	public void process() {
//		Article article = articleDAO.findById(id);
//		if(null != article){
//			long time = System.currentTimeMillis();
//			logger.debug(">> article.title["+article.getTitle()+"],time:" + time);
//		}else{
//			logger.debug(">> 暂无查询到文章");
//		}
//	}
	
	public void process(){
		long start = System.currentTimeMillis();
		List<PictureFile> list = pictureFileDAO.findLastPictureFile(30);
		if(null != list && list.size() > 0){
			logger.debug("---------------------------"+DateUtils.getNow()+"---------------------------");
			for(PictureFile pic:list){
				logger.debug("path:"+pic.getName());
			}
			long end = System.currentTimeMillis();
			logger.debug("----------------------------耗时:"+(end-start)+"ms----------------------------\n");
		}else{
			logger.debug(">> 暂未查询当日最新图片文件");
			Integer i = 2147483647;
		}
	}
	public IArticleDAO getArticleDAO() {
		return articleDAO;
	}

	public void setArticleDAO(IArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IPictureFileDAO getPictureFileDAO() {
		return pictureFileDAO;
	}

	public void setPictureFileDAO(IPictureFileDAO pictureFileDAO) {
		this.pictureFileDAO = pictureFileDAO;
	}

	
	
}
