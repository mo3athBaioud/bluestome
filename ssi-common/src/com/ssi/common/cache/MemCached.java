package com.ssi.common.cache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * memcached实现类
 * 
 */
public abstract class MemCached implements Cache {

	private static Log logger = LogFactory.getLog(MemCached.class);

	private MemcachedClient mc = null;

	// 有效期默认一天
	private int timeToLive = 60 * 60 * 24 * 1;

	private boolean isConnected = false;

	private String server;

	private int port;

	public MemCached() {
		super();
	}

	public Object get(Object key) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return null;
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

	public void put(Object key, Object value) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		put(key, value, timeToLive);
	}

	public void put(Object key, Object value, int TTL) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		Future<Boolean> ret = mc.set(key.toString(), TTL, value);
		// 下面这段不予执行，应该每次等待完成没有意义。因为net.spy.memcached.MemcachedClient是异步的
		// 如果出错了就让它下次跑的时候再插入key
		/*
		 * try{ if (ret.get().booleanValue()==false) { //if (!ret.isDone()) {
		 * throw new CacheException("Memcached set Object Error."); } }
		 * catch(Exception e){ throw new CacheException("Memcached set Object
		 * Error."); }
		 */
	}

	public void update(Object key, Object value) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		put(key, value);
	}

	public void remove(Object key) throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		Future<Boolean> isSuccess = mc.delete(key.toString());
		/*
		 * if (!isSuccess.isDone()) { throw new CacheException("Memcached delete
		 * Object Error."); }
		 */
	}

	public void clear() throws CacheException {
		if (!isConnected()) {
			logger.warn("Memcached not initialized");
			return;
		}

		Future<Boolean> isSuccess = mc.flush();
		/*
		 * if (!isSuccess.isDone()) { throw new CacheException("Memcached clean
		 * Object Error."); }
		 */
	}

	public void destroy() throws CacheException {
		mc = null;
	}

	public boolean containsKey(Object key) throws CacheException {

		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}

	public long incr(Object key, int inc) {

		return mc.incr((String) key, inc);
	}

	public boolean flushAll() {
		try {
			return mc.flush().get();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void init() {
		new Thread() {
			public void run() {
				logger.info("初始化Memcached...");

				if (mc == null) {
					try {
						mc = new MemcachedClient(getBindAddress());
						isConnected = true;
						logger.info(">>>> Initialize MemCached [" + getCacheRegion() + "] completed.");
					} catch (IOException e) {
						logger.error(">>>> Initialize MemCached [" + getCacheRegion() + "] failure:", e);
					}

				}
			}
		}.start();
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private InetSocketAddress getBindAddress() {
		return new InetSocketAddress(server, port);
	}
	
	protected abstract String getCacheRegion();
}
