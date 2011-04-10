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

public class EarthQuakeInfoDAO {

	private IEarthQuakeInfoDAO earthQuakeInfoDAO;
	private IEarthQuakeDetailDAO earthQuakeDetailDAO;
	private IEarthRegionDAO earthRegionDAO;
	
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
		if(null != earthQuakeDetailDAO){
			earthQuakeDetailDAO = null;
		}
		if(null != earthRegionDAO)
			earthRegionDAO = null;
	}
	
	public void insert(){
		List<EarthQuakeInfo> list =null;
		try{
			list = EatheQuakeParser.getLatestEarthQuake();
			int i=0;
			for(EarthQuakeInfo info:list){
				if(info.getDate().equals("") || getCount(info.getUrl())){
					continue;
				}
				
				Integer result = earthQuakeInfoDAO.insert(info);
				System.out.println(" >> i:"+i+"\tU:"+info.getUrl()+"\tResult:"+result);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
		}
	}
	
	public void find(){
		List<EarthQuakeInfo> list = null;
		HashMap map = new HashMap();
		try{
			list = earthQuakeInfoDAO.find(map);
			System.out.println(" >> list.size:"+list.size());
			for(EarthQuakeInfo info:list){
				System.out.println(" >> i:"+info.getId()+"\tU:"+info.getUrl()+"\tmagnitude:"+info.getMagnitude());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
		}
	}
	
	@Test
	public void insertByRegion(){
		List<EarthQuakeInfo> list =null;
		List<EarthRegion> rlist = null;
		HashMap map = new HashMap();
		try{
			rlist = earthRegionDAO.find(map);
			for(EarthRegion region:rlist){
				System.out.println(" >> U:"+region.getUrl());
				list = EatheQuakeParser.getRegionRealTimeQuake(region.getUrl());
				int i=0;
				for(EarthQuakeInfo info:list){
					if(info.getDate().equals("") || getCount(info.getUrl())){
						continue;
					}
					
					Integer result = earthQuakeInfoDAO.insert(info);
					System.out.println(" >> i:"+i+"\tU:"+info.getUrl()+"\tResult:"+result);
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != list)
				list = null;
			if(null != rlist)
				rlist = null;
		}
	}
	
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
//			System.out.println(" >> exists["+url+"]");
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
