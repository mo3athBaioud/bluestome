package test.com.ssi.activemq;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.activemq.ApmgtModPasswordRequest;
import com.ssi.activemq.IApmgtMessageProducer;
import com.ssi.activemq.MessageHeader;
import com.ssi.activemq.ModPasswordRequest;

public class ActiveMqTest {

	private IApmgtMessageProducer producer = null;

	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/applicationContext.xml");
		producer = (IApmgtMessageProducer) context.getBean("producer");
	}

	@After
	public void destory() {
		if(null != producer){
			producer = null;
		}
	}

	@Test
	public void process() {
		try {
			ApmgtModPasswordRequest messageData = new ApmgtModPasswordRequest();
			messageData.setMessageHeader(new MessageHeader());
			messageData.setMessageContent(new ModPasswordRequest());
			messageData.init();
			producer.sendMessage(messageData);
			Thread.sleep(60000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
