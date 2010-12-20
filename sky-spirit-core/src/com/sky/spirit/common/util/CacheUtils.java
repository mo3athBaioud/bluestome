/**
 * �ļ�com.sky.spirit.common.util.CacheUtils.java ������2008 2008-9-19 ����05:39:30
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-19 ����05:39:30
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.common.util;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.cache.CacheEntity;
import com.sky.spirit.basic.cache.CacheFactory;
import com.sky.spirit.basic.cache.provider.ehcache.Ehcache;
import com.sky.spirit.basic.cache.provider.memcached.MemCached;
import com.sky.spirit.basic.exception.CacheException;

/**
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-19 ����05:39:30
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public class CacheUtils {
	/**
	 * ��ȡCache����,�ȴӱ��ص�Ehcache�л�ȡ���ݣ����û���ٴ�Զ�̵�memcached��ȡ����
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
	 * �����ݵ�����Cache��
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
