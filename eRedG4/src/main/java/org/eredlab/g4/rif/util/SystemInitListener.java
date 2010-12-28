package org.eredlab.g4.rif.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.arm.service.ResourceService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.properties.PropertiesFactory;
import org.eredlab.g4.ccl.properties.PropertiesFile;
import org.eredlab.g4.ccl.properties.PropertiesHelper;
import org.eredlab.g4.ccl.util.G4Utils;
import org.springframework.context.ApplicationContext;

/**
 * 系统启动监听器
 * 
 * @author XiongChun
 * @since 2010-04-13
 */
public class SystemInitListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(SystemInitListener.class);
	private boolean success = true;
	private ApplicationContext wac = null;

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		systemStartup(sce.getServletContext());
	}

	/**
	 * 应用平台启动
	 */
	private void systemStartup(ServletContext servletContext) {
	    PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
	    String forceLoad = pHelper.getValue("forceLoad", ArmConstants.FORCELOAD_N);
		long start = System.currentTimeMillis();
		if (forceLoad.equalsIgnoreCase(ArmConstants.FORCELOAD_N)) {
			log.info("********************************************");
			log.info("易道系统集成与应用开发平台开始启动...");
			log.info("********************************************");
		}
		try {
			wac = SpringBeanLoader.getApplicationContext();
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		if (success) {
			//清楚托管Session
			MonitorService monitorService = (MonitorService)SpringBeanLoader.getSpringBean("monitorService");
			monitorService.deleteHttpSession(new BaseDto());
		}
		Dto dto = new BaseDto();
		dto.put("enabled", ArmConstants.EDITMODE_Y);
		if (success) {
			log.info("-------------------------------");
			log.info("系统开始启动代码表装载程序...");
			log.info("开始加载代码表...");
			ResourceService resourceService = (ResourceService) SpringBeanLoader.getSpringBean("resourceService");
			List codeList = null;
			try {
				codeList = resourceService.getCodeList(dto).getDefaultAList();
				log.info("代码表加载成功!");
			} catch (Exception e) {
				success = false;
				log.info("代码表加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EACODELIST", codeList);

		}
		if (success) {
			log.info("-------------------------------");
			log.info("系统开始启动全局参数表装载程序...");
			log.info("开始加载全局参数表...");
			List paramList = null;
			try {
				ResourceService resourceService = (ResourceService) SpringBeanLoader.getSpringBean("resourceService");
				paramList = resourceService.getParamList(dto).getDefaultAList();
				log.info("全局参数表加载成功!");
			} catch (Exception e) {
				success = false;
				log.info("全局参数表加载失败!");
				e.printStackTrace();
			}
			servletContext.setAttribute("EAPARAMLIST", paramList);
		}
		long timeSec = (System.currentTimeMillis() - start) / 1000;
		log.info("********************************************");
		if (success) {
			log.info("易道系统集成与应用开发平台启动成功[" + G4Utils.getCurrentTime() + "]");
			log.info( "启动总耗时: " + timeSec/60+ "分 " + timeSec%60 + "秒 ");
		} else {
			log.info("易道系统集成与应用开发平台启动失败[" + G4Utils.getCurrentTime() + "]");
			log.info( "启动总耗时: " + timeSec/60+ "分" + timeSec%60 + "秒");
		}
		log.info("********************************************");
	}
}
