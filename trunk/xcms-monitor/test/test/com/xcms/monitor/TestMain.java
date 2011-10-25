package test.com.xcms.monitor;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class TestMain {

	private ApplicationContext context;
	
	@Test
	public void init(){
		if(null == context){
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			context.getBean("monitorThread");
		}
	}

}
