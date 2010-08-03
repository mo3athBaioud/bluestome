package com.chinamilitary.htmlparser;

import java.util.HashMap;

import com.chinamilitary.bean.LinkBean;

public abstract class AbstractParser {

	public abstract HashMap<String,LinkBean> getLink(String url) throws Exception;
	
	public abstract HashMap<String,LinkBean> getLink(String url,String attribute,String value) throws Exception;
	
	public abstract HashMap<String,LinkBean> getDiv(String url) throws Exception;
	
	public abstract HashMap<String,LinkBean> getDiv(String url,String attribute,String value) throws Exception;
	
}
