package com.ssi.activemq;

public interface IApmgtMessageProducer {
	public abstract void sendMessage(ApmgtMessageData messageData);
}
