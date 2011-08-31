package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupBlackRoom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long roomId;
	private Long skyId;
	private Date blockUtil;
	private String creator;
	private Date createTime;
	
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
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Long getSkyId() {
		return skyId;
	}
	public void setSkyId(Long skyId) {
		this.skyId = skyId;
	}
	
	
}
