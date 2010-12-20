package com.sky.commons.monitor.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import com.sky.commons.monitor.file.listener.FileListenerInterface;
import com.sky.commons.monitor.file.listener.impl.DefaultFileReloadingListener;

/**
 * 文件监听类
 * 
 * @author ibm
 * 
 */
public class FileMonitor {
	private Timer timer;
	private long interval=1000;
	private HashMap<File, Long> files;
	private Collection<FileListenerInterface> listeners;
	private static FileMonitor fileMonitor = null;
	
	
	private FileMonitor(long interval) {
		this.interval = interval;
		files = new HashMap<File, Long>();
		listeners = new ArrayList<FileListenerInterface>();
		timer = new Timer(true);
		timer.schedule(new FileMonitorNotifier(), 0, this.interval);
	}
	private FileMonitor() {
		files = new HashMap<File, Long>();
		listeners = new ArrayList<FileListenerInterface>();
		listeners.add(new DefaultFileReloadingListener());
		timer = new Timer(true);
		timer.schedule(new FileMonitorNotifier(), 0, this.interval);
	}

	public static FileMonitor getDefaultInstance(){
		if(fileMonitor==null){
			fileMonitor=new FileMonitor();
		}
		return fileMonitor;
	}
	
	public static FileMonitor getInstance(long interval){
		if(fileMonitor==null){
			fileMonitor=new FileMonitor(interval);
		}
		return fileMonitor;
	}
	
	
	
	/**
	 * 增加监听器
	 * 
	 * @param fileListener
	 */
	public void addListener(FileListenerInterface fileListener) {
		for (Iterator<FileListenerInterface> i = listeners.iterator(); i
				.hasNext();) {
			FileListenerInterface listener = i.next();
			if (listener == fileListener)
				return;
		}
		listeners.add(fileListener);
	}

	/**
	 * 移除监听器
	 * 
	 * @param fileListener
	 */
	public void removeListener(FileListenerInterface fileListener) {
		for (Iterator<FileListenerInterface> i = listeners.iterator(); i
				.hasNext();) {
			FileListenerInterface listener = i.next();
			if (listener == fileListener) {
				i.remove();
				break;
			}
		}
	}

	/**
	 * 停止文件监控
	 */
	public void stop() {
		timer.cancel();
	}

	/**
	 * 加载监听文件
	 * 
	 * @param file
	 */
	public void addFile(File file) {
		if (!files.containsKey(file)) {
			long modifiedTime = file.exists() ? file.lastModified() : -1;
			files.put(file, new Long(modifiedTime));
		}
	}

	/**
	 * 移除监听文件
	 * 
	 * @param file
	 */
	public void removeFile(File file) {
		files.remove(file);
	}

	/**
	 * 监听任务类
	 * 
	 * @author ibm
	 * 
	 */
	private class FileMonitorNotifier extends TimerTask {
		public void run() {
			Collection<File> temfiles = new ArrayList<File>(files.keySet());

			for (Iterator<File> i = temfiles.iterator(); i.hasNext();) {
				File file = i.next();
				long lastModifiedTime = ((Long) files.get(file)).longValue();
				long newModifiedTime = file.exists() ? file.lastModified() : -1;

				if (newModifiedTime != lastModifiedTime) {
					files.put(file, new Long(newModifiedTime));

					for (Iterator<FileListenerInterface> j = listeners
							.iterator(); j.hasNext();) {

						FileListenerInterface listener = j.next();

						if (listener == null) {
							j.remove();
						} else {
							listener.fileChanged(file);
						}
					}
				}
			}
		}
	}
}
