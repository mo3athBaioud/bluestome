/**
 * 文件com.sky.spirit.common.util.CacheUtils.java 创建于2008 2008-9-19 下午05:39:30
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-19 下午05:39:30
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.cache.CacheEntity;
import com.sky.spirit.basic.cache.CacheFactory;
import com.sky.spirit.basic.cache.provider.ehcache.Ehcache;
import com.sky.spirit.basic.cache.provider.memcached.MemCached;
import com.sky.spirit.basic.exception.CacheException;

/**
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-19 下午05:39:30
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public class CacheUtils {
	/**
	 * 获取Cache策略,先从本地的Ehcache中获取数据，如果没有再从远程的memcached获取数据
	 * 
	 * @param domain
	 * @param cacheKey
	 * @return
	 */
	private static CacheEntity getCacheEntity(String cacheKey)
			throws CacheException {
		CacheEntity cacheEntity = null;
		Cache cache = CacheFactory.createCache(Ehcache.class.getName());

		cacheEntity = (CacheEntity) cache.get(cacheKey);

		if (cacheEntity == null) {
			cache = CacheFactory.createCache(MemCached.class.getName());

			cacheEntity = (CacheEntity) cache.get(cacheKey);

			if (cacheEntity != null) {
				cacheEntity.setRight(System.currentTimeMillis());
				putCacheEntity(cacheKey, cacheEntity);
			}
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
	private static void putCacheEntity(String cacheKey, CacheEntity cacheEntity)
			throws CacheException {
		Cache cache = CacheFactory.createCache(MemCached.class.getName());
		cache.put(cacheKey, cacheEntity);
		cache = CacheFactory.createCache(Ehcache.class.getName());
		cache.put(cacheKey, cacheEntity);
	}

	private static void removeCacheEntity(String cacheKey) throws CacheException {
		Cache cache = CacheFactory.createCache(MemCached.class.getName());
		cache.remove(cacheKey);
		cache = CacheFactory.createCache(Ehcache.class.getName());
		cache.remove(cacheKey);
	}
}
