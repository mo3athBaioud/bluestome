package com.ssi.common.cache;

public class SessionCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7001557107595681412L;

	private static final String CACHE_REGION = "session";

	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

	public void put(String key, Object value) {
		super.putToCache(key, value);
	}

	public Object get(String key) {
		return this.getFromCache(key, Object.class);
	}
}
