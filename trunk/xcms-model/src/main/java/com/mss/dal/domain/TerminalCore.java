package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_terminal_core")
public class TerminalCore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private Integer id;
	
	@Column("d_tac")
	private String tac;
	
	@Column("d_hsman")
	private String hsman;
	
	@Column("d_hsman_ch")
	private String hsmanch;
	
	@Column("d_hstype")
	private String hstype;
	
	@Column("d_hstype_ch")
	private String hstypech;
	
	@Column("d_gprs")
	private Integer gprs;
	
	@Column("d_wap")
	private Integer wap;
	
	@Column("d_smartphone")
	private Integer smartphone;
	
	@Column("d_os")
	private Integer os;
	
	@Column("d_wifi")
	private Integer wifi;
	
	@Column("d_createtime")
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getGprs() {
		return gprs;
	}

	public void setGprs(Integer gprs) {
		this.gprs = gprs;
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

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public Integer getSmartphone() {
		return smartphone;
	}

	public void setSmartphone(Integer smartphone) {
		this.smartphone = smartphone;
	}

	public Integer getWap() {
		return wap;
	}

	public void setWap(Integer wap) {
		this.wap = wap;
	}

	public Integer getWifi() {
		return wifi;
	}

	public void setWifi(Integer wifi) {
		this.wifi = wifi;
	}

	public String getHsmanch() {
		return hsmanch;
	}

	public void setHsmanch(String hsmanch) {
		this.hsmanch = hsmanch;
	}

	public String getHstypech() {
		return hstypech;
	}

	public void setHstypech(String hstypech) {
		this.hstypech = hstypech;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}
	
	
}
