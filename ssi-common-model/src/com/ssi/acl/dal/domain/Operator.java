package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Operator implements java.io.Serializable{


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private SpInfo spInfo;
     private Integer spId;
     private Code code;
     private String loginName;
     private String password;
     private Date createDt;
     private String realName;
     private Integer status;
     private String phone;
     private String mobile;
     private String email;
     private String fax;
     private String qq;
     private String msn;
     private Integer operatorType;
     private Integer spid;
     
     private Set operatorRoles = new HashSet(0);


    public Operator() {
    }


	public Code getCode() {
		return code;
	}


	public void setCode(Code code) {
		this.code = code;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getMsn() {
		return msn;
	}


	public void setMsn(String msn) {
		this.msn = msn;
	}


	public Set getOperatorRoles() {
		return operatorRoles;
	}


	public void setOperatorRoles(Set operatorRoles) {
		this.operatorRoles = operatorRoles;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public SpInfo getSpInfo() {
		return spInfo;
	}


	public void setSpInfo(SpInfo spInfo) {
		this.spInfo = spInfo;
	}

	public Date getCreateDt() {
		return createDt;
	}


	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}


	public Integer getSpId() {
		return spId;
	}


	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public Integer getSpid() {
		return spid;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}
    
	
	
    
}