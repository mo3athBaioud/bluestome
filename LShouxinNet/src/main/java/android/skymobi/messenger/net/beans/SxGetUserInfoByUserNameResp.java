package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;
import android.skymobi.messenger.net.beans.ppa.UserInfo;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 根据用户名搜索联系人响应
 * @ClassName: GetUserInfoByUserNameResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-21 上午09:49:58
 */
@EsbSignal(messageCode=0xA858)
public class SxGetUserInfoByUserNameResp extends ShouxinRespHeader {

    /**
     * 是否手信用户 0：不是 1：是
     */
    @TLVAttribute(tag=10000052,description="是否手信用户 0：不是 1：是")
    private byte userType;
    
    /**
     * 用户信息
     */
    @TLVAttribute(tag=14020001,description="用户信息")
    private UserInfo userInfo;

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
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    
}
