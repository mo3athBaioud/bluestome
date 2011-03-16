package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.usgs.dao.IEarthQuakeDetailDAO;
import com.ssi.dal.usgs.dao.IEarthQuakeInfoDAO;
import com.ssi.dal.usgs.dao.IEarthRegionDAO;
import com.ssi.dal.usgs.domain.EarthQuakeDetail;
import com.ssi.dal.usgs.domain.EarthQuakeInfo;
import com.ssi.dal.usgs.domain.EarthRegion;
import com.ssi.htmlparser.chinamilitary.EatheQuakeParser;

public class EarthQuakeDetailDAO {

	private IEarthQuakeInfoDAO earthQuakeInfoDAO;
	private IEarthRegionDAO earthRegionDAO;
	private IEarthQuakeDetailDAO earthQuakeDetailDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		earthQuakeInfoDAO = (IEarthQuakeInfoDAO)context.getBean("earthQuakeInfoDAO");
		earthRegionDAO = (IEarthRegionDAO)context.getBean("earthRegionDAO");
		earthQuakeDetailDAO = (IEarthQuakeDetailDAO)context.getBean("earthQuakeDetailDAO");
	}

	@After
	public void destory(){
		if(null != earthQuakeInfoDAO){
			earthQuakeInfoDAO = null;
		}
		if(null != earthRegionDAO)
			earthRegionDAO = null;
		if(null != earthQuakeDetailDAO)
			earthQuakeDetailDAO = null;
	}
	
	public void find(){
		List<EarthQuakeDetail> list = null;
		HashMap map = new HashMap();
		try{
			list = earthQuakeDetailDAO.find(map);
			System.out.println(" >> list.size:"+list.size());
			for(EarthQuakeDetail info:list){
				System.out.println(" >> i:"+info.getId()+"\tU:"+info.getEventId()+"\tmagnitude:"+info.getRegion());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
		}
	}
	
	@Test
	public void insertDetail(){
		List<EarthQuakeInfo> list =null;
		HashMap map = new HashMap();
		try{
				list = earthQuakeInfoDAO.find(map);
				int i=0;
				for(EarthQuakeInfo info:list){
					if(checkId(info.getId()) && null != info.getDetail()){
						continue;
					}
					EarthQuakeDetail detail = EatheQuakeParser.getEarthQuakeDetail(info.getUrl());
					if(null == detail.getEventId()){
						continue;
					}
					System.out.println("infoid:"+detail.getInfoid()+"\teventId:"+detail.getEventId()+"\tsource:"+detail.getSource()+"\tparameter:"+detail.getParameters());
					detail.setInfoid(info.getId());
					if(!chkEvent(detail.getEventId())){
						Integer result = earthQuakeDetailDAO.insert(detail);
						System.out.println(" >> 添加地震详情记录结果:"+result);
					}
					i++;
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
		}
	}
	
	private boolean getCount(String url){
		HashMap map = new HashMap();
		map.put("url",url);
		int count = earthQuakeInfoDAO.getCount(map);
		if(count > 0){
			System.out.println(" >> exists["+url+"]");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 检查是否存在相同记录
	 * @param eid
	 * @return
	 */
	private boolean chkEvent(String eid){
		if(null == eid || "".equals(eid)){
			return true;
		}
		HashMap map = new HashMap();
		map.put("eventId",eid);
		int count = earthQuakeDetailDAO.getCount(map);
		if(count > 0){
			System.out.println(" >> exists["+eid+"]");
			return true;
		}else{
			return false;
		}
	}
	
	boolean checkId(Integer infoid){
		if(null == infoid){
			return true;
		}
		HashMap map = new HashMap();
		map.put("id",infoid);
		int count = earthQuakeDetailDAO.getCount(map);
		if(count > 0){
			System.out.println(" >> exists infoid in detail["+infoid+"]");
			return true;
		}else{
			return false;
		}
	}
	
}
