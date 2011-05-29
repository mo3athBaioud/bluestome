package com.bluestome.activemq.message;

public class MessageHeader {
	/**
	 * 消息ID
	 */
	private long id;

	/**
	 * 消息类型
	 */
	private int type;

	/**
	 * 消息发送方，发送消息的模块
	 */
	private int sender;

	/**
	 * 消息接收方，接收消息的模块
	 */
	private int receiver;

	/**
	 * 消息发送者，具体的用户
	 */
	private String sendPerson;

	public MessageHeader() {
		this.id = System.currentTimeMillis();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSendPerson() {
		return sendPerson;
	}

	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
