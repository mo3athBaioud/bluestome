package com.takesoon.oms.ssi.cache;

/**
 * 缓存接口类
 */
public interface Cache {

	Object get(Object key) throws CacheException;

	void put(Object key, Object value) throws CacheException;

	void put(Object key, Object value, int TTL) throws CacheException;

	void update(Object key, Object value) throws CacheException;

	void remove(Object key) throws CacheException;

	void clear() throws CacheException;

	void destroy() throws CacheException;

	boolean containsKey(Object key) throws CacheException;

	long incr(Object key, int inc) throws CacheException;

	boolean flushAll();

	boolean isConnected();
}