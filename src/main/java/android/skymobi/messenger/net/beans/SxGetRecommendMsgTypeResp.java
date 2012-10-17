package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.MsgType;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取推荐短信类别响应
 * @ClassName: SxGetRecommendMsgTypeResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-7-18 上午09:34:46
 */
@EsbSignal(messageCode=0xA854)
public class SxGetRecommendMsgTypeResp extends ShouxinRespHeader {

    @TLVAttribute(tag=10000007,description="服务端的本版时间戳")
    private long updateTime;

    @TLVAttribute(tag=20000006)
    private ArrayList<MsgType> msgTypeList = new ArrayList<MsgType>();
    
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
     * @return the msgTypeList
     */
    public ArrayList<MsgType> getMsgTypeList() {
        return msgTypeList;
    }

    /**
     * @param msgTypeList the msgTypeList to set
     */
    public void setMsgTypeList(ArrayList<MsgType> msgTypeList) {
        this.msgTypeList = msgTypeList;
    }
    
}
