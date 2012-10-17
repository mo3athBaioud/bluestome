package android.skymobi.messenger.net.beans.commons;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode=0xA416)
public class ChangeStateAck extends ShouxinReqHeader {
	
	@TLVAttribute(tag=1001)
	private int seqid;

	public int getSeqid() {
		return seqid;
	}

	public void setSeqid(int seqid) {
		this.seqid = seqid;
	}

}
