package com.hzti;

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
	
	
	/**
	 * 查询页面
	 *
	 */
	public static void queryHTML(){
		Parser p1 = null;
		byte[] content = null;
		try{
			content = HttpClientUtils.getResponseBodyAsByte("http://www.hzti.com/", null, URL);
			if(null == content){
				return;
			}
			
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
				System.out.println("__EVENTARGUMENT:"+value.length());
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
				System.out.println("__EVENTTARGET:"+value.length());
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
				System.out.println("__EVENTVALIDATION:"+value.length());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != p1){
				p1 = null;
			}
		}
	}
	
	public static void main(String args[]){
		queryHTML();
	}
}
