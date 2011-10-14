package com.takesoon.oms.ssi.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class ImageCacheManager {
	
	public String cacheName = "fileImageCache";
	
	private CacheManager cacheManager;
	
	private Cache cache;
	
	public void init(){
		cache = cacheManager.getCache(cacheName);
		if(null == cache){
			System.err.println(" >> rebuild cache named ["+cacheName+"] ");
			cache = new Cache(cacheName, 1, true, false, 5, 2);     
			cacheManager.addCache(cache);
		}
	}
	
	/**
	 * 添加对象
	 * @param key
	 * @param body
	 */
	public void putByte(String key,byte[] body){
		if(null == cache.get(key)){
			Element element = new Element(key,body);
			cache.put(element);
		}
	}
	
	/**
	 * 获取缓存值
	 * @param key
	 * @return
	 */
	public byte[] getByte(String key){
		byte[] body = null;
		Element element = cache.get(key);
		if(null != element)
		{
			body = (byte[])element.getValue();
		}
		return body;
	}
	
	/**
	 * 删除缓存中的对象
	 * @param key
	 */
	public void removeByte(String key){
		Element element = cache.get(key);
		if(null != element)
		{
			cache.remove(element);
		}
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	
	
}
