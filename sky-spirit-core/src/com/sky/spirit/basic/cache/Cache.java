/**
 * 文件com.sky.spirit.basic.cache.Cache.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache;

import java.util.Date;
import java.util.Map;

import com.sky.spirit.basic.exception.CacheException;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午12:21:16
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public interface Cache {

	/**
	 * 
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public Object read(Object key) throws CacheException;

	/**
	 * 
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public Object get(Object key) throws CacheException;

	/**
	 * 
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void put(Object key, Object value) throws CacheException;
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 * @throws CacheException
	 */
	public void put(Object key, Object value,Date expiry) throws CacheException;
	/**
	 * 
	 * @param key
	 * @param value
	 * @throws CacheException
	 */
	public void update(Object key, Object value) throws CacheException;

	/**
	 * 
	 * @param key
	 * @throws CacheException
	 */
	public void remove(Object key) throws CacheException;

	/**
	 * 
	 * @throws CacheException
	 */
	public void clear() throws CacheException;

	/**
	 * 
	 * @throws CacheException
	 */
	public void destroy() throws CacheException;

	/**
	 * 
	 * @param key
	 * @throws CacheException
	 */
	public void lock(Object key) throws CacheException;

	/**
	 * 
	 * @param key
	 * @throws CacheException
	 */
	public void unlock(Object key) throws CacheException;

	/**
	 * 
	 * @return
	 */
	public long nextTimestamp();

	/**
	 * 
	 * @return
	 */
	public int getTimeout();

	/**
	 * 
	 * @return
	 */
	public String getRegionName();

	public long getSizeInMemory();

	public long getElementCountInMemory();

	public long getElementCountOnDisk();

	public Map<Object,Object> toMap();

	public long incr(Object key) throws CacheException;

	public long incr(Object key, long inc) throws CacheException;

	public boolean storeCounter(Object key, long counter) throws CacheException;

	public long getCounter(Object key) throws CacheException;

	public boolean flushAll();
}