package com.ssi.acl.dal.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 系统菜单表对象
 * @author bluestome
 *
 */
public class SysMenu implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Date createDt;
	
	private String menuName;
	
	private String menuUrl;
	
	private Integer status = 1;
	
	private Integer isDel = 1;
	
	private String menuId;
	
	private String parentMenuId;
	
	private String visitName;
	
    private Set roleSysMenu = new HashSet(0);
    
	public SysMenu(){
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
	
	
	public String getVisitName() {
		return visitName;
	}


	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}


	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getRoleSysMenu() {
		return roleSysMenu;
	}

	public void setRoleSysMenu(Set roleSysMenu) {
		this.roleSysMenu = roleSysMenu;
	}

	
}
