
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 增量获取推荐短信
 * @ClassName: SxGetRecommendMsgByUpdateTimeReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-18 上午09:37:37
 */
@EsbSignal(messageCode = 0xA855)
public class SxGetRecommendMsgByUpdateTimeReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000043, description = "类别ID")
    private int msgTypeId;
    
    @TLVAttribute(tag = 10000007, description = "服务端的本版时间戳")
    private long updateTime;
    
    @TLVAttribute(tag = 10000047)
    private int start = 1;
    
    @TLVAttribute(tag = 10000048)
    private int pageSize = 10;

    @TLVAttribute(tag = 10000068)
    private int capacity = 10;

    /**
     * @return the msgTypeId
     */
    public int getMsgTypeId() {
        return msgTypeId;
    }

    /**
     * @param msgTypeId the msgTypeId to set
     */
    public void setMsgTypeId(int msgTypeId) {
        this.msgTypeId = msgTypeId;
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
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
}
