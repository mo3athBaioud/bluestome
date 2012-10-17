package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 修改密码
 * @ClassName: ModifyPwdRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-9 下午02:54:54
 */
@EsbSignal(messageCode=0xC20F)
public class ModifyPwdRequest extends PPARequestHeader{
    
    @TLVAttribute(tag = 11010014,description="授权令牌")
    private String token;
    
    @TLVAttribute(tag=11010015 ,description = "原密码 utf-16be")
    private byte[] encryptOldPasswd;

    @TLVAttribute(tag=11010016 ,description = "新密码 utf-16be")
    private byte[] encryptNewPasswd;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the encryptOldPasswd
     */
    public byte[] getEncryptOldPasswd() {
        return encryptOldPasswd;
    }

    /**
     * @param encryptOldPasswd the encryptOldPasswd to set
     */
    public void setEncryptOldPasswd(byte[] encryptOldPasswd) {
        this.encryptOldPasswd = encryptOldPasswd;
    }

    /**
     * @return the encryptNewPasswd
     */
    public byte[] getEncryptNewPasswd() {
        return encryptNewPasswd;
    }

    /**
     * @param encryptNewPasswd the encryptNewPasswd to set
     */
    public void setEncryptNewPasswd(byte[] encryptNewPasswd) {
        this.encryptNewPasswd = encryptNewPasswd;
    }

}
