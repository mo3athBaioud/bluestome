package com.ssi.dal.usgs.domain;

import java.io.Serializable;
import java.util.Date;

public class EarthQuakeDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	//地震信息ID
	private Integer infoid;

	// 位置 纬度+经度
	private String location;

	// 区域
	private String region;

	// 距离
	private String distinces;

	// 不确定位置Location Uncertainty
	private String lu;

	// 参数
	private String parameters;

	// 源
	private String source;

	// 标记
	private String remarks;

	// 事件编号
	private String eventId;

	private Date createtime = new Date();

	private Date modifytime;

	public String getDistinces() {
		return distinces;
	}

	public void setDistinces(String distinces) {
		this.distinces = distinces;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getInfoid() {
		return infoid;
	}

	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}

	
}
