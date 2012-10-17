
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.RecommendMsg;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 增量获取推荐短信响应
 * @ClassName: SxGetRecommendMsgByUpdateTimeResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-18 上午09:42:09
 */
@EsbSignal(messageCode = 0xA856)
public class SxGetRecommendMsgByUpdateTimeResp extends ShouxinRespHeader {
    
    @TLVAttribute(tag = 20000017,description="短信列表")
    private ArrayList<RecommendMsg> recommendMsg = new ArrayList<RecommendMsg>();

    @TLVAttribute(tag = 10000041,description="推荐短信总数")
    private int totalSize;

    @TLVAttribute(tag = 10000047,description="开始位置（页码）")
    private int start;

    @TLVAttribute(tag = 10000007, description = "服务端的本版时间戳")
    private long updateTime;

    /**
     * @return the recommendMsg
     */
    public ArrayList<RecommendMsg> getRecommendMsg() {
        return recommendMsg;
    }

    /**
     * @param recommendMsg the recommendMsg to set
     */
    public void setRecommendMsg(ArrayList<RecommendMsg> recommendMsg) {
        this.recommendMsg = recommendMsg;
    }

    /**
     * @return the totalSize
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize the totalSize to set
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

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
