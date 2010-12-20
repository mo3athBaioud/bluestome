/**
 * 文件com.sky.spirit.common.Constants.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common;



/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午11:25:42
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class Constants {

	/**
	 * Http Encoding
	 */
	public static final String SKY_SPIRIT_HTTP_ENCODEING = "UTF-8";
	/**
	 * 传输内容类型XML
	 */
	public static final String SKY_SPIRIT_HTTP_CONTENT_TYPE_XML = "text/xml";

	/**
	 * 传输内容类型二进制
	 */
	public static final String SKY_SPIRIT_HTTP_CONTENT_TYPE_OC_STREAM = "application/octet-stream";
	public static final String SKY_CACHE_DEFAULT_DOMAIN = "localhost";
	public static final String SKY_CACHE_DOMAIN_PROP_KEY="cacheDomain";
	public static final String SKY_CONFIG_PROPERTY_SLICE_LEN = "sliceLen";
	
	//现在用到的协议
	public static final String SKY_SPIRIT_HTTP_CONTENT_TYPE_APP_MRP = "app/mr";
	public static final String SKY_SPIRIT_HTTP_ENCODEING_GZIP = "gzip";

	public static final int SKY_SPIRIT_REQUEST_MIN_CONTENT_LENGTH = 36;
	//没用用到过 事实上用的是 10K
	public static final int SKY_SPIRIT_HTTP_RESPONSE_BUFFER_SIZE = 10240;
	public static final String SKY_REQUEST_MESSAGE = "RequestAttribute_SkyRequestMessage";
	public static final String SKY_BUSI_MESSAGE = "RequestAttribute_SkyBusiMessage";
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String SKY_REQUEST_ATTRIBUTE_DOWNLOAD_RESULT = "skyDownloadResult";
	public static final String SKY_REQUEST_ATTRIBUTE_DOWNLOAD_NAME = "downloadName";
	public static final String SKY_REQUEST_ATTRIBUTE_DOWNLOAD_TYPE="downloadType";
	public static final String SKY_CONFIG_PROPERTY_APP_CHANNEL_SWITCH = "app_channel_switch";
	public static final String SKY_CONFIG_PROPERTY_ADMIN_EMAIL = "admin_email";
	public static final String SYSTEM_MAILBOX = "spirit <jiaying_quan@email.sky-mobi.com>";
	public static final String NO_NEED_DOWN_PARSER = "NO NEED TO DOWNLOAD PARSER";

	public static final String SKY_JMS_MAP_KEY_BASE_ENTITY = "BaseEntity";
	public static final String SKY_JMS_MAP_KEY_EMAIL = "Email";
	public static final String SKY_MAIL_TEMPLATE_ERROR_NOTICE = "ErrorNoticeEmail.vm";
	
	public static final String SKY_FILE_MONITOR_NAME = "filemonitor.properties";
	public static final String SKY_CACHE_KEY_SEPARATOR = ":";
	public static final String SKY_CONFIG_PROPERTY_FIRST_LEVEL_CACHE_PROVIDER = "firstLevel.cacheProvider";
	public static final String SKY_CONFIG_PROPERTY_SECOND_LEVEL_CACHE_PROVIDER = "secondLevel.cacheProvider";
//	public static final String SKY_DEFAULT_CACHE_PROVIDER = "com.sky.spirit.basic.cache.provider.ehcache.Ehcache";
	public static final String SKY_DEFAULT_CACHE_PROVIDER = "com.sky.spirit.basic.cache.provider.memcached.MemCached";
}
