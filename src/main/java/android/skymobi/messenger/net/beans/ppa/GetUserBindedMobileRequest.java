package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: GetUserBindedMobileRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-17 下午04:37:06
 */
@EsbSignal(messageCode = 0xC221)
public class GetUserBindedMobileRequest extends PPARequestHeader {

    @TLVAttribute(tag=11010003)
    private String username;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    

}
