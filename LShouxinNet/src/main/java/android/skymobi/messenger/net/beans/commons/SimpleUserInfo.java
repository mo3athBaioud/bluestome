
package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: SimpleUserInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午08:34:04
 */
public class SimpleUserInfo {
    @TLVAttribute(tag = 1005,description="斯凯ID")
    private int skyId;
    
    @TLVAttribute(tag = 10000005,description="手机号码")
    private String phone;
    
    @TLVAttribute(tag = 11010003,description="用户名")
    private String userName;
    
    @TLVAttribute(tag = 14010020,description="签名")
    private String usignature;
    
    @TLVAttribute(tag = 11010077,description="昵称")
    private String nickname;
    
    @TLVAttribute(tag = 10000011,description="头像")
    private String imageHead;

    /**
     * @return the skyId
     */
    public int getSkyId() {
        return skyId;
    }

    /**
     * @param skyId the skyId to set
     */
    public void setSkyId(int skyId) {
        this.skyId = skyId;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the usignature
     */
    public String getUsignature() {
        return usignature;
    }

    /**
     * @param usignature the usignature to set
     */
    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the imageHead
     */
    public String getImageHead() {
        return imageHead;
    }

    /**
     * @param imageHead the imageHead to set
     */
    public void setImageHead(String imageHead) {
        this.imageHead = imageHead;
    }
    
}
