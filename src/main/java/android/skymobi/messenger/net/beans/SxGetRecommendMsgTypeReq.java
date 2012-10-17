package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取推荐短信类别请求
 * @ClassName: SxGetRecommendMsgTypeReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-18 上午09:33:07
 */
@EsbSignal(messageCode=0xA853)
public class SxGetRecommendMsgTypeReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000007,description="服务端的本版时间戳")
    private long updateTime;

    /**
     * @return the updateTime
     */
    public long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
    
}
