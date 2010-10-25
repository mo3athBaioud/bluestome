package test.com.ssi.dal.dao;

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
	
	@Test
	public void findById(){
		Article article = articleDAO.findById(15827);
		if(null != article){
			System.out.println("文章标题:"+article.getTitle());
			System.out.println("文章地址："+article.getArticleUrl());
		}
	}
}
