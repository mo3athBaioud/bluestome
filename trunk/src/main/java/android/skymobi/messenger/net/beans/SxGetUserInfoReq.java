package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取联系人详情请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA831)
public class SxGetUserInfoReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
	@TLVAttribute(tag=10000016)
	private int buddyId;

	public final int getBuddyId() {
		return buddyId;
	}

	public final void setBuddyId(int buddyId) {
		this.buddyId = buddyId;
	}

    /**
     * @return the sourceId
     */
    public short getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(short sourceId) {
        this.sourceId = sourceId;
    }
	
	
}
