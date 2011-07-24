package com.ssi.acl.dal.domain;

import com.ssi.common.cache.MemCached;

public class CacheModel extends MemCached {

	static final String CACHE_REGION = "resource";
	
	@Override
	protected String getCacheRegion() {
		return CACHE_REGION;
	}

}
