package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 设置是否推荐请求
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA833)
public class SxSetRecommendedReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002, description = "默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
	@TLVAttribute(tag=10000055,description="0：否，1：是")
	private byte recommended;

    @TLVAttribute(tag=10000068,description="0：不隐藏LBS信息；1：隐藏LBS信息")
    private byte hideLbs;
    
	public byte getRecommended() {
		return recommended;
	}

	public void setRecommended(byte recommended) {
		this.recommended = recommended;
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

    /**
     * @return the hideLbs
     */
    public byte getHideLbs() {
        return hideLbs;
    }

    /**
     * @param hideLbs the hideLbs to set
     */
    public void setHideLbs(byte hideLbs) {
        this.hideLbs = hideLbs;
    }
	
}
