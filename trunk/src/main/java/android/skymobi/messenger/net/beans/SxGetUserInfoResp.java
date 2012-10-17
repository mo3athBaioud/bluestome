package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;
import android.skymobi.messenger.net.beans.ppa.UserInfo;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取联系人详情响应 [有一个用户结构体未展现]
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA832)
public class SxGetUserInfoResp extends ShouxinRespHeader {

    @TLVAttribute(tag=14020001,description="用户信息")
    private UserInfo userinfo;
    
    @TLVAttribute(tag=10000020,description="在线状态0 / 不下发：不在线 1：在线")
    private byte status = 0;

    /**
     * @return the userinfo
     */
    public UserInfo getUserinfo() {
        return userinfo;
    }

    /**
     * @param userinfo the userinfo to set
     */
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
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
    
}
