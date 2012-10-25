
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 解绑通知
 * 
 * @ClassName: SxBindChangeNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-25 下午02:52:54
 */
@EsbSignal(messageCode = 0xB809)
public class SxBindChangeNotify extends ShouxinRespHeader {

    @TLVAttribute(tag = 1005, description = "用户的skyId")
    private int skyid;

    @TLVAttribute(tag = 10000005, description = "绑定的电话号码/解绑之前绑定的电话号码")
    private String phone;

    @TLVAttribute(tag = 10000078, description = "绑定解绑通知类型（1：绑定通知；2：解绑通知）")
    private byte notifyType;

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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the notifyType
     */
    public byte getNotifyType() {
        return notifyType;
    }

    /**
     * @param notifyType the notifyType to set
     */
    public void setNotifyType(byte notifyType) {
        this.notifyType = notifyType;
    }

}
