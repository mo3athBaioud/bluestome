package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 根据SKYID获取用户名响应
 * @ClassName: GetUsernameResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-27 上午11:09:52
 */
@EsbSignal(messageCode = 0xC21A)
public class GetUsernameResp extends PPAResponseHeader {

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
    
}
