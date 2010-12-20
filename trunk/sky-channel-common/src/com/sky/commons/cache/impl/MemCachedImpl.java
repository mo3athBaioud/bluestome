package com.sky.commons.cache.impl;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.sky.commons.cache.ICache;

/**
 * 
 * @description : memCached ʵ��
 * @create author :<a href="yizhiju@hotmail.com">Benny</a>
 * @create date:Aug 22, 2008 2:31:55 PM
 * @edit author:
 * @edit date:Aug 22, 2008
 * @����޸�˵��:
 */
public class MemCachedImpl implements ICache {
	private final static Logger logger = LoggerFactory
	.getLogger(MemCachedImpl.class);
	// ����ȫ�ֵ�Ψһʵ��
	private MemCachedClient mcc = new MemCachedClient();
	private String[] serverList;
//	private String regionName;
	private Integer[] weights={3};
	private int initialConnections = 128;
	private int minSpareConnections = 20;
	private int maxSpareConnections = 1024;
	private long maxIdleTime = 1000 * 60 * 30; // 30 minutes
	private long maxBusyTime = 1000 * 60 * 5; // 5 minutes
	private long maintThreadSleep = 1000 * 5; // 5 seconds
	private int socketTimeOut = 1000 * 3; // 3 seconds to block on reads
	private int socketConnectTO = 1000 * 3; // 3 seconds to block on initial
	// connections. If 0, then will use blocking connect (default)
	private boolean failover = true; // turn off auto-failover in event of
	// server down
	private boolean nagleAlg = false; // turn off Nagle's algorithm on all
	private int hashAlg;
	
	
	public MemCachedImpl(){
	}
	
	public void init(){
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
		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
		pool.setFailover(failover);
		pool.initialize();
		// ѹ�����ã�����ָ����С����λΪK������ݶ��ᱻѹ��
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(64 * 1024);
	}
	
	
	public void setMcc(MemCachedClient mcc) {
		this.mcc = mcc;
	}

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

	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
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

	public void setHashAlg(int hashAlg) {
		this.hashAlg = hashAlg;
	}

	/**
	 * ========��keyΪ���value,���۴���������򱣴棬�����滻==========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		return mcc.set(key, value);
	}

	public boolean set(String key, Object value, Long time) {
		return mcc.set(key, value, new Date(System.currentTimeMillis() + time));
	}

	public boolean set(String key, Object value, Date expiry) {
		return mcc.set(key, value, expiry);
	}
	
	/**
	 * ���һ��ָ����ֵ��������.���Ѿ������򲻱���
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		return mcc.add(key, value, expiry);
	}
	
	public boolean add(String key, Object value, Long time) {
		return mcc.add(key, value, new Date(System.currentTimeMillis() + time));
	}

	/**
	 * ===========�滻��ֵ����������滻===========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	/**
	 * ���ָ���Ĺؼ��ֻ�ȡ����.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return mcc.get(key);
	}
	
	/**
	 * ɾ��
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(String key) {
		return mcc.delete(key);
	}

	public boolean keyExists(String key) {
		return mcc.keyExists(key);
	}

	public boolean flushAll(){
		return mcc.flushAll();
	}

	public void destroy(){
		SockIOPool.getInstance().shutDown();
	}

	public boolean set(String key, Object value, int seconds) {
		return mcc.set(key, value, DateUtils.addSeconds(new Date(), seconds));
	}

	public boolean remove(String key) {
		return mcc.delete(key);
	}
}
