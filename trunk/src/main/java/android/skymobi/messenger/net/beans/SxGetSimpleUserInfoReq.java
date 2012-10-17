package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取联系人简单用户信息
 * @ClassName: SxGetSimpleUserInfoReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午07:24:36
 */
@EsbSignal(messageCode=0xA841)
public class SxGetSimpleUserInfoReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000047,description="开始位置（页码）")
    private int start;
    
    @TLVAttribute(tag=10000048,description="每页数据量")
    private int pageSize;
    
    @TLVAttribute(tag=10000063,description="0：全部，默认；1：指定人")
    private byte fetchFlag = 0;
    
    @TLVAttribute(tag=10000001,description="联系人ID")
    private int contactId;

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

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
}
