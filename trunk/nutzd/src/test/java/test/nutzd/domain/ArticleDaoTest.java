package test.nutzd.domain;

import java.util.List;

import org.junit.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import com.nutzd.domain.TempArticleGroup;
import com.nutzd.domain.Article;
import com.nutzd.domain.dao.NutzdDataSource;
public class ArticleDaoTest {
	
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
		List<Article> list = dao.query(Article.class, Cnd.where("url", "like", "%taobao%"), null);
		for(Article wb:list){
			System.out.println(" >> queryForList \t"+wb.getId()+"|"+wb.getTitle()+"|"+wb.getArticleUrl());
		}
	}
	
	@Test
	public void queryForPage(){
		List<Article> list = dao.query(Article.class, null, dao.createPager(20,10));
		for(Article wb:list){
			System.out.println(" >> queryForPage \t"+wb.getId()+"|"+wb.getTitle()+"|"+wb.getArticleUrl()+"|"+wb.getItotal()+"|"+wb.getCreatetime());
			if(null != wb.getWebsite()){
				System.out.println(" >> website:"+wb.getWebsite().getName()+"|"+wb.getWebsite().getUrl());
			}
		}
	}
	
	public void queryForObject(){
		int id = 5;
		String name = "健康图库-猎奇奇图";
		Article wb = dao.fetch(Article.class,id);
		System.out.println(" >> queryForObject \t"+wb.getId()+"|"+wb.getTitle()+"|"+wb.getArticleUrl()+"|"+wb.getItotal());
	}
	
	public void insert(){
		Article wb = new Article();
		wb = dao.insert(wb);
	}
	
	public void delete(){
		int id = 1667,id2 = 1668;
		int result = dao.delete(Article.class, id);
		System.out.println(" >> delete.result.id:"+result);
		
		result = dao.delete(Article.class, id2);
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