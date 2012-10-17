package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 批量获取指定用户的在线状态请求
 * @ClassName: SxGetOnlineStatusReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-18 上午09:17:05
 */
@EsbSignal(messageCode=0xA851)
public class SxGetOnlineStatusReq extends ShouxinReqHeader {

    
    @TLVAttribute(tag=10000031,description="skyid列表，逗号分割")
    private String destSkyids;

    /**
     * @return the destSkyids
     */
    public String getDestSkyids() {
        return destSkyids;
    }

    /**
     * @param destSkyids the destSkyids to set
     */
    public void setDestSkyids(String destSkyids) {
        this.destSkyids = destSkyids;
    }
    
}
