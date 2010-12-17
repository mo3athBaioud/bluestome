package com.utils;

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

	/**
	 * 验证URL是否可用
	 * 
	 * @param url
	 * @return
	 */
	public static boolean validationURL(String url) {
		boolean success = true;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);
			// 请求次数
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				success = false;
			}
		} catch (IOException ioe) {
			success = false;
			return false;
		} catch (Exception e) {
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
	 * 从url中获取响应头的内容
	 * 
	 * @param url
	 *            地址
	 * @param headerName
	 *            头名
	 * @return
	 */
	public static String getHttpHeaderResponse(String url, String headerName) {
		String result = null;
		long start = System.currentTimeMillis();
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(url);

			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				result = getMethod.getResponseHeader(headerName).getValue();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时:" + (end - start));
		return result;
	}

	/**
	 * 获取响应体
	 * 
	 * @param url
	 *            请求的地址
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

	public static void main(String args[]) {

		String value = "";
		String url = "http://tuku.military.china.com/military/html/2010-04-02/139456.xml";
		String pushUrl = "http://localhost:8080/mrpmusicfs/mrpmusic.do";
		try {
			
//			boolean suc = validationURL(url);
			boolean suc = validationURL(pushUrl);
			
			System.out.println("suc:"+suc);
			
//			httpclient = new HttpClient();
//			getMethod = new GetMethod(
//					url);
//
//			int statusCode = httpclient.executeMethod(getMethod);
//			if (statusCode == HttpStatus.SC_OK) {
//				value = new String(getMethod.getResponseBody(), "UTF-8");
//				// result = getMethod.getResponseHeader(headerName).getValue();
//			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
//		if (!"".equalsIgnoreCase(value)) {
//			int start = value.indexOf("intro=") + 6;
//			int end = value.indexOf("comment");
//			String tmp = value.substring(start, end);
//			System.out.println("value:" + value.replace(tmp, "\"\" "));
//		}
	}

}
