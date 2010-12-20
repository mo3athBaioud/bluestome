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
 * �ʼ�����server
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

	// ��ǰ���ʹ���
	private int currentNum = 0;
	// ���·���ʱ��
	private Date lastestSendTime = new Date();
	// �����ʼ��ļ��,���ڸü���ڣ����ֻ����sendLimit��
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

	// �ж��Ƿ�������,true:����false��������
	// ���ʹ���С��ָ���Ĵ����򷵻�true
	// ���������ǰʱ��ȵ�һ�η��͵�ʱ��������ָ���ļ�����򷵻�true
	// ��������false
	private boolean isSendSupport() {
		boolean isSupport = false;
		if (this.sendLimit > 0) {
			if (this.currentNum + 1 > this.sendLimit) {
				// ʱ����>ָ���ļ������������
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
		Properties props = new Properties();// Ҳ����Properties props =
											// System.getProperties();
		props.put("mail.smtp.host", this.smtpHost);// �洢�����ʼ�����������Ϣ
		props.put("mail.smtp.auth", "true");// ͬʱͨ����֤
		Session session = Session.getInstance(props, null);
		session.setDebug(false);
		Message message = new MimeMessage(session);
		Address from = new InternetAddress(mailFrom);// �����˵��ʼ���ַ
		message.setFrom(from);// ���÷�����
		// ========�ռ���===========
		if (mailTo != null) {
			for (String addr : mailTo) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(addr));// �����ռ���,���������������ΪTO,����3��Ԥ��������CC,BCC��
			}
		}
		// ========������===========
		if (mailCC != null) {
			for (String addr : mailCC) {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(addr));// �����ռ���,���������������ΪTO,����3��Ԥ��������CC,BCC��
			}
		}
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(content);// �����ż�����
		message.saveChanges();// �洢�ʼ���Ϣ
		// Transport ������������Ϣ�ģ�
		// �����ʼ����շ��������
		Transport transport = session.getTransport("smtp");
		transport.connect(this.smtpHost, userAccount, password);// ��smtp��ʽ��¼����
		transport.sendMessage(message, message.getAllRecipients());// �����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
		transport.close();
		}
	}

	/*
	 */
	public void sendMessage(String head, String content) throws Exception {
		if(isSendSupport()){
		Properties props = new Properties();// Ҳ����Properties props =
											// System.getProperties();
		props.put("mail.smtp.host", this.smtpHost);// �洢�����ʼ�����������Ϣ
		props.put("mail.smtp.auth", "true");// ͬʱͨ����֤
		Session session = Session.getInstance(props, null);
		session.setDebug(false);
		Message message = new MimeMessage(session);
		Address from = new InternetAddress(mailFrom);// �����˵��ʼ���ַ
		message.setFrom(from);// ���÷�����
		// ========�ռ���===========
		if (mailTo != null) {
			for (String addr : mailTo) {
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(addr));// �����ռ���,���������������ΪTO,����3��Ԥ��������CC,BCC��
			}
		}
		// ========������===========
		if (mailCC != null) {
			for (String addr : mailCC) {
				message.addRecipient(Message.RecipientType.CC,
						new InternetAddress(addr));// �����ռ���,���������������ΪTO,����3��Ԥ��������CC,BCC��
			}
		}
		message.setSubject(subject + "#" + head);
		message.setSentDate(new Date());
		message.setText(content);// �����ż�����
		message.saveChanges();// �洢�ʼ���Ϣ
		// Transport ������������Ϣ�ģ�
		// �����ʼ����շ��������
		Transport transport = session.getTransport("smtp");
		transport.connect(this.smtpHost, userAccount, password);// ��smtp��ʽ��¼����
		transport.sendMessage(message, message.getAllRecipients());// �����ʼ�,���еڶ�����������������õ��ռ��˵�ַ
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
