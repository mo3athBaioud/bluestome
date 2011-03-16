package com.ssi.htmlparser.cache;

import java.util.HashMap;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;
import com.ssi.common.dal.domain.Website;

/**
 * 网站记录缓存类
 * @author bluestome
 *
 */
public class WebsiteCache extends AbstractCache {

	private static final long serialVersionUID = 1L;

	private static final String CACHE_REGION = "website";
	private static final String CACHE_PREFIX = "website:";
	
	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

	public boolean containsKey(String key) throws CacheException {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void put(String key, Website website) {
		super.putToCache(CACHE_PREFIX + key, website);
	}

	public Website get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, Website.class);
	}
	
}
