package com.ssi.dal.usgs.domain;

import java.io.Serializable;
import java.util.Date;

public class EarthQuakeInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 序列号
	private Integer id;

	// 发布时间
	private String date = "";

	// 纬度
	private String latitude = "";

	// 经度
	private String longitude = "";

	// 震源深度
	private Float depth = 0f;

	// 震级
	private Float magnitude = 0f;

	// 备注
	private String comments = "";

	// 连接的URL
	private String url = "";

	private Date createtime = new Date();

	private Date modifytime;
	
	
	//地震详情
	private EarthQuakeDetail detail;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Float getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(Float magnitude) {
		this.magnitude = magnitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public EarthQuakeDetail getDetail() {
		return detail;
	}

	public void setDetail(EarthQuakeDetail detail) {
		this.detail = detail;
	}

	
}
