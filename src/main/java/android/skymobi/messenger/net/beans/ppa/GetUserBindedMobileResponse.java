package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: GetUserBindedMobileResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-17 下午04:38:17
 */
@EsbSignal(messageCode = 0xC222)
public class GetUserBindedMobileResponse extends PPAResponseHeader {

    @TLVAttribute(tag=11010006)
    private String mobile;

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    
}
