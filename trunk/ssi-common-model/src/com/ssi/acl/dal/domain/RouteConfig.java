package com.ssi.acl.dal.domain;

public class RouteConfig implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String interfacesAddr;
	
	private String zoneNum;
	
	private String cityName;
	
	private Integer status = 1;
	
	public RouteConfig(){
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZoneNum() {
		return zoneNum;
	}

	public void setZoneNum(String zoneNum) {
		this.zoneNum = zoneNum;
	}

	public String getInterfacesAddr() {
		return interfacesAddr;
	}

	public void setInterfacesAddr(String interfacesAddr) {
		this.interfacesAddr = interfacesAddr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
