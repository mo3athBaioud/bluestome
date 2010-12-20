/**
 * 文件com.sky.spirit.basic.mail.EmailServiceImpl.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.mail;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.jms.EmailProducer;
import com.sky.spirit.common.Constants;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:22:57
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
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
