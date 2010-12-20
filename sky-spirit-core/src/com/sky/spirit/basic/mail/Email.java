/**
 * 文件com.sky.spirit.basic.mail.Email.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.mail;

import java.io.Serializable;
import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import com.sky.spirit.common.Constants;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:22:03
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
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
		simpleMailMessage.setSubject("SkySpirit错误日志邮件");
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
