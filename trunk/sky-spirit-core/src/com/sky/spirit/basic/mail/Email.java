/**
 * �ļ�com.sky.spirit.basic.mail.Email.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.mail;

import java.io.Serializable;
import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import com.sky.spirit.common.Constants;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:22:03
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class Email implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleMailMessage simpleMailMessage;
	private String templateName;
	private String title;
	private String content;
	private String sendTo;

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
		simpleMailMessage.setTo(sendTo);
	}

	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public Email() {
		simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(sendTo);
		simpleMailMessage.setFrom(Constants.SYSTEM_MAILBOX);
		simpleMailMessage.setSubject("SkySpirit������־�ʼ�");
		simpleMailMessage.setSentDate(new Date());
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
