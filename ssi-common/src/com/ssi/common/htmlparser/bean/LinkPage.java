package com.ssi.common.htmlparser.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 链接分页对象
 * @author bluestome
 * @since 2011-04-06
 *
 */
public class LinkPage {

	/**
	 * 单例模式类属性
	 */
	public static LinkPage instance = null;
	
	/**
	 * 是否包含分页
	 */
	private boolean hasPage = false;
	
	/**
	 * 列表对象
	 */
	private List<LinkBean> pageList =  new ArrayList<LinkBean>();
	
	/**
	 * Map对象
	 */
	private HashMap<String,LinkBean> pageMap = new HashMap<String,LinkBean>();
	
	/**
	 * 页数
	 */
	private int pageCount = -1;

	/**
	 * 私有构造函数
	 *
	 */
	private LinkPage(){
	}
	
	public boolean isHasPage() {
		return hasPage;
	}

	public void setHasPage(boolean hasPage) {
		this.hasPage = hasPage;
	}

	/**
	 * 获取分页总数
	 * @return
	 */
	public int getPageCount() {
		if(null != pageList && pageList.size() > 0){
			pageCount = pageList.size();
		}else if(null != pageMap && pageMap.size() > 0){
			pageCount = pageMap.size();
		}else{
			pageCount = 0;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<LinkBean> getPageList() {
		return pageList;
	}

	public void setPageList(List<LinkBean> pageList) {
		this.pageList = pageList;
	}

	public HashMap<String, LinkBean> getPageMap() {
		return pageMap;
	}

	public void setPageMap(HashMap<String, LinkBean> pageMap) {
		this.pageMap = pageMap;
	}
	
	public synchronized void add(LinkBean bean){
		pageList.add(bean);
	}
	
	public synchronized void put(String key,LinkBean value){
		pageMap.put(key, value);
	}
	
	public synchronized void clear(){
		if(pageMap.size() > 0){
			pageMap.clear();
		}
		if(pageList.size() > 0){
			pageList.clear();
		}
	}
	
	/**
	 * 单例模式获取实例
	 * @return
	 */
	public static LinkPage getInstance(){
		if(null == instance){
			instance = new LinkPage();
		}
		return instance;
	}
}
