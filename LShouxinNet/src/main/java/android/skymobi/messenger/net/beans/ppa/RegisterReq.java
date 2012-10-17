package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode=0xC201)
public class RegisterReq extends PPARequestHeader{

	@TLVAttribute(tag=1001, description="客户端使用")
	private int seqid=0;
	
	@TLVAttribute(tag=11010003,description="用户名，当为空时，执行快速注册")
	public String username;
	
	@TLVAttribute(tag=11010004,description="密码")
	public byte[] password;
	
	@TLVAttribute(tag=11800008,description="是否校验手机 0:不校验 1:注册前校验  2:注册后校验")
	public byte checkMobile = 0;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public int getSeqid() {
		return seqid++;
	}

	public void setSeqid(int seqid) {
		this.seqid = seqid;
	}

	public byte getCheckMobile() {
		return checkMobile;
	}

	public void setCheckMobile(byte checkMobile) {
		this.checkMobile = checkMobile;
	}

}
