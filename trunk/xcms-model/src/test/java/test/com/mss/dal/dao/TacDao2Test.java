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
import com.xc.tools.GoogleSearchTools;
import com.xc.tools.IMEIParser;
import com.xc.tools.IndexParser;

public class TacDao2Test {
	
	private TacDao dao;
	
	private Ioc2 ioc;
	
	static String[] INVALID = {"86017400","358228032733960"};
	
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
		}catch(Exception e){
			System.err.println(e);
		}
		dao = ioc.get(TacDao.class,"tacDao");
		map.put("99720470","GPRS 50元套餐");
		map.put("99720492","GPRS 3元套餐");
		map.put("99720467","GPRS 20元套餐");
		map.put("99720473","GPRS 5元套餐");
		map.put("99720479","GPRS 10元套餐");
		map.put("99720468","GPRS 100元套餐");
		map.put("99720469","GPRS 200元套餐");
		map.put("99720478","GPRS 1元套餐");
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
		System.out.println(" >> tac.size:"+map.size());
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
	
	public void waplogPatch() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<Tac> tlist = dao.search(Tac.class, Cnd.orderBy().asc("d_id"));
			int fo = 0;
			int offset  = 0;
			for(Tac s:tlist){
				if(!s.getHsmanName().equals("参照机型名称(英文)")){
					continue;
				}
				if(null == s.getHstypeName() || s.getHstypeName().equals("")){
					continue;
				}
				String info = s.getHstypeName().split("/")[0];
				info = info.replace("\"", "").replace("_CMCC", "").trim();
				if(info.startsWith("Nokia")){
					int start = info.lastIndexOf("a");
					if(start > 0){
						s.setHsmanName("Nokia");
						s.setHsmanNameEn("Nokia");
						s.setHstypeName(info.substring(start+1,info.length()));
						s.setHstypeNameEn(info.substring(start+1,info.length()));
					}
				}else if(info.startsWith("SonyEricsson")){
					int start = info.lastIndexOf("n");
					if(start > 0){
						s.setHsmanName("SonyEricsson");
						s.setHsmanNameEn("SonyEricsson");
						s.setHstypeName(info.substring(start+1,info.length()));
						s.setHstypeNameEn(info.substring(start+1,info.length()));
					}
				}else{
					String[] hs = info.split("-");
					int length = hs.length;
					if(length < 2){
						System.out.println(" >> info:"+info);
					}else{
						if(length == 2){
							s.setHsmanName(hs[0]);
							s.setHsmanNameEn(hs[0]);
							s.setHstypeName(hs[1]);
							s.setHstypeNameEn(hs[1]);
						}else if(length == 3){
							s.setHsmanName(hs[0]+"-"+hs[1]);
							s.setHsmanNameEn(hs[0]+"-"+hs[1]);
							s.setHstypeName(hs[2]);
							s.setHstypeNameEn(hs[2]);
						}else{
							fo++;
						}
					}
					
				}
				
				if(null == s.getHsmanName() || "".equals(s.getHsmanName())){
					continue;
				}
				if(null == s.getHstypeName() || "".equals(s.getHstypeName())){
					continue;
				}
				if(dao.update(s)){
					offset++;
				}
//				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", tac.getTac()));
//				if(null == tmp){
//					tac = dao.save(tac);
//					offset++;
//				}
			}
			System.out.println(" >> fo:"+fo);
			System.out.println(" >> offset:"+offset);
		}else{
			System.out.println(" >> dao is null");
		}
	}

	public void waplog() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = IMEIParser.getCSV("wap19.csv");
			List<Tac> tlist = dao.search(Tac.class, Cnd.orderBy().asc("d_id"));
			Tac tac = null;
			int fo = 0;
			int offset  = 0;
			for(String[] s:list){
				tac = new Tac();
				try{
					if(null == s[2] || "".equals(s[2])){
						continue;
					}
				}catch(Exception e){
					continue;
				}
				tac.setTac(s[2]);
				String info = s[3].split("/")[0];
				info = info.replace("\"", "").replace("_CMCC", "").trim();
				if(info.startsWith("Nokia")){
					int start = info.lastIndexOf("a");
					if(start > 0){
						tac.setHsmanName("Nokia");
						tac.setHstypeName(info.substring(start+1,info.length()));
					}
				}else if(info.startsWith("SonyEricsson")){
					int start = info.lastIndexOf("n");
					if(start > 0){
						tac.setHsmanName("SonyEricsson");
						tac.setHstypeName(info.substring(start+1,info.length()));
					}
				}else if(info.startsWith("OPPO")){
					
				}else{
					String[] hs = info.split("-");
					int length = hs.length;
					if(length < 2){
						System.out.println(" >> info:"+info);
					}else{
						if(length == 2){
							tac.setHsmanName(hs[0]);
							tac.setHstypeName(hs[1]);
						}else if(length == 3){
							tac.setHsmanName(hs[0]+"-"+hs[1]);
							tac.setHstypeName(hs[2]);
						}else{
							fo++;
						}
					}
					
				}
				
				if(null == tac.getHsmanName() || "".equals(tac.getHsmanName())){
					continue;
				}
				if(null == tac.getHstypeName() || "".equals(tac.getHstypeName())){
					continue;
				}
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", tac.getTac()));
				if(null == tmp){
					tac = dao.save(tac);
//					System.out.println(" >> "+tac.getId());
					offset++;
				}else{
//					System.out.println(" >> 已存在tac["+tmp.getTac()+"]");
				}
			}
			System.out.println(" >> fo:"+fo);
			System.out.println(" >> offset:"+offset);
		}else{
			System.out.println(" >> dao is null");
		}
	}
	
	public void test4() throws ClassNotFoundException{
		List<String[]> list = CvsFileParser.getCSV("employer2_with_imei.csv");
		
		
	}

	@Test
	public void test3() throws ClassNotFoundException{
		String taget = "e0e1";
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = CvsFileParser.getCSV("2011-06-03_GPRS.csv");
			int offset = 0;
			int foffset = 0;
			int offset5 = 0;
			int lwoffset = 0;
			int dloffset = 0;
			int pcoffset = 0;
			Tac tac = null;
			List<String> outL = new ArrayList<String>();
			List<Tac> tList = new ArrayList<Tac>();
			HashMap ok = new HashMap();
			HashMap failure = new HashMap();
			HashMap common = new HashMap();
			String ttac = "";
			for(String[] s:list){
				Integer i=1;
				tac = new Tac();
				String phoneNum = s[0];
				String payCode = s[1];
				String uid = s[2];
				String date = s[3];
				String imei = s[4];
				try{
					ttac = imei.substring(0,8);
					tac.setTac(ttac);
				}catch(Exception e){
					System.err.println(" >> "+imei);
					continue;
				}
				
//				common.put(phoneNum, s);
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac));
				if(null == tmp){
//					failure.put(phoneNum+"_"+imei, tmp);
					foffset ++;
				}else{
					//5元套餐以上的(含5元)
//					if(!payCode.equals("99720478") && !payCode.equals("99720492")){
						//临渭区
//						if(uid.toLowerCase().equals(taget)){
//							lwoffset++;
							ok.put(phoneNum+"_"+imei+"_"+uid+"_"+payCode+"_"+date,tmp);
//						}
						//大荔
//						if(uid.toLowerCase().equals("weinan5")){
//							dloffset++;
//						}
						//蒲城
//						if(uid.toLowerCase().equals("weinan3")){
//							pcoffset++;
//						}
//						offset++;
//					}else{
//						offset5++;
//					}
				}
			}
			System.out.println(" >> 临渭区成功|offset："+lwoffset);
			System.out.println(" >> 大荔成功|offset："+dloffset);
			System.out.println(" >> 蒲城成功|offset："+pcoffset);
			System.out.println("\r\n");
			System.out.println(" >> 成功|offset："+offset);
			System.out.println(" >> 失败|foffset："+foffset);
			System.out.println(" >> 5元以下数据套餐数量:"+offset5);
			StringBuffer sb = new StringBuffer();
			System.out.println(" >> 识别的号码数量为:"+ok.size());
			System.out.println(" >> 未被识别的号码数量为:"+failure.size());
			System.out.println(" >> 总共的号码数量:"+common.size());
			
			
			int s = 0;
			Iterator it = ok.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				Object obj = ok.get(key) ;
				if( null != obj){
					String[] t  = key.split("_");
					Tac value = (Tac)obj;
					sb.append(t[4]).append(",").append(t[2]).append(",").append(t[0]).append(",").append(t[1]).append(",").append(t[1].substring(0,8)).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append(",").append(1).append(",").append(t[3]).append(",").append((String)map.get(t[3])).append("\r\n");
				}
			}
			System.out.println(" >> s:"+s);
			
			IOUtil.createFile(sb.toString(), "2011-06-05_ALL.csv");
//			IOUtil.createFile(sb.toString(), "2011-06-05_"+taget+".csv");
		}else{
			System.out.println(" >> dao is null");
		}
	}
	
	public void t20110514() throws Exception{
		List<String[]> list = CvsFileParser.getCSV("input_2011-05-14.csv");
		StringBuffer sb = new StringBuffer();
		int i = 0;
		HashMap  map = new HashMap();
		for(String[] s:list){
			sb.append(s[0]).append(",").append(s[1]).append(",").append(s[2]).append(",").append(s[3]).append(",").append(s[4]).append(",").append(s[5]).append(",").append(s[6]).append(",");
			String tac = s[2];
			if(null != tac && !"".equals(tac)){
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", tac));
				try{
					if(null != tmp.getHsmanName()){
						map.put(tmp.getHstypeName(), tmp);
					}else{
						map.put(tmp.getHstypeNameEn(), tmp);
					}
//						System.out.println(" >> "+tmp.getHsmanName()+"|"+tmp.getHstypeName());
//						if(GoogleSearchTools.process(tmp.getHsmanName(), tmp.getHstypeName())){
//							System.out.println(" >> 支持GPRS");
//							sb.append("支持");
//							i++;
//						}else{
//							sb.append("不支持");
//						}
//					}else{
//						System.out.println(" >> "+tmp.getHsmanNameEn()+"|"+tmp.getHstypeNameEn());
//						if(GoogleSearchTools.process(tmp.getHsmanNameEn(), tmp.getHstypeNameEn())){
//							System.out.println(">> 支持GPRS");
//							sb.append("支持");
//							i++;
//						}else{
//							sb.append("不支持");
//						}
//					}
				}catch(Exception e){
					sb.append("不支持");
				}
//				Thread.sleep(50);
			}
//			sb.append("\r\n");
		}
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			Object obj = map.get(key) ;
//			System.out.println(" >> key:"+key);
			if( null != obj){
				Tac value = (Tac)obj;
				System.out.println(" >> 该手机号在成功终端中已经存在:"+key+"|"+value.getHsmanName()+"|"+value.getHsmanNameEn()+"|"+value.getHstypeName()+"|"+value.getHstypeNameEn());
			}
		}
//		System.out.println(sb.toString());
//		IOUtil.createFile(sb.toString(), "2011-05-13-1052.csv");
		System.out.println(" >> 机型数量:"+map.size());
		
		System.gc();
	}
	
	public void test2(){
		Cnd condition = Cnd.where("d_phone_num","=","15891064365");
		ViewTerminal v = dao.findByCondition(ViewTerminal.class, condition);
		System.out.println(" >> "+Json.toJson(v));
	}

}
