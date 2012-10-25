
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 上传终端能力信息
 * 
 * @ClassName: SxUploadAbilityReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-25 下午03:51:59
 */
@EsbSignal(messageCode = 0xA875)
public class SxUploadAbilityReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000079, description = "能力值（0：文本；）")
    private int ability;

    /**
     * @return the ability
     */
    public int getAbility() {
        return ability;
    }

    /**
     * @param ability the ability to set
     */
    public void setAbility(int ability) {
        this.ability = ability;
    }

}
