package com.ssi.htmlparser.cache;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;

public class CommonCache extends AbstractCache {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8854950824070618736L;
	private static final String CACHE_REGION = "common";
	private static final String CACHE_PREFIX = "common:";
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
	
	public void put(String key, Object obj) {
		super.putToCache(CACHE_PREFIX + key, obj);
	}

	public Object get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, Object.class);
	}
}
