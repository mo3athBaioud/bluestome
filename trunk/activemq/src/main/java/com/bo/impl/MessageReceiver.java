package com.bo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.ObjectMessage;

import org.springframework.jms.core.support.JmsGatewaySupport;

import com.bo.Html;
import com.bo.Table;

public class MessageReceiver extends JmsGatewaySupport {
	
	public void receiverTextMsg() {
		TextMessage textMsg = (TextMessage) this.getJmsTemplate().receive();

		try {
			// 消息 header 中常有的 属性定义
			System.out.println("消息编码：" + textMsg.getJMSMessageID());
			System.out.println("目标对象：" + textMsg.getJMSDestination());
			System.out.println("消息模式：" + textMsg.getJMSDeliveryMode()); // 消息的模式
			// 分为持久模式和非持久模式,
			// 默认是
			// 非持久的模式（2）

			long sendTime = textMsg.getJMSTimestamp();
			Date date = new Date(sendTime);
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String temp = f.format(date);

			System.out.println("消息发送时间：" + temp);
			System.out.println("消息失效时间：" + textMsg.getJMSExpiration()); // 这里是一个
			// 整型值 0
			// 表示
			// 该消息永远不会过期
			System.out.println("消息优先级：" + textMsg.getJMSPriority()); // 优先级
			// 0~9,
			// 0
			// 表示最低
			System.out.println("关联编码：" + textMsg.getJMSCorrelationID());

			System.out.println("回复消息的地址：" + textMsg.getJMSReplyTo()); // 回复消息的地址(Destination类型),由发送者设定
			System.out.println("消息类型：" + textMsg.getJMSType()); // jms 不使用该字段，
			// 一般类型是由 用户自己定义
			System.out.println("是否签收过：" + textMsg.getJMSRedelivered()); // 如果是 真
			// ,表示客户端收到过该消息,但是并没有签收

			// 消息属性 (properties)
			System.out.println("用户编码："
					+ textMsg.getStringProperty("JMSXUserID"));
			System.out.println("应用程序编码："
					+ textMsg.getStringProperty("JMSXApp1ID"));
			System.out.println("已经尝试发送消息的次数："
					+ textMsg.getStringProperty("JMSXDeliveryCount"));

			// 消息体(body) 中传递的内容
			System.out.println("消息内容：" + textMsg.getText());

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收对象消息
	 *
	 */
	public void receiverObjectMsg(){
		ObjectMessage objMsg = (ObjectMessage)this.getJmsTemplate().receive();
		try{
			// 消息 header 中常有的 属性定义
			System.out.println("消息编码：" + objMsg.getJMSMessageID());
			System.out.println("目标对象：" + objMsg.getJMSDestination());
			System.out.println("消息模式：" + objMsg.getJMSDeliveryMode()); // 消息的模式

			long sendTime = objMsg.getJMSTimestamp();
			Date date = new Date(sendTime);
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String temp = f.format(date);
			System.out.println("消息发送时间：" + temp);
			System.out.println("消息失效时间：" + objMsg.getJMSExpiration()); // 这里是一个
			
			if(objMsg.getObject() instanceof  Html){
				Html html = (Html)objMsg.getObject();
				System.out.println("Html 对象消息内容:"+html.toString());
			}
			
			if(objMsg.getObject() instanceof  Table){
				Table table = (Table)objMsg.getObject();
				System.out.println("Table 对象消息内容:"+table.toString());
			}
		}catch(JMSException exception){
			
		}catch(Exception e){
			
		}
	}
}
