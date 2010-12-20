/**
 * �ļ�com.sky.spirit.basic.cache.ICacheable.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
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
 * @date 2008 2008-9-4 ����09:38:57
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public interface ICacheable {
	public CacheEntity getCacheEntity(String cacheKey) throws CacheException;

	public void putCacheEntity(String cacheKey, CacheEntity cacheEntity)
			throws CacheException;

	public void removeCacheEntity(String cacheKey) throws CacheException;
}
