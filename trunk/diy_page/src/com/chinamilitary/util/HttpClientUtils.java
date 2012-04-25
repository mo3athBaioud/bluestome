package com.chinamilitary.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientUtils {

	private static HttpClient httpclient = null;

	private static GetMethod getMethod = null;

	private static PostMethod postMethod = null;

	//HTTP响应头中的文件大小描述
	public static String CONTENTLENGTH = "Content-Length";
	
	public static boolean validationURL(String url) {
		boolean success = true;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			// 请求次数
			// getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new
			// DefaultHttpMethodRetryHandler());
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				success = false;
			}
		} catch (IOException ioe) {
			// System.err.println("HttpClient.validationURL.IOException:"+ioe.getMessage());
			success = false;
			return false;
		} catch (Exception e) {
			// System.err.println("HttpClient.validationURL.Exception:"+e.getMessage());
			success = false;
			return false;
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return success;
	}

	public static boolean urlValidation(String url) {
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);

			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("地址[" + url + "],状态码[" + statusCode + "]");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!getMethod.isAborted())
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return true;
	}

	static void test1(String url) {
		long start = System.currentTimeMillis();
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			// getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new
			// DefaultHttpMethodRetryHandler());
			// 执行getMethod
			int statusCode = httpclient.executeMethod(getMethod);
			System.out.println("URL:" + url + ",状态码:" + statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				// System.err.println("Method failed: "+
				// getMethod.getStatusLine());
				// System.err.println("URL: "+ url);
				// System.out.println();
			}
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时:" + (end - start));
	}

	/**
	 * 获取响应头
	 * 
	 * @param url
	 * @return
	 */
	static HashMap<String, String> getHttpHeaderResponse(String url) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);

			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				Header[] headers = getMethod.getResponseHeaders();
				for (Header header : headers) {
					// System.out.println(header.getName()+":"+header.getValue());
					result.put(header.getName(), header.getValue());
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
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
		try{
			URL urlc = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)urlc.openConnection();
			conn.setDoInput(true);
			conn.connect();
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				result = String.valueOf(conn.getContentLength());
			}
		}catch(Exception e){
			System.err.println(" > ERROR:"+e);
		}finally{
			System.out.println(" > 获取文件大小耗时:["+(System.currentTimeMillis()-start)+"]ms");
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
		String result = "";
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);

			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				Header[] headers = getMethod.getResponseHeaders();
				for (Header header : headers) {
//					System.out.println(header.getName()+":"+header.getValue());
					if(headerName.equals(header.getName())){
						 result = header.getValue();
						 break;
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
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
		String value = "";
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				value = new String(getMethod.getResponseBody(), "UTF-8");
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return value;
	}

	/**
	 * 获取响应体
	 * 
	 * @param url
	 * @return
	 */
	public static String getResponseBody(String url,String charset) {
		String value = "";
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				value = new String(getMethod.getResponseBody(), charset);
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return value;
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
		byte[] value = null;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			getMethod.addRequestHeader(headerName, headerValue);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				value = getMethod.getResponseBody();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return value;
	}
	
	/**
	 * 获取指定URL内容
	 * @param url
	 * @return
	 */
	public static byte[] getBody(String url){
		byte[] value = null;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				value = getMethod.getResponseBody();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
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
		byte[] value = null;
		HttpClientParams params = null;
		Header charset = null;
		try {
			httpclient = new HttpClient();
			// params.setContentCharset("GBK");
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			// getMethod.setParams(params);

			// 破解防盗链配置
			if (null != refence)
				getMethod.addRequestHeader("Referer", refence);
			if (null != cookie)
				getMethod.setRequestHeader("Cookie", cookie);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
//				charset = getMethod.getResponseHeader("charset");
//				if(null ==  charset){
//					
//				}else{
					value = getMethod.getResponseBody();
//				}
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return value;
	}

	public static boolean referTest(String referUrl, String url)
			throws Exception {
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Referer", referUrl);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("地址[" + url + "],状态码[" + statusCode + "]");
				return false;
			}
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
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
		String url = "http://172.18.1.21:8081/upload/upload.cgi";
		try{
			httpclient = new HttpClient();
			postMethod = new PostMethod(encodeURL(url,"UTF-8"));
			
			int statusCode = httpclient.executeMethod(postMethod);
			Thread.sleep(2000);
			System.out.println(" >> :"+statusCode);
		}catch(Exception e){
			
		}finally {
			if (null != postMethod)
				postMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
	}
	
	public static void main(String args[]) {
		String url = "http://www.showimg.com/other/jingxuan20101102/big/jingxuan003[1].jpg";
		try {
//			 url = url.replace("[", URLEncoder.encode("[", "utf-8")).replace("]", URLEncoder.encode("]", "utf-8"));
//			 System.out.println("url:"+url);
//			 if(null == url)
//				 return;
//			 byte[] value2 = getResponseBodyAsByte(null,
//			 null,url);
//			 if(null != value2){
//			 System.out.println("未增加破解防盗链引用文件长度:"+value2.length);
//			 }
			//			
			// long start = System.currentTimeMillis();
			// byte[] value =
			// getResponseBodyAsByte("http://www.bizhizhan.com//renwenjijiabizhi/shoujitupian-30-4598.html",
			// "rtime=2; ltime=1282990509523;
			// cnzz_eid=5808015-1282816593-http%3A//www.tuku.cn/;
			// virtualwall=vsid=0c8cafa6001de309645c11edffa3aa43",
			// "http://www.bizhizhan.com/uploads/allimg/090830/1-0ZS0102521.jpg");
			// if(null != value){
			// long end = System.currentTimeMillis();
			// System.out.println("增加破解防盗链引用后的文件长度:"+value.length);
			// System.out.println("耗时:"+(end-start));
			// IOUtil.createFile(value,
			// System.getProperty("user.dir")+"/"+"1-0ZS0102521.jpg");
			// }

			// http://www.china.com/zh_cn/ 长度[2010-09-06 18:52]：173574
//			String length = getHttpHeaderResponse(
//					"http://www.china.com/zh_cn/", "Content-Length");
//			System.out.println("网页长度：" + length);
			// System.out.println("isTRUE:"+urlValidation("http://www.bizhi.com/wallpaper/1150_2.html"));
			
			//Last-Modified
//			String time = getHttpHeaderResponse("http://www.tupian.com/", "Last-Modified");
//			System.out.println(" >> ora:"+time);
//			Date date = DateUtils.parserDate(time);
//			System.out.println(" >> time:"+DateUtils.formatDate(date, DateUtils.FULL_STANDARD_PATTERN2));
			
			
			//2011-06-11
			post();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
	}

}
