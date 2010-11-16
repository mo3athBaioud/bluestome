package com.ssi.acl.dal.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;
/**
 * 代码表对象
 * 
 * @author bluestome
 * 
 */
public class Code implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String codeName;

	private Date createDt;

	private String codeTp;

	private Integer status = 1;

	private String desc;

	private Integer isDel = 1;

	private Set<Code> spInfo = new HashSet<Code>(0);
	
	private Set<Code> operator = new HashSet<Code>(0);

	private Set<Code> privilegeObject = new HashSet<Code>(0);

	public Code() {
	}
	
	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeTp() {
		return codeTp;
	}

	public void setCodeTp(String codeTp) {
		this.codeTp = codeTp;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Set<Code> getOperator() {
		return operator;
	}

	public void setOperator(Set<Code> operator) {
		this.operator = operator;
	}

	public Set<Code> getPrivilegeObject() {
		return privilegeObject;
	}

	public void setPrivilegeObject(Set<Code> privilegeObject) {
		this.privilegeObject = privilegeObject;
	}

	public Set<Code> getSpInfo() {
		return spInfo;
	}

	public void setSpInfo(Set<Code> spInfo) {
		this.spInfo = spInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
