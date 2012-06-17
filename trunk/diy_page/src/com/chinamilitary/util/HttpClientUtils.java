package com.chinamilitary.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.IOUtils;

public class HttpClientUtils {

	//HTTP响应头中的文件大小描述
	public static String CONTENTLENGTH = "Content-Length";
	
	public static boolean validationURL(String url) {
		boolean success = false;
		URL sUrl = null;
		HttpURLConnection conn = null;
		try {
			sUrl = new URL(url);
			conn = (HttpURLConnection)sUrl.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.connect();
			long ss = System.currentTimeMillis();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				System.out.println("\t>>>>> 获取状态码耗时:[" + (System.currentTimeMillis()-ss)+"]ms");
				success = true;
			}
		} catch (Exception e) {
		} finally {
			if(null != conn){
				conn.disconnect();
				conn = null;
			}
			if(null != sUrl){
				sUrl = null;
			}
		}
		return success;
	}

	public static boolean urlValidation(String url) {
		boolean success = false;
		URL sUrl = null;
		HttpURLConnection conn = null;
		try {
			sUrl = new URL(url);
			conn = (HttpURLConnection)sUrl.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.connect();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				success = true;
			}
		} catch (Exception e) {
		} finally {
			if(null != conn){
				conn.disconnect();
				conn = null;
			}
			if(null != sUrl){
				sUrl = null;
			}
		}
		return success;
	}

	/**
	 * 获取响应头
	 * 
	 * @param url
	 * @return
	 */
	static HashMap<String, String> getHttpHeaderResponse(String url) {
		HashMap<String, String> result = new HashMap<String, String>();
		URL urlO = null;
		HttpURLConnection http = null;
		try {
			urlO = new URL(url);
			http = (HttpURLConnection)urlO.openConnection();
			http.connect();
			int code = http.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				Map<String, List<String>> responseHeader = http.getHeaderFields();
				Set<String> keySet = responseHeader.keySet();
				Iterator iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = (String)iterator.next();
					if(null != key){
						List<String> valueList = responseHeader.get(key);
						for(String value:valueList){
							result.put(key, value);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(null != http){
				http.disconnect();
			}
		}
		return result;
	}

	/**
	 * 调用HttpURLConnection获取文件大小
	 * @param url
	 * @return
	 */
	public static String getHttpConentLength(String url){
		long start = System.currentTimeMillis();
		String result = "0";
		URL urlc = null;
		HttpURLConnection conn = null;
		try{
			urlc = new URL(url);
			conn = (HttpURLConnection)urlc.openConnection();
			conn.setDoInput(true);
			conn.connect();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				result = String.valueOf(conn.getContentLength());
			}
		}catch(Exception e){
			System.err.println(" > ERROR:"+e);
		}finally{
			System.out.println(" > 获取文件大小耗时:["+(System.currentTimeMillis()-start)+"]ms");
			if(null != conn){
				conn.disconnect();
			}
		}
		return result;
	}
	
	
	/**
	 * 从url中获取响应头的内容
	 * 
	 * @param url
	 * @param headerName
	 * @return
	 */
	public static String getHttpHeaderResponse(String url, String headerName) {
		String result = null;
		URL urlO = null;
		HttpURLConnection http = null;
		try {
			urlO = new URL(url);
			http = (HttpURLConnection)urlO.openConnection();
			http.connect();
			int code = http.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				Map<String, List<String>> responseHeader = http.getHeaderFields();
				Set<String> keySet = responseHeader.keySet();
				Iterator iterator = keySet.iterator();
				while(iterator.hasNext()){
					String key = (String)iterator.next();
					if(null != key && key.trim().length() > 0){
						if(headerName.equals(key)){
							List<String> valueList = responseHeader.get(key);
							for(String value:valueList){
								result = value;
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(null != http){
				http.disconnect();
			}
		}
		return result;
	}

	/**
	 * 获取响应体
	 * 
	 * @param url
	 * @return
	 */
	public static String getResponseBody(String url) {
		String result = null;
		URL urlO = null;
		HttpURLConnection http = null;
		InputStream is = null;
		try {
			urlO = new URL(url);
			http = (HttpURLConnection)urlO.openConnection();
			http.connect();
			int code = http.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				is = http.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int ch;
				while((ch = is.read()) != -1){
					out.write(ch);
				}
				out.flush();
				//默认使用当前系统的字符集
				result = out.toString();
				out.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(null != http){
				http.disconnect();
			}
		}
		return result;
	}

	/**
	 * 获取响应体
	 * 
	 * @param url
	 * @return
	 */
	public static String getResponseBody(String url,String charset) {
		String result = null;
		URL urlO = null;
		HttpURLConnection http = null;
		InputStream is = null;
		try {
			urlO = new URL(url);
			http = (HttpURLConnection)urlO.openConnection();
			http.connect();
			int code = http.getResponseCode();
			if(code == HttpURLConnection.HTTP_OK){
				is = http.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int ch;
				while((ch = is.read()) != -1){
					out.write(ch);
				}
				out.flush();
				result = out.toString(charset);
				out.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if(null != http){
				http.disconnect();
			}
		}
		return result;
	}
	
	/**
	 * 获取响应体
	 * 
	 * @param url
	 *            实际下载地址
	 * @return
	 */
	public static byte[] getResponseBodyAsByte(String url) {
		byte[] value = null;
		value = getResponseBodyAsByte(null, null, url);
		return value;
	}

	/**
	 * 获取响应体
	 * 
	 * @param cookie
	 *            cookie内容
	 * @param url
	 *            实际下载地址
	 * @return
	 */
	public static byte[] getResponseBodyAsByte(String cookie, String url) {
		byte[] value = null;
		value = getResponseBodyAsByte(null, cookie, url);
		return value;
	}

	public static String encodeURL(String url, String encode)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		StringBuilder noAsciiPart = new StringBuilder();
		for (int i = 0; i < url.length(); i++) {
			char c = url.charAt(i);
			if (c > 255) {
				noAsciiPart.append(c);
			} else {
				if (noAsciiPart.length() != 0) {
					sb
							.append(URLEncoder.encode(noAsciiPart.toString(),
									encode));
					noAsciiPart.delete(0, noAsciiPart.length());
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取指定URL的内容
	 * @param url 
	 * @param header 请求头名
	 * @param headerValue 请求头值
	 * @return
	 */
	public static int getBodyLength(String url){
		int value = -1;
		URL cURL = null;
		URLConnection connection = null;
		try{
			cURL = new URL(url);
			connection = cURL.openConnection();
			//获取输出流
			connection.setDoOutput(true);
			connection.setConnectTimeout(5*1000);
			connection.connect();
			
			value = connection.getContentLength();
		}catch(Exception e){
			System.err.println("ERROR:"+e);
		}
		return value;
	}
	
	/**
	 * 获取指定URL的内容
	 * @param url 
	 * @param header 请求头名
	 * @param headerValue 请求头值
	 * @return
	 */
	public static int getBodyLength(String url,String header,String headerValue){
		int value = -1;
		URL cURL = null;
		URLConnection connection = null;
		try{
			cURL = new URL(url);
			connection = cURL.openConnection();
			//获取输出流
			connection.setDoOutput(true);
			connection.addRequestProperty(header, headerValue);
			connection.setConnectTimeout(5*1000);
			connection.connect();
			
			value = connection.getContentLength();
		}catch(Exception e){
			System.err.println("ERROR:"+e);
		}
		return value;
	}
	
	/**
	 * 获取指定URL内容
	 * @param url
	 * @param headerName 请求头名
	 * @param headerValue 请求头内容
	 * @return
	 */
	public static byte[] getBody(String url,String headerName,String headerValue){
		byte[] value =null;
		URL cURL = null;
		URLConnection connection = null;
		InputStream is = null;
		try{
			cURL = new URL(url);
			connection = cURL.openConnection();
			//获取输出流
			connection.setDoOutput(true);
			connection.setConnectTimeout(5*1000);
			connection.addRequestProperty(headerName, headerValue);
			connection.connect();
			int length = connection.getContentLength();
			is = connection.getInputStream();
			if(length > 0){
				value = IOUtils.toByteArray(is);
			}
		}catch(Exception e){
			System.err.println("ERROR:"+e);
		}finally{
			if(null != is){
				IOUtils.closeQuietly(is);
			}
		}
		return value;
	}
	
	/**
	 * 获取指定URL内容
	 * @param url
	 * @return
	 */
	public static byte[] getBody(String url){
		byte[] value =null;
		URL cURL = null;
		URLConnection connection = null;
		InputStream is = null;
		try{
			cURL = new URL(url);
			connection = cURL.openConnection();
			//获取输出流
			connection.setDoOutput(true);
			connection.setConnectTimeout(10*1000);
//			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.0.3; de-ch; HTC Sensation Build/IML74K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
			connection.connect();
			int length = connection.getContentLength();
			is = connection.getInputStream();
			if(length > 0){
				value = IOUtils.toByteArray(is);
			}
		}catch(Exception e){
			System.err.println("ERROR:"+e);
		}finally{
			if(null != is){
				IOUtils.closeQuietly(is);
			}
		}
		return value;
	}

	/**
	 * 获取响应体
	 * 
	 * @param refence
	 *            引用的地址
	 * @param cookie
	 *            页面保存的信息
	 * @param url
	 * @return
	 */
	public static byte[] getResponseBodyAsByte(String refence, String cookie,
			String url) {
		byte[] result = null;
		URL urlO = null;
		HttpURLConnection http = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
		try {
			urlO = new URL(url);
			http = (HttpURLConnection) urlO.openConnection();
			if (null != refence) {
				http.addRequestProperty("Referer", refence);
			}
			if (null != cookie) {
				http.addRequestProperty("Cookie", cookie);
			}
			http.connect();
			int code = http.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				int length = http.getContentLength();
				System.out.println("\t>>>>>> 响应体内容大小：" + length);
				if (length > 0) {
					out = new ByteArrayOutputStream();
					is = http.getInputStream();
					int ch;
					while ((ch = is.read()) != -1) {
						out.write(ch);
					}
					out.flush();
					result = out.toByteArray();
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != http) {
				http.disconnect();
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 获取页面的最后修改时间
	 * @param url
	 * @return
	 */
	public static String getLastModifiedByUrl(String url){
		String value = "1970-01-01 00:00:00";
		URL cURL = null;
		try{
			cURL = new URL(url);
			URLConnection connection = cURL.openConnection();
			//获取输出流
			connection.setDoOutput(true);
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			connection.setConnectTimeout(5*1000);
			connection.connect();
			
			String time = connection.getHeaderField("Last-Modified");
			Date date = DateUtils.parserDate(time);
			value = DateUtils.formatDate(date, DateUtils.FULL_STANDARD_PATTERN2);
		}catch(Exception e){
			System.err.println("ERROR:"+e);
		}		
		return value;
	}

	/**
	 * 执行POST
	 *
	 */
	static void post(){
		long start = System.currentTimeMillis();
		String url = "http://172.18.1.21:8081/upload/upload.cgi";
		URL urlO = null;
		HttpURLConnection http = null;
		try{
			urlO = new URL(url);
			http = (HttpURLConnection)urlO.openConnection();
			http.setConnectTimeout(15*1000);
			http.setReadTimeout(15*1000);
			http.setRequestMethod("POST");
			http.connect();
			int statusCode = http.getResponseCode();
			System.out.println(" >> :"+statusCode);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			System.out.println("\t>>>> HTTP连接耗时:"+(System.currentTimeMillis()-start));
			if(null != http){
				http.disconnect();
			}
		}
	}
	
	public static void main(String args[]) {
		String url = "http://bizhi.zhuoku.com/2012/06/16/zhuoku/Zhuoku004.jpg";
		try {
			post();
			getResponseBodyAsByte("http://www.zhuoku.com",null,url);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
		}
	}

}
