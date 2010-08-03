package com.chinamilitary.bean;

public class LinkBean {
	private String link;

	private String name;

	public LinkBean() {
	}
	
	public LinkBean(String name,String link){
		this.name = name;
		this.link = link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

}
