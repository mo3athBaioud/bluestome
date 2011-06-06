package test.com.mss.dal.dao;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.TmpGprsDao;
import com.mss.dal.dao.TmpHpgjDao;

public class TmpHpgjDAOTest {

	private TmpHpgjDao hpgjDAO;
	
	private Dao dao;
	
	private Ioc2 ioc;
	
	static HashMap map = new HashMap();
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
                "*org.nutz.ioc.loader.json.JsonLoader",
                "conf/datasource.json", 
                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
                "com.mss.dal"
                ));
			
			hpgjDAO = ioc.get(TmpHpgjDao.class);
			dao = ioc.get(Dao.class);
			map.put("99720470","GPRS 50元套餐");
			map.put("99720492","GPRS 3元套餐");
			map.put("99720467","GPRS 20元套餐");
			map.put("99720473","GPRS 5元套餐");
			map.put("99720479","GPRS 10元套餐");
			map.put("99720468","GPRS 100元套餐");
			map.put("99720469","GPRS 200元套餐");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@After
	public void destory(){
		if(null != hpgjDAO)
			hpgjDAO = null;
	}
	
	@Test
	public void asset(){
		if(null != hpgjDAO){
			System.out.println("hpgjDAO is not null");
		}
	}
	
}
