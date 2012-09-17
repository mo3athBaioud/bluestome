package com.bluestome.hzti.qry.net;

import com.bluestome.hzti.qry.common.Constants;

import junit.framework.Assert;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class QueryTools {
	
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
                String value = input.getAttribute("value");
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
    
	public static void doQuery(String carType,String carNum,String carId,String checkCode){
		URL url = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStream in = null;
		ByteArrayOutputStream byteArray = null;
		StringBuffer sb = new StringBuffer();
		try{
			url = new URL(Constants.URL);
			connection = (HttpURLConnection)url.openConnection();
			connection.addRequestProperty("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
			connection.addRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			connection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.addRequestProperty("Connection", "keep-alive");
			connection.addRequestProperty("Origin", "http://www.hzti.com");
			connection.addRequestProperty("X-MicrosoftAjax", "Delta=true");
			connection.addRequestProperty("Accept", "*/*");
			connection.addRequestProperty("Referer", "http://www.hzti.com/service/qry/violation_veh.aspx?type=2&node=249"); //?type=2&node=249
			connection.addRequestProperty("Cookie", "ASP.NET_SessionId=syyzj1vj52zms155ygcexp45; isLoginedWeb=T");
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
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$hpzl", "UTF-8")+"="+URLEncoder.encode(carType, "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$steelno", "UTF-8")+"="+URLEncoder.encode(carNum, "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$identificationcode", "UTF-8")+"="+URLEncoder.encode(carId, "UTF-8"));
			sb.append("&");
			sb.append(URLEncoder.encode("ctl00$ContentPlaceHolder1$checkcode", "UTF-8")+"="+URLEncoder.encode(checkCode, "UTF-8"));
			sb.append("&");
			sb.append("__EVENTTARGET=");
			sb.append("&");
			sb.append("__EVENTARGUMENT=");
			sb.append("&");
			sb.append("__VIEWSTATE="+URLEncoder.encode(Constants.VIEWSTATE,"UTF-8"));
			sb.append("&");
			sb.append("__EVENTVALIDATION="+URLEncoder.encode(Constants.EVENTVALIDATION,"UTF-8"));
			
			sb.append("&");
			sb.append("__ASYNCPOST=true");
			sb.append("&");
			sb.append("ctl00$ContentPlaceHolder1$Button1="+URLEncoder.encode("查询", "UTF-8"));
			String requestBody = sb.toString();
			System.out.println("提交内容:\r\n"+requestBody);
			out = connection.getOutputStream();
			out.write(requestBody.getBytes());
			out.flush();
			out.close();
			
			int code = -1;
			code = connection.getResponseCode();
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
					System.out.println("响应内容:"+"\r\n"+result);
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

}
