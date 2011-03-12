package com.ssi.common.dal.domain;

import java.util.Date;

/**
 * 系统配置类 tbl_sysconfig
 * 
 * @author bluestome
 * 
 */
public class SysConfig extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7662699072918415962L;

	private Integer id;

	private String name;

	private String description;

	private Integer groupid;

	private String type;

	private String value;

	private Date createtime = new Date();

	private Date modifytime = new Date();

	public SysConfig() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

}
