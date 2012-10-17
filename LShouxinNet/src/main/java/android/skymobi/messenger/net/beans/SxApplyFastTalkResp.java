
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 申请快聊响应
 * 
 * @ClassName: SxApplyFastTalkResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午09:44:23
 */
@EsbSignal(messageCode = 0xA868)
public class SxApplyFastTalkResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 10000075, description = "二级响应码（616：原来的会话），responseCode为200时解析此tag，有值且为616时说明是原来会话")
    private int nextRespCode;

    @TLVAttribute(tag = 10000045, description = "对方skyid，终端拿此skyid与对方快聊")
    private int destSkyid;

    @TLVAttribute(tag = 10000077, description = "等待的时间（单位秒，CMS配置）")
    private int createQueueWaitTime;

    /**
     * @return the nextRespCode
     */
    public int getNextRespCode() {
        return nextRespCode;
    }

    /**
     * @param nextRespCode the nextRespCode to set
     */
    public void setNextRespCode(int nextRespCode) {
        this.nextRespCode = nextRespCode;
    }

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
     * @return the createQueueWaitTime
     */
    public int getCreateQueueWaitTime() {
        return createQueueWaitTime;
    }

    /**
     * @param createQueueWaitTime the createQueueWaitTime to set
     */
    public void setCreateQueueWaitTime(int createQueueWaitTime) {
        this.createQueueWaitTime = createQueueWaitTime;
    }

}
