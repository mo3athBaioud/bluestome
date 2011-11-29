package com.xian.support.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="tbl_noplog")
public class Noplog extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;
	
//	@Column(name="d_uid")
	@Transient
	private Integer uid;
	
	@Column(name="d_phonenum")
	private String phonenum;
	
//	@Column(name="d_phonenum_bdistrict")
	@Transient
	private String phonenumBDistrict;
	
	@Column(name="d_btype")
	private Integer btype;
	
	@Column(name="d_loginname")
	private String loginname;
	
//	@Column(name="d_loginname_bdistrict")
	@Transient
	private String loginnameBDistrict;
	
	@Column(name="d_createtime")
	private Date createtime;
	
	@Column(name="d_result")
	private Integer result;
	
	@Column(name="d_ip")
	private String ip;

	@Column(name="d_ismarketing")
	private Integer isMarketing;
	
	@Column(name="d_msuccess")
	private Integer mSuccess;
	
	@Column(name="d_platsell")
	private Integer platsell;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "d_phonenum_bdistrict", referencedColumnName = "d_code")
	private BDistrict phoneBDistrict;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "d_loginname_bdistrict", referencedColumnName = "d_code")
	private BDistrict staffBDistrict;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "d_uid", referencedColumnName = "d_id")
	private Staff staff;
	
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

	public BDistrict getPhoneBDistrict() {
		return phoneBDistrict;
	}

	public void setPhoneBDistrict(BDistrict phoneBDistrict) {
		this.phoneBDistrict = phoneBDistrict;
	}

	public BDistrict getStaffBDistrict() {
		return staffBDistrict;
	}

	public void setStaffBDistrict(BDistrict staffBDistrict) {
		this.staffBDistrict = staffBDistrict;
	}

	public Integer getIsMarketing() {
		return isMarketing;
	}

	public void setIsMarketing(Integer isMarketing) {
		this.isMarketing = isMarketing;
	}

	public Integer getMSuccess() {
		return mSuccess;
	}

	public void setMSuccess(Integer success) {
		mSuccess = success;
	}

	public Integer getPlatsell() {
		return platsell;
	}

	public void setPlatsell(Integer platsell) {
		this.platsell = platsell;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
}
