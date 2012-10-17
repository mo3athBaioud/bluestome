package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.SimpleStatusItem;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取联系人在线状态响应
 * @ClassName: SxGetSimpleStatusResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午07:26:52
 */
@EsbSignal(messageCode=0xA840)
public class SxGetSimpleStatusResp extends ShouxinRespHeader {

    @TLVAttribute(tag=20000010,description="联系人状态列表")
    private ArrayList<SimpleStatusItem> simpleStatusItem = new ArrayList<SimpleStatusItem>();
    
    @TLVAttribute(tag=10000041,description="联系人总数大小")
    private int totalSize;
    
    @TLVAttribute(tag=10000047,description="开始位置（页码）")
    private int start;

    /**
     * @return the simpleStatusItem
     */
    public ArrayList<SimpleStatusItem> getSimpleStatusItem() {
        return simpleStatusItem;
    }

    /**
     * @param simpleStatusItem the simpleStatusItem to set
     */
    public void setSimpleStatusItem(ArrayList<SimpleStatusItem> simpleStatusItem) {
        this.simpleStatusItem = simpleStatusItem;
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
