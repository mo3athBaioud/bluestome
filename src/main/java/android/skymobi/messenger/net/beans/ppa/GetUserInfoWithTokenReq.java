
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC225)
public class GetUserInfoWithTokenReq extends PPARequestHeader {

    @TLVAttribute(tag = 1005)
    private int skyid;

    @TLVAttribute(tag = 11010014)
    private String token;

    @TLVAttribute(tag = 11800004)
    private int loginTag;

    public final int getSkyid() {
        return skyid;
    }

    public final void setSkyid(int skyid) {
        this.skyid = skyid;
    }

    public final String getToken() {
        return token;
    }

    public final void setToken(String token) {
        this.token = token;
    }

    public final int getLoginTag() {
        return loginTag;
    }

    public final void setLoginTag(int loginTag) {
        this.loginTag = loginTag;
    }

}
