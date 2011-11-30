package com.ssi.common.utils;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author huangbo
 * @version 1.0
 */


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//监听登录的整个过程

public class SessionListener implements HttpSessionListener {

    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    public SessionListener() {
    	logger.info("SessionLinster 初始化...");
    }

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        
        // 在application范围由一个HashSet集保存session的数量
        Integer sessionsCount = (Integer) application.getAttribute("sessionsCount");
        if (sessionsCount == null) {
        	sessionsCount = 0;
        }
        sessionsCount ++ ;
        application.setAttribute("sessionsCount", sessionsCount);
        logger.info(" current session is:",sessionsCount);
        // 可以在别处从application范围中取出sessionsCount

    }

    public void sessionDestroyed(HttpSessionEvent event) {

        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        Integer sessionsCount = (Integer) application.getAttribute("sessionsCount");
        if(null != sessionsCount  && sessionsCount > 0){
	        // 销毁的session均从HashSet集中移除
	        sessionsCount --;
	        logger.info(" > sessionCount:{}",sessionsCount);
        }
    }

}

