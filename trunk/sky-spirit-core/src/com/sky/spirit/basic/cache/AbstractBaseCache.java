/**
 * 文件com.sky.spirit.basic.cache.AbstractBaseCache.java 创建于2008 2008-9-5 下午09:38:25
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-5 下午09:38:25
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache;

import com.sky.spirit.basic.exception.CacheException;

/**
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-5 下午09:38:25
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public abstract class AbstractBaseCache implements ICacheable {
	protected Cache ehcache;
	protected boolean memcachedEnable = false;
	protected boolean ehcacheEnable = false;

	public abstract void setMemcachedEnable(boolean enable);

	public abstract void setEhcachedEnable(boolean enable);

	public void setEhcache(Cache ehcache) {
		this.ehcache = ehcache;
	}

	public void setMemcache(Cache memcache) {
		this.memcached = memcache;
	}

	protected Cache memcached;

	/**
	 * 获取Cache策略,先从本地的Ehcache中获取数据，如果没有再从远程的memcached获取数据
	 * 
	 * @param domain
	 * @param cacheKey
	 * @return
	 */
	public CacheEntity getCacheEntity(String cacheKey) throws CacheException {
		CacheEntity cacheEntity = null;
		if (ehcacheEnable) {
			cacheEntity = (CacheEntity) ehcache.get(cacheKey);
		}
		if (cacheEntity == null && memcachedEnable == true) {
			cacheEntity = (CacheEntity) memcached.get(cacheKey);
			//2010-12-09 将记录缓存时间不延续
//			if (cacheEntity != null) {
//				cacheEntity.setRight(System.currentTimeMillis());
//				putCacheEntity(cacheKey, cacheEntity);
//			}
		}
		return cacheEntity;
	}

	/**
	 * 存数据到两个Cache中
	 * 
	 * @param domain
	 * @param cacheKey
	 * @param cacheEntity
	 * @throws CacheException
	 */
	public void putCacheEntity(String cacheKey, CacheEntity cacheEntity)
			throws CacheException {
		if (ehcacheEnable) {
			ehcache.put(cacheKey, cacheEntity);
		}
		if (memcachedEnable) {
			memcached.put(cacheKey, cacheEntity);
		}
	}

	public void removeCacheEntity(String cacheKey) throws CacheException {
		if (ehcacheEnable) {
			ehcache.remove(cacheKey);
		}
		if (memcachedEnable) {
			memcached.remove(cacheKey);
		}
	}
}
