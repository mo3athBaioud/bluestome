package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.NearUser;

import java.util.ArrayList;

/**
 * @ClassName: NetGetNearByFriendResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午06:02:59
 */
public class NetGetNearByFriendResponse extends NetResponse {

    /**
     * 记录总数
     */
    private int totalSize;
    
    /**
     * 页码
     */
    private int start;

    /**
     * 附近的好友列表
     */
    private ArrayList<NearUser> users = new ArrayList<NearUser>();
    
    /**
     * 该参数只针对，服务端下发的需要重新计算的提示信息
     */
    private boolean needRecalculated = false;

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
     * @return the needRecalculated
     */
    public boolean isNeedRecalculated() {
        return needRecalculated;
    }

    /**
     * @param needRecalculated the needRecalculated to set
     */
    public void setNeedRecalculated(boolean needRecalculated) {
        this.needRecalculated = needRecalculated;
    }

    /**
     * @return the users
     */
    public ArrayList<NearUser> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<NearUser> users) {
        this.users = users;
    }
    
}
