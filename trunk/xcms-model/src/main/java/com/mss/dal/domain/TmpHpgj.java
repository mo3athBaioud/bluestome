package com.mss.dal.domain;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 号谱管家表
 * @author bluestome
 *
 */
@Table("tbl_tmp_hpgj")
//@Table("tbl_tmp_gprs_pucheng")
//@Table("tbl_tmp_gprs_dali")
public class TmpHpgj implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_hsman_name")
	private String hsmanName;
	
	@Column("d_hstype_name")
	private String hstypeName;
	
	@Column("d_phone_number")
	private String phoneNumber;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_id")
	private String rbit;
	
	@Column("d_gprs")
	private int gprs;
	
	@Column("d_status")
	private int status;
	
	@Column("d_uid")
	private String uid;
	
	@Column("d_recommdation")
	private Integer recommdation;
	
	@Column("d_date")
	private String date;
	
	
	@Column("d_bcode")
	private String bcode = "000000";

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRbit() {
		return rbit;
	}

	public void setRbit(String rbit) {
		this.rbit = rbit;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public int getGprs() {
		return gprs;
	}

	public void setGprs(int gprs) {
		this.gprs = gprs;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getRecommdation() {
		return recommdation;
	}

	public void setRecommdation(Integer recommdation) {
		this.recommdation = recommdation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBcode() {
		return bcode;
	}

	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	
	
}
