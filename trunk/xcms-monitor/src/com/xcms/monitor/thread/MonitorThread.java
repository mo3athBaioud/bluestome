package com.xcms.monitor.thread;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpException;

import com.ssi.common.utils.HttpClientUtils;
import com.xcms.monitor.mail.MimeMailService;

public class MonitorThread implements Runnable {

	private MimeMailService mailService;
	
	private String url = "http://117.34.89.190:8080/staff/list.cgi?start=0&limit=15";
	
	private long sleepTime = 15*60*1000;
	
	boolean isRun = true;
	
	private Thread th = null;
	
	public MonitorThread(){
	}
	
	public void run() {
		while(isRun){
			try{
				HashMap<String,Object> result = HttpClientUtils.getResponse(url);
				String code = "Response-Status";
				if(null != result.get(code)){
					String status = (String)result.get(code);
					if(null != status && status.equals("200")){
						byte[] body = (byte[])result.get("body");
						if(null != body && body.length > 0){
							System.out.println("正常:" + body.length);
						}else{
							//TODO 发邮件 内容未获取到
							mailService.sendEmail(url,"服务器异常", "服务端没有返回内容");
						}
					}else{
						//TODO 发邮件 服务不响应
						mailService.sendEmail(url,"服务器异常", "服务端响应异常,响应的状态码为["+status+"]");
					}
				}
				Thread.sleep(sleepTime);
			}catch(HttpException e){
				//TODO 服务器HTTP异常
				mailService.sendEmail(url,"监控程序异常", "异常信息为："+e.getMessage());
			}catch(IOException e){
				//TODO 服务器IO异常
				mailService.sendEmail(url,"监控程序异常", "异常信息为："+e.getMessage());
			}catch(Exception e){
				//TODO 服务器遇到异常
				mailService.sendEmail(url,"监控程序异常", "异常信息为："+e.getMessage());
			}
		}
	}
	
	public void start(){
		isRun = true;
		th = new Thread(this);
		th.start();
	}
	
	public void stop(){
		isRun = false;
		if(null != th)
			th = null;
	}

	public MimeMailService getMailService() {
		return mailService;
	}

	public void setMailService(MimeMailService mailService) {
		this.mailService = mailService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	
	
}
