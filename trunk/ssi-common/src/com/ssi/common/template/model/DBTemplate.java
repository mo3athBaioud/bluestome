package com.ssi.common.template.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author  liweijing
 * @version 2010-1-18 11:19:00
 */
public class DBTemplate  implements Serializable{

	private static final long serialVersionUID = 964095151824158454L;
	private String template;
	private String path;
	private Date updatetime;
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}

