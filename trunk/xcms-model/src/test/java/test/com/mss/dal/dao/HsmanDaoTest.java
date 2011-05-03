package test.com.mss.dal.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;
import org.htmlparser.tags.LinkTag;
import org.junit.*;

import com.mss.dal.dao.HsmanDao;
import com.mss.dal.dao.HstypeDao;
import com.mss.dal.domain.Hsman;
import com.mss.dal.domain.Hstype;
import com.xc.tools.HstypeParser;

public class HsmanDaoTest {

	private Ioc2 ioc;
	
	private HsmanDao hsmanDAO;
	
	private HstypeDao hstypeDAO;
	
	@Before
	public void init(){
		try{
			ioc = new NutIoc(new ComboIocLoader(
                "*org.nutz.ioc.loader.json.JsonLoader",
                "conf/datasource.json", 
                "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
                "com.mss.dal"
                ));
			
			hsmanDAO = ioc.get(HsmanDao.class);
			hstypeDAO = ioc.get(HstypeDao.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void destory(){
		if(null != ioc)
			ioc = null;
		if(null != hsmanDAO)
			hsmanDAO = null;
		if(null != hstypeDAO)
			hstypeDAO = null;
	}
	
	public void test(){
		if(null != ioc)
			System.out.println(" ioc is not null");
		if(null != hsmanDAO)
			System.out.println(" hsmanDAO is not null");
		if(null != hstypeDAO)
			System.out.println(" hstypeDAO is not null");
	}
	
	public void findHsman() throws Exception{
//		List<Hsman> list = hsmanDAO.search(Hsman.class, "");
		List<Hsman> list = hsmanDAO.search(Hsman.class,Cnd.where("d_status", "=", "1"));
		Hstype hstype = null;
		for(Hsman hs:list){
			System.out.println(" >> hs.id:"+hs.getId());
			System.out.println(" >> hs.name:"+hs.getName());
			System.out.println(" >> hs.icon:"+hs.getIcon());
			System.out.println(" >> hs.remarks:"+hs.getRemarks());
			System.out.println(" >> hs.size:"+hs.getHstypes().size());
			
//			if(hs.getId() != 29){
//				continue;
//			}
			HashMap<String, LinkTag>  page = HstypeParser.getPageList(hs.getRemarks());
			System.out.println(" >> size:"+page.size());
			Iterator it = page.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				List<String[]> hstypes = HstypeParser.hstypeWithIcon(key);
				for(String[] s:hstypes){
					hstype = new Hstype();
					String link = s[0];
					String imgSrc = s[1];
					hstype.setHsmanId(hs.getId());
					hstype.setIcon(imgSrc);
					hstype.setRemarks(link);
//					System.out.println(Json.toJson(hstype));
					if(null == hstypeDAO.findByCondition(Hstype.class, Cnd.where("d_remarks","=",link))){
						hstype = hstypeDAO.save(hstype);
						System.out.println(" >> hsman["+hs.getName()+"]下新增机型["+hstype.getId()+"]");
					}else{
						System.out.println(" >> 未在hsman["+hs.getName()+"]下新增机型["+link+"]");
					}
				}
				Thread.sleep(5000);
			}
			/**
			**/
		}
	}
	
	public void findHstype(){
		//
		try{
			List<Hstype> list = hstypeDAO.search(Hstype.class, Cnd.where("d_name", "=", "hstype").and("d_status", "=", "1"));
			for(Hstype ht:list){
				String url = ht.getRemarks();
				String title = HstypeParser.getTitle(url);
				if(null != title && !"".equals(title) && !"hstype".equals(title)){
					ht.setName(title);
					ht.setStatus(2);
					ht.setModifytime(new Date());
					boolean b = hstypeDAO.update(ht);
					System.out.println("机型【"+ht.getId()+"】"+(b?"更新成功":"更新失败"));
				}
				Thread.sleep(1000);
			}
			System.out.println(" >> size:"+list.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void patchModifyName(){
		try{		
		List<Hsman> list = hsmanDAO.search(Hsman.class,Cnd.where("d_status", "=", "1"));
		for(Hsman hs:list){
			if(hs.getId() == 29 || hs.getId() == 7){
				continue;
			}
			List<Hstype> hstypeList = hstypeDAO.search(Hstype.class, Cnd.where("d_hsman_id","=",hs.getId()).and("d_status","=","2"));
			for(Hstype ht:hstypeList){
				String name = ht.getName().replace(hs.getName(), "");
				System.out.println(" >> "+hs.getName()+"|"+name);
				ht.setName(name);
				ht.setStatus(3);
				boolean b = hstypeDAO.update(ht);
				if(b){
					System.out.println(" >> 更新成功");
				}else{
					System.out.println(" >> 更新失败");
				}
				/**
				**/
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
