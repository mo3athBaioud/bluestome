package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_calling_log")
public class CallingLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("d_id")
	private int id;
	
	@Column("d_phone_num")
	private String phoneNum;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_tac")
	private String tac;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}
	
}
