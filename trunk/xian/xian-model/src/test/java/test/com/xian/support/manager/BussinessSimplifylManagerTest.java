package test.com.xian.support.manager;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.xian.support.entity.BussinessSimplify;
import com.xian.support.entity.Channel;
import com.xian.support.service.BussinessSimplifyManager;
import com.xian.support.service.StaffManager;

public class BussinessSimplifylManagerTest {

	private BussinessSimplifyManager bussinessSimplifyManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		bussinessSimplifyManager = (BussinessSimplifyManager)context.getBean("bussinessSimplifyManager");
	}
	
	@After
	public void destory(){
		if(null != bussinessSimplifyManager)
			bussinessSimplifyManager = null;
	}
	
//	@Test
	public void test(){
		BussinessSimplify entity = new BussinessSimplify();
		if(null != bussinessSimplifyManager)
		{
			String bdistrict = "E0ED";
			entity.setBdistrict(bdistrict);
			int c = bussinessSimplifyManager.getTotalBySql(entity);
			if(c > 0){
				List<BussinessSimplify> list = bussinessSimplifyManager.getListBySql(entity, 0, 100);
				if(null != list && list.size() > 0){
					for(BussinessSimplify bs:list){
						System.out.println(" > bs.id:"+bs.getId());
						System.out.println(" > bs.btype:"+bs.getBtype());
						System.out.println(" > bs.phonenum:"+bs.getPhonenum());
						System.out.println();
					}
				}
			}
			
		}
	}
	
	@Test
	public void test2(){
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<13;i++){
			sb.append(",["+i+",'"+i+"']");
		}
		System.out.println(sb.toString());
	}
}
