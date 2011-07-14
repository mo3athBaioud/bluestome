package test.com.mss.dal.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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

public class TacDaoTest {
	
	private TacDao dao;
	
	private Ioc2 ioc;
	
	static String[] INVALID = {"86017400","358228032733960"};
	
	private static Integer BCOUNT = 0;
	
	static StringBuffer SB_SQL = new StringBuffer();
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
	            "*org.nutz.ioc.loader.json.JsonLoader",
	            "conf/jdbc.js", 
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
			String[] str = map.get(key);
			if(null != t){
				t.setTac(key);
				t.setHsmanNameEn(str[1]);
				t.setHsmanName(str[1]);
				t.setHstypeNameEn(str[2]);
				t.setHstypeName(str[2]);
				t.setModifytime(new Date());
				if(dao.update(t)){
					System.out.println(" >> Update Success!");
				}
			}else{
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

	public void test3(String fileName) throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
//			List<String[]> list = CvsFileParser.getCSV("tac.csv");
			List<String[]> list = CvsFileParser.getCSV(fileName, 1000000);
			int offset = 0;
			int foffset = 0;
			Tac tac = null;
//			List<String> outL = new ArrayList<String>();
//			List<Tac> tList = new ArrayList<Tac>();
			HashMap ok = new HashMap();
			HashMap failure = new HashMap();
			HashMap common = new HashMap();
			String ttac = "";
			for(String[] s:list){
//				Integer i=1;
				tac = new Tac();
//				ttac = s[0];
				String phoneNum = s[0];
//				String uid = s[2];
				String imei = s[1];
				String bd = s[2];
//				try{
//					ttac = imei.substring(0,8);
//					tac.setTac(ttac);
//				}catch(Exception e){
//					System.err.println(" >> "+imei);
//					continue;
//				}
				
//				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac));
//				if(null == tmp){
////					failure.put(phoneNum+"_"+imei, tmp);
//					foffset ++;
//				}else{
//					
////					tList.add(tmp);
//					ok.put(ttac,tmp);
////					if(null != tmp.getHsmanName()){
////						ok.put(tmp.getHsmanName()+"_"+tmp.getHstypeName(), tmp);
////					}else{
////						ok.put(tmp.getHsmanNameEn()+"_"+tmp.getHstypeNameEn(), tmp);
////					}
//					offset++;
					BCOUNT++;
//				}
				SB_SQL.append("insert into tbl_cmdata (d_phonenum,d_imei,d_bistrict) values (\'"+phoneNum+"\',\'"+imei+"\',\'"+bd+"\')");
			}
//			System.out.println(" >> 成功|offset："+offset);
//			System.out.println(" >> 失败|foffset："+foffset);
//			StringBuffer sb = new StringBuffer();
//			System.out.println(" >> 识别的号码数量为:"+ok.size());
//			System.out.println(" >> 未被识别的号码数量为:"+failure.size());
//			System.out.println(" >> 总共的号码数量:"+common.size());
			
			
//			int s = 0;
//			Iterator it = ok.keySet().iterator();
//			HashMap<String,String> HSMAN_MAP = new HashMap<String,String>();
//			while(it.hasNext()){
//				String key = (String)it.next();
//				Object obj = ok.get(key) ;
//				if( null != obj){
//					String[] t  = key.split("_");
//					Tac value = (Tac)obj;
//					sb.append(t[0]).append(",").append(t[1]).append(",").append(t[1]).append(",").append(t[1].substring(0,8)).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append(",").append(1).append("\r\n");
//					sb.append(key).append(",").append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append(",").append(1).append("\r\n");
//					sb.append(value.getHsmanName()).append(",").append(value.getHsmanNameEn()).append(",").append(value.getHstypeName()).append(",").append(value.getHstypeNameEn()).append("\r\n");
//					if(null != value.getHsmanName() && !"".equals(value.getHsmanName())){
//						HSMAN_MAP.put(value.getHsmanName(), value.getHsmanName());
//					}else if(null != value.getHsmanNameEn() && !"".equals(value.getHsmanNameEn())){
//						HSMAN_MAP.put(value.getHsmanNameEn(), value.getHsmanNameEn());
//					}
//					s++;
//				}
//			}
			/**
				sb.append("\r\n"+s);
				IOUtil.createFile(sb.toString(), "2011-07-11_已识别的终端数据_"+"_OK"+System.currentTimeMillis());
			**/
//			Iterator its = HSMAN_MAP.keySet().iterator();
//			while(its.hasNext()){
//				String value = (String)its.next();
//				sb.append(value).append("\r\n");
//			}
//			IOUtil.createFile(sb.toString(), "厂商数据"+"_OK"+System.currentTimeMillis());
//			BCOUNT += offset;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public String getUserSQL(String filePath){
		StringBuffer sb = new StringBuffer();
		try{
			List<String[]> list = CvsFileParser.getCSV(filePath, 100000);
			int i=0;
			for(String[] s:list){
				if(i%5000 == 0){
					sb.append("LOCK TABLES tbl_cmdata WRITE;\r\n");
					sb.append("insert into tbl_cmdata (d_phonenum,d_imei,d_bistrict) values \r\n");
				}
				String phoneNum = s[0];
				String imei = s[1];
				String bd = s[2];
				sb.append("(\'"+phoneNum+"\',\'"+imei+"\',\'"+bd+"\')");
				i++;
				if(i%5000 == 0){
					sb.append(";").append("\r\n");
					sb.append("UNLOCK TABLES;");
				}else{
					if(i<list.size()){
						sb.append(",").append("\r\n");
					}
				}
			}
			sb.append(";").append("\r\n");
			sb.append("UNLOCK TABLES;");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 组装终端核心SQL
	 * @param filePath
	 * @return
	 */
	public HashMap<String,String> getTerminalCoreSQL(String filePath){
		HashMap<String,String> map = new HashMap<String,String>();
		try{
			List<String[]> list = CvsFileParser.getCSV(filePath);
//			sb.append("insert into tbl_terminal_core (d_hsman_ch,d_hsman,d_hstype_ch,d_hstype,d_tac,d_gprs,d_wap,d_smartphone,d_os,d_wifi,d_createtime) values ");
			for(String[] s:list){
				StringBuffer sb = new StringBuffer();
				String tac = s[0];
				String hsman = s[1];
				String hsmanen = s[2];
				String hstype = s[3];
				String hstypeen = s[4];
				String gprs = s[5];
				String wap = s[6];
				String smartphone = s[7];
				String os = s[8];
				String wifi = s[9];
				
				sb.append("(\'"+hsman+"\',").append("\'"+hsmanen+"\',").append("\'"+hstype+"\',").append("\'"+hstypeen+"\',").append("\'"+tac+"\',").append(gprs).append(",");
				sb.append(wap).append(",").append(smartphone).append(",").append(os).append(",").append(wifi).append(",").append("now()),");
				sb.append("\r\n");
				map.put(tac, sb.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	public void generatorTerminalCore(){
		StringBuffer sb = new StringBuffer();
		sb.append("LOCK TABLES `tbl_terminal_core` WRITE;\r\n");
		sb.append("insert into tbl_terminal_core (d_hsman_ch,d_hsman,d_hstype_ch,d_hstype,d_tac,d_gprs,d_wap,d_smartphone,d_os,d_wifi,d_createtime) values \r\n");
		HashMap<String,String> map = getTerminalCoreSQL("终端核心表_02.csv");
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			String sql = map.get(key);
			sb.append(sql);
		}
		sb.append("UNLOCK TABLES;");
//		System.out.println(sb.toString());
		IOUtil.createFile(sb.toString(), "SQL_终端核心表_03"+System.currentTimeMillis());
	}
	
	@Test
	public void generatorFile(){
		File file = null;
		try{
			file = new File("F:\\tmp\\split\\20110714\\");
			if(file.isDirectory()){
				File[] subs = file.listFiles();
				int i=0;
				for(File sub:subs){
					String sql = getTerminalSQL(sub.getAbsolutePath());
//					System.out.println(sql);
					System.out.println(" > "+i+" 已生成!");
					IOUtil.createFile(sql, "F:\\tmp\\split\\20110714\\result\\","CTA_"+i);
					System.gc();
					Thread.sleep(100);
					i++;
				}
			}
			System.out.println(" >> 识别的总数:"+BCOUNT);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void updateTacTerminal(){
		/**
			List<String[]> list = CvsFileParser.getCSV("厂商数据_01.csv");
			for(String[] ss:list){
				if(ss.length < 3){
					System.out.println(" >> "+ss[0]+"|"+ss[1]);
				}
			}
		 **/
			Cnd condition = Cnd.where("d_status","=","1");
			List<Tac> tlist = dao.search(Tac.class, condition.orderBy().asc("d_hsman_name"));
			for(Tac t:tlist){
				List<String[]> list = CvsFileParser.getCSV("厂商数据_01.csv");
				for(String[] str:list){
					if(t.getHsmanName().toLowerCase().equals(str[0].toLowerCase())){
						if(!"0".equals(str[1])){
							t.setHsmanNameEn(str[1].toLowerCase());
						}
						if(!"0".equals(str[2])){
							t.setHsmanName(str[2].toLowerCase());
						}
						
						if(dao.update(t)){
							System.out.println(" >> 更新成功["+t.getId()+"|"+t.getTac()+"|"+t.getHsmanName()+"|"+t.getHsmanNameEn()+"]");
							break;
						}
					}
				}
		}
		
	}
	
	
	/**
	 * 已匹配的终端数据和终端属性数据
	 * @param filePath
	 * @return
	 */
	public String getKnownTerminalSQL(String filePath){
		StringBuffer sb = new StringBuffer();
		try{
			List<String[]> list = CvsFileParser.getCSV(filePath,100001);
			int i=0;
			for(String[] s:list){
				if(i%5000 == 0){
					sb.append("LOCK TABLES tbl_tmp_08 WRITE;\r\n");
					sb.append("insert into tbl_tmp_08 (d_phonenum,d_tac,d_bistrict) values \r\n");
				}
				String phoneNum = s[0];
				String imei = s[2];
				String bd = s[1];
				sb.append("(\'"+phoneNum+"\',\'"+imei+"\',\'"+bd+"\')");
				i++;
				if(i%5000 == 0){
					sb.append(";").append("\r\n");
					sb.append("UNLOCK TABLES;");
				}else{
					if(i<list.size()){
						sb.append(",").append("\r\n");
					}
				}
			}
			sb.append(";").append("\r\n");
			sb.append("UNLOCK TABLES;");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	/**
	 * 最终终端数据
	 * @param filePath
	 * @return
	 */
	public String getTerminalSQL(String filePath){
		StringBuffer sb = new StringBuffer();
		try{
			List<String[]> list = CvsFileParser.getCSV(filePath,100001);
			int i=0;
			for(String[] s:list){
				int support = 0;
				int supprotType = 0;
				if(i%5000 == 0){
					sb.append("LOCK TABLES tbl_bussiness_simplify WRITE;\r\n");
					sb.append("insert into tbl_bussiness_simplify (d_btype,d_bdistrict,d_phonenum,d_support,d_support_type) values \r\n");
				}
				String phoneNum = s[0];
				String bd = s[1];
				String gprs = s[2];
				String wap = s[3];
				String smartPhone = s[4];
				String os = s[5];
				String wifi = s[6];
				if(smartPhone.equals("1") || os.equals("1")){
					support = 1;
					supprotType = 2;
				}else{
					if(wap.equals("1") && gprs.equals("1")){
						support = 1;
						supprotType = 1;
					}
				}
				sb.append("(0,\'"+bd+"\',\'"+phoneNum+"\',"+support+","+supprotType+")");
				i++;
				if(i%5000 == 0){
					sb.append(";").append("\r\n");
					sb.append("UNLOCK TABLES;");
				}else{
					if(i<list.size()){
						sb.append(",").append("\r\n");
					}
				}
			}
			sb.append(";").append("\r\n");
			sb.append("UNLOCK TABLES;");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public void test2(){
		Cnd condition = Cnd.where("d_hsman_name","=","apple");
		List<Tac> list = dao.search(Tac.class, condition);
		StringBuffer sb = new StringBuffer(" in (\t");
		for(Tac tac:list){
			sb.append("\'"+tac.getTac()+"\'").append(",");
		}
		sb.append("\t)");
//		ViewTerminal v = dao.findByCondition(ViewTerminal.class, condition);
//		System.out.println(" >> "+Json.toJson(v));
		System.out.println(""+sb.toString());
	}
	
	public void test5(){
		List<String[]> list = CvsFileParser.getCSV("tac.csv");
		System.out.println(" >> list.size："+list.size());
	}

	public void test6(){
		Cnd condition = Cnd.where("d_status","=","1");
		List<Tac> tlist = dao.search(Tac.class, condition.orderBy().asc("d_hsman_name"));
		for(Tac t:tlist){
//			System.out.print(" > "+t.getHsmanName()+"|"+t.getHsmanNameEn()+"|"+t.getHstypeName()+"|"+t.getHstypeNameEn());
			//如果是英文，则将中文名称制空，然后修改英文
			/**
			if(t.getHsmanName().matches("^[a-zA-Z]*")){
				t.setHsmanName("暂无");
				t.setCreatetime(new Date());
			}
			**/
			if(null != t.getHsmanNameEn() && !"".equals(t.getHsmanNameEn()) && t.getHstypeName().toLowerCase().startsWith(t.getHsmanNameEn().toLowerCase())){
				String hstypename = t.getHstypeName().toLowerCase().replace(t.getHsmanNameEn().toLowerCase(),"");
				if(null != hstypename){
					t.setHstypeName(hstypename.trim());
					System.out.println(" -> 1:"+hstypename.trim());
				}
			}
			if(null != t.getHsmanName() && !"".equals(t.getHsmanName()) && t.getHstypeName().toLowerCase().startsWith(t.getHsmanName().toLowerCase())){
				String hstypename = t.getHstypeName().toLowerCase().replace(t.getHsmanName().toLowerCase(),"");
				if(null != hstypename){
					t.setHstypeName(hstypename.trim());
					System.out.println(" -> 2:"+hstypename.trim());
				}
			}
			if(t.getHstypeName().startsWith("one touch ")){
				String hstypename = t.getHstypeName().toLowerCase().replace("one touch ","");
				t.setHstypeName(hstypename);
			}
			String[] strs = t.getHstypeName().toLowerCase().split(" ");
			if(strs.length > 2){
				String hsman = strs[0];
				String hstype = strs[1];
				System.out.println(" > ["+t.getHsmanName()+"|"+t.getHstypeName()+"] "+hsman+"|"+hstype);
			}
			/**
			**/
//			if(dao.update(t)){
//				System.out.println(" >> 更新厂商名称为英文的厂商名称成功!");
//			}
		}
	}
}
