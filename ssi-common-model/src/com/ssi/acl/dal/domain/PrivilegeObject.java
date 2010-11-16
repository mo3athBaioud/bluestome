package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PrivilegeObject implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private Integer objectIndex;
    private String objectId;
    private String objectName;
    private String objectRight;
    private Integer status;
    private Date createDt;
    
    
    private Set<RolePriv> rolePrivs = new HashSet<RolePriv>(0);
    
    public PrivilegeObject(){
    }
    
    
	public Date getCreateDt() {
		return createDt;
	}


	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectIndex() {
		return objectIndex;
	}

	public void setObjectIndex(Integer objectIndex) {
		this.objectIndex = objectIndex;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Set<RolePriv> getRolePrivs() {
		return rolePrivs;
	}

	public void setRolePrivs(Set<RolePriv> rolePrivs) {
		this.rolePrivs = rolePrivs;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getObjectRight() {
		return objectRight;
	}


	public void setObjectRight(String objectRight) {
		this.objectRight = objectRight;
	}
    
	
    
}
