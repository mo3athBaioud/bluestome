package com.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chinamilitary.bean.WebsiteBean;

public class URLMatchTest {

	private List<String> urlList = new ArrayList<String>();
	private List<String> resultList = new ArrayList<String>();
	String[] stringarray = {};
	static String URL = "http://www.zhuoku.com/zhuomianbizhi/movie-oumei/20111006134002.htm";
	@Before
	public void init(){
		urlList.add("http://www.9ku.com/");
		urlList.add("http://sc.chinaz.com/");
		urlList.add("http://www.zhuoku.com/");
		urlList.add("http://www.zhuoku.com/new/");
		urlList.add("http://desk.zhuoku.com/zhuomianzhuti/533");
		urlList.add("http://www.zhuoku.com/zhuomianbizhi/");
		urlList.add("http://www.zhuoku.com/zhuomianbizhi/movie-oumei/");
	}
	
	@After
	public void destory(){
		if(null != urlList && urlList.size() > 0){
			urlList.clear();
			urlList = null;
		}
		
		if(null != resultList && resultList.size() > 0){
			resultList.clear();
			resultList = null;
		}
		
	}
	
	@Test
	public void run(){
		int i=0;
		for(String url:urlList){
			if(URL.startsWith(url)){
				resultList.add(url);
			}
		}
		
		
		
		if(null != resultList && resultList.size() > 0){
			stringarray = new String[resultList.size()];
			for(int j=0;j<resultList.size();j++){
				stringarray[j] = resultList.get(j);
			}
			
			sort(stringarray);
			System.out.println(stringarray[0]);
		}
	}
	
	/**
	 * 字符长度从大到小排序
	 * @param s1
	 */
	void sort(String[] s1){
		String tmp;
		for(int i=0;i<s1.length;i++){
			for(int j=0;j<s1.length-i-1;j++){
				if(s1[j].length() < s1[j+1].length() ){
					tmp = s1[j];
					s1[j] = s1[j+1];
					s1[j+1] = tmp;
				}
			}
		}
	}
	
	
	void sort(List<WebsiteBean> wlist){
		WebsiteBean tmp;
		for(int i=0;i<wlist.size();i++){
			for(int j=0;j<wlist.size()-i-1;j++){
				WebsiteBean w1 = wlist.get(j);
				WebsiteBean w2 = wlist.get(j+1);
				if(null != w1 && null != w2){
					if(w1.getUrl().length() < w2.getUrl().length()){
						tmp = w1;
						w1 = w2;
						w2 = tmp;
					}
				}
			}
		}
	}

}
