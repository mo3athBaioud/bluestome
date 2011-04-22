package test.nutzd.domain;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.nutzd.domain.Sysconfig;
import com.nutzd.domain.dao.NutzdDataSource;

public class SysconfigDaoTest {

	private NutzdDataSource nds = null;
	private Dao dao = null;
	
	@Before
	public void init(){
		Ioc2 ioc = new NutIoc(new JsonLoader("conf/jdbc.js"));
//		nds = NutzdDataSource.getInstance();
		dao = ioc.get(Dao.class,"dao");
	}
	
	@After
	public void desotry(){
		if(null != nds)
			nds = null;
		if(null != dao)
			dao = null;
	}

	@Test
	public void queryForList(){
		List<Sysconfig> list = dao.query(Sysconfig.class, null, dao.createPager(1, 10));
		for(Sysconfig wb:list){
			System.out.println(" >> queryForList \t"+wb.getName()+"|"+wb.getType()+"|"+wb.getValue());
		}
	}

}
