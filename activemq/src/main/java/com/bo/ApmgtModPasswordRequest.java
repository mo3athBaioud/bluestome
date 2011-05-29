package com.bo;

import com.bluestome.activemq.message.ApmgtMessageData;
import com.bluestome.activemq.message.MessageHeader;

public class ApmgtModPasswordRequest extends
		ApmgtMessageData<ModPasswordRequest> {
	private static final int REQ_MODPASSWORD = 0;

	private static final int INTF = 1;

	private static final int APMGT = 2;

	public void init() {
		messageHeader = new MessageHeader();
		messageContent = new ModPasswordRequest();
		messageHeader.setType(REQ_MODPASSWORD);
		messageHeader.setSender(INTF);
		messageHeader.setReceiver(APMGT);
		messageContent.setNewPassword("123456");
		messageContent.setOldPassword("654321");
	}
}
