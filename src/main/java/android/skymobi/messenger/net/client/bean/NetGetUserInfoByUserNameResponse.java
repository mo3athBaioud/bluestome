package android.skymobi.messenger.net.client.bean;

import android.skymobi.messenger.net.beans.ppa.UserInfo;

/**
 * @ClassName: NetGetUserInfoByUserNameResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-21 上午09:57:33
 */
public class NetGetUserInfoByUserNameResponse extends NetResponse {
    
    /**
     * 是否手信用户 0：不是 1：是
     */
    private byte userType;
    
    /**
     * 用户信息
     */
    private NetUserInfo userInfo;

    /**
     * 是否查找到了联系人，在错误码为[612]时，该值为false.
     */
    private boolean isFinded = true;
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

    /**
     * @return the userInfo
     */
    public NetUserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(NetUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the isFinded
     */
    public boolean isFinded() {
        return isFinded;
    }

    /**
     * @param isFinded the isFinded to set
     */
    public void setFinded(boolean isFinded) {
        this.isFinded = isFinded;
    }
    
}
