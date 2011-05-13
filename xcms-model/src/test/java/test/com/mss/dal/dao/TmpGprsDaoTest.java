package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;

import com.mss.dal.dao.TmpGprsDao;
import com.mss.dal.domain.Tac;
import com.mss.dal.domain.TmpGprs;
import com.xc.tools.CvsFileParser;
import com.xc.tools.GoogleSearchTools;

public class TmpGprsDaoTest {

	private TmpGprsDao grpsDao;
	
	private Dao dao;
	
	private Ioc2 ioc;
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
                "*org.nutz.ioc.loader.json.JsonLoader",
                "conf/datasource.json", 
                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
                "com.mss.dal"
                ));
			
			grpsDao = ioc.get(TmpGprsDao.class);
			dao = ioc.get(Dao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@After
	public void destory(){
		if(null != grpsDao)
			grpsDao = null;
	}
	
	public void asset(){
		if(null != grpsDao){
			System.out.println("grpsDao is not null");
		}
	}
	
	public void insert(){
		List<String[]> list = CvsFileParser.getCSV("input_2011-05-14.csv");
		TmpGprs tmp = null; 
		for(String[] s:list){
			String phoneNumber = s[0];
			String imei = s[1];
			if(null != imei && !"".equals(imei)){
				tmp = new TmpGprs();
				tmp.setImei(imei);
				try{
				String tac = imei.substring(0,8);
				tmp.setTac(tac);
				}catch(Exception e){
					System.err.println(e);
					continue;
				}
				tmp.setPhoneNumber(phoneNumber);
				int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_imei", "=", tmp.getImei()).and("d_phone_number", "=", tmp.getPhoneNumber()));
				if(count == 0){
					tmp = grpsDao.save(tmp);
				}else{
					System.err.println( " exists tmpgprs:" + Json.toJson(tmp));
				}
			}
		}
	}
	
	public void findHs(){
		int size = 2000;
//		int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_hsman_name", "=", "0").and("d_hstype_name", "=", "0").and("d_status", "=", "0"));
		int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_status", "=", "0"));
		
		System.out.println(" >> count:"+count);
		int pn = (count / 2000)+1;
		System.out.println(""+pn);
		for(int i=1;i<pn+1;i++){
			List<TmpGprs> list = dao.query(TmpGprs.class, Cnd.orderBy().asc("d_id"), dao.createPager(i, size));
			for(TmpGprs t:list){
				Tac tmp = dao.fetch(Tac.class, Cnd.where("d_tac", "=", t.getTac()));
				if(null != tmp){
					if(null != tmp.getHsmanName()){
						t.setHsmanName(tmp.getHsmanName());
					}else{
						t.setHsmanName(tmp.getHsmanNameEn());
					}
					
					if(null != tmp.getHstypeName()){
						t.setHstypeName(tmp.getHstypeName());
					}else{
						t.setHstypeName(tmp.getHstypeNameEn());
					}
					tmp.setStatus(1);
					if(grpsDao.update(t)){
						System.out.println(" >> 更新成功!");
					}
				}
			}
		}
	}
	
	@Test
	public void doGetGprs(){
		int size = 2000;
		int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_status", "=", "1"));
		System.out.println(" >> count:"+count);
		int pn = (count / 2000)+1;
		System.out.println(""+pn);
		for(int i=1;i<pn+1;i++){
			List<TmpGprs> list = dao.query(TmpGprs.class, Cnd.where("d_status", "=", "1"), dao.createPager(i, size));
			for(TmpGprs t:list){
				try {
					boolean b = GoogleSearchTools.process(t.getHsmanName(), t.getHstypeName());
					if(b){
						t.setGprs(1);
						t.setStatus(2);
						if(grpsDao.update(t)){
							List<TmpGprs> tl = dao.query(TmpGprs.class, Cnd.where("d_hsman_name", "=", t.getHsmanName()).and("d_hstype_name","=",t.getHstypeName()).and("d_status","=","1"), dao.createPager(1, 10000));
							for(TmpGprs s:tl){
								s.setStatus(2);
								s.setGprs(1);
								if(grpsDao.update(s)){
									System.out.println(" >> id ["+s.getId()+"] "+s.getGprs()+"|"+s.getStatus());
								}
							}
							System.out.println(" >> success ["+t.getId()+"] "+t.getGprs()+"|"+t.getStatus());
						}
					}
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e1) {
						System.err.println(e1);
					}
				}
			}
		}
	}
}
