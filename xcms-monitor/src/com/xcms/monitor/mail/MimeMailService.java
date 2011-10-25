package com.xcms.monitor.mail;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * MIME邮件服务类.
 * 
 * 发送由freemarker生成的的html格式邮件, 并带有附件.
 * 
 */
public class MimeMailService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);

	private JavaMailSender mailSender;
	
	private Template template;
	
	private String[] tos = {"zhangxiao917@21cn.com","bluestome@21cn.com"};
	
	public MimeMailService(){
		System.out.println("init");
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
		//根据freemarkerConfiguration的templateLoaderPath载入文件.
		template = freemarkerConfiguration.getTemplate("mailTemplate.ftl", DEFAULT_ENCODING);
	}

	/**
	 * 发送邮件.
	 */
	public void sendEmail(String url,String title,String message) {

		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);
			helper.setFrom("xcmstest2011@21cn.com");
			helper.setTo(tos);
			
			helper.setSubject(title);

			String content = generateContent(url,title,message);
			helper.setText(content, true);
			
			//附件
//			File attachment = generateAttachment();
//			helper.addAttachment("a.txt", attachment);
			mailSender.send(msg);
			logger.info("HTML版邮件已发送");
		} catch (MessagingException e) {
			e.printStackTrace();
			logger.error("构造邮件失败", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送邮件失败", e);
		}
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	@SuppressWarnings("unchecked")
	private String generateContent(String url,String title,String message) throws MessagingException {

		try {
			
			Map context = new HashMap();
			context.put("url", url);
			context.put("title", title);
			context.put("message", message);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
		} catch (IOException e) {
			throw new MessagingException("FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}

	/**
	 * 获取classpath中的附件.
	 */
	private File generateAttachment() throws MessagingException {
		try {
			Resource resource = new ClassPathResource("/email/a.txt");
			return resource.getFile();
		} catch (IOException e) {
			throw new MessagingException("附件不存在", e);
		}
	}

	public String[] getTos() {
		return tos;
	}

	public void setTos(String[] tos) {
		this.tos = tos;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}
	
	
}
