package com.ssi.htmlparser.cache;

import java.util.List;

import com.ssi.common.cache.AbstractCache;
import com.ssi.common.cache.CacheException;
import com.ssi.common.dal.domain.PictureFile;

public class PictureFileCache extends AbstractCache {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2287622536130759482L;
	private static final String CACHE_REGION = "picturefile";
	private static final String CACHE_PREFIX = "picturefile:";
	private static final String CACHE_LIST_PREFIX = "picturefile:list:";
	
	
	@Override
	protected String getCacheRegion() {
		// TODO Auto-generated method stub
		return CACHE_REGION;
	}

	public boolean containsKey(String key) throws CacheException {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 添加单个对象
	 * @param key
	 * @param picturefile
	 */
	public void put(String key, PictureFile picturefile) {
		super.putToCache(CACHE_PREFIX + key, picturefile);
	}

	/**
	 * 添加列表对象
	 * @param key
	 * @param list
	 */
	public void putList2Cache(String key, List<PictureFile> list) {
		super.putToCache(CACHE_LIST_PREFIX + key, list);
	}
	
	/**
	 * 从缓存中移除单个对象
	 * @param key
	 */
	public void remove(String key){
		super.removeFromCache(CACHE_PREFIX + key);
	}
	
	/**
	 * 从缓存中移除列表对象
	 * @param key
	 */
	public void removeList(String key){
		super.removeFromCache(CACHE_LIST_PREFIX + key);
	}
	
	/**
	 * 从缓存中获取单个对象
	 * @param key
	 * @return
	 */
	public PictureFile get(String key) {
		return this.getFromCache(CACHE_PREFIX + key, PictureFile.class);
	}
	
	/**
	 * 从缓存中获取列表对象
	 * @param key
	 * @return
	 */
	public List<PictureFile> getListFromCache(String key) {
		return this.getFromCache(CACHE_LIST_PREFIX + key, List.class);
	}
	
}
