
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC22A)
public class GetMessage4SetPasswdResp extends PPAResponseHeader {

    @TLVAttribute(tag = 11600002)
    private String recvsmsmobile;

    @TLVAttribute(tag = 11600001)
    private String smscontent;

    public final String getRecvsmsmobile() {
        return recvsmsmobile;
    }

    public final void setRecvsmsmobile(String recvsmsmobile) {
        this.recvsmsmobile = recvsmsmobile;
    }

    public final String getSmscontent() {
        return smscontent;
    }

    public final void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

}
