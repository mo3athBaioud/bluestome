package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 增量同步联系人响应
 * @ClassName: SxSyncContactsReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午03:57:44
 */
@EsbSignal(messageCode=0xA835)
public class SxSyncContactsReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000007)
    private long updateTime;
    
    @TLVAttribute(tag=10000047)
    private int start;
    
    @TLVAttribute(tag=10000048)
    private int pageSize;

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
    
    
}
