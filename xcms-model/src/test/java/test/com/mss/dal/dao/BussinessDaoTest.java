package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.BussinessDao;
import com.mss.dal.dao.StaffDao;
import com.mss.dal.domain.Bussiness;
import com.mss.dal.domain.Staff;

public class BussinessDaoTest {

	private Ioc ioc;
	
	private BussinessDao bussinessDao;
	
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
		bussinessDao = ioc.get(BussinessDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != bussinessDao)
			bussinessDao = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != bussinessDao)
			System.out.println(" bussinessDao is not null");
	}

	@Test
	public void find(){
		List<Bussiness> list = bussinessDao.search(Bussiness.class, "d_id");
		for(Bussiness bus:list){
			System.out.println(" >> "+bus.getHsman()+"|"+bus.getHstype());
		}
	}
}
