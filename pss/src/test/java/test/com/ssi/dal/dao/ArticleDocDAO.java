package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IArticleDocDAO;
import com.ssi.dal.domain.ArticleDoc;

public class ArticleDocDAO {
	
	private IArticleDocDAO articleDocDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		articleDocDAO = (IArticleDocDAO)context.getBean("articleDocDAO");
	}
	
	@After
	public void destory(){
		if(null != articleDocDAO){
			articleDocDAO = null;
		}
	}
	
	public void find(){
		HashMap map = new HashMap();
		map.put("status", 10);
//		map.put("limit", 10);
		map.put("sort", "desc");
		List<ArticleDoc> list = articleDocDAO.find(map);
		if(null != list && list.size() > 0){
			for(ArticleDoc doc:list){
				System.out.println(">> doc.id:"+doc.getId());
				System.out.println(">> doc.title:"+doc.getTitle());
				System.out.println(">> doc.status:"+doc.getStatus());
			}
			System.out.println(">> 文章数量:"+list.size());
		}
	}
	
	public void update(){
		HashMap map = new HashMap();
		map.put("id", 1585);
		List<ArticleDoc> list = articleDocDAO.find(map);
		if(null != list && list.size()>0){
			System.out.println(">> 文章数量:"+list.size());
			if(list.size() == 1){
				ArticleDoc doc = (ArticleDoc)list.get(0);
				doc.setTag("测试标签");
				int result = articleDocDAO.update(doc);
				System.out.println(">> 结果:"+result);
			}
		}
	}
	
	@Test
	public void getCount(){
		HashMap map = new HashMap();
		int count = articleDocDAO.getCount(map);
		System.out.println(">> 结果:"+count);
	}
	
	public void insert(){
		ArticleDoc articleDoc = new ArticleDoc();
		articleDoc.setWebId(156);
		articleDoc.setTag("测试文章");
		articleDoc.setUrl("http://www.google.com.hk");
		int result = articleDocDAO.insert(articleDoc);
		System.out.println(">> 结果:"+result);
	}

}
