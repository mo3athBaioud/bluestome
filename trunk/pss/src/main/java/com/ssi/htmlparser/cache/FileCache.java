package com.ssi.htmlparser.cache;

import com.ssi.common.cache.AbstractCache;

/**
 * 文件缓存类
 * @author bluestome
 *
 */
public class FileCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295410105726981479L;
	private static final String CACHE_REGION = "file";

	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

	public void put(String key, byte[] fileContent) {
		super.putToCache(key, fileContent);
	}

	public byte[] get(String key) {
		return this.getFromCache(key, byte[].class);
	}
}
