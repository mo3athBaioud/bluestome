package test.com.bo.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bo.impl.MessageReceiver;

public class TestMessageReceiver {
	private ApplicationContext ctx = null;
	private MessageReceiver messageReceiver = null;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("conf/spring-activemq_receiver.xml");
		messageReceiver = (MessageReceiver)ctx.getBean("messageReceiver");
	}
	
	public void destory(){
		if(null != ctx){
			ctx = null;
		}
		
		if(null != messageReceiver){
			messageReceiver = null;
		}
	}

	public void getTextMsg() {
		messageReceiver.receiverTextMsg();
	}

	@Test
	public void getObjectMsg(){
		for(int i=0;i<2000;i++){
			messageReceiver.receiverObjectMsg();
		}
	}
}
