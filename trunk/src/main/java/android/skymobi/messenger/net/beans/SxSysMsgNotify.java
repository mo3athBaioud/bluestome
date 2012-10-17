package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 系统消息通知
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xB805)
public class SxSysMsgNotify extends ShouxinRespHeader {
	@TLVAttribute(tag=1001)
	private int seqid;
	
	@TLVAttribute(tag=1005)
	private int skyid;
	
	@TLVAttribute(tag=11010077)
	private String nickname;
	
	@TLVAttribute(tag=10000051)
	private String timestamp;
	
	@TLVAttribute(tag=10000032)
	private String msgContent;
	
	/**
	 * 1：激活成功
     * 2：绑定成功
     * 3：换绑成功
     * 4：重置密码成功
     * 5：失败
	 */
	@TLVAttribute(tag=10000061)
	private byte resultType;

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

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

    public byte getResultType() {
        return resultType;
    }

    public void setResultType(byte resultType) {
        this.resultType = resultType;
    }
	
}
