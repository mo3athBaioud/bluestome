package test.com.mss.dal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import com.mss.dal.dao.OPlogDao;
import com.mss.dal.domain.OPlog;

public class OPlogDaoTest {

	private Ioc ioc;
	
	private OPlogDao opLogDao;

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
		opLogDao = ioc.get(OPlogDao.class);
	}
	
	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != opLogDao)
			opLogDao = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != opLogDao)
			System.out.println(" opLogDao is not null");
		
	}

	@Test
	public void insert(){
		OPlog op = new OPlog();
		op.setName("weinan1");
		op.setOpType("GPRS_QUERY");
		op.setOpValue("15800371329");
		op.setOpResult("失败");
		op = opLogDao.save(op);
		System.out.println(" >> op.id:"+op.getId());
	}
}
