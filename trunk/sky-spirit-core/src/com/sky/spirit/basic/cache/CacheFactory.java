/**
 * 文件com.sky.spirit.basic.cache.CacheFactory.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.common.Constants;
import com.sky.spirit.common.util.PropertiesUtils;
import com.sky.spirit.common.util.spring.SpringBeanUtils;

/**
 * 
 * cache工厂类，创建不同的Cache Provider
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-4 下午09:43:44
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public class CacheFactory {
	private static Log log = LogFactory.getLog(CacheFactory.class);
	public static String springBeanName=Constants.SKY_DEFAULT_CACHE_PROVIDER;
//	static {
//		if (springBeanName == null) {
//			Properties skyConfigProp = null;
//			try {
//				skyConfigProp = PropertiesUtils.loadProperties(
//						"sky-config.properties", CacheFactory.class);
//			} catch (IOException e) {
//				log.error(e);
//			}
//			if (skyConfigProp != null) {
//				springBeanName = skyConfigProp
//						.getProperty(Constants.SKY_CONFIG_PROPERTY_FIRST_LEVEL_CACHE_PROVIDER);
//			} else {
//				springBeanName = Constants.SKY_DEFAULT_CACHE_PROVIDER;
//			}
//		}
//	}

	public static Cache createCache() {
		return (Cache) SpringBeanUtils.getBean(springBeanName);
	}

	public static Cache createCache(String springBeanName) {
		return (Cache) SpringBeanUtils.getBean(springBeanName);
	}
}
