package com.chinamilitary.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.chinamilitary.bean.Article;
import com.chinamilitary.bean.ImageBean;
import com.chinamilitary.dao.ArticleDao;
import com.chinamilitary.dao.ImageDao;
import com.chinamilitary.factory.DAOFactory;

public class TestHttpClient {
	static final String URL1 = "http://www.showimg.com/tabulation.php?mid=6775";
	
	public static HttpClient httpclient = new HttpClient();
	
	public static GetMethod getMethod = null;
	
	static ArticleDao  articleDao = DAOFactory.getInstance().getArticleDao();
	
	static ImageDao imageDao = DAOFactory.getInstance().getImageDao();
	
	static HashMap<String,String> LINK_NOT_OK = new HashMap<String,String>();
	
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
//			List<Article> list = articleDao.findByWebId(1);
//			for(Article bean:list){
//				test1(bean.getArticleUrl());
//			}
//			boolean isTrue = urlValidation("http://www.showimg.com/game2.htm");
//			System.out.println("页面是否合法:"+isTrue);
			
//			getResponseHeaders("http://image.tuku.china.com/tuku.military.china.com/military//pic/2010-08-09/f003abea-7bd8-478b-b9f8-a11b59d6a0e0.jpg");
			getResponseHeaders("http://www.china.com");
//			getImgInfoFromResponse();
			
			
			Iterator it = LINK_NOT_OK.keySet().iterator();
			System.out.println("无效链接如下\n");
			while(it.hasNext()){
				String key = (String)it.next();
				System.out.println("URL["+key+"] \t状态码["+LINK_NOT_OK.get(key)+"]");
			}
			
			LINK_NOT_OK.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean urlValidation(String url){
		try {
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("地址["+url+"],状态码["+statusCode+"]");
				return false;
			}
			getMethod.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	static void getResponseHeaders(String url){
		long start = System.currentTimeMillis();
		try{
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK){
				String length = getMethod.getResponseHeader("Content-Length").getValue();
				String type = getMethod.getResponseHeader("Content-Type").getValue();
				long end = System.currentTimeMillis();
				System.out.println("响应体类型:"+type+"\t响应头长度:"+length + "\t耗时:"+(end-start));
				Header[] headers = getMethod.getResponseHeaders();
				for(Header header:headers){
					System.out.println(header.getName()+":"+header.getValue());
//					System.out.println("Value:"+header.getValue());
				}
			}else{
				LINK_NOT_OK.put(url, String.valueOf(statusCode));
			}
			getMethod.releaseConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void getImgInfoFromResponse(){
		try{
			List<ImageBean> list = imageDao.findImage(36, "FD");
			for(ImageBean img:list){
				getResponseHeaders(img.getHttpUrl());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
