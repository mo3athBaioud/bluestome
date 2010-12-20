package com.sky.commons.channel.ws;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.channel.sync.ISyncChannelFile;
import com.sky.channel.sync.bean.SyncFileBean;
//import com.sky.commons.cache.ICache;
import com.sky.spirit.basic.cache.Cache;
import com.sky.spirit.basic.exception.CacheException;
import com.sky.commons.channel.dao.IChannelDAO;
import com.sky.commons.channel.domain.ChannelFileInfo;
import com.sky.commons.config.SystemConfig;

@WebService(endpointInterface = "com.sky.channel.sync.ISyncChannelFile")
public class ChannelFileSyncImpl implements ISyncChannelFile{
	private IChannelDAO channelDao = null;
	private Cache cache = null;
	private static Logger logger = LoggerFactory.getLogger(ChannelFileSyncImpl.class);
	
	public ChannelFileSyncImpl() {
		super();
	}

	public void setChannelDao(IChannelDAO channelDao) {
		this.channelDao = channelDao;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public int synFile(List<SyncFileBean> list) {
		if (list == null) {
			logger.error("received channel file info is null....");
			return PARAM_INVALID;
		}
		logger.info("received channel file info=[{}]{}",list.size(),list.toString());
		for(SyncFileBean bean:list){
			if(!validate(bean.getProvinceCode(), bean.getFileContent())){
				return PARAM_INVALID;
			}
		}
		for(SyncFileBean bean:list){
			if(!saveChannelFile2DB(bean)){
				return DATA_ACCESS_FAIL;
			}
			if(bean.getStartPrice()==0){
				ChannelFileInfo cfi = assembleToChannelFileInfo(bean);
				String key = SystemConfig.cache_key_channel_file_prefix+bean.getProvinceCode();
				if(SystemConfig.cache_valid){
					try{
						if(cache.get(key) != null){
							cache.remove(key);
							logger.debug(">> remove content["+key+"] from cache!");
						}
						cache.put(SystemConfig.cache_key_channel_file_prefix+bean.getProvinceCode(), cfi);
						logger.info("+++set {} file[{}] into cache success ",SystemConfig.cache_key_channel_file_prefix+bean.getProvinceCode(),bean.getFileContent().length);
					}catch(CacheException e){
						logger.info("+++set {} file[{}] into cache failure ",SystemConfig.cache_key_channel_file_prefix+bean.getProvinceCode(),bean.getFileContent().length);
					}
				}
			}
		}
		return SUCCESS;
	}

	private ChannelFileInfo assembleToChannelFileInfo(SyncFileBean syncFileBean){
		ChannelFileInfo channelFileInfo = new ChannelFileInfo();
		channelFileInfo.setProvinceCode(syncFileBean.getProvinceCode());
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
	
	private boolean validate(String provinceCode, byte[] fileContent) {
		if (StringUtils.isBlank(provinceCode)) {
			if (logger.isWarnEnabled()) {
				logger.warn("provinceCode is null or empty.");
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
	
	
	private boolean saveChannelFile2Disk(String filename,byte[] fileContent){
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
	
	private boolean saveChannelFile2DB(SyncFileBean syncFileBean){
		try {
			ChannelFileInfo channelFileInfo = new ChannelFileInfo();
			channelFileInfo.setProvinceCode(syncFileBean.getProvinceCode());
			channelFileInfo.setFileName(syncFileBean.getFileName());
			channelFileInfo.setStartPrice(syncFileBean.getStartPrice());
			channelFileInfo.setEndPrice(syncFileBean.getEndPrice());
			channelFileInfo.setStartTime(DateUtils.parseDate(syncFileBean.getStartTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			channelFileInfo.setEndTime(DateUtils.parseDate(syncFileBean.getEndTime(),new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			channelFileInfo.setFileBytes(syncFileBean.getFileContent());
			this.channelDao.saveChannelFileInfo(channelFileInfo);
			logger.info("saveProvinceFileInfo={} ok. ",channelFileInfo);
		} catch (Exception e) {
			logger.error("saveProvinceFileInfo={} into db erro.{}",syncFileBean,e);
			return false;
		}
		return true;
	}
	
	public static void main(String args[]){
		try {
			System.out.println(DateUtils.parseDate("2009-10-10 10:10:10",new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			System.out.println(DateUtils.parseDate("20091010",new String[]{"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyyMMdd"}));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ChannelFileSyncImpl cfsi = new ChannelFileSyncImpl();
//		List<SyncFileBean> list  = new ArrayList<SyncFileBean>();
//		SyncFileBean sfb = new SyncFileBean();
//		list.add(sfb);
//		System.out.println(cfsi.synFile(list));
	}
	
}
