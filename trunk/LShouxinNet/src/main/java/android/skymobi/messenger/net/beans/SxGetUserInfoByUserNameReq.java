package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 根据用户名查找联系人
 * 1、根据用户名（用户名/手机号码）精确搜索联系人，如果存在则返回该用户的详细信息；
 * 2、过滤手信用户，非手信用户不返回；
 * @ClassName: SxGetUserInfoByUserNameReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-21 上午09:47:07
 */
@EsbSignal(messageCode=0xA857)
public class SxGetUserInfoByUserNameReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
    /**
     * 用户名：手机号码或者用户名
     */
    @TLVAttribute(tag=11010003,description="用户名")
    private String userName;

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
