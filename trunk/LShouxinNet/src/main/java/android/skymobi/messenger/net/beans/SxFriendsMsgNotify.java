package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.FriendsList;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 推荐好友消息
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xB806)
public class SxFriendsMsgNotify extends ShouxinRespHeader {
	
	@TLVAttribute(tag=1001)
	private int seqid;
	
	@TLVAttribute(tag=10000051)
	private String timestamp;
	
	@TLVAttribute(tag=20000003)
	private ArrayList<FriendsList> friendsList = new ArrayList<FriendsList>();
	
	public final int getSeqid() {
		return seqid;
	}

	public final void setSeqid(int seqid) {
		this.seqid = seqid;
	}

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the friendsList
     */
    public ArrayList<FriendsList> getFriendsList() {
        return friendsList;
    }

    /**
     * @param friendsList the friendsList to set
     */
    public void setFriendsList(ArrayList<FriendsList> friendsList) {
        this.friendsList = friendsList;
    }

}
