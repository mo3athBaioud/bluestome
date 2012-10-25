
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 比较终端唯一ID响应
 * 
 * @ClassName: SxCompareTerminalUIDResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-25 下午03:07:43
 */
@EsbSignal(messageCode = 0xA880)
public class SxCompareTerminalUIDResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 10000081, description = "-1：服务端没有；0：不相等；1：相等")
    private byte compareResult;

    /**
     * @return the compareResult
     */
    public byte getCompareResult() {
        return compareResult;
    }

    /**
     * @param compareResult the compareResult to set
     */
    public void setCompareResult(byte compareResult) {
        this.compareResult = compareResult;
    }

}
