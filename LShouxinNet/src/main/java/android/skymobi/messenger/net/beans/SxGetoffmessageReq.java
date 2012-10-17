package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 离线消息推送请求接口
 * @ClassName: SxGetoffmessageReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午01:46:07
 */
@EsbSignal(messageCode=0xB101)
public class SxGetoffmessageReq extends ShouxinReqHeader {


    @TLVAttribute(tag=11010012)
    private int appid;

    /**
     * @return the appid
     */
    public int getAppid() {
        return appid;
    }

    /**
     * @param appid the appid to set
     */
    public void setAppid(int appid) {
        this.appid = appid;
    }
    
}
