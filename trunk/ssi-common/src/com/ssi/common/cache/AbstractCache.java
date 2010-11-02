package com.ssi.common.cache;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractCache extends MemCached implements Serializable {
	/**
	 * 
	 */
	protected final Log logger = LogFactory.getLog(this.getClass());

	public boolean clean() {
		try {
			if (isConnected()) {
				if (logger.isInfoEnabled()) {
					logger.info(">>>> clean the cache [" + getCacheRegion()
							+ "]");
				}
				clear();
			}
			return true;
		} catch (Exception ex) {
			logger.warn(">>>> clean the cache [" + getCacheRegion()
					+ "] failure.", ex);
			return false;
		}

	}

	public void removeFromCache(Object key) {
		try {
			if (isConnected()) {
				super.remove(key);
				if (logger.isDebugEnabled()) {
					logger.debug("Removed from cache [" + getCacheRegion()
							+ "], key [" + key + "]");
				}
			}
		} catch (CacheException e) {
			logger.warn("Failed to remove from cache [" + getCacheRegion()
					+ "]", e);
		}
	}

	public Object getFromCache(Object key) {
		if (!isConnected()) {
			return null;
		}
		return getFromCache(key, Object.class);
	}

	public <T extends Object> T getFromCache(Object key, Class<T> clazz) {
		T ret = null;
		try {

			if (isConnected()) {
				ret = (T) get(key);
				if (logger.isDebugEnabled()) {
					logger.debug("Getting from cache [" + getCacheRegion()
							+ "], key [" + key + "]");
				}
/*				if (logger.isDebugEnabled()) {
					logger.debug("get from cache [" + getCacheRegion()
							+ "], key [" + key + "], result [" + ret + "]");
				}*/

			}
		} catch (CacheException e) {
			logger.warn("Failed to read from cache [" + getCacheRegion() + "]",
					e);
		}
		return ret;
	}

	public void putToCache(Object key, Object obj) {
		if (obj == null) {
			return;
		}

		try {
			if (isConnected()) {

				put(key, obj);
				if (logger.isDebugEnabled()) {
					logger.debug(">>>> put cached object: " + key + " obj: "
							+ obj + " into cache [" + getCacheRegion() + "]");
				}
			}
		} catch (CacheException ex) {
			logger.warn(">>>> Put the object into cache [" + getCacheRegion()
					+ "] failure.", ex);
		}
	}
	
	
}
