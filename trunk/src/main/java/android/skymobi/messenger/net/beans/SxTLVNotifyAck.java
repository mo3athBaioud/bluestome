package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 消息类的应答
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA509)
public class SxTLVNotifyAck extends ShouxinReqHeader {

	@TLVAttribute(tag=1001)
	private int seqid;

	public int getSeqid() {
		return seqid;
	}

	public void setSeqid(int seqid) {
		this.seqid = seqid;
	}
	
	
}
