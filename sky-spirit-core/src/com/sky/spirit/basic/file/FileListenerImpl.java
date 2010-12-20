/**
 * �ļ�com.sky.spirit.basic.file.FileListenerImpl.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
import com.sky.spirit.common.util.FileUtils;
import com.sky.spirit.common.util.PropertiesUtils;
import com.sky.spirit.common.util.StringUtils;
import com.sky.spirit.common.util.monitor.FileListener;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����01:30:32
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class FileListenerImpl implements FileListener {
	private static Log log = LogFactory.getLog(FileListenerImpl.class);
	private List<MonitorFileInfo> mfiList;
	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public List<MonitorFileInfo> getMfiList() {
		return mfiList;
	}

	public void setMfiList(List<MonitorFileInfo> mfiList) {
		this.mfiList = mfiList;
	}

	public void fileChanged(File file) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("fileChange...." + file.getPath());
		}
		for (MonitorFileInfo mfi : mfiList) {
			File mfiFile = new File(mfi.getFilePath());
			if (mfiFile.getPath().equals(file.getPath())) {
				if (file.isFile()) {
					if (StringUtils.isNotBlank(mfi.getCacheKey())) {
						String cacheKey = "";
						if (StringUtils
								.indexOf(mfi.getCacheKey(), "byFilePath") != -1) {
							cacheKey = file.getPath();
						}
						Object content = null;
						if ("String".equals(mfi.getCacheType())) {
							content = FileUtils.readFileToString(file);
						} else if ("byteArray".equals(mfi.getCacheType())) {
							content = FileUtils.readFileToByteArray(file);
						} else if ("Properties".equals(mfi.getCacheType())) {
							content = PropertiesUtils.loadProperties(file
									.getPath(), FileListenerImpl.class);
						}
						
					}
				} else if (file.isDirectory()) {
					if (log.isDebugEnabled()) {
						log.debug("file dir change update file Cache!");
					}
					String[] fileList = file.list();
					for (String filePath : fileList) {
						File lFile = new File(file.getPath() + "\\" + filePath);
						if (lFile.isFile()
								&& StringUtils.indexOf(filePath, ".mrp") != -1) {
							if (StringUtils.isNotBlank(mfi.getCacheKey())) {
								String cacheKey = "";
								if (StringUtils.indexOf(mfi.getCacheKey(),
										"byFilePath") != -1) {
									cacheKey = lFile.getPath();
								}
								Object content = null;
								if ("String".equals(mfi.getCacheType())) {
									content = FileUtils.readFileToString(lFile);
								} else if ("byteArray".equals(mfi
										.getCacheType())) {
									content = FileUtils
											.readFileToByteArray(lFile);
									String cacheBlockKey = StringUtils.replace(
											mfi.getCacheBlockKey(), "Ori",
											"Cookied");
								
//									if (cache != null) {
//										try {
//											cache
//													.remove(CacheUtils
//															.getOriFileContentCacheKey(
//																	Constants.SKY_DEFAULT_DOMAIN,
//																	cacheKey));
//										} catch (CacheException e) {
//											e.printStackTrace();
//										}
//									}
								} else if ("Properties".equals(mfi
										.getCacheType())) {
									content = PropertiesUtils.loadProperties(
											lFile.getPath(),
											FileListenerImpl.class);
								}
								
							}
						}
					}
				}
			}
		}
	}
}
