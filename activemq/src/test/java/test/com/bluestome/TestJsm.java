package test.com.bluestome;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bluestome.activemq.listener.IApmgtMessageProducer;
import com.bluestome.activemq.message.MessageHeader;
import com.bo.ApmgtModPasswordRequest;
import com.bo.ModPasswordRequest;

public class TestJsm {

	private ApplicationContext ctx = null;

	private IApmgtMessageProducer producer = null;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("conf/spring-jms.xml");
		producer = (IApmgtMessageProducer) ctx.getBean("producer");
	}

	public void destory() {
		if (null != ctx) {
			ctx = null;
		}
	}

	@Test
	public void sender() {
		ApmgtModPasswordRequest messageData = new ApmgtModPasswordRequest();
		messageData.setMessageHeader(new MessageHeader());
		messageData.setMessageContent(new ModPasswordRequest());
		messageData.init();
		producer.sendMessage(messageData);
	}
}
