package com.ssi.common.dal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.dal.BaseDAOImpl;
import com.ssi.common.dal.IbatisEntityDao;
import com.ssi.common.dal.dao.IArticleDAO;
import com.ssi.common.dal.domain.Article;

public class ArticleDAOImpl extends IbatisEntityDao<Article> implements IArticleDAO {

	/**
	 * 根据ID查找记录
	 * @param id 记录ID
	 */
	public Article findById(Integer id) {
		return find(id,"FD");
	}
	
	/**
	 * 添加记录
	 * @param article
	 * @return
	 */
//	public int insert(Article article){
//		int result = -1;
//		if(checkExists(article.getTitle(),article.getWebId(),article.getArticleUrl())){
//			logger.info("webid["+article.getWebId()+"],title["+article.getTitle()+"],url["+article.getArticleUrl()+"] exists");
//			return result;
//		}
//		result = (Integer)getEntityDelegate().insert("INSERT_ARTICLE", article, getRoute());
//		return result;
//	}
	/**
	 * 更新方法
	 * @param article
	 * @return
	 */
//	public int update(Article article){
//		int result = -1;
//		result = getEntityDelegate().update("UPDATE_ARTICLE", article, getRoute());
//		return result;
//	}
	
	/**
	 * 根据对象查找记录
	 * @param map
	 * @return
	 */
	public List<Article> find(HashMap map){
		List<Article> list = new ArrayList<Article>();
		Object obj = getQueryDelegate().queryForList("QUERY_ARTICLE_BY_MAP", map, getRoute());
		if(null != obj){
			list = (List<Article>)obj;
		}
		return list;
	}

	
	/**
	 * 根据指定ID以及文字说明获取文章
	 * @param id
	 * @param text
	 * @return
	 */
	private Article find(Integer id,String text){
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("text", text);
		Article article = null;
		Object obj = getQueryDelegate().queryForObject("GET_ARTICLE_BY_MAP",map,getRoute());
		if(null != obj)
			article = (Article)obj;
		return article;
	}

	/**
	 * 检查是否存在相同标题的代码
	 * @param title
	 * @param webId
	 * @return
	 * @throws Exception
	 */
	boolean checkExists(String title,Integer webId,String url){
		boolean b = false;
		if(null == title || title.equalsIgnoreCase("")){
			return b;
		}
		if(null == url || url.equalsIgnoreCase("")){
			return b;
		}
		HashMap map = new HashMap();
		map.put("title", title);
		map.put("webId", webId);
		map.put("articleUrl", url);
		int count = getCount(map);
		if(count > 0){
			return true;
		}
		return b;
	}

	/**
	 * 获取总数
	 * @param map
	 * @return
	 */
	public int getCount(HashMap map){
		int count = -1;
		map.put("limit", 1);
		count = getQueryDelegate().queryForCount("GET_ARTICLE_COUNT",map,getRoute());
		return count;
	}
	
}
