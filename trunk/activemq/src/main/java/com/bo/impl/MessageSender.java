package com.bo.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import com.bo.Html;
import com.bo.Table;

public class MessageSender extends JmsGatewaySupport {
	
	/**
	 * 发送文本消息
	 * @param msg
	 */
	public void sendTextMsg(final String msg) {
		this.getJmsTemplate().send(new MessageCreator() {
			// 这里创建了一个 message 对象,然后可以对该对象进行 各种属性的定义
			private Message message;

			public Message createMessage(Session session) throws JMSException {
				message = session.createTextMessage(msg);

				message.setStringProperty("JMSXUserID", "123456789"); // 消息所属的用户编码
				message.setStringProperty("JMSXApp1ID", "001002"); // 消息所属的应用程序编码

				return message;
			}
		});
	}
	
	/**
	 * 发送HTML对象
	 * @param html
	 */
	public void sendObjectMsg(final Html html){
		if(null != html){
			this.getJmsTemplate().send(new MessageCreator() {
				// 这里创建了一个 message 对象,然后可以对该对象进行 各种属性的定义
				private Message message;
	
				public Message createMessage(Session session) throws JMSException {
					message = session.createObjectMessage(html);
					return message;
				}
			});
		}
	}

	/**
	 * 发送Table对象
	 * @param html
	 */
	public void sendObjectMsg(final Table table){
		if(null != table){
			this.getJmsTemplate().send(new MessageCreator() {
				// 这里创建了一个 message 对象,然后可以对该对象进行 各种属性的定义
				private Message message;
	
				public Message createMessage(Session session) throws JMSException {
					message = session.createObjectMessage(table);
					message.setJMSType("JMS_ACTIVEMQ_OBJECT");
					message.setJMSTimestamp(System.currentTimeMillis());
					return message;
				}
			});
		}
	}
}
