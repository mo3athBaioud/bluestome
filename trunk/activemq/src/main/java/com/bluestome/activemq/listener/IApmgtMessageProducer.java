package com.bluestome.activemq.listener;

import com.bluestome.activemq.message.ApmgtMessageData;

public interface IApmgtMessageProducer {

	public abstract void sendMessage(ApmgtMessageData messageData);
}
