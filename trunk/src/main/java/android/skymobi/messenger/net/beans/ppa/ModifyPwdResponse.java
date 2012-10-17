package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 修改密码响应
 * @ClassName: ModifyPwdResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-9 下午02:57:45
 */
@EsbSignal(messageCode=0xC210)
public class ModifyPwdResponse extends PPAResponseHeader {

    @TLVAttribute(tag = 11010004,description = "密码密文 用于终端自动登录")
    private byte[] encryptpasswd;

    /**
     * @return the encryptpasswd
     */
    public byte[] getEncryptpasswd() {
        return encryptpasswd;
    }

    /**
     * @param encryptpasswd the encryptpasswd to set
     */
    public void setEncryptpasswd(byte[] encryptpasswd) {
        this.encryptpasswd = encryptpasswd;
    }

}
