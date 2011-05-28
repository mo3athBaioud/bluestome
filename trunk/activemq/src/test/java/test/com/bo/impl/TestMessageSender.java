package test.com.bo.impl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.Html;
import com.bo.Table;
import com.bo.impl.MessageReceiver;
import com.bo.impl.MessageSender;

public class TestMessageSender {
	
	private ApplicationContext ctx = null;
	private MessageSender messageSender = null;
	private MessageReceiver messageReceiver = null;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("conf/spring-*.xml");
		messageSender = (MessageSender) ctx.getBean("messageSender");
		messageReceiver = (MessageReceiver)ctx.getBean("messageReceiver");
	}

	@After
	public void destory(){
		if(null != ctx){
			ctx = null;
		}
		if(null != messageSender){
			messageSender = null;
		}
		if(null != messageReceiver){
			messageReceiver = null;
		}
	}
	
	public void sentTextMsg() {
		messageSender.sendTextMsg("这个世界真的很无奈！");
	}
	
	public void getTextMsg() {
		messageReceiver.receiverTextMsg();
	}

	@Test
	public void sendObjectMsg(){
		Html html = null;
		Table table = null;
		for(int i=0;i<1000;i++){
			html = new Html(String.valueOf(i),"Html.body."+String.valueOf(i));
			messageSender.sendObjectMsg(html);
			table = new Table("Table_"+String.valueOf(i));
			messageSender.sendObjectMsg(table);
		}
	}

	@Test
	public void getObjectMsg(){
		for(int i=0;i<2000;i++){
			messageReceiver.receiverObjectMsg();
		}
	}

}
