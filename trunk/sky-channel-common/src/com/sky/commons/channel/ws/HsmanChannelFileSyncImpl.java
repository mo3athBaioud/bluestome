package com.sky.commons.channel.ws;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.channel.sync.ISyncHsmanChannelFile;
import com.sky.channel.sync.bean.SyncHsmanFileBean;
//import com.sky.commons.cache.ICache;
import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
import com.sky.commons.channel.dao.IHsmanChannelDAO;
import com.sky.commons.channel.domain.HsmanChannelFileInfo;
import com.sky.commons.config.SystemConfig;

@WebService(endpointInterface = "com.sky.channel.sync.ISyncHsmanChannelFile")
public class HsmanChannelFileSyncImpl implements ISyncHsmanChannelFile{
	private IHsmanChannelDAO hsmanChannelDao = null;
	private Cache cache = null;
	private static Logger logger = LoggerFactory.getLogger(HsmanChannelFileSyncImpl.class);
	
	public HsmanChannelFileSyncImpl() {
		super();
	}
	
	public void setHsmanChannelDao(IHsmanChannelDAO hsmanChannelDao) {
		this.hsmanChannelDao = hsmanChannelDao;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public int synHsmanFile(List<SyncHsmanFileBean> list) {
		if (list == null) {
			logger.error("received channel file info is null....");
			return PARAM_INVALID;
		}
		logger.info("received channel file info=[{}]{}",list.size(),list.toString());
		for(SyncHsmanFileBean bean:list){
			if(!validate(bean.getMcc(),bean.getHsman(), bean.getFileContent())){
				return PARAM_INVALID;
			}
		}
		for(SyncHsmanFileBean bean:list){
			String key = SystemConfig.cache_key_channel_file_prefix+bean.getMcc() + SystemConfig.CACHE_SPLIT + bean.getHsman();
			if(!saveHsmanChannelFile2DB(bean)){
				return DATA_ACCESS_FAIL;
			}
			if(bean.getStartPrice()==0){
				HsmanChannelFileInfo cfi = assembleToChannelFileInfo(bean);
				if(SystemConfig.cache_valid){
					try{
						if(cache.get(key) != null){
							cache.remove(key);
							logger.info("remove content["+key+"] from cache !");
						}
						cache.put(key, cfi);
						logger.info("+++set {} file[{}] into cache.",SystemConfig.cache_key_channel_file_prefix+bean.getMcc() + SystemConfig.CACHE_SPLIT+bean.getHsman(),bean.getFileContent().length);
					}catch(CacheException e){
						logger.error("+++set {} file[{}] into cache failure!",SystemConfig.cache_key_channel_file_prefix+bean.getMcc() + SystemConfig.CACHE_SPLIT+bean.getHsman(),bean.getFileContent().length);
					}
				}
			}
		}
		return SUCCESS;
	}

	private HsmanChannelFileInfo assembleToChannelFileInfo(SyncHsmanFileBean syncFileBean){
		HsmanChannelFileInfo channelFileInfo = new HsmanChannelFileInfo();
		channelFileInfo.setMcc(syncFileBean.getMcc());
		channelFileInfo.setHsman(syncFileBean.getHsman());
		channelFileInfo.setFileName(syncFileBean.getFileName());
		channelFileInfo.setStartPrice(syncFileBean.getStartPrice());
		channelFileInfo.setEndPrice(syncFileBean.getEndPrice());
		channelFileInfo.setFileBytes(syncFileBean.getFileContent());
		try {
			channelFileInfo.setStartTime(DateUtils.parseDate(syncFileBean.getStartTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			channelFileInfo.setEndTime(DateUtils.parseDate(syncFileBean.getEndTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
		} catch (ParseException e) {
			logger.error(""+e);
		}
		return channelFileInfo;
	}
	
	private boolean validate(String mcc,String hsman, byte[] fileContent) {
		
		if (StringUtils.isBlank(mcc)) {
			if (logger.isWarnEnabled()) {
				logger.warn("mcc is null or empty.");
			}
			return false;
		}
		
		if (StringUtils.isBlank(hsman)) {
			if (logger.isWarnEnabled()) {
				logger.warn("hsman is null or empty.");
			}
			return false;
		}
		if (fileContent == null || fileContent.length == 0) {
			if (logger.isWarnEnabled()) {
				logger.warn("fileContent is null or empty.");
			}
			return false;
		}
		logger.debug("Param validation pass.");
		return true;
	}
	
	
	private boolean saveHsmanChannelFile2Disk(String filename,byte[] fileContent){
		try {
			File file = new File(filename);
			if(!file.exists()){
				FileUtils.writeByteArrayToFile(file, fileContent);
				logger.info("save channel file[{}] ok!",filename);
			}else{
				logger.warn("channel file[{}] already exists.",filename);
			}
		} catch (IOException e) {
			logger.error("save channel file[{}] error.{}",filename,e);
			return false;
		}
		return true;
	}

	private boolean deleteChannelFileFromDisk(String filename){
		return FileUtils.deleteQuietly(new File(filename));
	}
	
	private boolean saveHsmanChannelFile2DB(SyncHsmanFileBean syncFileBean){
		try {
			HsmanChannelFileInfo channelFileInfo = new HsmanChannelFileInfo();
			channelFileInfo.setMcc(syncFileBean.getMcc());
			//设置厂商属性 2010-09-09
			channelFileInfo.setHsman(syncFileBean.getHsman());
			channelFileInfo.setFileName(syncFileBean.getFileName());
			channelFileInfo.setStartPrice(syncFileBean.getStartPrice());
			channelFileInfo.setEndPrice(syncFileBean.getEndPrice());
			channelFileInfo.setStartTime(DateUtils.parseDate(syncFileBean.getStartTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			channelFileInfo.setEndTime(DateUtils.parseDate(syncFileBean.getEndTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			channelFileInfo.setFileBytes(syncFileBean.getFileContent());
			
			int result = hsmanChannelDao.saveHsmanChannelFileInfo(channelFileInfo);
			if(result != 200){
				logger.info("saveHsmanChannelFileInfo={} failure. ",syncFileBean);
				return false;
			}else{
				logger.info("saveHsmanChannelFileInfo={} ok. ",channelFileInfo);
				return true;
			}
		} catch (Exception e) {
			logger.error("saveHsmanChannelFileInfo={} into db erro.{}",syncFileBean,e);
			return false;
		}
	}
	
	public static void main(String args[]){
		try {
			System.out.println(DateUtils.parseDate("2009-10-10 10:10:10",new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			System.out.println(DateUtils.parseDate("20091010",new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
