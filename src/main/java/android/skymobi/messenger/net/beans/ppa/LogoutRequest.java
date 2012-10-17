package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal( messageCode = 0xC20D )
public class LogoutRequest extends PPARequestHeader {

	@TLVAttribute(tag = 1005,description="用户的SKYID")
	private int skyid;
	
	@TLVAttribute(tag = 11010014,description="授权令牌,登录时产生")
	private String token;
	
	@TLVAttribute(tag = 11010012,description="来源")
	private String source;

	public int getSkyid() {
		return skyid;
	}

	public void setSkyid(int skyid) {
		this.skyid = skyid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
