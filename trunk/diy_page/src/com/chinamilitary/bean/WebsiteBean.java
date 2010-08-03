package com.chinamilitary.bean;

import java.io.Serializable;
import java.util.Date;

public class WebsiteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9138376143415042891L;
	private Integer id;
	private Integer parentId;
	private String url;
	private String name;
	private Date createtme;
	private Date modifytime;
	private Integer status;
	
	public WebsiteBean(){
	}

	public Date getCreatetme() {
		return createtme;
	}

	public void setCreatetme(Date createtme) {
		this.createtme = createtme;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	
	
	
}
