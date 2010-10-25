package com.ssi.common.heartbeats;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author wangqi
 * 
 */
public class ServerStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9005684852181343392L;

	/**
	 * 请求总数
	 */
	private long reqNum;

	/**
	 * 会话总数
	 */
	private long sessionNum;

	/**
	 * 缓存服务器已运行时间
	 */
	private long cacheServerUptime;

	/**
	 * 查询缓存命中总数
	 */
	private long cacheTotalHits;

	/**
	 * 查询缓存请求总数
	 */
	private long cacheTotalReq;

	/**
	 * 缓存对象数目
	 */
	private long cacheElements;

	/**
	 * 空间总大小
	 */
	private long cacheTotalBytes;

	/**
	 * 已使用字节数
	 */
	private long cacheUsedBytes;

	/**
	 * 缓存可用空间
	 */
	private long cacheAvailBytes;

	/**
	 * 在缓存对象过期之前，LRU移除的元素个数
	 */
	private long cacheEvictions;

	/**
	 * 缓存读取字节数
	 */
	private long cacheBytesRead;

	/**
	 * 缓存写字节数
	 */
	private long cacheBytesWritten;

	/**
	 * Memcached服务器IP
	 */
	private String memcachedIp;

	/**
	 * Memcached服务器PORT
	 */
	private String memcachedPort;
	
	private String startupScript = "";

	public long getReqNum() {
		return reqNum;
	}

	public void setReqNum(long reqNum) {
		this.reqNum = reqNum;
	}

	public long getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(long sessionNum) {
		this.sessionNum = sessionNum;
	}

	public long getCacheTotalHits() {
		return cacheTotalHits;
	}

	public void setCacheTotalHits(long cacheTotalHits) {
		this.cacheTotalHits = cacheTotalHits;
	}

	public long getCacheTotalReq() {
		return cacheTotalReq;
	}

	public void setCacheTotalReq(long cacheTotalReq) {
		this.cacheTotalReq = cacheTotalReq;
	}

	public long getCacheElements() {
		return cacheElements;
	}

	public void setCacheElements(long cacheElements) {
		this.cacheElements = cacheElements;
	}

	public long getCacheAvailBytes() {
		return cacheAvailBytes;
	}

	public void setCacheAvailBytes(long cacheAvailBytes) {
		this.cacheAvailBytes = cacheAvailBytes;
	}

	public long getCacheEvictions() {
		return cacheEvictions;
	}

	public void setCacheEvictions(long cacheEvictions) {
		this.cacheEvictions = cacheEvictions;
	}

	public long getCacheServerUptime() {
		return cacheServerUptime;
	}

	public void setCacheServerUptime(long cacheServerUptime) {
		this.cacheServerUptime = cacheServerUptime;
	}

	public long getCacheTotalBytes() {
		return cacheTotalBytes;
	}

	public void setCacheTotalBytes(long cacheTotalBytes) {
		this.cacheTotalBytes = cacheTotalBytes;
	}

	public long getCacheUsedBytes() {
		return cacheUsedBytes;
	}

	public void setCacheUsedBytes(long cacheUsedBytes) {
		this.cacheUsedBytes = cacheUsedBytes;
	}

	public long getCacheBytesRead() {
		return cacheBytesRead;
	}

	public void setCacheBytesRead(long cacheBytesRead) {
		this.cacheBytesRead = cacheBytesRead;
	}

	public long getCacheBytesWritten() {
		return cacheBytesWritten;
	}

	public void setCacheBytesWritten(long cacheBytesWritten) {
		this.cacheBytesWritten = cacheBytesWritten;
	}

	public String getMemcachedIp() {
		return memcachedIp;
	}

	public void setMemcachedIp(String memcachedIp) {
		this.memcachedIp = memcachedIp;
	}

	public String getMemcachedPort() {
		return memcachedPort;
	}

	public void setMemcachedPort(String memcachedPort) {
		this.memcachedPort = memcachedPort;
	}

	public String getStartupScript() {
		return startupScript;
	}

	public void setStartupScript(String startupScript) {
		this.startupScript = startupScript;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
