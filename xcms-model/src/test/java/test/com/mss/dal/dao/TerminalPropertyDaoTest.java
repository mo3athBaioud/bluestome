package test.com.mss.dal.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;

import com.mss.dal.dao.TerminalPropertyDao;
import com.mss.dal.dao.StaffDao;
import com.mss.dal.domain.TerminalProperty;
import com.mss.dal.domain.Staff;
import com.xc.tools.CvsFileParser;

public class TerminalPropertyDaoTest {

	private Ioc ioc;
	
	private TerminalPropertyDao terminalPropertyDao;
	
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
			e.printStackTrace();
		}
		terminalPropertyDao = ioc.get(TerminalPropertyDao.class);
	}

	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != terminalPropertyDao)
			terminalPropertyDao = null;
	}
	
	public void asset(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != terminalPropertyDao)
			System.out.println(" terminalPropertyDao is not null");
	}

	@Test
	public void find(){
		List<String[]> tlist = CvsFileParser.getCSV("终端属性1.csv");
		int c = 0;
		TerminalProperty tp = null;
		for(String[] st:tlist){
			if(st.length == 7){
				String hsman = st[0];
				String hstype = st[1];
				int gprs = 0;
				int wap = 0;
				int sp = 0;
				int os = 0;
				int wifi = 0;
				Cnd condition = Cnd.where("d_hsman", "=", hsman).and("d_hstype","=",hstype);
				int count = terminalPropertyDao.searchCount(TerminalProperty.class, condition);
				if(count > 0){
					System.out.println(" > 已经存在该厂商["+hsman+"],机型["+hstype+"]");
					System.out.println("st[0]:"+st[0]);
					System.out.println("st[1]:"+st[1]);
//					System.out.println("st[2]:"+st[2]);
//					System.out.println("st[3]:"+st[3]);
//					System.out.println("st[4]:"+st[4]);
//					System.out.println("st[5]:"+st[5]);
//					System.out.println("st[6]:"+st[6]);
//					System.out.println("\r\n");
					c ++ ;
				}else{
					tp = new TerminalProperty();
					tp.setHsman(hsman);
					tp.setHstype(hstype);
					try{
						gprs = Integer.valueOf(st[2]);
						wap = Integer.valueOf(st[3]);
						if(st[4].equals("1") || st[5].equals("1") ){
							os = 1;
							sp = 1;
						}
						wifi = Integer.valueOf(st[6]);
						tp.setGprs(gprs);
						tp.setWap(wap);
						tp.setOs(os);
						tp.setSmartphone(sp);
						tp.setWifi(wifi);
						tp = terminalPropertyDao.save(tp);
						if(tp.getId() > 0){
							System.out.println(" > 保存终端["+tp.getId()+"]属性成功!");
						}
					}catch(Exception e){
						e.printStackTrace();
						continue;
					}
					
				}
			}
		}
		System.out.println(" >> 存在["+c+"]个相同的终端属性。");
		/**
		List<TerminalProperty> list = terminalPropertyDao.search(TerminalProperty.class, "d_id");
		for(TerminalProperty bus:list){
			System.out.println(" >> "+bus.getHsman()+"|"+bus.getHstype());
		}
		**/
	}
}
