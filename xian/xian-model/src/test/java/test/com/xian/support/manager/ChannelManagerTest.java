package test.com.xian.support.manager;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.xian.support.entity.Channel;
import com.xian.support.service.ChannelManager;
import com.xian.support.service.StaffManager;

public class ChannelManagerTest {

	private ChannelManager channelManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		channelManager = (ChannelManager)context.getBean("channelManager");
	}
	
	@After
	public void destory(){
		if(null != channelManager)
			channelManager = null;
	}
	
	@Test
	public void test(){
		if(null != channelManager)
		{
			String code = "EE200054";
			Channel entity = channelManager.get(code);
			if(null != entity)
			{
				System.out.println(" > :"+entity.getChannelcode()+"|"+entity.getChannelname());
			}
		}
	}
	
}
