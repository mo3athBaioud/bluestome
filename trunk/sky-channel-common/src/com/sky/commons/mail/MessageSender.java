package com.sky.commons.mail;

/*
 * ��������Ϣ���ͽӿڣ�����Ϣ��ʽ�����ʼ����ѷ�ʽ
 */
public interface MessageSender {
	/*
	 * ��Ϣ���ͽӿ�
	 */
	public void sendMessage() throws Exception;
	
	/*
	 * ����ָ������Ϣ
	 */
	public void sendMessage(String message) throws Exception;
	
	/*
	 * ����ָ������Ϣ
	 */
	public void sendMessage(String head,String content) throws Exception;
	
	/**
	 * ���;��������׳��κ��쳣
	 */
	public void sendAlarm(String head,String content);
}
