package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.commons.FriendsList;

import java.util.ArrayList;

/**
 * 好友推荐通知对象
 * @ClassName: NetFriendsMsgNotify
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:51:36
 */
public class NetFriendsMsgNotify extends NetNotify {

    private int seqid;
    private String timestamp;
    private ArrayList<FriendsList> friendsList = new ArrayList<FriendsList>();
    /**
     * @return the seqid
     */
    public final int getSeqid() {
        return seqid;
    }
    /**
     * @param seqid the seqid to set
     */
    public final void setSeqid(int seqid) {
        this.seqid = seqid;
    }
    /**
     * @return the timestamp
     */
    public final String getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp the timestamp to set
     */
    public final void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * @return the friendsList
     */
    public final ArrayList<FriendsList> getFriendsList() {
        return friendsList;
    }
    /**
     * @param friendsList the friendsList to set
     */
    public final void setFriendsList(ArrayList<FriendsList> friendsList) {
        this.friendsList = friendsList;
    }
 
    
}
