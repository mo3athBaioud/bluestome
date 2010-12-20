/**
 * �ļ�com.sky.spirit.basic.cache.CacheFactory.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
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
 * cache�����࣬������ͬ��Cache Provider
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-4 ����09:43:44
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
