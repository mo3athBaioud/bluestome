package test.com.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.ArticleDocIService;
import com.ssi.dal.usgs.dao.IEarthQuakeDetailDAO;
import com.ssi.dal.usgs.dao.IEarthQuakeInfoDAO;
import com.ssi.dal.usgs.domain.EarthQuakeDetail;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;

public class EarthQuakeDAO {

	private IEarthQuakeDetailDAO earthQuakeDetailDAO;
	private IEarthQuakeInfoDAO earthQuakeInfoDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		earthQuakeDetailDAO = (IEarthQuakeDetailDAO)context.getBean("earthQuakeDetailDAO");
		earthQuakeInfoDAO = (IEarthQuakeInfoDAO)context.getBean("earthQuakeInfoDAO");
	}
	
	@After
	public void destory(){
		if(null != earthQuakeDetailDAO)
			earthQuakeDetailDAO = null;
		if(null != earthQuakeInfoDAO)
			earthQuakeInfoDAO = null;
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		List<EarthQuakeInfo> ilist = earthQuakeInfoDAO.find(map);
		System.out.println(" >> info.size:"+ilist.size());
		for(EarthQuakeInfo info:ilist){
			System.out.println(" >> data:"+info.getDate());
			if(null != info.getDetail()){
				System.out.println(" >> detail eventid ["+info.getDetail().getEventId()+"] ");
			}
		}
		
//		List<EarthQuakeDetail>  list = earthQuakeDetailDAO.find(map);
//		System.out.println(" >> detail.size:"+list.size());
		
	}
}
