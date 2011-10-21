package com.takesoon.oms.ssi.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class FileCacheManager {
	
	private static Log logger = LogFactory.getLog(FileCacheManager.class);
	public String cacheName = "fileCache";
	private Cache cache;
	
	/**
	 * 添加对象
	 * @param key
	 * @param body
	 */
	public void putByte(String key,byte[] body){
		if(null == cache.get(key)){
			Element element = new Element(key,body);
			logger.debug("putByte key["+key+"]|value["+body+"]");
			cache.put(element);
		}
	}
	
	public void putUrl(String key,String value){
		if(null == cache.get(key)){
			Element element = new Element(key,value);
			cache.put(element);
			logger.debug("putUrl key["+key+"]|value["+value+"]");
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
			logger.debug("getByte key["+key+"]|body.length["+body.length+"]");
		}
		return body;
	}
	
	public String getUrl(String key){
		String value = null;
		Element element = cache.get(key);
		if(null != element)
		{
			value = (String)element.getValue();
			logger.debug("getUrl key["+key+"]|value.length["+value+"]");
		}
		return value;
	}
	
	/**
	 * 删除缓存中的对象
	 * @param key
	 */
	public void removeByte(String key){
		Element element = cache.get(key);
		if(null != element)
		{
			logger.debug("remove key:"+key);
			cache.remove(element);
		}
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
