package com.skymobi.android.skyaaa.bean.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@TLVAttribute(tag = 11020002)
public class RegisterUserInfo {
	@TLVAttribute(tag=11010002,description="斯凯ID")
	private int skyId;
	
	@TLVAttribute(tag=11010014,description="授权令牌")
	private String token;
	
	@TLVAttribute(tag=11010003,description="用户名")
	private String username;
	
	@TLVAttribute(tag=11010010,description="密码明文")
	private String passwd;
	
	@TLVAttribute(tag=11010005,description="昵称")
	private String nickname;
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSkyId() {
		return skyId;
	}
	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString() {
		return  ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
