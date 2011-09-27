package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.entity.Article;
import com.takesoon.oms.ssi.json.DateJsonValueProcessor;
import com.takesoon.oms.ssi.service.ArticleManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

public class ArticleTest {

	private ArticleManager articleManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		articleManager = (ArticleManager)context.getBean("articleManager");
	}
	
	@After
	public void destory(){
		if(null != articleManager)
			articleManager = null;
	}
	
	public void test(){
		Article entity = new Article();
		entity.setId(135);
		if(null != articleManager){
			System.out.println("articleManager is not null");
			int c = articleManager.getCount(entity);
			System.out.println(" > c:" + c);
		}
		
	}
	
	@Test
	public void get(){
		Integer id = 135;
		Article entity = articleManager.get(id);
		if(null != entity){
			System.out.println(" > entity.id:"+entity.getId());
			System.out.println(" > entity.webId:"+entity.getWebId());
			System.out.println(" > entity.title:"+entity.getTitle());
			System.out.println(" > json:" + entity.toJson());
		}else{
			System.err.println(" > error");
		}
	}
	
	public void getTotal(){
		Article entity = new Article();
		int t = articleManager.getTotal(entity);
		System.out.println(" > t:" + t);
	}
	
	public void buildCountSQL(){
		Article entity = new Article();
//		entity.setId(13);
//		entity.setArticleUrl("military");
//		entity.setActicleRealUrl("http://");
//		entity.setActicleXmlUrl("xml");
//		entity.setIntro(null);
//		entity.setText("FD");
//		entity.setWebId(36);
		entity.setTitle("中国");
		String sql = articleManager.buildCountSQL(entity);
		System.out.println(" > sql:" + sql);
		int c = articleManager.getTotalBySql(entity);
		System.out.println(" > c:"+c);
		List<Article> list = articleManager.getListBySql(entity, 0, 200);
//		for(Article art:list){
//			System.out.println(" > art.toJson():"+art.toJson());
//		}
		
		String json = ExtUtil.getJsonFromList(c, list);
		System.out.println(" > json.length:"+json.length());
		
//		String json2 = DateJsonValueProcessor.obj2JsonObj(list);
		
		String xml = ExtUtil.getXmlFromList(c, list);
		System.out.println(" > xml.length:"+xml.length());
		
		System.out.println(" > list.size:" + list.size());
		System.out.println(" > count:" + c);
	}
}
