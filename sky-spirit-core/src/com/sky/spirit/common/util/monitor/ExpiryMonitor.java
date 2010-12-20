/**
 * 文件com.sky.spirit.common.util.monitor.ExpiryMonitor.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util.monitor;

import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.emory.mathcs.backport.java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:40:30
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class ExpiryMonitor extends TimerTask implements Disposable {
	/**
	 * logger used by this class
	 */
	protected static final Log logger = LogFactory.getLog(ExpiryMonitor.class);

	private Timer timer;
	private Map monitors;

	public ExpiryMonitor() {
		this(1000);
	}

	public ExpiryMonitor(long monitorFrequency) {

		timer = new Timer(true);
		timer.schedule(this, monitorFrequency, monitorFrequency);
		monitors = new ConcurrentHashMap();
	}

	/**
	 * Adds an expirable object to monitor. If the Object is already being
	 * monitored it will be reset and the millisecond timeout will be ignored
	 * 
	 * @param milliseconds
	 * @param expirable
	 */
	public void addExpirable(long milliseconds, Expirable expirable) {
		if (isRegistered(expirable)) {
			resetExpirable(expirable);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Adding new expirable: " + expirable);
			}
			monitors.put(expirable,
					new ExpirableHolder(milliseconds, expirable));
		}
	}

	public boolean isRegistered(Expirable expirable) {
		return (monitors.get(expirable) != null);
	}

	public void removeExpirable(Expirable expirable) {
		if (logger.isDebugEnabled()) {
			logger.debug("Removing expirable: " + expirable);
		}
		monitors.remove(expirable);
	}

	public void resetExpirable(Expirable expirable) {
		ExpirableHolder eh = (ExpirableHolder) monitors.get(expirable);
		if (eh != null) {
			eh.reset();
			if (logger.isDebugEnabled()) {
				logger.debug("Reset expirable: " + expirable);
			}
		}
	}

	/**
	 * The action to be performed by this timer task.
	 */
	public void run() {
		ExpirableHolder holder;
		for (Iterator iterator = monitors.values().iterator(); iterator
				.hasNext();) {
			holder = (ExpirableHolder) iterator.next();
			if (holder.isExpired()) {
				removeExpirable(holder.getExpirable());
				holder.getExpirable().expired();
			}
		}
	}

	public void dispose() {
		logger.info("disposing monitor");
		timer.cancel();
		ExpirableHolder holder;
		for (Iterator iterator = monitors.values().iterator(); iterator
				.hasNext();) {
			holder = (ExpirableHolder) iterator.next();
			removeExpirable(holder.getExpirable());
			try {
				holder.getExpirable().expired();
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
	}

	private class ExpirableHolder {

		private long milliseconds;
		private Expirable expirable;
		private long created;

		public ExpirableHolder(long milliseconds, Expirable expirable) {
			this.milliseconds = milliseconds;
			this.expirable = expirable;
			created = System.currentTimeMillis();
		}

		public long getMilliseconds() {
			return milliseconds;
		}

		public Expirable getExpirable() {
			return expirable;
		}

		public boolean isExpired() {
			return (System.currentTimeMillis() - milliseconds) > created;
		}

		public void reset() {
			created = System.currentTimeMillis();
		}
	}
}
