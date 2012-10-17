package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 用户简单状态对象
 * @ClassName: SimpleStatus
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午07:29:22
 */
public class SimpleStatus {

    @TLVAttribute(tag=1005,description="斯凯ID")
    private int skyId;
    
    @TLVAttribute(tag=10000005,description="电话号码")
    private String phone;
    
    @TLVAttribute(tag=10000020,description="联系人在线状态")
    private byte status;
    
    @TLVAttribute(tag=10000052,description="是否手信用户 0:不是  1:是")
    private byte userType;

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
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * @return the userType
     */
    public byte getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(byte userType) {
        this.userType = userType;
    }
    
    
}
