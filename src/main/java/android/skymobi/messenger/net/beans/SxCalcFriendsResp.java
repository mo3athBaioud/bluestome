package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 *  计算推荐好友响应
 * @ClassName: SxCalcFriendsResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-5-3 下午03:24:29
 */
@EsbSignal(messageCode=0xA846)
public class SxCalcFriendsResp extends ShouxinRespHeader {
    @TLVAttribute(tag=10000045,description="目标skyid（使用说明中，第二种情况时返回，第一种情况不返回）")
    private int destSkyid;
    
    @TLVAttribute(tag=10000060,description="第一次打招呼显示的提示语（使用说明中，第二种情况时返回，第一种情况不返回）")
    private String talkReason;

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

    /**
     * @return the talkReason
     */
    public String getTalkReason() {
        return talkReason;
    }

    /**
     * @param talkReason the talkReason to set
     */
    public void setTalkReason(String talkReason) {
        this.talkReason = talkReason;
    }

    
}
