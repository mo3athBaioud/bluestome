package test.com.ssi.dal.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.dal.dao.IArticleDAO;
import com.ssi.dal.domain.Article;

public class ArticleDAO {
	
	private IArticleDAO articleDAO;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		articleDAO = (IArticleDAO)context.getBean("articleDAO");
	}
	
	@After
	public void destory(){
		if(null != articleDAO){
			articleDAO = null;
		}
	}
	
	public void findById(){
		Article article = articleDAO.findById(15827);
		if(null != article){
			System.out.println("文章标题:"+article.getTitle());
			System.out.println("文章地址："+article.getArticleUrl());
		}
	}
	
	public void update(){
		Article article = articleDAO.findById(15827);
		if(null != article){
			article.setIntro("");
			article.setText("FD");
			int result = articleDAO.update(article);
			System.out.println(">> 结果:"+result);
		}
	}
	
	public void insert(){
		Article article = new Article();
		article.setActicleRealUrl("");
		article.setActicleXmlUrl("");
		article.setArticleUrl("http://www.china.com/");
		article.setIntro("测试添加");
		article.setText("NED");
		article.setTitle("测试添加");
		article.setWebId(110);
		int result = articleDAO.insert(article);
		System.out.println(">> 结果:"+result);
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		map.put("limit",10);
		map.put("text","NED");
		List<Article> list = articleDAO.find(map);
		if(null != list && list.size() > 0){
			for(Article art:list){
				System.out.println("article.id:"+art.getId());
				System.out.println("article.title:"+art.getTitle());
			}
		}
	}

}
