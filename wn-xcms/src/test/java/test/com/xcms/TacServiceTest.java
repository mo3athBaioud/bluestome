package test.com.xcms;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.domain.Tac;
import com.xcms.web.service.HstypeService;
import com.xcms.web.service.TacService;

public class TacServiceTest {

	private TacService tacService;
	
	private Ioc ioc;

	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
	            "*org.nutz.ioc.loader.json.JsonLoader",
	            "conf/", 
	            "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
	            "com.mss.dal","com.xcms.web"
            ));
			tacService = ioc.get(TacService.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@After
	public void destory(){
		if(null != ioc){
			ioc = null;
		}
		if(null != tacService){
			tacService = null;
		}
	}
	
	@Test
	public void test(){
		if(null != tacService){
			System.out.println(" >> tacService is not null");
		}
	}
	
	@Test
	public void getCount(){
		int count = tacService.getCount();
		System.out.println(" >> count:"+count);
	}
	
	@Test
	public void search(){
		int start = 81;
		int limit =  20;
		
		List<Tac> list = tacService.search("d_hsman_name","诺基亚",start, limit);
		System.out.println(" >> list.size:"+list.size());
	}

}
