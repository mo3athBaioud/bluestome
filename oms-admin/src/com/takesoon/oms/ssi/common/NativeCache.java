package com.takesoon.oms.ssi.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takesoon.oms.ssi.entity.SystemConfig;
import com.takesoon.oms.ssi.service.SystemConfigManager;

/**
 * 本地数据缓存
 * @author bluestome
 *
 */
public class NativeCache {
	
	static Logger logger = LoggerFactory.getLogger(NativeCache.class);
	
	private SystemConfigManager systemConfigManager;
	
	/**
	 * 图片缩略图是否显示
	 * @return
	 */
	public boolean getImageIconEable(){
		boolean b = false;
		SystemConfig entity = null;
		try{
			entity = new SystemConfig();
			entity.setType("SYS");
			entity.setKey("IMAGE_ICON_ENABLE");
			if(null != systemConfigManager){
				List<SystemConfig> list = systemConfigManager.getList(entity);
				if(null != list && list.size() > 0){
					entity = list.get(0);
					String tmp = entity.getValue();
					b = Boolean.valueOf(tmp.toLowerCase());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("NativeCache.IMAGE_ICON_ENABLE.exception:"+e);
		}
		return b;
	}

	public SystemConfigManager getSystemConfigManager() {
		return systemConfigManager;
	}

	public void setSystemConfigManager(SystemConfigManager systemConfigManager) {
		this.systemConfigManager = systemConfigManager;
	}
	
	
}
