package com.hzti;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.junit.Assert;

import com.chinamilitary.util.HttpClientUtils;

public class WeizhangQuery {

	public static String URL = "http://www.hzti.com/service/qry/violation_veh.aspx?type=2&node=249";
	public static String AUTH_CODE_URL = "http://www.hzti.com/government/CreateCheckCode.aspx&d="+System.currentTimeMillis();
	public static final String __EVENTARGUMENT = "__EVENTARGUMENT";
	public static final String __EVENTTARGET = "__EVENTARGUMENT";
	public static final String __EVENTVALIDATION = "__EVENTARGUMENT";
	
	/**
	 * 查询页面
	 *
	 */
	public static HashMap<String,String> queryHTML(byte[] content){
		Parser p1 = null;
		HashMap<String,String> values = new HashMap<String,String>();
		try{
			String body = new String(content,"UTF-8");
			p1 = new Parser();
			p1.setInputHTML(body);
			
			//获取__VIEWSTATE 参数
			NodeFilter fileter = new NodeClassFilter(InputTag.class);
			NodeList list = p1.extractAllNodesThatMatch(fileter)
					.extractAllNodesThatMatch(
							new HasAttributeFilter("id", "__VIEWSTATE"));
			if(null != list && list.size() > 0){
				InputTag input = (InputTag)list.elementAt(0);
				Assert.assertNotNull(input);
				
				String value = input.getAttribute("value");
				Assert.assertNotNull(value);
				System.out.println("__VIEWSTATE:"+value.length());
			}
			
			//获取__EVENTARGUMENT 参数
			p1.setInputHTML(body);
			fileter = new NodeClassFilter(InputTag.class);
			list = p1.extractAllNodesThatMatch(fileter)
			.extractAllNodesThatMatch(
					new HasAttributeFilter("id", "__EVENTARGUMENT"));
			if(null != list && list.size() > 0){
				InputTag input = (InputTag)list.elementAt(0);
				Assert.assertNotNull(input);
				
				String value = input.getAttribute("value");
				Assert.assertNotNull(value);
				values.put("__EVENTARGUMENT", value);
			}
			
			//获取__EVENTTARGET 参数
			p1.setInputHTML(body);
			fileter = new NodeClassFilter(InputTag.class);
			list = p1.extractAllNodesThatMatch(fileter)
			.extractAllNodesThatMatch(
					new HasAttributeFilter("id", "__EVENTTARGET"));
			if(null != list && list.size() > 0){
				InputTag input = (InputTag)list.elementAt(0);
				Assert.assertNotNull(input);
				
				String value = input.getAttribute("value");
				Assert.assertNotNull(value);
				values.put("__EVENTTARGET", value);
			}
			
			//获取__EVENTVALIDATION 参数
			p1.setInputHTML(body);
			fileter = new NodeClassFilter(InputTag.class);
			list = p1.extractAllNodesThatMatch(fileter)
			.extractAllNodesThatMatch(
					new HasAttributeFilter("id", "__EVENTVALIDATION"));
			if(null != list && list.size() > 0){
				InputTag input = (InputTag)list.elementAt(0);
				Assert.assertNotNull(input);
				
				String value = input.getAttribute("value");
				Assert.assertNotNull(value);
				values.put("__EVENTVALIDATION", value);
			}
			
			//获取__VIEWSTATE 参数
			p1.setInputHTML(body);
			fileter = new NodeClassFilter(InputTag.class);
			list = p1.extractAllNodesThatMatch(fileter)
			.extractAllNodesThatMatch(
					new HasAttributeFilter("id", "__VIEWSTATE"));
			if(null != list && list.size() > 0){
				InputTag input = (InputTag)list.elementAt(0);
				Assert.assertNotNull(input);
				
				String value = input.getAttribute("value");
				Assert.assertNotNull(value);
				values.put("__VIEWSTATE", value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p1){
				p1 = null;
			}
		}
		return values;
	}
	
	public static void doQuery(HashMap<String,String> params){
		URL url = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream byteArray = null;
		StringBuffer sb = new StringBuffer();
		try{
			url = new URL(URL);
			connection = (HttpURLConnection)url.openConnection();
			connection.addRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
			connection.addRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			connection.addRequestProperty("Accept", "*/*");
			connection.addRequestProperty("Referer", "http://www.hzti.com/service/qry/violation_veh.aspx?type=2&node=249");
			connection.addRequestProperty("Cookie", "ASP.NET_SessionId=i5rklwmtposzhh55egotym55; isLoginedWeb=T");
			connection.addRequestProperty("Cache-Control", "no-cache");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.63 Safari/535.7");
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			connection.setReadTimeout(10*1000);
			connection.setConnectTimeout(15*1000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.connect();
			
			sb.append(URLEncoder.encode("ctl00$ScriptManager1", "UTF-8")+"="+URLEncoder.encode("ctl00$ContentPlaceHolder1$UpdatePanel1|ctl00$ContentPlaceHolder1$Button1", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$hpzl", "UTF-8")+"="+URLEncoder.encode("小型汽车", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$steelno", "UTF-8")+"="+URLEncoder.encode("浙A360DD", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$identificationcode", "UTF-8")+"="+URLEncoder.encode("007656", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$checkcode", "UTF-8")+"="+URLEncoder.encode("ntbs", "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$Button1", "UTF-8")+"="+URLEncoder.encode("查询", "UTF-8"));
			sb.append("&");
			sb.append("__EVENTTARGET=");
			sb.append("&");
			sb.append("__EVENTARGUMENT=");
			sb.append("&");
			sb.append("__ASYNCPOST=true");
			
			Iterator it = params.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = params.get(key);
				sb.append("&");
				sb.append(key+":"+value);
			}
			String requestBody = sb.toString();
//			String encoderd = URLEncoder.encode(requestBody, "UTF-8");
			System.out.println(requestBody);
			out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
			
			int code = connection.getResponseCode();
			switch(code){
				case 200:
					in = connection.getInputStream();
					byteArray = new ByteArrayOutputStream();
					int ch;
					while((ch = in.read()) != -1){
						byteArray.write(ch);
					}
					byteArray.flush();
					String result = byteArray.toString("UTF-8");
					System.out.println(result);
					break;
				default:
					System.err.println(connection.getResponseCode()+":"+connection.getResponseMessage());
					break;
			}
			
			if(null != out){
				out.close();
			}
			if(null != in){
				in.close();
			}
		}catch(Exception e){
			if(null != byteArray){
				try {
					byteArray.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(null != connection){
				connection.disconnect();
				connection = null;
			}
			
			if(null != url){
				url = null;
			}
			sb = null;
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		
		byte[] content = null;
		long start = System.currentTimeMillis();
		content = HttpClientUtils.getResponseBodyAsByte("http://www.hzti.com/service/qry/violation_veh.aspx?type=2&node=249", null, URL);
		if(null == content){
			System.out.println(" > 获取网页数据为空");
			return;
		}
		//获取一把验证码
		HttpClientUtils.validationURL(AUTH_CODE_URL);
		
		long spend = (System.currentTimeMillis()-start)/1000;
		System.out.println(">> 获取HTML耗时："+(System.currentTimeMillis()-start)+" ms");
		System.out.println(">> 下载速度："+((content.length/spend)/1024)+"."+((content.length/spend)%1024)+" KB/s");
		
		HashMap<String,String> map = queryHTML(content);
//		Iterator it = map.keySet().iterator();
//		while(it.hasNext()){
//			String key = (String)it.next();
//			String value = map.get(key);
//			System.out.println(key+"\r\n"+value+"\r\n"+"\r\n"+value.length()+"\r\n");
//		}
		
		doQuery(map);
	}
}
