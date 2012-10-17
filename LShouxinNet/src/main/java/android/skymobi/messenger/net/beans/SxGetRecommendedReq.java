package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取用户设置的是否推荐值
 * @ClassName: SxGetRecommendedReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-10 下午01:28:19
 */
@EsbSignal(messageCode=0xA849)
public class SxGetRecommendedReq extends ShouxinReqHeader {
    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;

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
