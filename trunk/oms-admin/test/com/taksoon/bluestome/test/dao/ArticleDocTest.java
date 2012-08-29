package com.taksoon.bluestome.test.dao;

import java.util.List;

import org.junit.Test;

import com.takesoon.oms.ssi.entity.ArticleDoc;
import com.takesoon.oms.ssi.utils.ExtUtil;

public class ArticleDocTest extends AbstractTestCase{

	public void test(){
		ArticleDoc entity = new ArticleDoc();
		entity.setId(10010);
		if(null != articleDocManager){
			System.out.println("articleDocManager is not null");
			int c = articleDocManager.getCount(entity);
			System.out.println(" > c:" + c);
		}
		
	}
	
	public void get(){
		Integer id = 135;
		ArticleDoc entity = articleDocManager.get(id);
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
		ArticleDoc entity = new ArticleDoc();
		int t = articleDocManager.getTotal(entity);
		System.out.println(" > t:" + t);
	}
	
	@Test
	public void buildCountSQL(){
		ArticleDoc entity = new ArticleDoc();
//		entity.setId(13);
//		entity.setArticleUrl("military");
//		entity.setActicleRealUrl("http://");
//		entity.setActicleXmlUrl("xml");
//		entity.setIntro(null);
//		entity.setText("FD");
		entity.setWebId(1350);
		String sql = articleDocManager.buildCountSQL(entity);
		System.out.println(" > sql:" + sql);
		
		int c = articleDocManager.getTotalBySql(entity);
		System.out.println(" > c:"+c);
		
		List<ArticleDoc> list = articleDocManager.getListBySql(entity, 0, 200);
//		for(ArticleDoc art:list){
//			System.out.println(" > art.toJson():"+art.toJson());
//		}
		
		//JSON格式
		String json = ExtUtil.getJsonFromList(c, list);
		System.out.println(json);
		System.out.println(" > json.length:"+json.length());
		
//		String json2 = DateJsonValueProcessor.obj2JsonObj(list);
		
//		String xml = ExtUtil.getXmlFromList(c, list);
//		System.out.println(" > xml.length:"+xml.length());
		
//		System.out.println(" > list.size:" + list.size());
		System.out.println(" > count:" + c);
	}
}
