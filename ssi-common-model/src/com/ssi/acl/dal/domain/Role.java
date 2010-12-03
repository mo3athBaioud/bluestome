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
	
    private Set<RoleSysMenu> roleSysMenu = new HashSet<RoleSysMenu>(0);
    
    private Set<RolePriv> rolePrivs = new HashSet<RolePriv>(0);
    
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

	public Set<RolePriv> getRolePrivs() {
		return rolePrivs;
	}

	public void setRolePrivs(Set<RolePriv> rolePrivs) {
		this.rolePrivs = rolePrivs;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Set<RoleSysMenu> getRoleSysMenu() {
		return roleSysMenu;
	}

	public void setRoleSysMenu(Set<RoleSysMenu> roleSysMenu) {
		this.roleSysMenu = roleSysMenu;
	}
	
	
}
