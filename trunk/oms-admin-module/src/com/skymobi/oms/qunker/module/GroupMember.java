package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long roomId;
	private Long memberId;
	private Date joinTime;
	private Integer memberType;
	private Integer messageConfig;
	private Integer memberStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	public Integer getMessageConfig() {
		return messageConfig;
	}
	public void setMessageConfig(Integer messageConfig) {
		this.messageConfig = messageConfig;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	
}
