
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: UpdateUUIDReq
 * @Description: TODO
 * @author Wing.Hu
 * @date 2012-10-24 下午01:57:23
 */
@EsbSignal(messageCode = 0xA877)
public class SxUpdateUUIDReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000080)
    private String tuid;

    public String getTuid() {
        return tuid;
    }

    public void setTuid(String tuid) {
        this.tuid = tuid;
    }

}
