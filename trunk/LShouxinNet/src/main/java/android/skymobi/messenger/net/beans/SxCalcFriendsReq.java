package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 *  计算推荐好友请求
 * @ClassName: SxCalcFriendsReq
 * @Description: 
 * 1. 实时计算登录用户的推荐好友数据
 * 当用户首次使用手信时，因云端没有该用户的通讯录，所以在用户登录时无法计算该用户的推荐好友，在用户上传完通讯录后，终端调用该请求，实时计算用户的推荐好友。
 * 2. 获取指定联系人与登录用户的推荐关系（陌生人打招呼时显示的提示语）
 * 在推荐算法中会计算推荐好友与登录用户的推荐关系，附带在好友列表中，当陌生人打招呼时终端根据skyid从推荐列表中找到该陌生人，拿出提示语显示，
 * 当找不到时，终端需请求该接口实时计算提示语用于显示。
 * 
 * @author Bluestome.Zhang
 * @date 2012-5-3 下午03:19:05
 */
@EsbSignal(messageCode=0xA845)
public class SxCalcFriendsReq extends ShouxinReqHeader {

    @TLVAttribute(tag=10000045,description="目标skyid（使用说明中，第二种情况时必传，第一种情况不传）")
    private int destSkyid;
    
    @TLVAttribute(tag=11010014,description="授权码")
    private String token;

    /**
     * @return the destSkyid
     */
    public int getDestSkyid() {
        return destSkyid;
    }

    /**
     * @param destSkyid the destSkyid to set
     */
    public void setDestSkyid(int destSkyid) {
        this.destSkyid = destSkyid;
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
    
    
}
