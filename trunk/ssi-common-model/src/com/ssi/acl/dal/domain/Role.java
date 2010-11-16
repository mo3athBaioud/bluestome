package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色表对象
 * @author bluestome
 *
 */
public class Role implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String roleName;
	
	private String roleDesc;
	
	private Integer roleStatus = 1;
	
	private Date createDt;
	
    private Set roleSysMenu = new HashSet(0);
    
    private Set rolePrivs = new HashSet(0);
    
    private Set<OperatorRole> operatorRoles = new HashSet(0);
    
	public Role(){
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

	public Set<OperatorRole> getOperatorRoles() {
		return operatorRoles;
	}

	public void setOperatorRoles(Set<OperatorRole> operatorRoles) {
		this.operatorRoles = operatorRoles;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set getRolePrivs() {
		return rolePrivs;
	}

	public void setRolePrivs(Set rolePrivs) {
		this.rolePrivs = rolePrivs;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Set getRoleSysMenu() {
		return roleSysMenu;
	}

	public void setRoleSysMenu(Set roleSysMenu) {
		this.roleSysMenu = roleSysMenu;
	}
	
	
}
