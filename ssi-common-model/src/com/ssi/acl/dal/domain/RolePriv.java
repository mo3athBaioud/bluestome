package com.ssi.acl.dal.domain;

import java.util.Date;


public class RolePriv implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer privilegeId;
	private Integer roleId;
	
    private Integer delete = 2;
    private Integer update = 2;
    private Integer insert = 2;
    private Integer select = 2;
    private Date createDt;

    private PrivilegeObject privilegeObject;
    private Role role;
    
    public RolePriv(){
    }

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInsert() {
		return insert;
	}

	public void setInsert(Integer insert) {
		this.insert = insert;
	}

	public PrivilegeObject getPrivilegeObject() {
		return privilegeObject;
	}

	public void setPrivilegeObject(PrivilegeObject privilegeObject) {
		this.privilegeObject = privilegeObject;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getSelect() {
		return select;
	}

	public void setSelect(Integer select) {
		this.select = select;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
    
    
}
