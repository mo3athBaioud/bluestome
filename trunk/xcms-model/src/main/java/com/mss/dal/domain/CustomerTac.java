package com.mss.dal.domain;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_customer_tac")
public class CustomerTac {

	@Column("d_id")
	private int id;
	
	@Column("d_hstype_id")
	private int hstypeId;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_status")
	private int status;
	
	@Column("d_phone_num")
	private String phoneNum;

	public int getHstypeId() {
		return hstypeId;
	}

	public void setHstypeId(int hstypeId) {
		this.hstypeId = hstypeId;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
