
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: CompareUUIDResp
 * @Description: TODO
 * @author Wing.Hu
 * @date 2012-10-24 下午01:57:38
 */
@EsbSignal(messageCode = 0xA880)
public class SxCompareUUIDResp extends ShouxinRespHeader {

    @TLVAttribute(tag = 10000081)
    private int compareResult;

    public int getCompareResult() {
        return compareResult;
    }

    public void setCompareResult(int compareResult) {
        this.compareResult = compareResult;
    }

}
