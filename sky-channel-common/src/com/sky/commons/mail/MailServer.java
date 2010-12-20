package com.sky.commons.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
 * 邮件发送server
 */
public class MailServer implements MessageSender {

	private List<String> mailTo;

	private String mailFrom;

	private List<String> mailCC;

	private String subject;

	private String content;

	private String smtpHost;

	private String userAccount;

	private String password;

	private int sendLimit = 10;

	// 当前发送次数
	private int currentNum = 0;
	// 最新发送时间
	private Date lastestSendTime = new Date();
	// 发送邮件的间隔,即在该间隔内，最多只发送sendLimit次
	private int interval = 10;

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 */
	public void sendMessage() throws Exception {
		if(isSendSupport()){
			this.sendMessage(this.content);
		}
	}

	// 判断是否允许发送,true:允许，false，不允许
	// 发送次数小于指定的次数则返回true
	// 否则如果当前时间比第一次发送的时间间隔大于指定的间隔，则返回true
	// 其他返回false
	private boolean isSendSupport() {
		boolean isSupport = false;
		if (this.sendLimit > 0) {
			if (this.currentNum + 1 > this.sendLimit) {
				// 时间间隔>指定的间隔，则允许发送
				if ((new Date().getTime() - this.lastestSendTime.getTime()) / 1000 / 60 >= this.interval) {
					this.lastestSendTime = new Date();
					this.currentNum = 1;
					isSupport = true;
				}
			} else {
				this.currentNum++;
				if (this.currentNum == 1) {
					this.lastestSendTime = new Date();
				}
				isSupport = true;
			}
		} else {
			isSupport = true;
		}
		return isSupport;
	}

	/*
	 */
	public void sendMessage(String content) throws Exception {
		if(isSendSupport()){
		Properties props = new Properties();// 也可用Properties props =
											// System.getProperties();
		props.put("mail.smtp.host", this.smtpHost);// 存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");// 同时通过验证
		Session session = Session.getInstance(props, null);
		session.setDebug(false);
		Message message = new MimeMessage(session);
		Address from = new InternetAddress(mailFrom);// 发件人的邮件地址
		message.setFrom(from);// 设置发件人
		// ========收件人===========
		if (mailTo != null) {
			for (String addr : mailTo) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(addr));// 设置收件人,并设置其接收类型为TO,还有3种预定义类型CC,BCC：
			}
		}
		// ========抄送人===========
		if (mailCC != null) {
			for (String addr : mailCC) {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(addr));// 设置收件人,并设置其接收类型为TO,还有3种预定义类型CC,BCC：
			}
		}
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(content);// 设置信件内容
		message.saveChanges();// 存储邮件信息
		// Transport 是用来发送信息的，
		// 用于邮件的收发打操作。
		Transport transport = session.getTransport("smtp");
		transport.connect(this.smtpHost, userAccount, password);// 以smtp方式登录邮箱
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
		}
	}

	/*
	 */
	public void sendMessage(String head, String content) throws Exception {
		if(isSendSupport()){
		Properties props = new Properties();// 也可用Properties props =
											// System.getProperties();
		props.put("mail.smtp.host", this.smtpHost);// 存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");// 同时通过验证
		Session session = Session.getInstance(props, null);
		session.setDebug(false);
		Message message = new MimeMessage(session);
		Address from = new InternetAddress(mailFrom);// 发件人的邮件地址
		message.setFrom(from);// 设置发件人
		// ========收件人===========
		if (mailTo != null) {
			for (String addr : mailTo) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(addr));// 设置收件人,并设置其接收类型为TO,还有3种预定义类型CC,BCC：
			}
		}
		// ========抄送人===========
		if (mailCC != null) {
			for (String addr : mailCC) {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(addr));// 设置收件人,并设置其接收类型为TO,还有3种预定义类型CC,BCC：
			}
		}
		message.setSubject(subject + "#" + head);
		message.setSentDate(new Date());
		message.setText(content);// 设置信件内容
		message.saveChanges();// 存储邮件信息
		// Transport 是用来发送信息的，
		// 用于邮件的收发打操作。
		Transport transport = session.getTransport("smtp");
		transport.connect(this.smtpHost, userAccount, password);// 以smtp方式登录邮箱
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
		}
	}

	public List<String> getMailTo() {
		return mailTo;
	}

	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}

	public List<String> getMailCC() {
		return mailCC;
	}

	public void setMailCC(List<String> mailCC) {
		this.mailCC = mailCC;
	}

	public int getSendLimit() {
		return sendLimit;
	}

	public void setSendLimit(int sendLimit) {
		this.sendLimit = sendLimit;
	}

	public Date getLastestSendTime() {
		return lastestSendTime;
	}

	public void setLastestSendTime(Date lastestSendTime) {
		this.lastestSendTime = lastestSendTime;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void sendAlarm(String head,String content) {
		try {
			this.sendMessage(head, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		MailServer send = new MailServer();
		send.setSendLimit(1);
		send.setInterval(1);
		for (int i = 0; i < 100; i++) {
			System.out.println(send.isSendSupport());
		}
		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(send.isSendSupport());
		}
		try {
			Thread.sleep(1000 * 35);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(send.isSendSupport());
		}
	}

}
