package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 根据SKYID获取用户名请求
 * @ClassName: GetUsernameReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-27 上午11:08:15
 */
@EsbSignal(messageCode = 0xC219)
public class GetUsernameReq extends PPARequestHeader {
    
    @TLVAttribute(tag = 1005,description="斯凯ID")
    private int skyid;

    /**
     * @return the skyid
     */
    public int getSkyid() {
        return skyid;
    }

    /**
     * @param skyid the skyid to set
     */
    public void setSkyid(int skyid) {
        this.skyid = skyid;
    }

    
}
