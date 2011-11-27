package com.xian.support.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 简单业务类型对象
 * @author Bluestome.Zhang
 *
 */
@Entity
@Table(name="tbl_bussiness_simplify")
public class BussinessSimplify extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6436905909107883775L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="d_id")
	private Integer id;
	
	@Column(name="d_bdistrict")
	private String bdistrict;
	
	@Column(name="d_phonenum")
	private String phonenum;
	
	@Column(name="d_btype")
	private Integer btype;
	
	@Column(name="d_support")
	private Integer support;
	
	@Column(name="d_support_type")
	private Integer suuporttype;

	@Column(name="d_ismarketing")
	private Integer isMarketing;
	
	@Column(name="d_msuccess")
	private Integer mSuccess;
	
	@Column(name="d_platsell")
	private Integer platsell;
	
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
	
}
