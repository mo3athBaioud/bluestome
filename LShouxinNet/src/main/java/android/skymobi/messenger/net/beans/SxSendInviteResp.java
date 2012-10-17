package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送联系人邀请开通响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA830)
public class SxSendInviteResp extends ShouxinRespHeader {

	@TLVAttribute(tag=10000032)
	private String msgContent;

	public final String getMsgContent() {
		return msgContent;
	}

	public final void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
}
