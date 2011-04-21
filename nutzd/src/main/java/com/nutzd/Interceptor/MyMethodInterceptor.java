package com.nutzd.Interceptor;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.interceptor.AbstractMethodInterceptor;

public class MyMethodInterceptor extends AbstractMethodInterceptor {

	@Override
	public void filter(InterceptorChain chain) throws Throwable {
		System.out.println("咦?这个方法执行了!");
		chain.doChain();// 继续执行其他拦截器
		System.out.println("这个方法执行完成了!");
	}

}
