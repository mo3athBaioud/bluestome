package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_wap_log")
public class Waplog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("d_id")
	private int id;
	
	@Column("d_phone_num")
	private String phoneNum;
	
	@Column("d_hsman_name")
	private String hsmanName;
	
	@Column("d_hstype_name")
	private String hstypeName;

	public String getHsmanName() {
		return hsmanName;
	}

	public void setHsmanName(String hsmanName) {
		this.hsmanName = hsmanName;
	}

	public String getHstypeName() {
		return hstypeName;
	}

	public void setHstypeName(String hstypeName) {
		this.hstypeName = hstypeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
