package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.SimpleUserInfoItem;

import java.util.ArrayList;

/**
 * @ClassName: NetGetContactsList2Response
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 上午10:22:04
 */
public class NetGetContactsList2Response extends NetResponse {

    private long updateTime;
    private ArrayList<SimpleUserInfoItem> contactsList = new ArrayList<SimpleUserInfoItem>();
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
     * @return the contactsList
     */
    public ArrayList<SimpleUserInfoItem> getContactsList() {
        return contactsList;
    }
    /**
     * @param contactsList the contactsList to set
     */
    public void setContactsList(ArrayList<SimpleUserInfoItem> contactsList) {
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
