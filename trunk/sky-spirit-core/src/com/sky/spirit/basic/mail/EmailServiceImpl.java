/**
 * �ļ�com.sky.spirit.basic.mail.EmailServiceImpl.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.mail;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.jms.EmailProducer;
import com.sky.spirit.common.Constants;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:22:57
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class EmailServiceImpl implements IEmailService {
	private static Log log = LogFactory.getLog(EmailServiceImpl.class);
	private EmailProducer emailProducer;
	private String sendTo;

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public void setEmailProducer(EmailProducer emailProducer) {
		this.emailProducer = emailProducer;
	}

	public void sendSystemErrorNotice(String content) {
		Email email = new Email();
		email.setContent(content);
		email.setSendTo(sendTo);
		email.setTemplateName(Constants.SKY_MAIL_TEMPLATE_ERROR_NOTICE);
		try {
			emailProducer.send(email);
		} catch (JMSException e) {
			log.error(e);
		}
	}
}
