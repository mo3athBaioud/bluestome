package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送网络名片响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA816)
public class SxSendVCardResp extends ShouxinRespHeader{

    @TLVAttribute(tag=10000065,description="离线消息提示内容")
    private String offlineMsg;

    /**
     * @return the offlineMsg
     */
    public String getOfflineMsg() {
        return offlineMsg;
    }

    /**
     * @param offlineMsg the offlineMsg to set
     */
    public void setOfflineMsg(String offlineMsg) {
        this.offlineMsg = offlineMsg;
    }
    
}
