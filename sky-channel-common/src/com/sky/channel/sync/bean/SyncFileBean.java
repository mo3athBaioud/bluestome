/**
 * 
 */
package com.sky.channel.sync.bean;

import java.io.Serializable;

/**
 * @author lilixin
 * 
 */
public class SyncFileBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String provinceCode;
	private String startTime;
	private String endTime;
	private int startPrice;
	private int endPrice;
	private byte[] fileContent;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[fileName = ").append(fileName).append("] ");
		buffer.append("[provinceCode = ").append(provinceCode).append("] ");
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
