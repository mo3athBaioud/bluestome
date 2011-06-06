package test.com.mss.dal.dao;

import java.util.HashMap;
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
import com.ssi.common.utils.IOUtil;
import com.xc.tools.CvsFileParser;
import com.xc.tools.GoogleSearchTools;

public class TmpGprsDao2Test {

	private TmpGprsDao grpsDao;
	
	private Dao dao;
	
	private Ioc2 ioc;
	
	static HashMap map = new HashMap();
	
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
			map.put("99720470","GPRS 50元套餐");
			map.put("99720492","GPRS 3元套餐");
			map.put("99720467","GPRS 20元套餐");
			map.put("99720473","GPRS 5元套餐");
			map.put("99720479","GPRS 10元套餐");
			map.put("99720468","GPRS 100元套餐");
			map.put("99720469","GPRS 200元套餐");
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
		int gprs = 1;
		int ca = 5;
		List<String[]> list = CvsFileParser.getCSV("2011-06-05_weinan5.csv");
		System.out.println(" >> size:"+list.size());
		int ecount = 0;
		TmpGprs tmp = null;
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i5 = 0;
		
		//每日数据个数
		int d0530 = 0;
		int d0531 = 0;
		int d0601 = 0;
		int d0602 = 0;
		
		for(String[] s:list){
			String date = s[0];
			String uid = s[1];
			String phoneNumber = s[2];
			String imei = s[3];
			String hsman = s[5];
			String enhsman = s[6];
			String hstype = s[7];
			String enhstype = s[8];
			String bcode = s[10];
			if(hsman.equals("参照机型名称(英文)")){
				continue;
			}
			if(null != imei && !"".equals(imei)){
				tmp = new TmpGprs();
				tmp.setImei(imei);
				if(uid.equals("E0E1")){
					uid = "weinan1";
				}
				tmp.setUid(uid);
				tmp.setStatus(1);
				try{
					String tac = imei.substring(0,8);
					tmp.setTac(tac);
				}catch(Exception e){
					System.err.println(e);
					continue;
				}
				if(hsman.equals("null")){
					tmp.setHsmanName(enhsman);
				}else{
					tmp.setHsmanName(hsman);
				}
				
				if(hstype.equals("null")){
					tmp.setHstypeName(enhstype);
				}else{
					tmp.setHstypeName(hstype);
				}
				
				tmp.setGprs(gprs);
				tmp.setDate(date);
				tmp.setBcode(bcode);
				if(date.equals("2011/5/30")){
					if(d0530 < ca){
						tmp.setPhoneNumber(phoneNumber);
						tmp.setRecommdation(1);
						int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_imei", "=", tmp.getImei()).and("d_phone_number", "=", tmp.getPhoneNumber()));
						if(count == 0){
							tmp = grpsDao.save(tmp);
						}else{
							System.err.println( " exists tmpgprs:" + Json.toJson(tmp));
						}
						d0530++;
					}else{
						continue;
					}
				}
				
				if(date.equals("2011/5/31")){
					if(d0531 < ca){
						tmp.setPhoneNumber(phoneNumber);
						tmp.setRecommdation(1);
						int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_imei", "=", tmp.getImei()).and("d_phone_number", "=", tmp.getPhoneNumber()));
						if(count == 0){
							tmp = grpsDao.save(tmp);
						}else{
							System.err.println( " exists tmpgprs:" + Json.toJson(tmp));
						}
						d0531++;
					}else{
						continue;
					}
				}
				
				if(date.equals("2011/6/1")){
					if(d0601 < ca){
						tmp.setPhoneNumber(phoneNumber);
						tmp.setRecommdation(1);
						int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_imei", "=", tmp.getImei()).and("d_phone_number", "=", tmp.getPhoneNumber()));
						if(count == 0){
							tmp = grpsDao.save(tmp);
						}else{
							System.err.println( " exists tmpgprs:" + Json.toJson(tmp));
						}
						d0601++;
					}else{
						continue;
					}
				}
				
				if(date.equals("2011/6/2")){
					if(d0602 < ca){
						tmp.setPhoneNumber(phoneNumber);
						tmp.setRecommdation(1);
						int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_imei", "=", tmp.getImei()).and("d_phone_number", "=", tmp.getPhoneNumber()));
						if(count == 0){
							tmp = grpsDao.save(tmp);
						}else{
							System.err.println( " exists tmpgprs:" + Json.toJson(tmp));
						}
						d0602++;
					}else{
						continue;
					}
				}
				
				// 50
				if(uid.equals("weinan3")){
					i3 ++;
				}
				
				// 50 
				if(uid.equals("weinan5")){
					i5 ++;
				}
				
				// 100
				if(uid.equals("E0E1")){
					i1++;
				}
				
				/**
				**/
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" >> d0530:["+d0530+"],\td0531:["+d0531+"],\td0601:["+d0601+"],\td0602:["+d0602+"]");
		System.out.println(" >> i1:["+i1+"],\ti3:["+i3+"],\ti5:["+i5+"]");
		System.out.println(" >> 不存在"+ecount+"个相同号码和终端的数据");
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
					t.setStatus(1);
					if(grpsDao.update(t)){
						System.out.println(" >> 更新成功!");
					}
				}
			}
		}
	}
	
	@Test
	public void find(){
		StringBuffer sb = new StringBuffer();
		List<TmpGprs> list = grpsDao.search(TmpGprs.class,Cnd.where("d_status", "<>", "0").orderBy().asc("d_uid,d_date,d_phone_number,d_recommdation"));
		int uc = 0;
		int rc = 0;
		for(TmpGprs t:list){
//			if(t.getId() < 2368){
//				sb.append("weinan1").append(",");
//			}else if(t.getId() > 2367 && t.getId() < 4736){
//				sb.append("weinan2").append(",");
//			}else if(t.getId() > 4735 && t.getId() < 7103){
//				sb.append("weinan3").append(",");
//			}else if(t.getId() > 7102){
//				sb.append("weinan5").append(",");
//			}
			if(t.getUid().equals("weinan1")){
				uc++;
				if(t.getRecommdation() == 1){
					rc ++;
				}
			}
			if(uc < 2367 && t.getUid().equals("weinan1")){
				sb.append(t.getUid()).append(",");
			}else if(t.getUid().equals("weinan1")){
				sb.append("weinan2").append(",");
			}else{
				sb.append(t.getUid()).append(",");
			}
			
			sb.append(t.getPhoneNumber()).append(",").append(t.getHsmanName()).append(",").append(t.getHstypeName()).append(",");
			if(t.getGprs() == 1){
				sb.append("支持GPRS");
			}else{
				sb.append("不支持GPRS");
			}
			sb.append(",");
			if(t.getRecommdation() == 1){
				sb.append("已推荐");
			}else{
				sb.append("未推荐");
			}
			if(null != t.getBcode() && !t.getBcode().equals("") && !t.getBcode().equals("000000")){
				sb.append(",").append(t.getBcode()).append(",").append((String)map.get(t.getBcode()));
			}
			if(null != t.getDate() && !t.getDate().equals("null")){
				sb.append(",").append(t.getDate()).append(",");
			}
			sb.append("\r\n");
		}
		IOUtil.createFile(sb.toString(), "2011-06-05_工号数据_05.csv");
//		IOUtil.createFile(sb.toString());
//		System.out.println(sb.toString());
	}
	
	public void doGetGprs(){
		int size = 200;
		int count = grpsDao.searchCount(TmpGprs.class,Cnd.where("d_status", "=", "1").and("d_hsman_name","!=","参照机型名称(英文)"));
		System.out.println(" >> count:"+count);
//		if(1 == 1){
//			return;
//		}
		int pn = (count / size)+1;
		System.out.println(""+pn);
		for(int i=1;i<pn+1;i++){
			List<TmpGprs> list = dao.query(TmpGprs.class, Cnd.where("d_status", "=", "1").and("d_hsman_name","!=","参照机型名称(英文)"), dao.createPager(i, size));
			System.out.println(" >> ["+i+"] list.size:"+list.size());
			for(TmpGprs t:list){
				try {
					boolean b = GoogleSearchTools.process(t.getHsmanName(), t.getHstypeName());
					if(b){
						t.setGprs(1);
						t.setStatus(2);
						if(grpsDao.update(t)){
//							List<TmpGprs> tl = dao.query(TmpGprs.class, Cnd.where("d_hsman_name", "=", t.getHsmanName()).and("d_hstype_name","=",t.getHstypeName()).and("d_status","=","1"), dao.createPager(1, 10000));
//							for(TmpGprs s:tl){
//								s.setStatus(2);
//								s.setGprs(1);
//								if(grpsDao.update(s)){
//									System.out.println(" >> id ["+s.getId()+"] "+s.getGprs()+"|"+s.getStatus());
//								}
//							}
							System.out.println(" >> success ["+t.getId()+"] "+t.getGprs()+"|"+t.getStatus());
						}
					}else{
//						List<TmpGprs> tl = dao.query(TmpGprs.class, Cnd.where("d_hsman_name", "=", t.getHsmanName()).and("d_hstype_name","=",t.getHstypeName()).and("d_status","=","1"), dao.createPager(1, 10000));
//						for(TmpGprs s:tl){
//							s.setStatus(3);
//							s.setGprs(0);
//							if(grpsDao.update(s)){
//								System.out.println(" >> id ["+s.getId()+"] "+s.getGprs()+"|"+s.getStatus());
//							}
//						}
//						System.out.println(" >> success ["+t.getId()+"] "+t.getGprs()+"|"+t.getStatus());
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
