package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupRoomBlockHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long roomId;
	private Short activeType;
	private String creator;
	private String reason;
	private Date blockUtil;
	private Date createTime;
	
	public Short getActiveType() {
		return activeType;
	}
	public void setActiveType(Short activeType) {
		this.activeType = activeType;
	}
	public Date getBlockUtil() {
		return blockUtil;
	}
	public void setBlockUtil(Date blockUtil) {
		this.blockUtil = blockUtil;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	
}
