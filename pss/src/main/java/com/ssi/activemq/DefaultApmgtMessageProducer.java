package com.ssi.activemq;

import org.springframework.jms.core.JmsTemplate;

public class DefaultApmgtMessageProducer implements IApmgtMessageProducer {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessage(ApmgtMessageData messageData) {
		this.jmsTemplate.convertAndSend(messageData);
	}
}
