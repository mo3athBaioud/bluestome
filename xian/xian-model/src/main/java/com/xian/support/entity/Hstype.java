package com.xian.support.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_hstype")
public class Hstype extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private int id;
	
	@Column(name="d_hsman_id")
	private int hsmanId;
	
	@Column(name="d_name")
	private String name;
	
	@Column(name="d_tac")
	private String tac;
	
	@Column(name="d_icon")
	private String icon;
	
	@Column(name="d_remarks")
	private String remarks;
	
	@Column(name="d_status")
	private int status = 1;
	
	@Column(name="d_createtime")
	private Date createtime;
	
	@Column(name="d_modifytime")
	private Date modifytime;
	
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getHsmanId() {
		return hsmanId;
	}

	public void setHsmanId(int hsmanId) {
		this.hsmanId = hsmanId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}
	
	
}
