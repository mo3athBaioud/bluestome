package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.BlackList;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取黑名单列表响应
 * @ClassName: GetBlackListResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-9 下午02:52:34
 */
@EsbSignal(messageCode=0xA848)
public class SxGetBlackListResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 20000015)
    private ArrayList<BlackList> blackList = new ArrayList<BlackList>();
    @TLVAttribute(tag = 10000041)
    private int totalSize;
    @TLVAttribute(tag = 10000047)
    private int start;
    
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
     * @return the blackList
     */
    public ArrayList<BlackList> getBlackList() {
        return blackList;
    }
    /**
     * @param blackList the blackList to set
     */
    public void setBlackList(ArrayList<BlackList> blackList) {
        this.blackList = blackList;
    }

}
