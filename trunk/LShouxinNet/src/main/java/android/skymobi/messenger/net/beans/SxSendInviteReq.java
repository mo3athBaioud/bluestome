package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送邀请联系人开通请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA829)
public class SxSendInviteReq extends ShouxinReqHeader {

	@TLVAttribute(tag=10000016)
	private int buddyId;

	public int getBuddyId() {
		return buddyId;
	}

	public void setBuddyId(int buddyId) {
		this.buddyId = buddyId;
	}
	
}
