package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.entity.Image;
import com.takesoon.oms.ssi.json.DateJsonValueProcessor;
import com.takesoon.oms.ssi.service.ImageManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

public class ImageTest {

	private ImageManager imageManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		imageManager = (ImageManager)context.getBean("imageManager");
	}
	
	@After
	public void destory(){
		if(null != imageManager)
			imageManager = null;
	}
	
	public void test(){
		Image entity = new Image();
		entity.setId(135);
		if(null != imageManager){
			System.out.println("imageManager is not null");
			int c = imageManager.getCount(entity);
			System.out.println(" > c:" + c);
		}
		
	}
	
	public void get(){
		Integer id = 2986106;
		Image entity = imageManager.get(id);
		if(null != entity){
			System.out.println(" > entity.id:"+entity.getId());
			System.out.println(" > entity.articleId:"+entity.getArticleId());
			System.out.println(" > entity.title:"+entity.getTitle());
			System.out.println(" > json:" + entity.toJson());
		}else{
			System.err.println(" > error");
		}
	}
	
	public void getTotal(){
		Image entity = new Image();
		int t = imageManager.getTotal(entity);
		System.out.println(" > t:" + t);
	}
	
	@Test
	public void buildCountSQL(){
		Image entity = new Image();
		entity.setArticleId(139963);
		String sql = imageManager.buildCountSQL(entity);
		System.out.println(" > sql:" + sql);
		int c = imageManager.getTotalBySql(entity);
		System.out.println(" > c:"+c);
		List<Image> list = imageManager.getListBySql(entity, 0, 200);
		for(Image art:list){
			System.out.println(" > art.toJson():"+art.toJson());
		}
		
		String json = ExtUtil.getJsonFromList(c, list);
		System.out.println(" > json.length:"+json.length());
		
//		String json2 = DateJsonValueProcessor.obj2JsonObj(list);
		
		String xml = ExtUtil.getXmlFromList(c, list);
		System.out.println(" > xml.length:"+xml.length());
		
		System.out.println(" > list.size:" + list.size());
		System.out.println(" > count:" + c);
	}
}
