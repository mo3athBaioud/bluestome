package com.ssi.acl.dal.domain;

import java.util.Date;

/**
 * 操作员角色关系表对象
 * @author bluestome
 *
 */
public class OperatorRole implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer operatorId;
	private Integer roleId;
	
    private Operator operator;
    private Role role;
    private Date createDt;
    
    public OperatorRole(){
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

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public Integer getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	
    
}
