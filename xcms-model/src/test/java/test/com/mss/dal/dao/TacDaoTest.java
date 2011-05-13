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

	public void waplog() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = IMEIParser.getCSV("wap19.csv");
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
//						System.out.println(" >> Hsman[Nokia],Hstype["+info.substring(start+1,info.length())+"]");
						tac.setHsmanName("Nokia");
						tac.setHstypeName(info.substring(start+1,info.length()));
					}
				}else if(info.startsWith("SonyEricsson")){
					int start = info.lastIndexOf("n");
					if(start > 0){
//						System.out.println(" >> Hsman[SonyEricsson],Hstype["+info.substring(start+1,info.length())+"]");
						tac.setHsmanName("SonyEricsson");
						tac.setHstypeName(info.substring(start+1,info.length()));
					}
				}else{
					String[] hs = info.split("-");
					int length = hs.length;
					if(length < 2){
						System.out.println(" >> info:"+info);
					}else{
						if(length == 2){
//							System.out.println("Hsman["+hs[0]+"],Hstype["+hs[1]+"]");
							tac.setHsmanName(hs[0]);
							tac.setHstypeName(hs[1]);
						}else if(length == 3){
//							System.out.println("length 3=Hsman["+hs[0]+"-"+hs[1]+"],Hstype["+hs[2]+"]");
							tac.setHsmanName(hs[0]+"-"+hs[1]);
							tac.setHstypeName(hs[2]);
						}else{
//							System.out.println(" >> info["+info+"].length"+length);
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
				/**
				**/
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
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = CvsFileParser.getCSV("imeishuju.csv");
			int offset = 0;
			int foffset = 0;
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
				String imei = s[1];
				try{
					ttac = imei.substring(0,8);
					tac.setTac(ttac);
				}catch(Exception e){
					System.err.println(" >> "+imei);
					continue;
				}
				
				common.put(phoneNum, s);
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac));
				if(null == tmp){
					failure.put(phoneNum, tmp);
					foffset ++;
				}else{
//					tList.add(tmp);
					ok.put(phoneNum,tmp);
					offset++;
				}
			}
			System.out.println(" >> 成功|offset："+offset);
			System.out.println(" >> 失败|foffset："+foffset);
			StringBuffer sb = new StringBuffer();
			System.out.println(" >> 识别的号码数量为:"+ok.size());
			System.out.println(" >> 未被识别的号码数量为:"+failure.size());
			System.out.println(" >> 总共的号码数量:"+common.size());
			
			
			int s = 0;
			Iterator it = ok.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				Object obj = ok.get(key) ;
//				System.out.println(" >> key:"+key);
				if( null != obj){
//					String[] t  = key.split("_");
					Tac value = (Tac)obj;
//					System.out.println(" >> 该手机号在成功终端中已经存在:"+key+"|"+value.getHsmanName()+"|"+value.getHsmanNameEn()+"|"+value.getHstypeName()+"|"+value.getHstypeNameEn());
//					System.out.println(" >> 该手机号在成功终端中已经存在:"+t[0]+"|"+t[1]+"|"+value.getHsmanName()+"|"+value.getHsmanNameEn()+"|"+value.getHstypeName()+"|"+value.getHstypeNameEn());
//					sb.append(t[0]).append(",").append(t[1]).append(",").append(t[1].substring(0,8)).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append("\r\n");
//					sb.append(key).append(",").append(value.getTac()).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append("\r\n");
//					t ++;
				}
			}
			System.out.println(" >> s:"+s);
			
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
//			System.out.println(" >> \r\n"+sb.toString());
//			IOUtil.createFile(sb.toString(), "TAC(8位)识别出的终端数据_2011-05-13.csv");
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
