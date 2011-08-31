package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupGlobleAdminSkyid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long skyId;
	private String creator;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSkyId() {
		return skyId;
	}
	public void setSkyId(Long skyId) {
		this.skyId = skyId;
	}
	
	
}
