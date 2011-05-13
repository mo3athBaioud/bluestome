package test.com.mss.dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.junit.*;

import com.mss.dal.dao.CallingLogDao;
import com.mss.dal.domain.CallingLog;
import com.mss.dal.domain.Tac;
import com.xc.tools.CmDataParser;
import com.xc.tools.CvsFileParser;

public class CallingLogDaoTest {

	private Ioc ioc;
	
	private CallingLogDao cmdataDAO;
	
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
		cmdataDAO = ioc.get(CallingLogDao.class);
	}
	
	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != cmdataDAO)
			cmdataDAO = null;
	}
	
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != cmdataDAO)
			System.out.println(" cmdataDAO is not null");
		
	}
	
	public void insert(){
		List<CallingLog> list = new ArrayList<CallingLog>();
		CallingLog data = null;
		try{
			List<String[]> tl = CmDataParser.getCSV("移动数据.csv");
			int i = 0;
			for(String[] s:tl){
				if(i == 0){
					i++;
					continue;
				}
				data = new CallingLog();
				data.setImei(s[3]);
				data.setPhoneNum(s[2]);
				data.setTac(s[8]);
				data = cmdataDAO.save(data);
				if(data.getId() > 0){
					System.out.println("\t\t >> 添加成功!"+Json.toJson(data));
				}
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(" >> list.size:"+list.size());
		}
	}
	
	@Test
	public void test3() throws ClassNotFoundException{
		System.out.println(" >> dao is not null");
		List<String[]> list = CvsFileParser.getCSV("imeishuju3.csv");
//		List<String[]> list = CvsFileParser.getCSV("employer2_with_imei.csv");
		int offset = 0;
		CallingLog data = null;
		for(String[] s:list){
			String imei = s[1];
			
			data = new CallingLog();
			data.setImei(imei);
			data.setPhoneNum(s[0]);
			try{
				String tac = imei.substring(0,8);
				data.setTac(tac);
			}catch(Exception e){
				System.err.println(" >> imei:"+imei);
				continue;
			}
			
			System.out.println(Json.toJson(data));
			
			CallingLog tmp = cmdataDAO.findByCondition(CallingLog.class, Cnd.where("d_imei", "=", imei).and("d_phone_num", "=", data.getPhoneNum()));
			if(null == tmp){
				data = cmdataDAO.save(data);
				System.out.println(" >> 新增:"+data.getId());
			}else{
				System.out.println(" >> 已存在tac["+tmp.getTac()+"]");
				offset++;
			}
		}
		System.out.println(" >> list.size："+list.size());
		System.out.println(" >> offset："+offset);
	}
	
	
	public void createDate(){
		StringBuffer sb = new StringBuffer();
	}

	
}
