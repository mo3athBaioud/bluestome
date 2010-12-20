package com.sky.commons.mail;

/*
 * 定义了消息发送接口，短消息方式或者邮件提醒方式
 */
public interface MessageSender {
	/*
	 * 消息发送接口
	 */
	public void sendMessage() throws Exception;
	
	/*
	 * 发送指定的消息
	 */
	public void sendMessage(String message) throws Exception;
	
	/*
	 * 发送指定的消息
	 */
	public void sendMessage(String head,String content) throws Exception;
	
	/**
	 * 发送警报，不抛出任何异常
	 */
	public void sendAlarm(String head,String content);
}
