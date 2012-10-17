
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC234)
public class JudgeMobileStatusResponse extends PPAResponseHeader {

    @TLVAttribute(tag = 11600002, description = "企信通号码")
    private String recvsmsmobile;

    @TLVAttribute(tag = 11600001, description = "发给企信通的短信内容")
    private String smscontent;

    @TLVAttribute(tag = 11600019, description = "1 未绑定|2 已经绑定 imsi不一致|3 已经绑定 imsi一致")
    private byte status;

    public String getRecvsmsmobile() {
        return recvsmsmobile;
    }

    public void setRecvsmsmobile(String recvsmsmobile) {
        this.recvsmsmobile = recvsmsmobile;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

}
