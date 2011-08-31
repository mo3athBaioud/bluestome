package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupGlobleRoomWelcomeWords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String welcomeWord;
	private Short active;
	private String creator;
	private Date createTime;
	
	public Short getActive() {
		return active;
	}
	public void setActive(Short active) {
		this.active = active;
	}
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
	public String getWelcomeWord() {
		return welcomeWord;
	}
	public void setWelcomeWord(String welcomeWord) {
		this.welcomeWord = welcomeWord;
	}
	
	
}
