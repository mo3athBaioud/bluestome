package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: RestorableContacts
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-8-13 下午04:48:36
 */
public class RestorableContacts {

    @TLVAttribute(tag=10000073,description="可恢复的ID")
    private int restoreId;
    
    @TLVAttribute(tag=10000002,description="联系人姓名")
    private String contactName;
    
    @TLVAttribute(tag=11010077,description="个性昵称")
    private String personNickname;
    
    @TLVAttribute(tag=11010003,description="用户名")
    private String userName;
    
    @TLVAttribute(tag=10000011,description="头像（如果有自定义头像，该字段放的是自定义头像的UUID值，否则放的是设置的系统头像，如果没有则不下发）")
    private String imageHead;
    
    @TLVAttribute(tag=1005,description="skyid")
    private int skyId;
    
    @TLVAttribute(tag=10000005,description="电话号码")
    private String phone;

    /**
     * @return the restoreId
     */
    public int getRestoreId() {
        return restoreId;
    }

    /**
     * @param restoreId the restoreId to set
     */
    public void setRestoreId(int restoreId) {
        this.restoreId = restoreId;
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
     * @return the personNickname
     */
    public String getPersonNickname() {
        return personNickname;
    }

    /**
     * @param personNickname the personNickname to set
     */
    public void setPersonNickname(String personNickname) {
        this.personNickname = personNickname;
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
    
    
}
