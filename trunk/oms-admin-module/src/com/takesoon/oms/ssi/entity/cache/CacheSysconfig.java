package com.takesoon.oms.ssi.entity.cache;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;

public class CacheSysconfig extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String CACHE_REGION = "sky-sysconfig";

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
	
	public void put(String key, String value) {
		super.putToCache(CACHE_REGION + key, value);
	}
	
	public String get(String key) {
		return super.getFromCache(CACHE_REGION + key,String.class);
	}

	
	@Override
	public void removeFromCache(Object key) {
		// TODO Auto-generated method stub
		super.removeFromCache(CACHE_REGION + key);
	}

	public String getCACHE_REGION() {
		return CACHE_REGION;
	}

	public void setCACHE_REGION(String cache_region) {
		CACHE_REGION = cache_region;
	}

	
}
