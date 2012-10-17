package android.skymobi.messenger.net.client.bean;

import java.util.ArrayList;

/**
 * @ClassName: NetSyncContactsResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午06:07:12
 */
public class NetSyncContactsResponse extends NetResponse {

    private long updateTime;
    
    private ArrayList<NetContacts> contactsList = new ArrayList<NetContacts>();
    
    private int totalSize;
    
    private int start;
    
    /**
     * 是否有更新:
     * true: 有更新
     * false: 没有更新
     */
    private boolean hasUpdate = true;

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
    public ArrayList<NetContacts> getContactsList() {
        return contactsList;
    }

    /**
     * @param contactsList the contactsList to set
     */
    public void setContactsList(ArrayList<NetContacts> contactsList) {
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

    /**
     * @return the hasUpdate
     */
    public boolean isHasUpdate() {
        return hasUpdate;
    }

    /**
     * @param hasUpdate the hasUpdate to set
     */
    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    
}
