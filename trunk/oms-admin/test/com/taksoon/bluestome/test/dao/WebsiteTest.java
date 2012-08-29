package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.entity.Website;
import com.takesoon.oms.ssi.service.WebsiteManager;

public class WebsiteTest extends AbstractTestCase{

	private WebsiteManager websiteManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		websiteManager = (WebsiteManager)context.getBean("websiteManager");
	}
	
	@After
	public void destory(){
		if(null != websiteManager)
			websiteManager = null;
	}
	
	public void test(){
		if(null != websiteManager){
			System.out.println(" > websiteManager is not null");
		}
	}
	
	public void getList(){
		int parentId = 0;
		getList(parentId);
	}
	
	public void getList(int parentId){
		Website entity = new Website();
		entity.setParentId(parentId);
		List<Website> list = websiteManager.getList(entity, 0, 200);
		for(Website tmp:list){
			Website ttmp = new Website();
			ttmp.setParentId(tmp.getId());
			int count = websiteManager.getCount(ttmp);
			System.out.println("[" + tmp.getId()+ "]  > " +  count );
//			/**
			if(count > 0){
				getList(tmp.getId());
			}else{
				System.out.println(" >> " + tmp.toJson());
			}
//			**/
			ttmp = null;
		}
	}
	
	public void get(){
		Website entity = new Website();
		entity = websiteManager.get(200);
		if(null != entity){
			System.out.println(entity.toJson());
		}
		
	}
	
	@Test
	public void testTree2(){
		Website entity = new Website();
		entity.setParentId(700);
		List<Website> list = websiteManager.getList(entity, 0, 200);
		String tree = websiteManager.tree2(list,"/oms");
		System.out.println(" > "+tree);
	}
}
