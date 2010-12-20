/**
 * 文件com.sky.spirit.basic.jms.EmailConsumer.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
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
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:18:34
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
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
