package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: SxGetRestorableContactsReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午04:40:43
 */
@EsbSignal(messageCode=0xA863)
public class SxGetRestorableContactsReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000047)
    private int start;
    
    @TLVAttribute(tag=10000048)
    private int pageSize;

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
