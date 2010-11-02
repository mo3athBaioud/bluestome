package com.ssi.common.cache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ssi.common.heartbeats.ServerStatus;

/**
 * 
 * @author wangqi
 * 
 */
public class CacheMonitor {

	private static final Log logger = LogFactory.getLog(CacheMonitor.class);

	private long checkTimeout = 1000;

	private String server;

	private int port;

	private boolean isConnected = false;

	private Timer timer = new Timer();

	private MemcachedClient mc = null;

	private AtomicReference<ServerStatus> status;

	private void scheduleNextCheck() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					checkCacheStatus();
				} catch (Exception e) {
					logger.error("checkCacheStatus: ", e);
				} finally {
					scheduleNextCheck();
				}
			}
		}, checkTimeout);
	}

	public void start() {
		connect();
		scheduleNextCheck();
	}

	private void connect() {
		new Thread() {
			public void run() {
				logger.info("初始化Memcached...");
				if (mc == null) {
					try {
						mc = new MemcachedClient(getBindAddress());
						isConnected = true;
						logger.info(">>>> Inialize MemCached completed.");
					} catch (IOException e) {
						logger.warn(">>>> initialize MemCached failure.");
						logger.error(">>>> Exception:", e);
					}
				}
			}
		}.start();
	}

	public void checkCacheStatus() {
		if (isConnected) {
			status.get().setCacheServerUptime(getMemcachedStatus("uptime"));

			status.get().setCacheTotalBytes(
					getMemcachedStatus("limit_maxbytes"));// 总内存
			status.get().setCacheUsedBytes(getMemcachedStatus("bytes"));// 已使用内存
			status.get().setCacheAvailBytes(
					status.get().getCacheTotalBytes()
							- status.get().getCacheUsedBytes());// 剩余内存
			status.get().setCacheElements(getMemcachedStatus("curr_items"));// 当前缓存个数
			status.get().setCacheEvictions(getMemcachedStatus("evictions"));// LRU移除的缓存个数

			status.get().setCacheTotalReq(getMemcachedStatus("cmd_get"));// 查询缓存的次数
			status.get().setCacheTotalHits(getMemcachedStatus("get_hits"));// 命中的次数

			status.get().setCacheBytesRead(getMemcachedStatus("bytes_read"));// 网络I/O
			status.get().setCacheBytesWritten(
					getMemcachedStatus("bytes_written"));// 网络I/O
		} else {
			connect();
		}
	}

	public long getMemcachedStatus(String key) {
		InetSocketAddress server = getBindAddress();

		Map<String, String> statuses = mc.getStats().get(server);
		if (statuses == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("No memcached server [" + server + "] found");
			}
			return 0L;
		}

		if (statuses.containsKey(key)) {
			return Long.parseLong(statuses.get(key));
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("No key [" + key
						+ "] found in memcached status description.");
			}
			return 0L;
		}
	}

	public void setCheckTimeout(long checkTimeout) {
		this.checkTimeout = checkTimeout;
	}

	public void setStatus(AtomicReference<ServerStatus> status) {
		this.status = status;
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
}
