package com.takesoon.oms.ssi.entity;

import java.util.Date;

import javax.persistence.Transient;

import net.sf.json.util.JSONBuilder;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.skymobi.qunker.util.JSONUtils;

public abstract class AbstractEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8636890432022276216L;
	@Transient
	protected String json;
	@Transient
	private Date startDate;
	@Transient
	private Date endDate;
	
	@Transient
	private Integer start = 0;
	
	@Transient
	private Integer limit = 15;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	//将对象转换成JSON格式
	public String toJson(){
		return JSONUtils.bean2Json(this);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
	
}
