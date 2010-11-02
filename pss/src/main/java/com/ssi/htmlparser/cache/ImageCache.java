package com.ssi.htmlparser.cache;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;
import com.ssi.dal.domain.Image;

public class ImageCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7262046181551246299L;
	private static final String CACHE_REGION = "image";
	private static final String CACHE_PREFIX = "image:";
	
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
	
	public void put(String key, Image image) {
		super.putToCache(CACHE_PREFIX + key, image);
	}

	public Image get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, Image.class);
	}

}
