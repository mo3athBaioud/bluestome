package test.com.xian.support.dao;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.xian.support.dao.StaffDao;
import com.xian.support.entity.Staff;

public class StaffDaoTest {

	private StaffDao staffDao;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		staffDao = (StaffDao)context.getBean("staffDao");
	}
	
	@After
	public void destory(){
		if(null != staffDao)
			staffDao = null;
	}
	
	public void get(){
		Integer id = 100000;
		Staff entity = staffDao.get(id);
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
		int t = staffDao.getTotalBySQL("select count(*) as total from tbl_staff");
		System.out.println(" > t:" + t);
	}
	
}
