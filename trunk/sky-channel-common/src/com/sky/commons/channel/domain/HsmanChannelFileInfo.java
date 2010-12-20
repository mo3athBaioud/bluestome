package com.sky.commons.channel.domain;

import java.io.Serializable;
import java.util.Date;

public class HsmanChannelFileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mcc = "000";
	private String fileName;
	private byte[] fileBytes = new byte[0];
	private int startPrice;
	private int endPrice;
	private Date startTime;
	private Date endTime;
	
	//增加厂商属性 2010-09-09
	private String hsman;

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
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
	
	
	public String getHsman() {
		return hsman;
	}

	public void setHsman(String hsman) {
		this.hsman = hsman;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[hsman = ").append(hsman).append("]");
		buffer.append("[mcc = ").append(mcc).append("] ");
		buffer.append("[fileName = ").append(fileName).append("] ");
		buffer.append("[fileBytes length = ").append(fileBytes==null?0:fileBytes.length).append("] ");
		buffer.append("[startPrice = ").append(startPrice).append("] ");
		buffer.append("[endPrice = ").append(endPrice).append("] ");
		buffer.append("[startTime = ").append(startTime).append("] ");
		buffer.append("[endTime = ").append(endTime).append("] ");
		return buffer.toString();
	}
	
}
