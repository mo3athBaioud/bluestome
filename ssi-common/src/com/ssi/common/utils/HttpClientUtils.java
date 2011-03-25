package com.ssi.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;

/**
 * 
 * @author bluestome
 *
 */
public class HttpClientUtils {

	private static HttpClient httpclient = null;

	private static GetMethod getMethod = null;

	private static PostMethod postMethod = null;

	public static boolean validationURL(String url) {
		boolean success = true;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
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
		return true;
	}

	/**
	 * 对URL进行编码，为了防止中文地址无法识别的问题
	 * @param url
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 * 修改记录
	 * 2010-11-08
	 *  修改对URI中地址中的空格的处理，将空格转化为%20
	 */
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
				//字符串过滤 
				switch(c){
					case 32:
						sb.append("%20");
						break;
					case 42:
						sb.append("%2A");
						break;
					case 43:
						sb.append("%2B");
						break;
					case 44:
						sb.append("%2C");
						break;
//					case 45:
//						sb.append("%2D");
//						break;
//					case 46:
//						sb.append("%2E");
//						break;
//					case 47:
//						sb.append("%2F");
//						break;
//					case 58:
//						sb.append("%3A");
//						break;
					case 59:
						sb.append("%3B");
						break;
					case 60:
						sb.append("%3C");
						break;
					case 61:
						sb.append("%3D");
						break;
					case 62:
						sb.append("%3E");
						break;
					case 63:
						sb.append("%3F");
						break;
					case 64:
						sb.append("%40");
						break;
					case 91:
						sb.append("%5B");
						break;
					case 92:
						sb.append("%5C");
						break;
					case 93:
						sb.append("%5D");
						break;
//					case 94:
//						sb.append("%5E");
//						break;
//					case 95:
//						sb.append("%5F");
//						break;
					case 96:
						sb.append("%60");
						break;
					default:
						sb.append(c);
						break;
				}
			}
		}
		System.out.println(">> url:"+sb.toString());
		return sb.toString();
	}

	public static boolean urlValidation(String url) {
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));

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
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
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
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));

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
	 * 从url中获取响应头的内容
	 * 
	 * @param url
	 * @param headerName
	 * @return
	 */
	public static String getHttpHeaderResponse(String url, String headerName) {
		String result = null;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));

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
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				value = new String(getMethod.getResponseBody(), "GB2312");
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
	
	/**
	 * 获取网站的响应代码
	 * @param url
	 * @return
	 */
	public static int getResponseCode(String url){
		int statusCode = -200;
		try{
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			statusCode = httpclient.executeMethod(getMethod);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (null != getMethod)
				getMethod.releaseConnection();
			if (null != httpclient)
				httpclient = null;
		}
		return statusCode;
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
		String contentType = null;
		try {
			httpclient = new HttpClient();
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));
			// 破解防盗链配置
			if (null != refence)
				getMethod.addRequestHeader("Referer", refence);
			if (null != cookie)
				getMethod.setRequestHeader("Cookie", cookie);
			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				contentType = getMethod.getResponseHeader("Content-Type").getValue();
				value = getMethod.getResponseBody();
			}
			System.out.println("contentType:"+contentType);
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
			getMethod = new GetMethod(encodeURL(url,"UTF-8"));

			getMethod.setRequestHeader("Referer", referUrl);
			// getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows;
			// U; Windows NT 6.1; zh-CN; rv:1.9.2.8) Gecko/20100722
			// Firefox/3.6.8");
			// getMethod.setRequestHeader("Cookie", "rtime=2;
			// ltime=1282990509523;
			// cnzz_eid=5808015-1282816593-http%3A//www.tuku.cn/;
			// virtualwall=vsid=0c8cafa6001de309645c11edffa3aa43");
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

	public static void main(String args[]) {

		try {
			// byte[] value2 = getResponseBodyAsByte(null,
			// null,"http://www.bizhizhan.com/uploads/allimg/090830/1-0ZS0102521.jpg");
			// if(null != value2){
			// System.out.println("未增加破解防盗链引用文件长度:"+value2.length);
			// }
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
//			String uri= "http://www.showimg.com/star/my1200994724/big/张学友/Famke_Janssen _22780685.jpg";
//			try{
//				System.out.println(encodeURL(uri,"UTF-8"));
//			}catch(Exception e){
//				e.printStackTrace();
//			}

			// System.out.println("isTRUE:"+urlValidation("http://www.bizhi.com/wallpaper/1150_2.html"));
			
			
//			String url = "http://www.showimg.com/nature/nature20100914/big/02833_034_ZOabx1li[1].jpg";
//			byte[] big = getResponseBodyAsByte(null,null,url);
//			System.out.println("长度:"+big.length);
			int statusCode = getResponseCode("http://www.163.com/");
			System.out.println(" >> 站点状态："+statusCode);
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
