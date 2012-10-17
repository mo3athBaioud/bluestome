
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 发送消息响应
 * 
 * @author Bluestome.Zhang
 */
@EsbSignal(messageCode = 0xA814)
public class SxSendChatMsgResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 10000065, description = "离线消息提示内容")
    private String offlineMsg;

    @TLVAttribute(tag = 10000075, description = "二级响应码（614：对方已离线；615：对方已离开）")
    private int nextRespCode;

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

    /**
     * @return the nextRespCode
     */
    public int getNextRespCode() {
        return nextRespCode;
    }

    /**
     * @param nextRespCode the nextRespCode to set
     */
    public void setNextRespCode(int nextRespCode) {
        this.nextRespCode = nextRespCode;
    }

}
