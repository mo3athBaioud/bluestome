package com.ssi.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;

/**
 * memcached实现类
 * 
 */
public abstract class MemCached implements Cache {

	private static Logger logger = LoggerFactory.getLogger(MemCached.class);

	private MemCachedClient mc = null;

	// 有效期默认一天
	private int timeToLive = 60 * 60 * 24 * 1;

	private boolean isConnected = false;

	public MemCached() {
		super();
	}

	public Object get(Object key) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return null;
		}

		if (key == null) {
			return null;
		}

		Object result = mc.get(key.toString());
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}

	public void put(Object key, Object value) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		put(key, value, timeToLive);
	}

	public void put(Object key, Object value, int TTL) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		mc.set(key.toString(),value, TTL);
	}

	public void update(Object key, Object value) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		put(key, value);
	}

	public void remove(Object key) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}
		mc.delete(key.toString());
	}

	public void clear() throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		mc.flushAll();
	}

	public void destroy() throws CacheException {
		if(null != mc)
		{
			mc = null;
		}
	}

	public boolean containsKey(Object key) throws CacheException {

		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}

	public long incr(Object key, int inc) {
		return mc.incr((String) key, inc);
	}

	public boolean flushAll() {
		boolean b = false;
		if(isConnected())
		{
			try {
				b = mc.flushAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public boolean isConnected() {
		if(!isConnected)
		{
			logger.warn("Memcached not connect");
		}
		return isConnected;
	}

	public MemCachedClient getMc() {
		return mc;
	}

	public void setMc(MemCachedClient mc) {
		if(null != mc)
		{
			this.mc = mc;
			isConnected = true;
		}
	}
	
	/**
	 * 抽象方法用于获取缓存的KEY名称前缀
	 * @return
	 */
	protected abstract String getCacheRegion();

}
