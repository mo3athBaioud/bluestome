/**
 * 文件com.sky.spirit.basic.cache.provider.ehcache.Ehcache.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache.provider.ehcache;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Timestamper;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;

/**
 * 
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午12:13:27
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public class Ehcache implements Cache {
	private static Log log = LogFactory.getLog(Ehcache.class);
	public static final String SPRING_BEAN_NAME = "com.skymobi.skyspirit.cache.ehcache.Ehcache";
	private static final int SIXTY_THOUSAND_MS = 60000;
	private net.sf.ehcache.Cache cache;

	public void clear() throws CacheException {
		if (cache != null) {
			cache.removeAll();
		}
	}

	public void destroy() throws CacheException {
		if (cache != null) {
			cache.removeAll();
			cache.dispose();
			cache.getCacheManager().removeCache(cache.getName());
		}
	}

	public boolean flushAll() {
		if (cache != null) {
			cache.flush();
		}
		return true;
	}

	public Object get(Object key) throws CacheException {
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
			return null;
		} else {
			return null;
		}
	}

	public long getCounter(Object key) throws CacheException {

		return 0;
	}

	public long getElementCountInMemory() {
		return cache.getMemoryStoreSize();
	}

	public long getElementCountOnDisk() {
		return cache.getDiskStoreSize();
	}

	public String getRegionName() {
		return cache.getName();
	}

	public long getSizeInMemory() {
		try {
			return cache.calculateInMemorySize();
		} catch (Throwable t) {
			return -1;
		}
	}

	public int getTimeout() {
		return Timestamper.ONE_MS * SIXTY_THOUSAND_MS;
	}

	public long incr(Object key) throws CacheException {

		return 0;
	}

	public long incr(Object key, long inc) throws CacheException {

		return 0;
	}

	public void lock(Object key) throws CacheException {

	}

	public long nextTimestamp() {
		return Timestamper.next();
	}

	public void put(Object key, Object value) throws CacheException {
		Element cacheElement = this.cache.get(key);
		if (cacheElement == null) {
			cacheElement = new Element(key, value);
		} else {
			cacheElement = new Element(key, value);
		}
		if (this.cache != null) {
			log.debug("----store cache key:" + key + " success!");
			this.cache.put(cacheElement);
		}
	}

	public Object read(Object key) throws CacheException {
		if (cache != null) {
			Element e = cache.get(key);
			if (e != null) {
				log.debug("----get cache key:" + key + " success!");
				return e.getValue();
			}else if(!cache.isKeyInCache(key)){
				log.debug("----no cache key:" + key + " in cache!");
			}
		} else {
			return null;
		}
		return null;
	}

	public void remove(Object key) throws CacheException {
		if (this.cache != null) {
			if (cache.isKeyInCache(key)) {
				log.debug("----remove cache key:" + key + " success!");
				this.cache.remove(key);
			}else{
				log.debug("----no cache key:" + key + " in cache!");
			}
		}
	}

	public boolean storeCounter(Object key, long counter) throws CacheException {

		return false;
	}

	@SuppressWarnings("unchecked")
	public Map<Object, Object> toMap() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		Iterator<Object> iter = cache.getKeys().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			result.put(key, cache.get(key).getObjectValue());
		}
		return result;
	}

	public void unlock(Object key) throws CacheException {

	}

	public void update(Object key, Object value) throws CacheException {

	}

	public void setCache(net.sf.ehcache.Cache cache) {
		this.cache = cache;
	}

	public void put(Object key, Object value, Date expiry)
			throws CacheException {
		Element cacheElement = this.cache.get(key);
		if (cacheElement == null) {
			cacheElement = new Element(key, value);
		} else {
			cacheElement = new Element(key, value);
		}
		if (this.cache != null) {
			this.cache.put(cacheElement);
		}
	}

}
