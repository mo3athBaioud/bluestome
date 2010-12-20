/**
 * 文件com.sky.spirit.basic.file.FileMonitorService.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.common.Constants;
import com.sky.spirit.common.util.PropertiesUtils;
import com.sky.spirit.common.util.StringUtils;
import com.sky.spirit.common.util.monitor.FileMonitor;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午01:33:48
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class FileMonitorService {
	private static Log log = LogFactory.getLog(FileMonitorService.class);

	private List<MonitorFileInfo> monitorFileInfoList;
	private Properties monitorProps;

	public FileMonitorService() {
		if (log.isInfoEnabled()) {
			log.info("init file monitor service...");
		}
		try {
			monitorProps = PropertiesUtils.loadProperties(
					Constants.SKY_FILE_MONITOR_NAME, FileMonitorService.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<Object> keys = monitorProps.keySet();
		monitorFileInfoList = new ArrayList<MonitorFileInfo>();
		for (Iterator<Object> iter = keys.iterator(); iter.hasNext();) {
			Object key = iter.next();
			Object value = monitorProps.get(key);
			if (key != null) {
				MonitorFileInfo monitorFileInfo = new MonitorFileInfo();
				String[] keySplit = StringUtils.split((String) key, "_");
				if (keySplit.length > 1) {
					monitorFileInfo.setCacheBlockKey(keySplit[1]);
				}
				if (keySplit.length > 2) {
					monitorFileInfo.setCacheKey(keySplit[2]);
				}
				if (keySplit.length > 3) {
					monitorFileInfo.setCacheType(keySplit[3]);
				}
				monitorFileInfo.setFilePath((String) value);
				monitorFileInfoList.add(monitorFileInfo);
			}
		}
	}

	public void registerMonitor() {
		FileMonitor fm = new FileMonitor(10000);
		for (MonitorFileInfo monitorFileInfo : monitorFileInfoList) {
			if (log.isInfoEnabled()) {
				log.info("register monitor file path:"
						+ monitorFileInfo.getFilePath());
			}
			File file = new File(monitorFileInfo.getFilePath());
			fm.addFile(file);
		}
		if (log.isInfoEnabled()) {
			log.info("register monitor file finished");
		}
		FileListenerImpl fileListener = new FileListenerImpl();
		fileListener.setMfiList(monitorFileInfoList);
		fm.addListener(fileListener);
		fm.start();
		if (log.isInfoEnabled()) {
			log.info("started monitor file Service");
		}
	}
}
