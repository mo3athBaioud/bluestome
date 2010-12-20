/**
 * �ļ�com.sky.spirit.basic.jms.EmailConsumer.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */


package com.sky.spirit.basic.jms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.mail.Email;
import com.sky.spirit.basic.mail.MailEngine;
import com.sky.spirit.common.util.DateUtils;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:18:34
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class EmailConsumer {
	private static Log log = LogFactory.getLog(EmailConsumer.class);
	private MailEngine mailEngine;

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	public void sendEmail(Email email) {
		Map<String,String> model = new HashMap<String,String>();
		model.put("title", email.getTitle());
		model.put("content", email.getContent());
		model.put("date", DateUtils.getStringFromDate(new Date(),
				"yyyy MM-dd HH:mm:ss"));
		mailEngine.sendMessage(email.getSimpleMailMessage(), email
				.getTemplateName(), model);
	}
}
