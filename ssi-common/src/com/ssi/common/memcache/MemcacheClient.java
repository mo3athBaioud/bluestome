package com.ssi.common.memcache;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheClient {

	// 创建全局的唯一实例
	private static MemCachedClient cache = null;
	
	private static String[] servers = {"127.0.0.1:11211"};
	
	private static long expiry = 3600;

	// 设置与缓存服务器的连接池
	static {
		// 服务器列表和其权重
		Integer[] weights = { 3 };

		// 获取socke连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();

		// 设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(3000);

		// 初始化连接池
		pool.initialize();

		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		cache.setCompressEnable(true);
		cache.setCompressThreshold(64 * 1024);
	}

	/**
	 * 保护型构造方法，不允许实例化！
	 * 
	 */
	public MemcacheClient() {
	}

	/**
	 * 按照过期时间添加一个指定的值到缓存中.
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		return cache.add(key, value,new Date(System.currentTimeMillis()+1000*expiry));
	}

	public boolean add(String key, Object value, Date expiry) {
		return cache.add(key, value, expiry);
	}

	/**
	 * 按照过期时间替换缓存中的数据
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value) {
		return cache.replace(key, value,new Date(System.currentTimeMillis()+1000*expiry));
	}

	public boolean replace(String key, Object value, Date expiry) {
		return cache.replace(key, value, expiry);
	}
	
	/**
	 * 删除缓存中的记录
	 * @param key
	 * @return
	 */
	public boolean remove(String key){
		return cache.delete(key);
	}

	/**
	 * 根据指定的关键字获取对象.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return cache.get(key);
	}

	public static long getExpiry() {
		return expiry;
	}

	public static void setExpiry(long expiry) {
		MemcacheClient.expiry = expiry;
	}

	public String[] getServers() {
		return servers;
	}

	public void setServers(String[] servers) {
		MemcacheClient.servers = servers;
	}
	
	
}