package com.sky.commons.channel.domain;

import java.io.Serializable;
import java.util.Date;

public class ChannelFileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String provinceCode = "000000";
	private String fileName;
	private byte[] fileBytes = new byte[0];
	private int startPrice;
	private int endPrice;
	private Date startTime;
	private Date endTime;
	
	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	public int getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(int endPrice) {
		this.endPrice = endPrice;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[provinceCode = ").append(provinceCode).append("] ");
		buffer.append("[fileName = ").append(fileName).append("] ");
		buffer.append("[fileBytes length = ").append(fileBytes==null?0:fileBytes.length).append("] ");
		buffer.append("[startPrice = ").append(startPrice).append("] ");
		buffer.append("[endPrice = ").append(endPrice).append("] ");
		buffer.append("[startTime = ").append(startTime).append("] ");
		buffer.append("[endTime = ").append(endTime).append("] ");
		return buffer.toString();
	}
	
}
