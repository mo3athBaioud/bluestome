package com.ssi.common.htmlparser.bean;

import java.io.Serializable;
/**
 * 链接对象
 * @author bluestome
 * @since 2011-04-06
 *
 */
public class LinkBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 链接地址
	 */
	private String url;
	
	/**
	 * 链接标题
	 */
	private String title;
	
	/**
	 * 是否包含缩略图
	 */
	private boolean thumb = false;
	
	/**
	 * 缩略图链接
	 */
	private String thumbUrl =  null;
	
	/**
	 * 发布时间
	 */
	private String publishTime = null;
	
	public LinkBean(){
	}
	
	public LinkBean(String url,String title){
		this.url = url;
		this.title = title;
	}
	
	public LinkBean(String url,String title,boolean thumb,String thumbUrl){
		this.url = url;
		this.title = title;
		this.thumb = thumb;
		this.thumbUrl = thumbUrl;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isThumb() {
		if(null != thumbUrl && !"".equals(thumbUrl)){
			return true;
		}
		return false;
	}

	public void setThumb(boolean thumb) {
		this.thumb = thumb;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
