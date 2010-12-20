package com.sky.commons.log4j;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.FileWatchdog;

public class DynamicLoadLog4j {

//	private static final Logger logger = LoggerFactory.getLogger("test");
	private String log4jPath=DynamicLoadLog4j.class.getClassLoader().getResource("").getFile()+"log4j.properties";
	private long delay=FileWatchdog.DEFAULT_DELAY;
	public DynamicLoadLog4j(){
		PropertyConfigurator.configureAndWatch(log4jPath,delay);
	}
//	public static void main(String args[]){
//		try {
//			DynamicLoadLog4j d = new DynamicLoadLog4j();
//			d.init();
//			while(true){
//				logger.info("test");
//				Thread.sleep(1000);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//µ•Œª√Î
	public void setDelayInSeconds(long delay) {
		this.delay = delay*1000;
	}
}
