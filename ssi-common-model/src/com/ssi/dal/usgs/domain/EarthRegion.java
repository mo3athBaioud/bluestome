package com.ssi.dal.usgs.domain;

import java.io.Serializable;
import java.util.Date;

public class EarthRegion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String url;
	private String desc;
	private Integer status = 1;
	private Date createtime = new Date();
	private Date modifytime;
	
	public EarthRegion(){
	}
	
	public EarthRegion(String name,String url){
		this.name = name;
		this.url = url;
		this.desc = name;
	}

	public EarthRegion(String name,String url,String desc){
		this.name = name;
		this.url = url;
		this.desc = desc;
	}
	
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
