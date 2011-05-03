package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_hstype")
public class Hstype implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_hsman_id")
	private int hsmanId;
	
	@Column("d_name")
	private String name;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_icon")
	private String icon;
	
	@Column("d_remarks")
	private String remarks;
	
	@Column("d_status")
	private int status = 1;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_modifytime")
	private Date modifytime;
	
	@One(target = Hsman.class, field = "hsmanId")
	private Hsman hsman;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Hsman getHsman() {
		return hsman;
	}

	public void setHsman(Hsman hsman) {
		this.hsman = hsman;
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
