/**
 * �ļ�com.sky.spirit.basic.message.i18n.Message.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.message.i18n;

import java.io.Serializable;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:26:50
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class Message implements Serializable {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -6109760447384477924L;

	private String message;
	private int code = 0;
	private Object[] args;
	private Message nextMessage;

	protected Message(String message, int code, Object[] args) {
		super();
		this.message = message;
		this.code = code;
		this.args = args;
	}

	public int getCode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}

	public String getMessage() {
		return message
				+ (nextMessage != null ? ". " + nextMessage.getMessage() : "");
	}

	public Message setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
		return this;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public String toString() {
		return this.getMessage();
	}
}
