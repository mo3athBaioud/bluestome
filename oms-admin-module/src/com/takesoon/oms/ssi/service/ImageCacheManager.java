package com.takesoon.oms.ssi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class ImageCacheManager {

	private static Log logger = LogFactory.getLog(ImageCacheManager.class);
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
//			cache.remove(element,true);
			cache.remove(key);
			if(null != cache.get(key))
			{
				logger.debug(" > key["+key+"] is not remove from ehcache!");
				cache.remove(element);
			}
			else
			{
				logger.debug(" > key["+key+"] is remove from ehcache!");
			}
				
		}
	}
	
	/**
	 * 删除缓存中的对象
	 * @param key
	 */
	public void remove(String key){
		Element element = cache.get(key);
		if(null != element)
		{
			cache.remove(key);
			if(null != cache.get(key))
			{
				logger.debug(" > key["+key+"] is not remove from ehcache!");
				cache.remove(element);
			}
			else
			{
				logger.debug(" > key["+key+"] is remove from ehcache!");
			}
				
		}
	}
	
	/**
	 * 添加缓存对象
	 * @param key
	 * @param obj
	 */
	public void put(String key,Object obj)
	{
		if(null == cache.get(key)){
			Element element = new Element(key,obj);
			cache.put(element);
		}
		
	}
	
	/**
	 * 获取缓存中元素数量
	 * @return int[] 元素数组 
	 * int[0] = 缓存中总的缓存数目
	 * int[1] = 内存中的元素数量 
	 * int[2] = 硬盘中的缓存元素数量
	 */
	public int[] getCacheElementSize()
	{
		int[] sizes = new int[3];
		sizes[0] = cache.getSize();
		sizes[1] = Long.valueOf(cache.getMemoryStoreSize()).intValue();
		sizes[2] = cache.getDiskStoreSize();
		return sizes;
	}

	/**
	 * 获取缓存值
	 * @param key
	 * @return
	 */
	public Object get(String key){
		Object body = null;
		Element element = cache.get(key);
		if(null != element)
		{
			body = element.getValue();
		}
		return body;
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
