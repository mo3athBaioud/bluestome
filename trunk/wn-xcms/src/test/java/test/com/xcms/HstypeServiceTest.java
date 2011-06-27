package test.com.xcms;

import java.util.List;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.junit.*;

import com.mss.dal.domain.Hstype;
import com.xcms.web.service.HstypeService;


public class HstypeServiceTest {

	private HstypeService hstypeService;
	
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
			hstypeService = ioc.get(HstypeService.class,"hstypeService");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void destory(){
		if(null != ioc){
			ioc = null;
		}
		if(null != hstypeService){
			hstypeService = null;
		}
	}
	
	@Test
	public void find(){
		List<Hstype> list = hstypeService.find(7);
		for(Hstype ht:list){
			System.out.println(" >> "+ht.getId()+"|"+ht.getName()+"|"+ht.getRemarks());
		}
	}
}
