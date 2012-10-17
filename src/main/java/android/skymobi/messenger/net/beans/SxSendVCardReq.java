package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送网络名片请求
 * 
 * @author Bluestome.Zhang
 * 
 */
@EsbSignal(messageCode = 0xA815)
public class SxSendVCardReq extends ShouxinReqHeader {
	
	@TLVAttribute(tag = 10000031)
	private String destSkyids;
	
	@TLVAttribute(tag = 11010077)
	private String nickName;
	
	@TLVAttribute(tag = 10000033)
	private String vCardContent;

	public String getDestSkyids() {
		return destSkyids;
	}

	public void setDestSkyids(String destSkyids) {
		this.destSkyids = destSkyids;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getvCardContent() {
		return vCardContent;
	}

	public void setvCardContent(String vCardContent) {
		this.vCardContent = vCardContent;
	}

}
