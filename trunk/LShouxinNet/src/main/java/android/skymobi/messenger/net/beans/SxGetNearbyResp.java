package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.NearUser;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 查找附近的人的响应对象
 * @ClassName: SxGetNearbyResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:42:27
 */
@EsbSignal(messageCode=0xA860)
public class SxGetNearbyResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 10000041)
    private int totalSize;
    
    @TLVAttribute(tag = 10000047)
    private int start;

    @TLVAttribute(tag=20000018,description="附近的人列表")
    private ArrayList<NearUser> users = new ArrayList<NearUser>();

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
