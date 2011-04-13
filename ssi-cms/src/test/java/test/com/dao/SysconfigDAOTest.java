package test.com.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.ArticleDocIService;
import com.ssi.common.dal.dao.ISysConfigDAO;
import com.ssi.common.dal.domain.SysConfig;
import com.ssi.dal.usgs.dao.IEarthQuakeDetailDAO;
import com.ssi.dal.usgs.dao.IEarthQuakeInfoDAO;
import com.ssi.dal.usgs.domain.EarthQuakeDetail;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public class SysconfigDAOTest {

	private ISysConfigDAO sysconfigDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		sysconfigDAO = (ISysConfigDAO)context.getBean("sysconfigDAO");
	}
	
	@After
	public void destory(){
		if(null != sysconfigDAO)
			sysconfigDAO = null;
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		List<SysConfig> list = sysconfigDAO.find(map);
		for(SysConfig sc:list){
			System.out.println(" >> name:"+sc.getName());
			System.out.println(" >> value:"+sc.getValue());
			System.out.println(" >> type:"+sc.getType());
			System.out.println();
		}
	}
}
