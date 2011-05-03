package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_tac")
public class Tac implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_tac")
	@Name
	private String tac;
	
	@Column("d_hsman_name")
	private String hsmanName;
	
	@Column("d_hsman_name_en")
	private String hsmanNameEn;
	
	@Column("d_hstype_name")
	private String hstypeName;
	
	@Column("d_hstype_name_en")
	private String hstypeNameEn;
	
	@Column("d_status")
	private int status;
	
	@Column("d_createtime")
	private Date createtime;
	
	@Column("d_modifytime")
	private Date modifytime;
	
	@One(target = Terminal.class, field = "id")
	private Terminal terminal;

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

	public String getHsmanNameEn() {
		return hsmanNameEn;
	}

	public void setHsmanNameEn(String hsmanNameEn) {
		this.hsmanNameEn = hsmanNameEn;
	}

	public String getHstypeName() {
		return hstypeName;
	}

	public void setHstypeName(String hstypeName) {
		this.hstypeName = hstypeName;
	}

	public String getHstypeNameEn() {
		return hstypeNameEn;
	}

	public void setHstypeNameEn(String hstypeNameEn) {
		this.hstypeNameEn = hstypeNameEn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
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

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	
	
}
