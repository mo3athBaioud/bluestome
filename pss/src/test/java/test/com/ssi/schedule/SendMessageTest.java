package test.com.ssi.schedule;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMessageTest {
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//		context.getBean("QuartzJobFactory");
	}
	
	@After
	public void destory(){
	}
	
	@Test
	public void process(){
		try{
			Thread.sleep(60000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
