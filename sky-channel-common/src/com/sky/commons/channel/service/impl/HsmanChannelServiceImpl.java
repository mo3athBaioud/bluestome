package com.sky.commons.channel.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
//import com.sky.commons.cache.ICache;
import com.sky.commons.channel.dao.IHsmanChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.channel.domain.HsmanChannelFileInfo;
import com.sky.commons.channel.service.IHsmanChannelService;
import com.sky.commons.config.SystemConfig;

public class HsmanChannelServiceImpl implements IHsmanChannelService {

	private static final Logger log = LoggerFactory.getLogger(HsmanChannelServiceImpl.class);
	private IHsmanChannelDAO hsmanChannelDao;
	private Cache cache = null;
	
	/**
	 * 根据厂商获取通道
	 */
	public HsmanChannelFileInfo getHsmanChannelFileByHsman(String mcc,String hsman) {
		HsmanChannelFileInfo hsmanChannelFileInfo = null;
		if(StringUtils.isBlank(mcc)){
			mcc = SystemConfig.default_mcc;
		}
		if (StringUtils.isBlank(hsman)) {
			log.debug(">> NO_PARAM_OF_HSMAN");
			return hsmanChannelFileInfo;
		}
		if (SystemConfig.cache_valid) {
			log.debug(">> GET HSMAN CHANNEL FROM CACHE");
			try{
				hsmanChannelFileInfo = getHsmanChannelFileByHsmanFromCache(mcc,hsman);
			}catch(CacheException e){
				log.error(">> GET HSMAN CHANNEL FROM CACHE FAILURE!"+e);
			}
		} else {
			log.debug(">> GET HSMAN CHANNEL FROM DB");
			hsmanChannelFileInfo = hsmanChannelDao.getHsmanChannelFileInfo(mcc,hsman);
			if(null != hsmanChannelFileInfo){
				System.out.println(">> SET HSMAN CHANNEL TO CACHE");
				String fileCacheKey = SystemConfig.cache_key_channel_file_prefix + mcc + SystemConfig.CACHE_SPLIT + hsman;
				try{
//					cache.set(fileCacheKey, hsmanChannelFileInfo,
//					SystemConfig.cache_object_live_time_channel_file);
					cache.put(fileCacheKey, hsmanChannelFileInfo);
					log.debug("+++set [{}]hsmanchannel file bytes[{}] into cache...",mcc+ SystemConfig.CACHE_SPLIT+hsman, hsmanChannelFileInfo);
				}catch(CacheException e){
					log.error("+++set [{}]hsmanchannel file bytes[{}] into cache failure ",mcc+ SystemConfig.CACHE_SPLIT+hsman, hsmanChannelFileInfo);
				}
			}
				
		}
		return hsmanChannelFileInfo;
	}
	
	/**
	 * 从缓存中获取带有厂商的通道数据
	 * @param hsman
	 * @return
	 * @throws CacheException 
	 */
	HsmanChannelFileInfo getHsmanChannelFileByHsmanFromCache(String mcc,String hsman) throws CacheException {
		HsmanChannelFileInfo hsmanChannelFileInfo = null;
		String fileCacheKey = SystemConfig.cache_key_channel_file_prefix+ mcc + SystemConfig.CACHE_SPLIT+ hsman;
		hsmanChannelFileInfo = (HsmanChannelFileInfo)cache.get(fileCacheKey);
		if(null == hsmanChannelFileInfo){
			hsmanChannelFileInfo = hsmanChannelDao.getHsmanChannelFileInfo(mcc,hsman);
			if(null != hsmanChannelFileInfo){
				try{
//				cache.set(fileCacheKey, hsmanChannelFileInfo,
//						SystemConfig.cache_object_live_time_channel_file);
				cache.put(fileCacheKey, hsmanChannelFileInfo);
				log.debug("+++set [{}] [{}]hsmanchannel file bytes[{}] into cache...",
						mcc+ SystemConfig.CACHE_SPLIT+hsman, hsmanChannelFileInfo);
				}catch(CacheException e){
					log.error("+++set [{}] [{}]hsmanchannel file bytes[{}] into cache...",
							mcc+ SystemConfig.CACHE_SPLIT+hsman, hsmanChannelFileInfo);
				}
				log.debug(" >> get hsman channel from db success!");
			}else{
				log.debug(" >> get hsman channel from db failure!");
			}
		}else{
			log.debug(" >> get hsman channel from cache success!");
		}
		return hsmanChannelFileInfo;
	}	

	public IHsmanChannelDAO getHsmanChannelDao() {
		return hsmanChannelDao;
	}

	public void setHsmanChannelDao(IHsmanChannelDAO hsmanChannelDao) {
		this.hsmanChannelDao = hsmanChannelDao;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	

}
