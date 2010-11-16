package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer parentId = 0;
	
	private Date createDt;

	private String spShortName;

	private String spCode;

	private String spName;

	private String manageRange;

	private String bankName;

	private String bankNo;

	private String linkManName;

	private String linkManTel;

	private String linkManPhone;

	private String fax;

	private String email;

	private String companyAddr;

	private String webUrl;

	private String messageAddr;

	private String zip;

	private String customerServiceName;

	private String customerServiceTel;

	private Integer status;

	private String key1;

	private String key2;

	private String key3;

	private String key4;
	private Integer areaCode;
	private Integer spTypeId;
	
	private RouteConfig areCode;
	private Code code;
	private Set<Operator> operators = new HashSet<Operator>(0);

	public SpInfo() {
	}

	public RouteConfig getAreCode() {
		return areCode;
	}

	public void setAreCode(RouteConfig areCode) {
		this.areCode = areCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCustomerServiceName() {
		return customerServiceName;
	}

	public void setCustomerServiceName(String customerServiceName) {
		this.customerServiceName = customerServiceName;
	}

	public String getCustomerServiceTel() {
		return customerServiceTel;
	}

	public void setCustomerServiceTel(String customerServiceTel) {
		this.customerServiceTel = customerServiceTel;
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

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkManPhone() {
		return linkManPhone;
	}

	public void setLinkManPhone(String linkManPhone) {
		this.linkManPhone = linkManPhone;
	}

	public String getLinkManTel() {
		return linkManTel;
	}

	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}

	public String getManageRange() {
		return manageRange;
	}

	public void setManageRange(String manageRange) {
		this.manageRange = manageRange;
	}

	public String getMessageAddr() {
		return messageAddr;
	}

	public void setMessageAddr(String messageAddr) {
		this.messageAddr = messageAddr;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpShortName() {
		return spShortName;
	}

	public void setSpShortName(String spShortName) {
		this.spShortName = spShortName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getSpTypeId() {
		return spTypeId;
	}

	public void setSpTypeId(Integer spTypeId) {
		this.spTypeId = spTypeId;
	}
	
	
	
}