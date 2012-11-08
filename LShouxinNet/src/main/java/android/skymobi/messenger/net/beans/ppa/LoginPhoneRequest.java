
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC209)
public class LoginPhoneRequest extends PPARequestHeader {

    @TLVAttribute(tag = 11010003, description = "当小于等于10位时为普通用户名，当等于11位时为手机号码")
    private String username;

    @TLVAttribute(tag = 11010004, description = "密码密文")
    private byte[] encryptPasswd;

    @TLVAttribute(tag = 11010065, description = "验证码编号（连续错误尝试次数超过N次时需传入）")
    private String authCodeId;

    @TLVAttribute(tag = 11010066, description = "验证码（连续错误尝试次数超过N次时需传入）")
    private String authCode;

    @TLVAttribute(tag = 11800009, description = "渠道标志")
    private String channelSymbol;

    @TLVAttribute(tag = 11010092)
    private String uuid = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getEncryptPasswd() {
        return encryptPasswd;
    }

    public void setEncryptPasswd(byte[] encryptPasswd) {
        this.encryptPasswd = encryptPasswd;
    }

    public String getAuthCodeId() {
        return authCodeId;
    }

    public void setAuthCodeId(String authCodeId) {
        this.authCodeId = authCodeId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getChannelSymbol() {
        return channelSymbol;
    }

    public void setChannelSymbol(String channelSymbol) {
        this.channelSymbol = channelSymbol;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
