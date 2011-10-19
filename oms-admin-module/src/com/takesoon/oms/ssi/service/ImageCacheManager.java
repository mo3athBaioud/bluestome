package com.takesoon.oms.ssi.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class ImageCacheManager {
	
	public String cacheName = "fileImageCache";
	
	private Cache cache;
	
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

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	
	
}
