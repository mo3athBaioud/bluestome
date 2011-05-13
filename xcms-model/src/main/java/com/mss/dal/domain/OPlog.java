package com.mss.dal.domain;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_oplog")
public class OPlog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column("d_id")
	private int id;
	
	@Column("d_name")
	private String name;
	@Column("d_optype")
	private String opType;
	@Column("d_opvalue")
	private String opValue;
	@Column("d_opresult")
	private String opResult = "成功";
	@Column("d_userid")
	private int userid;
	@Column("d_createtime")
	private Date createtime;
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getOpValue() {
		return opValue;
	}
	public void setOpValue(String opValue) {
		this.opValue = opValue;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOpResult() {
		return opResult;
	}
	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}
	
}
