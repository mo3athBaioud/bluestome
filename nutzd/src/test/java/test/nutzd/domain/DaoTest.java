package test.nutzd.domain;

import java.util.List;

import org.junit.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import com.nutzd.domain.TempArticleGroup;
import com.nutzd.domain.Website;
import com.nutzd.domain.dao.NutzdDataSource;
public class DaoTest {
	
	private NutzdDataSource nds = null;
	private Dao dao = null;
	
	@Before
	public void init(){
		nds = NutzdDataSource.getInstance();
		dao = new NutDao(nds.getBds());
	}
	
	@After
	public void desotry(){
		if(null != nds)
			nds = null;
		if(null != dao)
			dao = null;
	}
	
	public void queryForList(){
		List<Website> list = dao.query(Website.class, Cnd.where("url", "like", "%taobao%"), null);
		for(Website wb:list){
			System.out.println(" >> queryForList \t"+wb.getId()+"|"+wb.getName()+"|"+wb.getUrl());
		}
	}
	
	@Test
	public void queryForPage(){
		List<Website> list = dao.query(Website.class, null, dao.createPager(20,10));
		for(Website wb:list){
			System.out.println(" >> queryForPage \t"+wb.getId()+"|"+wb.getName()+"|"+wb.getUrl()+"|"+wb.getAtotal());
		}
	}
	
	public void queryForObject(){
		int id = 5;
		String name = "健康图库-猎奇奇图";
		Website wb = dao.fetch(Website.class,id);
		System.out.println(" >> queryForObject \t"+wb.getId()+"|"+wb.getName()+"|"+wb.getUrl()+"|"+wb.getAtotal());
	}
	
	public void insert(){
		Website wb = new Website();
		wb.setName("测试");
		wb.setUrl("http://test.test.test");
		wb = dao.insert(wb);
		System.out.println(" >> insert \t"+wb.getId()+"|"+wb.getName()+"|"+wb.getUrl());
	}
	
	public void delete(){
		int id = 1667,id2 = 1668;
		int result = dao.delete(Website.class, id);
		System.out.println(" >> delete.result.id:"+result);
		
		result = dao.delete(Website.class, id2);
		System.out.println(" >> delete.result.id2:"+result);
	}
	
	public void queryTempGroup(){
		TempArticleGroup tag = new TempArticleGroup();
		tag.setName("测试");
		tag.setTotal(120);
		
		tag = dao.insert(tag);
		
		dao.clear(TempArticleGroup.class,Cnd.where("name","=","测试"));
		
	}
}
