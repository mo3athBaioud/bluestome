
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 结束快聊
 * 
 * @ClassName: SxLeaveFastTalkReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午09:53:35
 */
@EsbSignal(messageCode = 0xA869)
public class SxLeaveFastTalkReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000045, description = "对方SKYID")
    private int destSkyid;

    /**
     * @return the destSkyid
     */
    public int getDestSkyid() {
        return destSkyid;
    }

    /**
     * @param destSkyid the destSkyid to set
     */
    public void setDestSkyid(int destSkyid) {
        this.destSkyid = destSkyid;
    }

}
