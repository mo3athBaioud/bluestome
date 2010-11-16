package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 监控配置表对象
 * 
 * @author bluestome
 * 
 */
public class Config implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String configName;

	private String configValue;

	private Date configDate;

	private Integer status = 0;

	private Set monitor = new HashSet(0);

	public Config(){
	}

	public Date getConfigDate() {
		return configDate;
	}

	public void setConfigDate(Date configDate) {
		this.configDate = configDate;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Set getMonitor() {
		return monitor;
	}

	public void setMonitor(Set monitor) {
		this.monitor = monitor;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
