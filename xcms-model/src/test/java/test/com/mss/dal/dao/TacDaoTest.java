package test.com.mss.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.*;
import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.annotation.AnnotationIocLoader;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;

import com.mss.dal.dao.TacDao;
import com.mss.dal.domain.Tac;
import com.mss.dal.domain.Terminal;
import com.mss.dal.view.ViewTerminal;
import com.ssi.common.utils.IOUtil;
import com.xc.tools.CvsFileParser;
import com.xc.tools.IMEIParser;
import com.xc.tools.IndexParser;

public class TacDaoTest {
	
	private TacDao dao;
	
	private Ioc2 ioc;
	
	static String[] INVALID = {"86017400","358228032733960"};
	
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
			System.err.println(e);
		}
		dao = ioc.get(TacDao.class,"tacDao");
	}

	public void find() {
		String tac = "01058001";
		int c = 0;
		IndexParser.init();
		HashMap<String, String[]> map = IndexParser.getTacMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			Tac t = dao.getByTac(key);
			if(null != t){
				System.out.println(" > hsmanName:"+t.getHsmanNameEn()+"|"+t.getHstypeNameEn()+"|"+t.getHsmanName());
			}else{
				String[] str = map.get(key);
				t = new Tac();
				t.setTac(key);
				t.setHsmanNameEn(str[1]);
				t.setHstypeNameEn(str[2]);
				System.out.println(" >> 添加数据:"+Json.toJson(t));
				t = dao.save(t);
				if(t.getId() > 0){
					c++;
					System.out.println(Json.toJson(t));
				}
			}
		}
		System.out.println(" >> c:"+c);
		
	}
	
	public void test() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = IMEIParser.getCSV("IMEI终端型号统计.csv");
			Tac tac = null;
			for(String[] s:list){
				tac = new Tac();
				tac.setTac(s[0]);
				tac.setHsmanName(s[1]);
				tac.setHstypeName(s[2]);
				
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", tac.getTac()));
				if(null == tmp){
					tac = dao.save(tac);
					System.out.println(" >> "+tac.getId());
				}else{
					System.out.println(" >> 已存在tac["+tmp.getTac()+"]");
				}
				break;
			}
		}else{
			System.out.println(" >> dao is null");
		}
	}

	public void test4() throws ClassNotFoundException{
		List<String[]> list = CvsFileParser.getCSV("employer2_with_imei.csv");
		
		
	}

	@Test
	public void test3() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = CvsFileParser.getCSV("employer2_with_imei.csv");
			int offset = 0;
			Tac tac = null;
			List<String> outL = new ArrayList<String>();
			List<Tac> tList = new ArrayList<Tac>();
			HashMap ok = new HashMap();
			HashMap failure = new HashMap();
			HashMap common = new HashMap();
			for(String[] s:list){
				Integer i=1;
				tac = new Tac();
				String phoneNum = s[0];
				String imei = s[1];
				String ttac = imei.substring(0,8);
				tac.setTac(ttac);
//				if(null != common.get(phoneNum)){
//					Integer c = (Integer)common.get(phoneNum);
//					c = c+1;
//					common.put(phoneNum, c);
//				}else{
//					common.put(phoneNum, i);
//				}
				
				common.put(phoneNum, s);
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac));
				if(null == tmp){
//					tmp = dao.findByCondition(Tac.class, Cnd.where("substring(d_tac,1,6)", "=", ttac.substring(0,6)));
//					tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac.substring(0,6)));
//					if(null == tmp){
						failure.put(phoneNum+"_"+imei, tmp);
//					}else{
//						ok.put(phoneNum+"_"+imei, tmp);
//						offset++;
//					}
				}else{
					tList.add(tmp);
					ok.put(phoneNum+"_"+imei, tmp);
					offset++;
				}
			}
			System.out.println(" >> list.size："+list.size());
			System.out.println(" >> offset："+offset);
//			System.err.println("未匹配到的数据如下:\r\n");
			StringBuffer sb = new StringBuffer();
//			for(String s:outL){
//				String[] s1 = s.split("_");
//				sb.append(s1[0]).append(",").append(s1[1]).append("\r\n");
//			}
//			System.out.println(sb.toString());
			System.out.println(" >> 识别的号码数量为:"+ok.size());
			System.out.println(" >> 未被识别的号码数量为:"+failure.size());
			System.out.println(" >> 总共的号码数量:"+common.size());
			
			
			/**
			for(Tac tmp:tList){
				sb.append(tmp.getHsmanName()).append(",").append(tmp.getHsmanNameEn()).append(",").append(tmp.getHstypeName()).append(",").append(tmp.getHstypeNameEn()).append("\r\n");
			}
			**/
			
//			Iterator it = ok.keySet().iterator();
//			while(it.hasNext()){
//				String key = (String)it.next();
//				Object obj = ok.get(key) ;
//				if( null != obj){
//					String[] t  = key.split("_");
//					Tac value = (Tac)obj;
//					System.out.println(" >> 该手机号在成功终端中已经存在:"+t[0]+"|"+t[1]+"|"+value.getHsmanName()+"|"+value.getHsmanNameEn()+"|"+value.getHstypeName()+"|"+value.getHstypeNameEn());
//					sb.append(t[0]).append(",").append(t[1]).append(",").append(t[1].substring(0,8)).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append("\r\n");
//				}
//			}
			
			Iterator it = failure.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
//				Object obj = failure.get(key) ;
//				if( null != obj){
					String[] t  = key.split("_");
//					Tac value = (Tac)obj;
//					System.out.println(" >> 该手机号在失败终端中已经存在:"+t[0]+"|"+t[1]+"|"+value.getHsmanName()+"|"+value.getHsmanNameEn()+"|"+value.getHstypeName()+"|"+value.getHstypeNameEn());
					sb.append(t[0]).append(",").append(t[1]).append(",").append(t[1].substring(0,8)).append("\r\n");
//					append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append("\r\n");
//				}
			}
			
//			System.out.println(sb.toString());
			/**
			common.clear();
			Iterator it = failure.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String[] value = (String[])failure.get(key);
//				System.out.println(" >> 未被识别的手机号码:"+key+"|"+value[0]+"|"+value[1]);
				sb.append(key).append(",").append(value[1]).append(",").append(value[1].substring(0,8)).append("\r\n");
			}
			HashMap bt = new HashMap();
			Iterator its = common.keySet().iterator();
			while(its.hasNext()){
				String key = (String)its.next();
				String[] value = (String[])common.get(key);
				sb.append(key).append(",").append(value[0]).append(",").append(value[1]).append("\r\n");
			}
			**/
			System.out.println(" >> \r\n"+sb.toString());
			IOUtil.createFile(sb.toString(), "TAC(8位)未识别出的终端数据.csv");
		}else{
			System.out.println(" >> dao is null");
		}
	}
	
	public void test2(){
		Cnd condition = Cnd.where("d_phone_num","=","15891064365");
		ViewTerminal v = dao.findByCondition(ViewTerminal.class, condition);
		System.out.println(" >> "+Json.toJson(v));
	}

}
