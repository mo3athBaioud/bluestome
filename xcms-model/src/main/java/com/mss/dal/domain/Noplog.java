package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_noplog")
public class Noplog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private Integer id;
	
	@Column("d_uid")
	private Integer uid;
	
	@Column("d_phonenum")
	private String phonenum;
	
	@Column("d_phonenum_bdistrict")
	private String phonenumBDistrict;
	
	@Column("d_btype")
	private Integer btype;
	
	@Column("d_loginname")
	private String loginname;
	
	@Column("d_loginname_bdistrict")
	private String loginnameBDistrict;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_result")
	private Integer result;
	
	@Column("d_ip")
	private String ip;

	public Integer getBtype() {
		return btype;
	}

	public void setBtype(Integer btype) {
		this.btype = btype;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginnameBDistrict() {
		return loginnameBDistrict;
	}

	public void setLoginnameBDistrict(String loginnameBDistrict) {
		this.loginnameBDistrict = loginnameBDistrict;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getPhonenumBDistrict() {
		return phonenumBDistrict;
	}

	public void setPhonenumBDistrict(String phonenumBDistrict) {
		this.phonenumBDistrict = phonenumBDistrict;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
