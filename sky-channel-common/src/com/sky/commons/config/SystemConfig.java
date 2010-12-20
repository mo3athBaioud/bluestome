package com.sky.commons.config;

public class SystemConfig {

	public static String default_province="000000";
	public static String default_mcc="000";
	
	public static String cache_key_channel_file_prefix="COMMONSCHFILEINFO#";
	
	public static String path_channel_file="d:/";
	
	public static boolean cache_valid=true;
	
	public static String CACHE_SPLIT = ":";
	
	public static String cache_key_ip_province="COMMONS_IP_PROVINCE";
	
	//++++++++++++cache object live time+++++��++++++
	public static int cache_object_live_time_channel_file=1800;
	
	public static int cache_object_live_time_ip_province=86400*15;
	
	public void setCache_key_ip_province(String cacheKeyIpProvince) {
		cache_key_ip_province = cacheKeyIpProvince;
	}
	
	public void setCache_object_live_time_channel_file(
			int cacheObjectLiveTimeChannelFile) {
		cache_object_live_time_channel_file = cacheObjectLiveTimeChannelFile;
	}
	
	public void setCache_object_live_time_ip_province(
			int cacheObjectLiveTimeIpProvince) {
		cache_object_live_time_ip_province = cacheObjectLiveTimeIpProvince;
	}
	
	public void setPath_channel_file(String pathChannelFile) {
		if(pathChannelFile!=null){
			if(pathChannelFile.lastIndexOf("/")<0
					&&pathChannelFile.lastIndexOf("\\")<0){
				pathChannelFile = pathChannelFile+"/";
			}
		}else{
			System.out.println("pathChannelFile =["+pathChannelFile+"] is not valid!");
		}
		path_channel_file = pathChannelFile;
	}

	public void setCache_valid(boolean cacheValid) {
		cache_valid = cacheValid;
	}

	public void setCache_key_channel_file_prefix(
			String cacheKeyChannelFilePrefix) {
		cache_key_channel_file_prefix = cacheKeyChannelFilePrefix;
	}

	public void setDefault_province(String defaultProvince) {
		default_province = defaultProvince;
	}

	public void setDefault_mcc(String defaultMcc) {
		default_mcc = defaultMcc;
	}
	
	
}
