package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.ContactsStatusItem;

import java.util.ArrayList;

/**
 * @ClassName: NetSpecifiedContactsStatusResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午02:38:45
 */
public class NetSpecifiedContactsStatusResponse extends NetResponse {

    private ArrayList<ContactsStatusItem> items = new ArrayList<ContactsStatusItem>();
    
    private int totalSize;
    
    private long updateTime;

    /**
     * @return the items
     */
    public ArrayList<ContactsStatusItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<ContactsStatusItem> items) {
        this.items = items;
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
