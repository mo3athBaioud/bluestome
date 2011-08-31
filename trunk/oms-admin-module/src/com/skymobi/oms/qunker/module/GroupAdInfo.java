package com.skymobi.oms.qunker.module;

import java.util.Date;

public class GroupAdInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String creator;
	private String imageId;
	private String link;
	private Long position;
	private Long screenWidth;
	private Long clickTimes;
	private Date beginTime;
	private Date endTime;
	private Short active;
	private String description;
	private Date createTime;
	
	public Short getActive() {
		return active;
	}
	public void setActive(Short active) {
		this.active = active;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Long getClickTimes() {
		return clickTimes;
	}
	public void setClickTimes(Long clickTimes) {
		this.clickTimes = clickTimes;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
	public Long getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(Long screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	
}
