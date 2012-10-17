
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 结束快聊通知
 * 
 * @ClassName: SxLeaveFastTalkNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午10:25:26
 */
@EsbSignal(messageCode = 0xB808)
public class SxLeaveFastTalkNotify extends ShouxinRespHeader {
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
