package com.mss.dal.domain;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_bussiness")
public class Bussiness implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private Integer id;
	
	@Column("d_bdistrict")
	private String bdistrict;
	
	@Column("d_phonenum")
	private String phonenum;
	
	@Column("d_btype")
	private Integer btype;
	
	@Column("d_hsman")
	private String hsman;
	
	@Column("d_hstype")
	private String hstype;
	
	@Column("d_imei")
	private String imei;
	
	@Column("d_support")
	private Integer support;
	
	@Column("d_support_type")
	private Integer suuporttype;
	
	//需要从日志表中获取统计的数据
	@Readonly
	private Integer qcount = 0;

	public Integer getBtype() {
		return btype;
	}

	public void setBtype(Integer btype) {
		this.btype = btype;
	}

	public String getHsman() {
		return hsman;
	}

	public void setHsman(String hsman) {
		this.hsman = hsman;
	}

	public String getHstype() {
		return hstype;
	}

	public void setHstype(String hstype) {
		this.hstype = hstype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public Integer getQcount() {
		return qcount;
	}

	public void setQcount(Integer qcount) {
		this.qcount = qcount;
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

	public String getBdistrict() {
		return bdistrict;
	}

	public void setBdistrict(String bdistrict) {
		this.bdistrict = bdistrict;
	}
	
	
}
