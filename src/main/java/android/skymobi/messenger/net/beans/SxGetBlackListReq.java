package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取黑名单列表
 * @ClassName: SxGetBlackListReq
 * @Description: 
 * 分三种：
 * fetchFlag：0，获取全部黑名单列表；
 * fetchFlag：1（默认），获取陌生人黑名单列表；
 * fetchFlag：2，获取联系人黑名单列表；
 * @author Bluestome.Zhang
 * @date 2012-4-9 上午11:38:19
 */
@EsbSignal(messageCode=0xA847)
public class SxGetBlackListReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000047)
    private int start;
    
    @TLVAttribute(tag=10000048)
    private int pageSize;
    
    @TLVAttribute(tag=10000063)
    private byte fetchFlag;

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
     * @return the fetchFlag
     */
    public byte getFetchFlag() {
        return fetchFlag;
    }

    /**
     * @param fetchFlag the fetchFlag to set
     */
    public void setFetchFlag(byte fetchFlag) {
        this.fetchFlag = fetchFlag;
    }
    
}
