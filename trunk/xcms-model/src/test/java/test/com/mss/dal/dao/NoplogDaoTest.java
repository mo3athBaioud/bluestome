package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.NOplogDao;
import com.mss.dal.domain.Bussiness;
import com.mss.dal.domain.Noplog;

public class NoplogDaoTest {

	private Ioc ioc;
	
	private NOplogDao noplogDao;
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
	                "*org.nutz.ioc.loader.json.JsonLoader",
//	                "conf/datasource.json", 
	                "conf/jdbc.js", 
	                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
	                "com.mss.dal"
	                ));
		}catch(Exception e){
			
		}
		noplogDao = ioc.get(NOplogDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != noplogDao)
			noplogDao = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != noplogDao)
			System.out.println(" noplogDao is not null");
	}

	@Test
	public void add(){
		Noplog op = null;
		for(int i=1;i<10001;i++){
			try{
				op = new Noplog();
				op.setBtype(2);
				op.setLoginname("bluestome");
				op.setLoginnameBDistrict("E0E1");
				op.setPhonenum("15800371329");
				op.setPhonenumBDistrict("E1E0");
				op.setResult(1);
				op.setUid(2);
				op = noplogDao.save(op);
				if(op.getId() > 0){
					System.out.println(" >> 添加数据成功!");
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(null != op){
					op = null;
				}
			}
		}
	}
	
	
	public void find(){
		List<Noplog> list = noplogDao.search(Noplog.class, "d_id");
		for(Noplog bus:list){
			System.out.println(" >> "+bus.getLoginname()+"|"+bus.getLoginnameBDistrict());
		}
	}
}
