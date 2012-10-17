
package android.skymobi.messenger.net.beans.lcs;

import android.skymobi.messenger.net.beans.header.CommonRequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 复合接口协议请求对象
 * 
 * @ClassName: LcsAndroidComplexRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-16 下午02:26:18
 */
@EsbSignal(messageCode = 0xA410)
public class LcsAndroidComplexRequest extends CommonRequestHeader {

    @TLVAttribute(tag = 32010100)
    private String complexMessage;

    /**
     * @return the complexMessage
     */
    public String getComplexMessage() {
        return complexMessage;
    }

    /**
     * @param complexMessage the complexMessage to set
     */
    public void setComplexMessage(String complexMessage) {
        this.complexMessage = complexMessage;
    }

}
