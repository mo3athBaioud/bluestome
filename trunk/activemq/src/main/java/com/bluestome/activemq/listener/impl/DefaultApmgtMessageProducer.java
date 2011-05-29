package com.bluestome.activemq.listener.impl;

import org.springframework.jms.core.JmsTemplate;

import com.bluestome.activemq.listener.IApmgtMessageProducer;
import com.bluestome.activemq.message.ApmgtMessageData;

/**
 * 消息发送类
 * 
 * @author bluestome
 * 
 */
public class DefaultApmgtMessageProducer implements IApmgtMessageProducer {

	private JmsTemplate jmsTemplate;

	public void sendMessage(ApmgtMessageData messageData) {
		this.jmsTemplate.convertAndSend(messageData);
	}

	public final JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public final void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
