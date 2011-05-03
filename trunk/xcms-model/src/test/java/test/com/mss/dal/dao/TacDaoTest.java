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
				System.out.println(" > hsmanName:"+t.getHsmanName());
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
	
	@Test
	public void test3() throws ClassNotFoundException{
		if(null != dao){
			System.out.println(" >> dao is not null");
			List<String[]> list = CvsFileParser.getCSV("employer2_with_imei.csv");
			int offset = 0;
			Tac tac = null;
			List<String> outL = new ArrayList<String>();
			List<Tac> tList = new ArrayList<Tac>();
			for(String[] s:list){
				tac = new Tac();
				String imei = s[1];
				String phoneNum = s[0];
				String ttac = imei.substring(0,8);
				tac.setTac(ttac);
				
				Tac tmp = dao.findByCondition(Tac.class, Cnd.where("d_tac", "=", ttac));
				if(null == tmp){
					tmp = dao.findByCondition(Tac.class, Cnd.where("substring(d_tac,1,6)", "=", ttac.substring(0,6)));
					if(null == tmp){
						System.out.println(" >> TAC,8位查询再查6位,仍未查找到对应记录");
						outL.add(phoneNum+"_"+imei);
					}
				}else{
					tList.add(tmp);
					offset++;
				}
			}
			System.out.println(" >> list.size："+list.size());
			System.out.println(" >> offset："+offset);
			System.err.println("未匹配到的数据如下:\r\n");
			StringBuffer sb = new StringBuffer();
			for(String s:outL){
				String[] s1 = s.split("_");
				sb.append(s1[0]).append(",").append(s1[1]).append("\r\n");
			}
			System.out.println(sb.toString());
//			for(Tac tmp:tList){
//				sb.append(tmp.getHsmanName()).append(",").append(tmp.getHsmanNameEn()).append(",").append(tmp.getHstypeName()).append(",").append(tmp.getHstypeNameEn()).append("\r\n");
//			}
//			IOUtil.createFile(sb.toString(), "TAC(前8位)识别出的厂商机型.csv");
//			IOUtil.createFile(sb.toString(), "TAC(前8位)未识别出的手机号码和IMEI.csv");
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
