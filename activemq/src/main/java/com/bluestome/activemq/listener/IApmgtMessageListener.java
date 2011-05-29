package com.bluestome.activemq.listener;

import com.bluestome.activemq.message.ApmgtMessageData;

public interface IApmgtMessageListener {
	 public void onMessage(ApmgtMessageData message);
}
