package com.sky.commons.cache;

import java.util.Date;

/**
 * 
 * 	@description 	:	缓存交互统一接口
 * 	@create author :<a href="yizhiju@hotmail.com">Benny</a>
 *	@create date:Oct 23, 2008  4:03:57 PM
 *	@edit author:
 * 	@edit date:Oct 23, 2008
 * 	@最后修改说明:
 */
public interface ICache {

	/**
	 * ========以key为键保存value,无论存在与否，无则保存，有则替换==========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value);

	/**
	 * ========以key为键保存value,无论存在与否，无则保存，有则替换==========
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            对象存活时间
	 * @return
	 */
	public boolean set(String key, Object value, Long time);
	public boolean set(String key, Object value, int seconds);
	/**
	 * ========以key为键保存value,无论存在与否，无则保存，有则替换==========
	 * @param key
	 * @param value
	 * @param expiry 对象失效时间点
	 * @return 保存成功返回true
	 */
	public boolean set(String key, Object value, Date expiry);
	/**
	 * 添加一个指定的值到缓存中.若已经存在则不保存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value);

	/**
	 * 添加一个值,如果已经存在则不添加
	 * @param key
	 * @param value
	 * @param time 有效时长，单位为：毫秒
	 * @return
	 */
	public boolean add(String key, Object value, Long time);

	/**
	 * 添加一个值,如果已经存在则不添加
	 * @param key
	 * @param value
	 * @param expiry 失效时间点
	 * @return
	 */
	public boolean add(String key, Object value, Date expiry);
	/**
	 * ===========替换旧值，若不存在则不替换===========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value);

	
//	public boolean replace(String key, Object value, Date expiry);

	/**
	 * 根据key删除某一个对象
	 * 
	 * @param key
	 * @return 不存在返回false，存在返回true
	 */
	public boolean delete(String key);

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return 存在返回对象，否则返回null
	 */
	public Object get(String key);
	
	/**
	 * 判断键是否存在
	 * @param key
	 * @return
	 */
	public boolean keyExists(String key);
	
	public boolean flushAll();
	
	public boolean remove(String key);
}
