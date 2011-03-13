package test.com.service;

import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssi.cms.web.service.ArticleDocIService;
import com.ssi.common.dal.domain.ArticleDoc;
import com.ssi.common.utils.JSONUtils;

public class ArticleDocService {
	
	private ArticleDocIService articleDocService;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		articleDocService = (ArticleDocIService)context.getBean("articleDocService");
	}
	
	@After
	public void destory(){
		if(null != articleDocService){
			articleDocService = null;
		}
	}
	
	public void findById() throws Exception{
		List<ArticleDoc> list = articleDocService.findByWebId(196);
		if(null != list){
			for(ArticleDoc doc:list){
				System.out.println("文章标题:"+doc.getTitle());
				System.out.println("文章作者："+doc.getAuthor());
				System.out.println("文章发布时间:"+doc.getPublishTime());
			}
		}
	}
	
	@Test
	public void find(){
		HashMap map = new HashMap();
		map.put("limit", 10);
		map.put("offset", 10);
		
		List<ArticleDoc> list = articleDocService.find(map);
		System.out.println(" >> 列表数量:"+list.size());
		for(ArticleDoc doc:list){
			System.out.println("文章标题:"+doc.getTitle());
			System.out.println("文章作者："+doc.getAuthor());
			System.out.println("文章发布时间:"+doc.getPublishTime());
		}
	}
	

}
