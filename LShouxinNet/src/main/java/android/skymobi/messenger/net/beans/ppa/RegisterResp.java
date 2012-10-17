package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC202)
public class RegisterResp extends PPAResponseHeader {

	@TLVAttribute(tag = 11020002,description="注册响应用户信息")
	public RegisterUserInfo registerUserInfo;

	@TLVAttribute(tag = 11010004,description="密码密文,用户终端自动登录")
	public byte[] encryptPasswd;
	
    @TLVAttribute(tag = 11600002, description = "企信通号码")
    private String recvsmsmobile;

    @TLVAttribute(tag = 11600001, description = "发给企信通的短信内容")
    private String smscontent;

	public RegisterUserInfo getRegisterUserInfo() {
		return registerUserInfo;
	}

	public void setRegisterUserInfo(RegisterUserInfo registerUserInfo) {
		this.registerUserInfo = registerUserInfo;
	}

	public byte[] getEncryptPasswd() {
		return encryptPasswd;
	}

	public void setEncryptPasswd(byte[] encryptPasswd) {
		this.encryptPasswd = encryptPasswd;
	}

    /**
     * @return the recvsmsmobile
     */
    public String getRecvsmsmobile() {
        return recvsmsmobile;
    }

    /**
     * @param recvsmsmobile the recvsmsmobile to set
     */
    public void setRecvsmsmobile(String recvsmsmobile) {
        this.recvsmsmobile = recvsmsmobile;
    }

    /**
     * @return the smscontent
     */
    public String getSmscontent() {
        return smscontent;
    }

    /**
     * @param smscontent the smscontent to set
     */
    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

	
}