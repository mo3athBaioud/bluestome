/**
 * 文件com.sky.spirit.basic.cache.provider.memcached.MemCached.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache.provider.memcached;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Timestamper;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-5 下午11:56:12
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class MemCached implements Cache {

	public static final String SPRING_BEAN_NAME = "com.skymobi.skyspirit.cache.om.skymobi.skyspirit.cache.memcached.MemCached";
	private static final Log log = LogFactory.getLog(MemCached.class);
	private String[] serverList;
	private MemCachedClient mc = null;
	private final String regionName;
	private Integer[] weights;
	private int initialConnections = 128;
	private int minSpareConnections = 20;

	private int maxSpareConnections = 1024;
	private long maxIdleTime = 1000 * 60 * 30; // 30 minutes
	private long maxBusyTime = 1000 * 60 * 5; // 5 minutes
	private long maintThreadSleep = 1000 * 5; // 5 seconds
	private int socketTimeOut = 1000 * 3; // 3 seconds to block on reads
	private int socketConnectTO = 1000 * 3; // 3 seconds to block on initial
	// connections. If 0, then will use blocking connect (default)
	private boolean failover = false; // turn off auto-failover in event of
	// server down
	private boolean nagleAlg = false; // turn off Nagle's algorithm on all
	private int hashAlg;
	public void setHashAlg(int hashAlg) {
		this.hashAlg = hashAlg;
	}

	public MemCached() {
		super();
		mc = new MemCachedClient();
		regionName = "default";
	}

	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}

	public MemCached(String regionName2) {
		mc = new MemCachedClient();
		regionName = regionName2;
	}

	// sockets in pool
	public void setServerList(String[] serverList) {
		this.serverList = serverList;
	}

	public void setWeights(Integer[] weights) {
		this.weights = weights;
	}

	public void setInitialConnections(int initialConnections) {
		this.initialConnections = initialConnections;
	}

	public void setMinSpareConnections(int minSpareConnections) {
		this.minSpareConnections = minSpareConnections;
	}

	public void setMaxSpareConnections(int maxSpareConnections) {
		this.maxSpareConnections = maxSpareConnections;
	}

	public void setMaxIdleTime(long maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public void setMaxBusyTime(long maxBusyTime) {
		this.maxBusyTime = maxBusyTime;
	}

	public void setMaintThreadSleep(long maintThreadSleep) {
		this.maintThreadSleep = maintThreadSleep;
	}

	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public void setFailover(boolean failover) {
		this.failover = failover;
	}

	public void setNagleAlg(boolean nagleAlg) {
		this.nagleAlg = nagleAlg;
	}

	public void init() {
		InitThread init = new InitThread();
		init.start();
	}

	public void initPool() {
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(serverList);
		pool.setWeights(weights);
		pool.setInitConn(initialConnections);
		pool.setMinConn(minSpareConnections);
		pool.setMaxConn(maxSpareConnections);
		pool.setMaxIdle(maxIdleTime);
		pool.setMaxBusyTime(maxBusyTime);
		pool.setMaintSleep(maintThreadSleep);
		pool.setSocketTO(socketTimeOut);
		pool.setSocketConnectTO(socketConnectTO);
		pool.setNagle(nagleAlg);
		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		pool.setFailover(failover);
		pool.initialize();
		log.info("初始化memcached...");
	}

	public Object read(Object key) throws CacheException {
		if (log.isDebugEnabled()) {
			log.debug("key: " + key);
		}
		if (key == null) {
			return null;
		}
		Object result = mc.get(key.toString());
		if (result != null) {
			return result;
		} else {
			return null;
		}
	}

	public Object get(Object key) throws CacheException {
		return read(key);
	}

	public void put(Object key, Object value) throws CacheException {
		mc.set(key.toString(), value);
	}
	
	public void put(Object key, Object value,Date expiry) throws CacheException {
		mc.set(key.toString(), value,expiry);
	}

	public void update(Object key, Object value) throws CacheException {
		put(key, value);
	}

	public void remove(Object key) throws CacheException {
		mc.delete(key.toString());
	}

	public void clear() throws CacheException {
		mc.flushAll();
	}

	public void destroy() throws CacheException {
		SockIOPool.getInstance().shutDown();
	}

	public void lock(Object key) throws CacheException {
		
	}

	public void unlock(Object key) throws CacheException {

	}

	public long nextTimestamp() {
		return Timestamper.next();
	}

	public int getTimeout() {
		return Timestamper.ONE_MS * 60000;
	}

	public String getRegionName() {
		return regionName;
	}

	public long getSizeInMemory() {
		return Long.valueOf(mc.stats().get("bytes_written").toString())
				.longValue();
	}

	public long getElementCountInMemory() {
		return Long.valueOf(mc.stats().get("total_items").toString())
				.longValue();
	}

	public long getElementCountOnDisk() {
		return 0;
	}

	public Map<Object,Object> toMap() {
		throw new UnsupportedOperationException();
	}

	public long incr(Object key) {
		return mc.incr((String) key);
	}

	public long incr(Object key, long inc) {
		return mc.incr((String) key, inc);
	}

	public boolean storeCounter(Object key, long counter) {
		return mc.storeCounter((String) key, counter);
	}

	public long getCounter(Object key) {
		return mc.getCounter((String) key);
	}

	public boolean flushAll() {
		return mc.flushAll();
	}

	private class InitThread extends Thread {
		@Override
		public void run() {
			super.run();
			initPool();
		}
	}
}
