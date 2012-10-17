package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 设置用户请求对象
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode = 0xC21F)
public class SetUserInfoReq extends PPARequestHeader {
	
	@TLVAttribute(tag=14020001,description="用户信息")
	private UserInfo userInfo = new UserInfo();
	
	@TLVAttribute(tag=11010014,description = "")
	private String token;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public String getToken() {
		return token;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
