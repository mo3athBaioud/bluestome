package test.com.mss.dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.junit.*;

import com.mss.dal.dao.CmdataDao;
import com.mss.dal.domain.Cmdata;
import com.xc.tools.CmDataParser;

public class CmdataDaoTest {

	private Ioc ioc;
	
	private CmdataDao cmdataDAO;
	
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
		cmdataDAO = ioc.get(CmdataDao.class);
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
	
	@Test
	public void insert(){
		List<Cmdata> list = new ArrayList<Cmdata>();
		Cmdata data = null;
		try{
			List<String[]> tl = CmDataParser.getCSV("移动数据.csv");
			int i = 0;
			for(String[] s:tl){
				if(i == 0){
					i++;
					continue;
				}
				data = new Cmdata();
				data.setAcode(s[4]);
				data.setCallDuring(Integer.valueOf(s[7]));
				data.setCallTimes(Integer.valueOf(s[5]));
				data.setFeeTimes(Integer.valueOf(s[6]));
				data.setImei(s[3]);
				data.setPhoneNum(s[2]);
				data.setTac(s[8]);
				data.setUcode(s[1]);
//				list.add(data);
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
}
