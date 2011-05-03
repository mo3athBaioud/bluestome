package test.com.mss.dal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.junit.*;

import com.mss.dal.dao.CmdataDao;
import com.mss.dal.dao.HsmanDao;
import com.mss.dal.dao.HstypeDao;
import com.mss.dal.dao.TerminalDao;
import com.mss.dal.domain.Cmdata;
import com.mss.dal.domain.Hsman;
import com.mss.dal.domain.Hstype;
import com.mss.dal.domain.Terminal;
import com.xc.tools.CmDataParser;
import com.xc.tools.TerminalParser;

public class TerminalDaoTest {

	private Ioc ioc;
	
	private HsmanDao hsmanDAO;

	private TerminalDao terminalDAO;

	private HstypeDao hstypeDAO;

	@Before
	public void init() {
		try {
			ioc = new NutIoc(new ComboIocLoader(
					"*org.nutz.ioc.loader.json.JsonLoader",
					"conf/datasource.json",
					"*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
					"com.mss.dal"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		hsmanDAO = ioc.get(HsmanDao.class);
		terminalDAO = ioc.get(TerminalDao.class);
		hstypeDAO = ioc.get(HstypeDao.class);
	}

	@After
	public void destory() {
		if (null != ioc)
			ioc = null;
		if (null != terminalDAO)
			terminalDAO = null;
		if (null != hstypeDAO)
			hstypeDAO = null;
	}

	public void asset() {
		if (null != ioc)
			System.out.println(" ioc is not null");
		if (null != terminalDAO)
			System.out.println(" cmdataDAO is not null");

	}
	
	@Test
	public void getTerminalInfo(){
		List<Hsman> list = hsmanDAO.search(Hsman.class,Cnd.where("d_status", "=", "1"));
		Terminal ter = null;
		for(Hsman hs:list){
			if(hs.getId() != 7)
				continue;
			List<Hstype> htList = hstypeDAO.search(Hstype.class, Cnd.where("d_status", "=", "3").and("d_hsman_id", "=", hs.getId()));
			if(htList.size()>0){
				for(Hstype ht:htList){
					String url = ht.getRemarks();
					url = url.replace("Index", "Detail");
					String[] s = TerminalParser.getTerminalInfo(url); 
					if(null != s){
						ter = new Terminal();
						ter.setHstypeId(ht.getId());
						ter.setPrice(s[0]);
						ter.setMobileSystem(s[1]);
						ter.setOs(s[2]);
						ter.setScreensize(s[3]);
						ter.setScreenColor(s[4]);
						ter.setResolution(s[5]);
						ter.setCamera(Integer.valueOf(s[6]));
						ter.setCameraPixels(s[7]);
						ter.setGps(Integer.valueOf(s[8]));
						ter.setWap(Integer.valueOf(s[9]));
						ter.setWww(Integer.valueOf(s[10]));
						ter.setEmail(Integer.valueOf(s[11]));
						ter.setMms(Integer.valueOf(s[12]));
						ter.setJava(Integer.valueOf(s[13]));
						
						ter = terminalDAO.save(ter);
						if(ter.getId() > 0){
							ht.setStatus(9);
							boolean b = hstypeDAO.update(ht);
							if(b){
								System.out.println(" >> 添加终端属性成功!");
							}
						}
					}
				}
			}

		}
		
	}

}
