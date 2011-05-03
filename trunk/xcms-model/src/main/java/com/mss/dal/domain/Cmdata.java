package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_cm_data")
public class Cmdata implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_phone_num")
	private String phoneNum;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_ucode")
	private String ucode;
	
	@Column("d_acode")
	private String acode;
	
	@Column("d_call_times")
	private int callTimes;
	
	@Column("d_fee_times")
	private int feeTimes;
	
	@Column("d_call_during")
	private int callDuring;

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

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public int getCallDuring() {
		return callDuring;
	}

	public void setCallDuring(int callDuring) {
		this.callDuring = callDuring;
	}

	public int getCallTimes() {
		return callTimes;
	}

	public void setCallTimes(int callTimes) {
		this.callTimes = callTimes;
	}

	public int getFeeTimes() {
		return feeTimes;
	}

	public void setFeeTimes(int feeTimes) {
		this.feeTimes = feeTimes;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	
	
}
