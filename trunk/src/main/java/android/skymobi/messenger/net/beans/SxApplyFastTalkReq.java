
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 申请快聊
 * 
 * @ClassName: SxApplyFastTalkReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午09:42:09
 */
@EsbSignal(messageCode = 0xA867)
public class SxApplyFastTalkReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 14010006)
    private String usex = "0";

    /**
     * @return the usex
     */
    public String getUsex() {
        return usex;
    }

    /**
     * @param usex the usex to set
     */
    public void setUsex(String usex) {
        this.usex = usex;
    }

}
