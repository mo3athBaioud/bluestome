package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.takesoon.oms.ssi.entity.PictureFile;
import com.takesoon.oms.ssi.json.DateJsonValueProcessor;
import com.takesoon.oms.ssi.service.PictureFileManager;
import com.takesoon.oms.ssi.utils.ExtUtil;

public class PictureFileTest {

	private PictureFileManager pictureFileManager;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		pictureFileManager = (PictureFileManager)context.getBean("pictureFileManager");
	}
	
	@After
	public void destory(){
		if(null != pictureFileManager)
			pictureFileManager = null;
	}
	
	public void test(){
		PictureFile entity = new PictureFile();
		entity.setId(135);
		if(null != pictureFileManager){
			System.out.println("pictureFileManager is not null");
			int c = pictureFileManager.getCount(entity);
			System.out.println(" > c:" + c);
		}
		
	}
	
	public void get(){
		Integer id = 2986106;
		PictureFile entity = pictureFileManager.get(id);
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
		PictureFile entity = new PictureFile();
		int t = pictureFileManager.getTotal(entity);
		System.out.println(" > t:" + t);
	}
	
	@Test
	public void buildCountSQL(){
		PictureFile entity = new PictureFile();
		entity.setArticleId(123959);
		String sql = pictureFileManager.buildCountSQL(entity);
		System.out.println(" > sql:" + sql);
		int c = pictureFileManager.getTotalBySql(entity);
		System.out.println(" > c:"+c);
		List<PictureFile> list = pictureFileManager.getListBySql(entity, 0, 200);
		for(PictureFile art:list){
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
