package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.TerminalPropertyDao;
import com.mss.dal.dao.StaffDao;
import com.mss.dal.domain.TerminalProperty;
import com.mss.dal.domain.Staff;

public class TerminalPropertyDaoTest {

	private Ioc ioc;
	
	private TerminalPropertyDao terminalPropertyDao;
	
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
			e.printStackTrace();
		}
		terminalPropertyDao = ioc.get(TerminalPropertyDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != terminalPropertyDao)
			terminalPropertyDao = null;
	}
	
	@Test
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != terminalPropertyDao)
			System.out.println(" terminalPropertyDao is not null");
	}

	public void find(){
		List<TerminalProperty> list = terminalPropertyDao.search(TerminalProperty.class, "d_id");
		for(TerminalProperty bus:list){
			System.out.println(" >> "+bus.getHsman()+"|"+bus.getHstype());
		}
	}
}
