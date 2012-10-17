package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;
import android.skymobi.messenger.net.beans.ppa.UserInfo;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 设置用户信息请求
 * @ClassName: SxSetUserinfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-12 下午05:25:41
 */
@EsbSignal(messageCode=0xA837)
public class SxSetUserinfoReq extends ShouxinReqHeader {
    
    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
    @TLVAttribute(tag=14020001)
    private UserInfo userinfo;
    
    @TLVAttribute(tag=11010014)
    private String token;

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
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the sourceId
     */
    public short getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(short sourceId) {
        this.sourceId = sourceId;
    }
    
    
}
