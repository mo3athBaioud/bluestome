package com.ssi.htmlparser.cache;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;
import com.ssi.dal.domain.ArticleDoc;

public class ArticleDocCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = 126270338549240346L;
	private static final String CACHE_REGION = "articleDoc";
	private static final String CACHE_PREFIX = "articleDoc:";
	
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
	
	public void put(String key, ArticleDoc articleDoc) {
		super.putToCache(CACHE_PREFIX + key, articleDoc);
	}

	public ArticleDoc get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, ArticleDoc.class);
	}
	
}
