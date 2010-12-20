package com.sky.commons.cache.impl;

import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.commons.cache.ICache;


public class EhcacheImpl implements ICache {

	private Cache cache = null;
	private final static Logger logger = LoggerFactory
			.getLogger(EhcacheImpl.class);

	public boolean add(String key, Object value) {
		boolean b = false;
		try {
			cache.put(new Element(key, value));
			b = true;
		} catch (Exception e) {
			logger.warn("add object error={}", e);
		}
		return b;
	}

	public boolean add(String key, Object value, int seconds) {
		return this.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		return this.add(key, value);

	}

	public boolean delete(String key) {
		boolean b =false;
		try {
			cache.remove(key);
			b = true;
		} catch (Exception e) {
			logger.warn("set object error={}", e);
		}
		return b;
	}

	public boolean flushAll() {
		try {
		cache.flush();
		} catch (Exception e) {
			logger.warn("flushAll error={}", e);
			return false;
		}
		return true;
	}

	public Object get(String key) {
		Object o = null;
		try {
			Element element = cache.get(key);
			if (element != null) {
				o = element.getObjectValue();
			}
		} catch (Exception e) {
			o = null;
			logger.warn("get object error={}", e);
		}
		return o;
	}

	public boolean keyExists(String key) {
		return cache.isKeyInCache(key);
	}

	public boolean replace(String key, Object value) {
		return this.set(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return this.set(key, value, expiry);
	}

	public boolean set(String key, Object value) {
		boolean b =false;
		try {
			cache.put(new Element(key, value));
			b = true;
		} catch (Exception e) {
			logger.warn("set object error={}", e);
		}
		return b;
	}

	public boolean set(String key, Object value, Long time) {
		boolean b =false;
		try {
			Element element = new Element(key, value);
			element.setEternal(false);
			element.setTimeToIdle(time.intValue()/1000);
			element.setTimeToLive(time.intValue()/1000);
			cache.put(element);
			b = true;
		} catch (Exception e) {
			logger.warn("set(String key, Object value, Long time) error={}", e);
		}
		return b;
	}

	public boolean set(String key, Object value, int seconds) {
		boolean b =false;
		try {
			Element element = new Element(key, value);
			element.setEternal(false);
			element.setTimeToIdle(seconds);
			element.setTimeToLive(seconds);
			cache.put(element);
			b = true;
		} catch (Exception e) {
			logger.warn("set object error={}", e);
		}
		return b;
	}
	
	public boolean set(String key, Object value, Date expiry) {
		return true;
	}

	
	public boolean add(String key, Object value, Long time) {
		return this.add(key, value, (int)(time/1000));
		
	}

	public boolean remove(String key) {
		try {
			cache.remove(key);
		} catch (Exception e) {
			logger.warn("remove {} error",key);
			return false;
		}
		return true;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}


}
