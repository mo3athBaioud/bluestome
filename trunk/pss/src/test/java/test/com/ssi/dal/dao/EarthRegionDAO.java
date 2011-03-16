package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.usgs.dao.IEarthRegionDAO;
import com.ssi.dal.usgs.domain.EarthRegion;
import com.ssi.htmlparser.chinamilitary.EatheQuakeParser;

public class EarthRegionDAO {
	
	private IEarthRegionDAO earthRegionDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		earthRegionDAO = (IEarthRegionDAO)context.getBean("earthRegionDAO");
	}
	
	@After
	public void destory(){
		if(null != earthRegionDAO){
			earthRegionDAO = null;
		}
	}
	
	public void insert(){
		EarthRegion region = null;
		try{
			HashMap<String, String> umap = EatheQuakeParser.getRegionListUrl();
			Iterator it = umap.keySet().iterator();
			while (it.hasNext()) {
				String url = (String) it.next();
				String name = umap.get(url);
				System.out.println("name:"+name+"\t url:"+url);
				region = new EarthRegion(name,url);
				Integer result = earthRegionDAO.insert(region);
				if(result>0){
					System.out.println(" 成功");
				}else{
					System.out.println(" 失败");
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != region)
				region = null;
		}
	}
	
	@Test
	public void find(){
		List<EarthRegion> list = null;
		HashMap map = new HashMap();
		try{
			list = earthRegionDAO.find(map);
			for(EarthRegion region:list){
				System.out.println("N:"+region.getName()+"\tU:"+region.getUrl());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
		}
	}
	
}
