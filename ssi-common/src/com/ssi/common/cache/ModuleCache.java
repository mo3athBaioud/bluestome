package com.ssi.common.cache;

import java.util.HashMap;

public class ModuleCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7962688921535867492L;

	private static final String CACHE_REGION = "module";

	private static final String cacheKey = String.valueOf(Long.MIN_VALUE);

	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

	public void putModules(HashMap modules) {
		super.putToCache(cacheKey, modules);
	}

	public HashMap getModules() {
		return this.getFromCache(cacheKey, HashMap.class);
	}
}
