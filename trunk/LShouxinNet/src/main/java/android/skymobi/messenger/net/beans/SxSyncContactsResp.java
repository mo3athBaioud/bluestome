package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Contacts;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * @ClassName: SxSyncContactsResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午03:58:47
 */
@EsbSignal(messageCode=0xA836)
public class SxSyncContactsResp extends ShouxinRespHeader {

    @TLVAttribute(tag=10000007)
    private long updateTime;
    
    @TLVAttribute(tag = 20000001)
    private ArrayList<Contacts> contactsList = new ArrayList<Contacts>();
    
    @TLVAttribute(tag = 10000041)
    private int totalSize;
    
    @TLVAttribute(tag = 10000047)
    private int start;

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
     * @return the contactsList
     */
    public ArrayList<Contacts> getContactsList() {
        return contactsList;
    }

    /**
     * @param contactsList the contactsList to set
     */
    public void setContactsList(ArrayList<Contacts> contactsList) {
        this.contactsList = contactsList;
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
    
    
}
