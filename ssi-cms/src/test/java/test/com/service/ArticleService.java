package test.com.service;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.IArticleService;
import com.ssi.common.dal.domain.Article;
import com.ssi.common.utils.JSONUtils;

public class ArticleService {
	
	private IArticleService articleService;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		articleService = (IArticleService)context.getBean("articleService");
	}
	
	@After
	public void destory(){
		if(null != articleService){
			articleService = null;
		}
	}
	
	public void findById(){
		Article article = articleService.findById(15827);
		if(null != article){
			System.out.println("文章标题:"+article.getTitle());
			System.out.println("文章地址："+article.getArticleUrl());
			System.out.println("网站名称："+article.getWebsite().getName());
			String json = JSONUtils.bean2Json(article);
			System.out.println(" >> json:"+json);
		}
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		map.put("limit", 10);
		map.put("offset", 10);
		
		List<Article> list = articleService.find(map);
		System.out.println(" >> 列表数量:"+list.size());
		for(Article art:list){
			System.out.println("ID:"+art.getId());
			System.out.println("文章标题:"+art.getTitle());
			System.out.println("文章地址："+art.getArticleUrl());
		}
	}
	
	public void insert(){
		Article article = new Article();
		article.setWebId(9);
		article.setArticleUrl("none");
		boolean result = articleService.insert(article);
		System.out.println(" >> result:"+result);
	}
}
