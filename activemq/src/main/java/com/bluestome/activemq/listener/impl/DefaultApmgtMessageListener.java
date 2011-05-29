package com.bluestome.activemq.listener.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluestome.activemq.listener.IApmgtMessageListener;
import com.bluestome.activemq.message.ApmgtMessageData;

public class DefaultApmgtMessageListener implements IApmgtMessageListener {

	static Log log = LogFactory.getLog(DefaultApmgtMessageListener.class);
	public void onMessage(ApmgtMessageData message) {
		// TODO Auto-generated method stub
		log.debug("监听到消息："+message);
	}

}
