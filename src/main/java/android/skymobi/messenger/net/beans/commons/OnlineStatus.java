package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: OnlineStatus
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-18 上午09:23:00
 */
public class OnlineStatus {

    @TLVAttribute(tag = 10000016,description="陌生人skyid")
    private int buddyId;

    @TLVAttribute(tag = 10000020,description="状态 0:离线  1:在线")
    private byte status;

    /**
     * @return the buddyId
     */
    public int getBuddyId() {
        return buddyId;
    }

    /**
     * @param buddyId the buddyId to set
     */
    public void setBuddyId(int buddyId) {
        this.buddyId = buddyId;
    }

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }
    
    
}
