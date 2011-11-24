package test.com.xian.support.manager;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.ssi.common.utils.MD5;
import com.xian.support.entity.Staff;
import com.xian.support.service.StaffManager;

public class StaffManagerTest {

	private StaffManager staffManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		staffManager = (StaffManager)context.getBean("staffManager");
	}
	
	@After
	public void destory(){
		if(null != staffManager)
			staffManager = null;
	}
	
	public void get(){
		Integer id = 100000;
		Staff entity = staffManager.get(id);
		if(null != entity){
			System.out.println(" > entity.id:"+entity.getId());
			System.out.println(" > json:" + entity.toJson());
		}else{
			System.err.println(" > error");
		}
	}
	
	@Test
	public void getTotal(){
		Staff entity = new Staff();
		int t = staffManager.getTotalBySql(entity);
		if(t > 0)
		{
			List<Staff> list = staffManager.getList(entity);
			for(Staff stf:list)
			{
				String md5 = MD5.getInstance().getMD5ofStr(stf.getPassword());
				stf.setPassword(md5);
				staffManager.save(stf);
				System.out.println(" > "+stf.getUsername()+"|"+stf.getChannel().getAddress()+"|"+stf.getPassword());
			}
		}
		System.out.println(" > t:" + t);
	}
	
}
