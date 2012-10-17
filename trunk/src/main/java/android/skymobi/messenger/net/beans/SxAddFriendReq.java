package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode=0xA809)
public class SxAddFriendReq extends ShouxinReqHeader {

	@TLVAttribute(tag = 10000045)
	private int destSkyid;
	
	@TLVAttribute(tag = 10000046)
	private byte contactType;

	public int getDestSkyid() {
		return destSkyid;
	}

	public void setDestSkyid(int destSkyid) {
		this.destSkyid = destSkyid;
	}

	public byte getContactType() {
		return contactType;
	}

	public void setContactType(byte contactType) {
		this.contactType = contactType;
	}
	
	
}
