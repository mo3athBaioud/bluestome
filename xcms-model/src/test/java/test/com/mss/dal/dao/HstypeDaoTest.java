package test.com.mss.dal.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.htmlparser.tags.LinkTag;
import org.junit.*;

import com.mss.dal.dao.HsmanDao;
import com.mss.dal.dao.HstypeDao;
import com.mss.dal.domain.Hsman;
import com.mss.dal.domain.Hstype;
import com.xc.tools.HstypeParser;

public class HstypeDaoTest {

	private Ioc2 ioc;
	
	private HstypeDao hstypeDAO;
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
                "*org.nutz.ioc.loader.json.JsonLoader",
                "conf/datasource.json", 
                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
                "com.mss.dal"
                ));
			
			hstypeDAO = ioc.get(HstypeDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != hstypeDAO)
			hstypeDAO = null;
	}
	
	public void test(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != hstypeDAO)
			System.out.println(" hstypeDAO is not null");
	}
	
	@Test
	public void findHstype(){
		try{
			long start = System.currentTimeMillis();
			List<Hstype> list = hstypeDAO.search(Hstype.class, Cnd.where("d_status", "=", "9"));
			for(Hstype ht:list){
				String url = ht.getRemarks();
				System.out.println(" >> tac:"+ht.getTac()+"|url:"+url);
				Thread.sleep(100);
			}
			long end = System.currentTimeMillis();
			long spead = end - start;
			System.out.println(" >> "+spead+" ms");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
	}
	
}
