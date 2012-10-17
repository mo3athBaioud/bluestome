
package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.FriendsList;

import java.util.ArrayList;

/**
 * @ClassName: NetGetFriendstResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午05:31:41
 */
public class NetGetFriendstResponse extends NetResponse {

    private ArrayList<FriendsList> fiendList = new ArrayList<FriendsList>();
    private int totalSize = 0;
    private int start = 0;
    private long updateTime = 0L;
    private boolean isUpdate = true;

    /**
     * @return the fiendList
     */
    public ArrayList<FriendsList> getFiendList() {
        return fiendList;
    }

    /**
     * @param fiendList the fiendList to set
     */
    public void setFiendList(ArrayList<FriendsList> fiendList) {
        this.fiendList = fiendList;
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
     * 判断是否有联系人是否有更新
     * @return
     */
    public boolean isUpdate() {
        return isUpdate;
    }

    /**
     * @param isUpdate the isUpdate to set
     */
    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

}
