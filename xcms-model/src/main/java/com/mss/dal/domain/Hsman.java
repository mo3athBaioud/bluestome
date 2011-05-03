package com.mss.dal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_hsman")
public class Hsman implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_name")
	private String name;
	
	@Column("d_icon")
	private String icon;
	
	@Column("d_remarks")
	private String remarks;
	
	@Column("d_status")
	private int status;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_modifytime")
	private Date modifytime;
	
	@Many(target=Hstype.class,field="hsmanId")
	private List<Hstype> hstypes  = new ArrayList<Hstype>(); // = new ArrayList<Hstype>()

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public List<Hstype> getHstypes() {
		return hstypes;
	}

	public void setHstypes(List<Hstype> hstypes) {
		this.hstypes = hstypes;
	}
	
	
}
