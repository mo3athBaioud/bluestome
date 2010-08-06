package com.chinamilitary.test;

import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.chinamilitary.bean.Article;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.factory.DAOFactory;

public class TestHttpClient {
	static final String URL1 = "http://www.showimg.com/tabulation.php?mid=6775";
	
	static HttpClient httpclient = new HttpClient();
	
	static GetMethod getMethod = null;
	
	static ArticleDao  articleDao = DAOFactory.getInstance().getArticleDao();
	
	static void test1(String url) {
		try {
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			System.out.println("URL:"+url+",状态码:"+statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+ getMethod.getStatusLine());
				System.err.println("URL: "+ url);
				System.out.println();
			}
			getMethod.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		try{
			List<Article> list = articleDao.findByWebId(1);
			for(Article bean:list){
				test1(bean.getArticleUrl());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
