package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.ContactsStatusItem;

import java.util.ArrayList;

/**
 * @ClassName: NetGetContactsList
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 上午10:22:04
 */
public class NetGetContactsStatusResponse extends NetResponse {

    private long updateTime;
    private ArrayList<ContactsStatusItem> contactsStatusList = new ArrayList<ContactsStatusItem>();
    private int totalSize;
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
     * @return the contactsStatusList
     */
    public ArrayList<ContactsStatusItem> getContactsStatusList() {
        return contactsStatusList;
    }
    /**
     * @param contactsStatusList the contactsStatusList to set
     */
    public void setContactsStatusList(ArrayList<ContactsStatusItem> contactsStatusList) {
        this.contactsStatusList = contactsStatusList;
    }
    
    
}
