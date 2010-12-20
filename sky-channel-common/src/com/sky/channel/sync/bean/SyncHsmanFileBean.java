/**
 * 
 */
package com.sky.channel.sync.bean;

import java.io.Serializable;

/**
 * @author lilixin
 * 
 */
public class SyncHsmanFileBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String mcc;
	private String startTime;
	private String endTime;
	private int startPrice;
	private int endPrice;
	private byte[] fileContent;
	
	//增加厂商属性 2010-09-09
	private String hsman;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
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
		buffer.append("[mcc = ").append(mcc).append("]");
		buffer.append("[hsman = ").append(hsman).append("]");
		buffer.append("[fileName = ").append(fileName).append("] ");
		buffer.append("[startTime = ").append(startTime).append("] ");
		buffer.append("[endTime = ").append(endTime).append("] ");
		buffer.append("[startPrice = ").append(startPrice).append("] ");
		buffer.append("[endPrice = ").append(endPrice).append("] ");
		if(fileContent!=null)
			buffer.append("[fileContent length= ").append(fileContent.length).append("] ");
		else{
			buffer.append("[null] ");
		}
		return buffer.toString();
	}
}
