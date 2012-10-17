
package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.io.Serializable;

/**
 * 黑名单数据
 * @ClassName: BlackList
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-9 下午02:57:42
 */
public class BlackList implements Serializable {

    private static final long serialVersionUID = 1L;
    @TLVAttribute(tag = 1005, description = "斯凯ID")
    private int skyId;
    @TLVAttribute(tag = 10000001, description = "联系人ID")
    private Integer contactId = 0;
    @TLVAttribute(tag = 10000002, description = "联系人名称")
    private String contactName;
    @TLVAttribute(tag = 10000005, description = "手机号码")
    private String phone;
    @TLVAttribute(tag = 11010077, description = "昵称")
    private String nickname;
    @TLVAttribute(tag = 14010020, description = "签名")
    private String usignature;
    @TLVAttribute(tag = 10000064, description = "黑名单类型，1：陌生人；2：联系人")
    private byte blackType;
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
     * @return the contactId
     */
    public Integer getContactId() {
        return contactId;
    }
    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
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
     * @return the blackType
     */
    public byte getBlackType() {
        return blackType;
    }
    /**
     * @param blackType the blackType to set
     */
    public void setBlackType(byte blackType) {
        this.blackType = blackType;
    }
    
}
