package com.httpclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientUtils {
	
	private static HttpClient httpclient = null;
	
	private static GetMethod getMethod = null;
	
	private static PostMethod postMethod = null;
	
	
	public static boolean validationURL(String url){
		boolean success = true;
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			//请求次数
//			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				success = false;
			}
			}catch(IOException ioe){
//				System.err.println("HttpClient.validationURL.IOException:"+ioe.getMessage());
				success = false;
				return false;
			}catch(Exception e){
//				System.err.println("HttpClient.validationURL.Exception:"+e.getMessage());
				success = false;
				return false;
			}finally{
				if(null != getMethod)
					getMethod.releaseConnection();
				if(null != httpclient)
					httpclient = null;
			}
		return true;
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(!getMethod.isAborted())
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		return true;
	}
	
	static void test1(String url) {
		long start = System.currentTimeMillis();
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
//			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			System.out.println("URL:"+url+",状态码:"+statusCode);
			if (statusCode != HttpStatus.SC_OK) {
//				System.err.println("Method failed: "+ getMethod.getStatusLine());
//				System.err.println("URL: "+ url);
//				System.out.println();
			}
			if(null != getMethod)
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if( null != getMethod )
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时:"+(end-start));
	}
	
	/**
	 * 获取响应头
	 * @param url
	 * @return
	 */
	static HashMap<String,String> getHttpHeaderResponse(String url){
		HashMap<String,String> result = new HashMap<String,String>();
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			
			int statusCode = httpclient.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK){
				Header[] headers = getMethod.getResponseHeaders();
				for(Header header:headers){
//					System.out.println(header.getName()+":"+header.getValue());
					result.put(header.getName(), header.getValue());
				}
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if( null != getMethod )
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		return result;
	}
	
	/**
	 * 从url中获取响应头的内容
	 * @param url
	 * @param headerName
	 * @return
	 */
public static String getHttpHeaderResponse(String url,String headerName){
		String result = null;
		long start = System.currentTimeMillis();
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			
			int statusCode = httpclient.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK){
				result = getMethod.getResponseHeader(headerName).getValue();
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if( null != getMethod )
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时:"+(end-start));
		return result;
	}

	/**
	 * 获取响应体
	 * @param url
	 * @return
	 */
	public static String getResponseBody(String url){
		String value = "";
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			int statusCode = httpclient.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK){
				value = new String(getMethod.getResponseBody(),"GB2312");
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if( null != getMethod )
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		return value;
	}
	
	public static void main(String args[]){
		
//		boolean success = validationURL("http://www.google.com.hk");
//		System.out.println(success?"成功":"失败");
		
//		test1("http://www.google.com.hk");
		
//		String value = getHttpHeaderResponse("http://image.tuku.china.com/tuku.military.china.com/military//pic/2010-08-10/693fa27b-3aad-477e-bf87-aadd947ee03f.jpg").get("Content-Length");
//		String value = getHttpHeaderResponse("http://tuku.game.china.com/game/html/2010-08-19/149536.xml", "Content-Body");
//		System.out.println("value:"+value);
		String value = "";
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod("http://tuku.game.china.com/game/html/2010-08-19/149536.xml");
			
			int statusCode = httpclient.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK){
				value = new String(getMethod.getResponseBody(),"UTF-8");
//				result = getMethod.getResponseHeader(headerName).getValue();
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			if( null != getMethod )
				getMethod.releaseConnection();
			if(null != httpclient)
				httpclient = null;
		}
		if(!"".equalsIgnoreCase(value)){
//			System.out.println("value:"+value);
			int start = value.indexOf("intro=")+6;
			int end = value.indexOf("comment");
			String tmp = value.substring(start,end);
			System.out.println("value:"+value.replace(tmp, "\"\" "));
		}
	}

}
