package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.SimpleUserInfoItem;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 *获取联系人简单用户信息响应
 * @ClassName: SxGetSimpleUserInfoResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午07:26:52
 */
@EsbSignal(messageCode=0xA842)
public class SxGetSimpleUserInfoResp extends ShouxinRespHeader {

    @TLVAttribute(tag=20000012,description="联系人信息列表")
    private ArrayList<SimpleUserInfoItem> simpleUserItem = new ArrayList<SimpleUserInfoItem>();
    
    @TLVAttribute(tag=10000041,description="联系人总数大小")
    private int totalSize;
    
    @TLVAttribute(tag=10000047,description="开始位置（页码）")
    private int start;
    
    /**
     * @return the simpleUserItem
     */
    public ArrayList<SimpleUserInfoItem> getSimpleUserItem() {
        return simpleUserItem;
    }

    /**
     * @param simpleUserItem the simpleUserItem to set
     */
    public void setSimpleUserItem(ArrayList<SimpleUserInfoItem> simpleUserItem) {
        this.simpleUserItem = simpleUserItem;
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
