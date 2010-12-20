/**
 * �ļ�com.sky.spirit.basic.cache.AbstractBaseCache.java ������2008 2008-9-5 ����09:38:25
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-5 ����09:38:25
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.cache;

import com.sky.spirit.basic.exception.CacheException;

/**
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-5 ����09:38:25
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
	 * ��ȡCache����,�ȴӱ��ص�Ehcache�л�ȡ���ݣ����û���ٴ�Զ�̵�memcached��ȡ����
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
			//2010-12-09 ����¼����ʱ�䲻����
//			if (cacheEntity != null) {
//				cacheEntity.setRight(System.currentTimeMillis());
//				putCacheEntity(cacheKey, cacheEntity);
//			}
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
