package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.FriendsList;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;

/**
 * 获取推荐好友列表响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal( messageCode = 0xA808 )
public class SxGetFriendsResp extends ShouxinRespHeader {
	@TLVAttribute(tag = 20000003)
	private ArrayList<FriendsList> fiendList = new ArrayList<FriendsList>();
	@TLVAttribute(tag = 10000041)
	private int totalSize;
	@TLVAttribute(tag =10000047)
	private int start;
    @TLVAttribute(tag=10000007,description="推荐好友版本")
    private long updateTime = 0L;

	public ArrayList<FriendsList> getFiendList() {
		return fiendList;
	}

	public void setFiendList(ArrayList<FriendsList> fiendList) {
		this.fiendList = fiendList;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		return start;
	}

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

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
