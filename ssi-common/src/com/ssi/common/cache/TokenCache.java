package com.ssi.common.cache;


public class TokenCache extends AbstractCache{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2295410105726981479L;
	private static final String CACHE_REGION = "token";

	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

	public void put(String key, String token) {
		super.putToCache(key, token);
	}

	public String get(String key) {
		return this.getFromCache(key, String.class);
	}
}