package test.com.xian.support.manager;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.xian.support.entity.Noplog;
import com.xian.support.service.NoplogManager;

public class NoplogManagerTest {

	private NoplogManager noplogManager;
	
	private Noplog entity = null;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		noplogManager = (NoplogManager)context.getBean("noplogManager");
		entity = new Noplog();
	}
	
	@After
	public void destory(){
		if(null != noplogManager)
			noplogManager = null;
		if(null != entity)
			entity = null;
	}
	
	@Test
	public void test(){
		if(null == noplogManager){
			System.out.println(" >  noplogManager is null!");
		}
		entity.setUid(2814);
		System.out.println(" > noplogManager is not null!");
		int c = noplogManager.getTotalBySql(entity);
		System.out.println(" > total:" + c);
		if(c < 100){
			List<Noplog> list = noplogManager.getListBySql(entity);
			for(Noplog np:list){
				System.out.println(" > "+np.getUid()+"|"+np.getPhonenum()+"|"+np.getBtype());
				System.out.println(" > bd.name:"+np.getPhoneBDistrict().getName());
				System.out.println(" > channel.name:"+np.getStaff().getChannel().getChannelname());
				System.out.println("");
			}
		}
	}
}
