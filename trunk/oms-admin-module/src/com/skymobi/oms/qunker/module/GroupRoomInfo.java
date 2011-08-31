package com.skymobi.oms.qunker.module;

import java.io.Serializable;
import java.util.Date;

public class GroupRoomInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long creator;
	private String roomName;
	private String nameStyle;
	private String roomDesc;
	private String welcomeWords;
	private String password;
	private Integer maxMembers;
	private Integer maxVisitors;
	private boolean allowVisitors;
	private boolean visitorCanTalk;
	private Integer memberSpeakInterval;
	private Integer visitorSpeakInterval;
	private Long logo;
	private String custLogo;
	private Integer appId;
	private Short status;
	private Date blockUtil;
	private Date createTime;
	
	public boolean isAllowVisitors() {
		return allowVisitors;
	}
	public void setAllowVisitors(boolean allowVisitors) {
		this.allowVisitors = allowVisitors;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
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
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public String getCustLogo() {
		return custLogo;
	}
	public void setCustLogo(String custLogo) {
		this.custLogo = custLogo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLogo() {
		return logo;
	}
	public void setLogo(Long logo) {
		this.logo = logo;
	}
	public Integer getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(Integer maxMembers) {
		this.maxMembers = maxMembers;
	}
	public Integer getMaxVisitors() {
		return maxVisitors;
	}
	public void setMaxVisitors(Integer maxVisitors) {
		this.maxVisitors = maxVisitors;
	}
	public Integer getMemberSpeakInterval() {
		return memberSpeakInterval;
	}
	public void setMemberSpeakInterval(Integer memberSpeakInterval) {
		this.memberSpeakInterval = memberSpeakInterval;
	}
	public String getNameStyle() {
		return nameStyle;
	}
	public void setNameStyle(String nameStyle) {
		this.nameStyle = nameStyle;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoomDesc() {
		return roomDesc;
	}
	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public boolean isVisitorCanTalk() {
		return visitorCanTalk;
	}
	public void setVisitorCanTalk(boolean visitorCanTalk) {
		this.visitorCanTalk = visitorCanTalk;
	}
	public Integer getVisitorSpeakInterval() {
		return visitorSpeakInterval;
	}
	public void setVisitorSpeakInterval(Integer visitorSpeakInterval) {
		this.visitorSpeakInterval = visitorSpeakInterval;
	}
	public String getWelcomeWords() {
		return welcomeWords;
	}
	public void setWelcomeWords(String welcomeWords) {
		this.welcomeWords = welcomeWords;
	}
	
	
}
