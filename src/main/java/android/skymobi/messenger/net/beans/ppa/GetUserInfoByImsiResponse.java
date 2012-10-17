
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC238)
public class GetUserInfoByImsiResponse extends PPAResponseHeader {

    @TLVAttribute(tag = 11010003)
    private String username;
    @TLVAttribute(tag = 11010005)
    private String nickname;
    @TLVAttribute(tag = 11010077)
    private String persionNickname;
    @TLVAttribute(tag = 11010006)
    private String mobile;
    @TLVAttribute(tag = 1005)
    private int skyid;

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getNickname() {
        return nickname;
    }

    public final void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public final String getPersionNickname() {
        return persionNickname;
    }

    public final void setPersionNickname(String persionNickname) {
        this.persionNickname = persionNickname;
    }

    public final String getMobile() {
        return mobile;
    }

    public final void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public final int getSkyid() {
        return skyid;
    }

    public final void setSkyid(int skyid) {
        this.skyid = skyid;
    }
}
