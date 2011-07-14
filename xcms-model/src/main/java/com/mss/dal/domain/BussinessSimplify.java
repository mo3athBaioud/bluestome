package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_bussiness_simplify")
public class BussinessSimplify implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6436905909107883775L;

	@Id
	@Column("d_id")
	private Integer id;
	
	@Column("d_bdistrict")
	private String bdistrict;
	
	@Column("d_phonenum")
	private String phonenum;
	
	@Column("d_btype")
	private Integer btype;
	
	@Column("d_support")
	private Integer support;
	
	@Column("d_support_type")
	private Integer suuporttype;

	public String getBdistrict() {
		return bdistrict;
	}

	public void setBdistrict(String bdistrict) {
		this.bdistrict = bdistrict;
	}

	public Integer getBtype() {
		return btype;
	}

	public void setBtype(Integer btype) {
		this.btype = btype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getSuuporttype() {
		return suuporttype;
	}

	public void setSuuporttype(Integer suuporttype) {
		this.suuporttype = suuporttype;
	}
	
	

}
