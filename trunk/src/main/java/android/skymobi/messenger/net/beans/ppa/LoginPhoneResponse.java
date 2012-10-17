package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.skyaaa.bean.common.LoginUserInfo;

@EsbSignal(messageCode = 0xC20A)
public class LoginPhoneResponse extends PPAResponseHeader {

    @TLVAttribute(tag = 1005, description = "斯凯ID")
    private int skyId;

    @TLVAttribute(tag = 11010014, description = "授权令牌")
    private String token;

    @TLVAttribute(tag = 11010010,description = "第一次登录则返回初始密码明文 否则为空")
    private String passwd;

    @TLVAttribute(tag = 11010004, description = "用于终端自动登录")
    private byte[] encryptPasswd;

    @TLVAttribute(tag = 11010023, description = "上一次登录的时间")
    private int lastLogin;

    @TLVAttribute(tag = 11010024, description = "标识用户上一次登录的身份")
    private byte identity;

    @TLVAttribute(tag = 11010065,description = "验证码编号（连续错误尝试次数等于或超过N次时返回）")
    private String authCodeId;

    @TLVAttribute(tag = 11010067, description = "验证码图片（连续错误尝试次数等于或超过N次时返回）")
    private byte[] authCodeImg;

    @TLVAttribute(tag = 11010025, description = "登陆后需要检查是否有设置密保问题")
    private Integer secretQTag;

    @TLVAttribute(tag = 11600002, description = "企信通号码")
    private String recvsmsmobile;

    @TLVAttribute(tag = 11600001,description = "发给企信通的短信内容")
    private String smscontent;

    @TLVAttribute(tag = 11020003, description = "")
    private LoginUserInfo userInfo;

    public int getSkyId() {
        return skyId;
    }

    public void setSkyId(int skyId) {
        this.skyId = skyId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
    }

    public byte getIdentity() {
        return identity;
    }

    public void setIdentity(byte identity) {
        this.identity = identity;
    }

    public String getAuthCodeId() {
        return authCodeId;
    }

    public void setAuthCodeId(String authCodeId) {
        this.authCodeId = authCodeId;
    }

    public byte[] getAuthCodeImg() {
        return authCodeImg;
    }

    public void setAuthCodeImg(byte[] authCodeImg) {
        this.authCodeImg = authCodeImg;
    }

    public LoginUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public byte[] getEncryptPasswd() {
        return encryptPasswd;
    }

    public void setEncryptPasswd(byte[] encryptPasswd) {
        this.encryptPasswd = encryptPasswd;
    }

    public int getSecretQTag() {
        return secretQTag;
    }

    public void setSecretQTag(int secretQTag) {
        this.secretQTag = secretQTag;
    }

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
}
