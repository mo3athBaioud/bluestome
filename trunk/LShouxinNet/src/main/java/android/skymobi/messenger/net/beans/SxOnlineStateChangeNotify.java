package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 在线状态通知
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xB802)
public class SxOnlineStateChangeNotify extends ShouxinRespHeader {
	@TLVAttribute(tag=1001)
	private int seqid;
	
	@TLVAttribute(tag=1005)
	private int skyid;
	
	@TLVAttribute(tag=11010077)
	private String nickname;
	
	@TLVAttribute(tag=10000051)
	private String timestamp;
	
	@TLVAttribute(tag=10000020,description="0 不在线  1在线")
	private byte status;

	public int getSeqid() {
		return seqid;
	}

	public void setSeqid(int seqid) {
		this.seqid = seqid;
	}

	public int getSkyid() {
		return skyid;
	}

	public void setSkyid(int skyid) {
		this.skyid = skyid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
