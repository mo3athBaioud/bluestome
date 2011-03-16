package com.ssi.activemq;

import java.io.Serializable;

public class ModPasswordRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 旧密码
	 */
	private String oldPassword;

	/**
	 * 新密码
	 */
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
