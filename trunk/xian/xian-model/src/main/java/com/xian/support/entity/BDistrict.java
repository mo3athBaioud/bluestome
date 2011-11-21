package com.xian.support.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 业务区表
 * @author bluestome
 *
 */
@Entity
@Table(name="tbl_bdistrict")
public class BDistrict extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="d_code")
	private String code;
	
	@Column(name="d_name")
	private String name;
	
	@Column(name="d_parent_code")
	private String parentcode = "0000";
	
	@Column(name="d_description")
	private String description;
	
	@Column(name="d_status")
	private Integer status = 1;
	
	@Column(name="d_createtime")
	private Date createtime = new Date();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
