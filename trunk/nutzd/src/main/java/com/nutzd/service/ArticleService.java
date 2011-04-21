package com.nutzd.service;

import org.nutz.ioc.aop.Aop;

public class ArticleService {
	
	@Aop("MyMethodInterceptor")
	public void insert(){
		System.out.println("没人知道我在干什么的!");
	}
	
	public static void main(String args[]){
		ArticleService art = new ArticleService();
		art.insert();
	}
}
