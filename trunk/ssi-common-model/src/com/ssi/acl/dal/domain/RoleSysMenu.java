package com.ssi.acl.dal.domain;

import java.util.Date;

public class RoleSysMenu implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private Integer sysMenuId;
	private Integer roleId;
	
    private SysMenu sysMenu;
    private Role role;
    private Date createDt;
	
	public RoleSysMenu(){
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public Integer getSysMenuId() {
		return sysMenuId;
	}


	public void setSysMenuId(Integer sysMenuId) {
		this.sysMenuId = sysMenuId;
	}
	
	
}
