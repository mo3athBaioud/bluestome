package com.sky.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtils {
	/*
	 * 取得POST的内容，转化成字符串
	 */
	public static String getContentFromStream(InputStream in)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bLen = 0;
		while ((bLen = in.read(buffer)) > 0) {
			baos.write(buffer, 0, bLen);
		}
		String xml = new String(baos.toByteArray(),"UTF-8");
		baos.close();
		in.close();
		buffer = null;
		return xml;
	}
	/*
	 * 取得POST的内容，转化成字符串
	 */
	public static String getContentFromStream(InputStream in,String encoding)
	throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bLen = 0;
		while ((bLen = in.read(buffer)) > 0) {
			baos.write(buffer, 0, bLen);
		}
		String xml = new String(baos.toByteArray(),encoding);
		baos.close();
		in.close();
		buffer = null;
		return xml;
	}
	
	/*
	 * 取得POST的内容，转化成字符串
	 */
	public static byte[] getBytesFromStream(InputStream in)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int bLen = 0;
		while ((bLen = in.read(buffer)) > 0) {
			baos.write(buffer, 0, bLen);
		}
		in.close();
		return baos.toByteArray();
	}
	
	/**
	 * 发送HTTP请求,并返回结果
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String requestByGet(String url,String encoding)throws IOException{
		String response = null;
		GetMethod get = null;
		HttpClient client = null;
		try {
			client = new HttpClient();
			client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			get = new GetMethod(url);
//			get.setRequestHeader("Accept","application/xml");
			get.addRequestHeader("Connection", "close"); 
			client.executeMethod(get);
			response = getContentFromStream(get.getResponseBodyAsStream(),encoding);
		} catch (IOException e) {
			throw e;
		}finally{
			if(get!=null){
				get.releaseConnection();
			}
			client = null;
		}
		return response;
	}
	/**
	 * 发送HTTP请求,并返回结果
	 * @param url
	 * @param content
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static String requestByGet(String url)throws IOException{
		String response = null;
		GetMethod get = null;
		HttpClient client = null;
		try {
			client = new HttpClient();
			client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
			get = new GetMethod(url);
//			get.setRequestHeader("Accept","application/xml");
			get.addRequestHeader("Connection", "close"); 
			client.executeMethod(get);
			response = getContentFromStream(get.getResponseBodyAsStream());
		} catch (IOException e) {
			throw e;
		}finally{
			if(get!=null){
				get.releaseConnection();
			}
			client = null;
		}
		return response;
	}
	
	public static byte [] requestByPost(String url,byte [] bytes) throws IOException{
		HttpClient client = new HttpClient();
		client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Connection", "close"); 
		// post方式
		post.setRequestEntity(new ByteArrayRequestEntity(bytes,"text/xml; charset=UTF-8"));
		client.executeMethod(post);
		byte[] byteList = getBytesFromStream(post.getResponseBodyAsStream());
		post.releaseConnection();
		return byteList;
	}

    /**
     * 取得URL中的参数值。
     * <p>如不存在，返回空值。</p>
     * 
     * @param url
     * @param name
     * @return
     */
    public static String getParameter(String url, String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        name = name + "=";
        int start = url.indexOf(name);
        if (start < 0) {
            return null;
        }
        start += name.length();
        int end = url.indexOf("&", start);
        if (end == -1) {
            end = url.length();
        }
        return url.substring(start, end);
    }

}
