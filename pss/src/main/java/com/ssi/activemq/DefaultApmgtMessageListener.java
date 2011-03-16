package com.ssi.activemq;

public class DefaultApmgtMessageListener implements IApmgtMessageListener {
	public void onMessage(ApmgtMessageData message) {
		System.out.println("监听到消息：" + message);
	}
}
