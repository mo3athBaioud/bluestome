package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.RestorableContacts;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * @ClassName: SxGetRestorableContactsResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午04:44:08
 */
@EsbSignal(messageCode=0xA864)
public class SxGetRestorableContactsResp extends ShouxinRespHeader {

    @TLVAttribute(tag=10000041,description="列表(总)大小")
    private int totalSize;
    
    @TLVAttribute(tag=10000047,description="开始位置")
    private int start;
    
    @TLVAttribute(tag=20000019,description="可恢复联系人列表")
    private ArrayList<RestorableContacts> restorableContacts = new ArrayList<RestorableContacts>();

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
     * @return the restorableContacts
     */
    public ArrayList<RestorableContacts> getRestorableContacts() {
        return restorableContacts;
    }

    /**
     * @param restorableContacts the restorableContacts to set
     */
    public void setRestorableContacts(ArrayList<RestorableContacts> restorableContacts) {
        this.restorableContacts = restorableContacts;
    }
    
    
}
