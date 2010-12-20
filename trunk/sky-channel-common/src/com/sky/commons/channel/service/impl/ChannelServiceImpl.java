package com.sky.commons.channel.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
import com.sky.commons.channel.dao.IChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.channel.service.IChannelService;
import com.sky.commons.config.SystemConfig;
import com.sky.commons.ip.bean.IpProvinceRelation;
import com.sky.commons.ip.service.IipService;

/**
 * @description:ͨ�����ҵ��ʵ��
 * @author:<E-mail="zjf@email.sky-mobi.com"/>
 * @create Date:May 4, 2009
 * @modify:
 */
public class ChannelServiceImpl implements IChannelService {

	private static final Logger logger = LoggerFactory
			.getLogger(ChannelServiceImpl.class);
	private Cache cache = null;
	private IChannelDAO channelDao = null;
	private IipService ipService = null;

	public void setIpService(IipService ipService) {
		this.ipService = ipService;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public void setChannelDao(IChannelDAO channelDao) {
		this.channelDao = channelDao;
	}

	public ChannelFileInfo getChannelFileByProvinceCode(String provinceCode) {
		if (StringUtils.isBlank(provinceCode)) {
			provinceCode = SystemConfig.default_province;
		}
		ChannelFileInfo channelFileInfo = null;
		if (SystemConfig.cache_valid) {
			try{
				channelFileInfo = this.getChannelFileWithCache(provinceCode);
			}catch(CacheException e){
				logger.error(">> get  ["+provinceCode+"]  content from cache exception happen");
			}
		} else {
			channelFileInfo = this.getChannelFile(provinceCode);
		}
		return channelFileInfo;
	}

	/**
	 * ������ip��ַ4��ȡ��Ӧ�ļƷ�ͨ���ļ�
	 */
	public ChannelFileInfo getChannelFileByIp(String ip) {
		ChannelFileInfo channelFileInfo = null;
		if (ip == null) {
			ip = "0.0.0.0";
		}
		// Ĭ��ȫ��
		String provinceCode = SystemConfig.default_province;
		IpProvinceRelation provinceRelation = ipService
				.getProvinceRelationByIp(ip);
		if (provinceRelation != null) {
			provinceCode = provinceRelation.getProvince();
		}
		logger.info("IP[{}],provinceCode={}", ip, provinceCode);
		// get file by provinceCode
		if (SystemConfig.cache_valid) {
			try{
				channelFileInfo = this.getChannelFileWithCache(provinceCode);
			}catch(CacheException e){
				logger.error(">> get  ["+provinceCode+"]  content from cache exception happen");
			}
		} else {
			channelFileInfo = this.getChannelFile(provinceCode);
		}
		return channelFileInfo;
	}

	// get file from db,if not exists get default file
	private ChannelFileInfo getChannelFile(String provinceCode) {
		ChannelFileInfo channelFileInfo = null;
		// byte[] channelFileBytes = null;
		// if (channelFileBytes == null) {
		try {
			channelFileInfo = this.channelDao.getChannelFileInfo(provinceCode);
			if (channelFileInfo != null) {
				logger.info("+++getChannelFile[{}]", channelFileInfo);
			} else if (!SystemConfig.default_province.equals(provinceCode)) {
				channelFileInfo = this
						.getChannelFile(SystemConfig.default_province);
				logger.info("+++getDefaultChannelFile[{}]", channelFileInfo);
			}
		} catch (Exception e) {
			logger.error("getChannelFile[{}] error.{}", provinceCode, e);
			channelFileInfo = new ChannelFileInfo();
		}
		return channelFileInfo;
	}

	// add cache fun
	// get file from cache
	// get file from db,if not exists get default file
	private ChannelFileInfo getChannelFileWithCache(String provinceCode) throws CacheException {
		ChannelFileInfo channelFileInfo = null;
		// get file from cache
		String fileCacheKey = SystemConfig.cache_key_channel_file_prefix
				+ provinceCode;
		channelFileInfo = (ChannelFileInfo) cache.get(fileCacheKey);
		if (channelFileInfo == null) {
			channelFileInfo = this.getChannelFile(provinceCode);
			if (channelFileInfo != null
					&& channelFileInfo.getFileBytes().length > 0) {
				cache.put(fileCacheKey, channelFileInfo);
//				cache.set(fileCacheKey, channelFileInfo,
//						SystemConfig.cache_object_live_time_channel_file);
				logger.debug("+++set [{}]channel file bytes[{}] into cache...",
						provinceCode, channelFileInfo);
			}
		} else {
			logger.debug("+++get channel file from cache");
		}
		return channelFileInfo;
	}

	public ChannelFileInfo getChannelFileByMcc(String provinceCode) {
		if (StringUtils.isBlank(provinceCode)) {
			provinceCode = SystemConfig.default_mcc;
		}
		ChannelFileInfo channelFileInfo = null;
		if (SystemConfig.cache_valid) {
			try{
				channelFileInfo = this.getChannelFileWithCache(provinceCode);
				if(null != channelFileInfo){
					logger.info(" >> get channel content["+channelFileInfo.getFileName()+"] from cache success!");
				}else{
					logger.info(" >> get channel content from cache failure!");
				}
			}catch(CacheException e){
				logger.error(">> get  ["+provinceCode+"]  content from cache exception happen");
			}
		} else {
			channelFileInfo = this.getChannelFile(provinceCode);
			if(null != channelFileInfo){
				logger.info(" >> get channel content["+channelFileInfo.getFileName()+"] from db success!");
			}else{
				logger.info(" >> get channel content from db failure!");
			}
		}
		return channelFileInfo;
	}
	

	public ChannelFileInfo getChannelFileByImsi(String imsi) {
		String mcc = SystemConfig.default_mcc;
		if(imsi!=null&&imsi.length()>2){
			mcc = imsi.substring(0,3);
		}
		return this.getChannelFileByMcc(mcc);
	}

}
