package com.bluestome.activemq.message;

import java.io.Serializable;

public class ApmgtMessageData<T extends Serializable> {
	protected T messageContent;

	protected MessageHeader messageHeader;

	public T getMessageContent() {
		return this.messageContent;
	}

	public MessageHeader getMessageHeader() {
		return this.messageHeader;
	}

	public void setMessageContent(T messageContent) {
		this.messageContent = messageContent;
	}

	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}
}
