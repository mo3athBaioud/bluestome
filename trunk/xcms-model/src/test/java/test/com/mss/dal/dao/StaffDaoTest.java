package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.StaffDao;
import com.mss.dal.domain.Staff;

public class StaffDaoTest {

	private Ioc ioc;
	
	private StaffDao staffDao;
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
	                "*org.nutz.ioc.loader.json.JsonLoader",
	                "conf/datasource.json", 
	                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
	                "com.mss.dal"
	                ));
		}catch(Exception e){
			
		}
		staffDao = ioc.get(StaffDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != staffDao)
			staffDao = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != staffDao)
			System.out.println(" staffDao is not null");
	}

	public void add(){
		Staff sta = new Staff();
		sta.setUsername("liwei001");
		sta.setPassword("123456");
		sta.setMobile("15800371329");
		sta.setOfficephone("021-5432145");
		sta.setChannelcode("E0E7A0A1");
		sta = staffDao.save(sta);
		System.out.println(" >> "+sta.getId());
	}
	
	@Test
	public void find(){
		List<Staff> list = staffDao.search(Staff.class, "d_id");
		for(Staff sta:list){
			System.out.println(" >> username:"+sta.getUsername());
			System.out.println(" >> channelcode:"+sta.getChannelcode());
			if(null != sta.getChannel()){
				System.out.println(" >> username:"+sta.getChannel().getChannlename());
			}
		}
	}
}
