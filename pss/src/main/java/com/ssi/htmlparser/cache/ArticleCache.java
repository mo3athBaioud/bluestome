package com.ssi.htmlparser.cache;

import java.util.List;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.dal.domain.PictureFile;

public class ArticleCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = 350676064588313822L;
	private static final String CACHE_REGION = "article";
	private static final String CACHE_PREFIX = "article:";
	private static final String CACHE_LIST_PREFIX = "article:list:";
	
	@Override
	protected String getCacheRegion() {
		// TODO Auto-generated method stub
		return CACHE_REGION;
	}

	public boolean containsKey(String key) throws CacheException {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void put(String key, Article article) {
		super.putToCache(CACHE_PREFIX + key, article);
	}

	public void putList2Cache(String key, List<Article> list) {
		super.putToCache(CACHE_LIST_PREFIX + key, list);
	}
	
	public void removeList(String key){
		super.removeFromCache(CACHE_LIST_PREFIX + key);
	}
	
	public Article get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, Article.class);
	}
	
	public List<Article> getListFromCache(String key) {
		return this.getFromCache(CACHE_LIST_PREFIX + key, List.class);
	}
	
}
