package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 添加黑名单用户请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA817)
public class SxAddBlackReq extends ShouxinReqHeader {
	
	@TLVAttribute(tag = 10000001)
	private int contactId;
	
	@TLVAttribute(tag = 10000045)
	private int destSkyid;

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getDestSkyid() {
		return destSkyid;
	}

	public void setDestSkyid(int destSkyid) {
		this.destSkyid = destSkyid;
	}

	
}
