
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC229)
public class GetMessage4SetPasswdReq extends PPARequestHeader {

    @TLVAttribute(tag = 11010003)
    private String username;

    @TLVAttribute(tag = 11010004)
    private byte[] encryptPasswd;

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final byte[] getEncryptPasswd() {
        return encryptPasswd;
    }

    public final void setEncryptPasswd(byte[] encryptPasswd) {
        this.encryptPasswd = encryptPasswd;
    }


}
