package test.com.mss.dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.junit.*;

import com.mss.dal.dao.CmdataDao;
import com.mss.dal.dao.WaplogDao;
import com.mss.dal.domain.Cmdata;
import com.mss.dal.domain.Waplog;

public class WaplogDaoTest {

	private Ioc ioc;
	
	private WaplogDao waplogDAO;
	
	private CmdataDao cmdataDAO;
	
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
		waplogDAO = ioc.get(WaplogDao.class);
		cmdataDAO = ioc.get(CmdataDao.class);
	}
	
	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != waplogDAO)
			waplogDAO = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != waplogDAO)
			System.out.println(" waplogDAO is not null");
		
	}
	
	@Test
	public void inesrt(){
		List<Cmdata> list = cmdataDAO.search(Cmdata.class, Cnd.orderBy().asc("d_id"));
		Waplog wl = null;
		for(Cmdata cd:list){
			wl = new Waplog();
			wl.setPhoneNum(cd.getPhoneNum());
		}
	}
	
}
