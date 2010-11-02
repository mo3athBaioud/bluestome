package com.ssi.main;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String args[]){
		
		//加载SPRING配置
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		//加载日志配置文件
//		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("conf/log4j.properties")); 
	}
}
