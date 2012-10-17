package com.skymobi.android.skyaaa.bean.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
@TLVAttribute(tag = 11020003)
public class LoginUserInfo {
	@TLVAttribute(tag=11010003,description="用户名")
	private String username;
	
	@TLVAttribute(tag=11010005,description="用户昵称")
	private String nickname;
	
	@TLVAttribute(tag=11010068,description="用户头像id")
	private String headPic;
	
	@TLVAttribute(tag=11010069,description="用户性别 0=未设置 1=男 2=女 3=保密")
	private String sex;
	
	@TLVAttribute(tag=11010070,description="用户的出生年月日yyyy-MM-dd")
	private String birthday;
	
	@TLVAttribute(tag=11010071,description="用户地区(省份)")
	private String province;
	
	@TLVAttribute(tag=11010073, description="城市")
	private String city;

	@TLVAttribute(tag=11010006, description="绑定的手机号码")
	private String mobile;
	
	@TLVAttribute(tag=11010075, description="国家/地区")
	private String country;
	
	@TLVAttribute(tag=11010076, description="国家代码")
	private String countryCode;
	
	@TLVAttribute(tag=11010077, description="个性昵称")
	private String personNickname;
	
	@TLVAttribute(tag=11010078, description="个性签名")
	private String signature;
	
	@TLVAttribute(tag=11010084, description="创建时间")
	private String createTime;
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPersonNickname() {
		return personNickname;
	}

	public void setPersonNickname(String personNickname) {
		this.personNickname = personNickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getCity() {
		return city;
	}

	public String getHeadPic() {
		return headPic;
	}

	public String getNickname() {
		return nickname;
	}

	public String getProvince() {
		return province;
	}

	public String getSex() {
		return sex;
	}

	public String getUsername() {
		return username;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return  ToStringBuilder.reflectionToString(this, 
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
