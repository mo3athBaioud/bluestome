
package android.skymobi.messenger.net.beans.fs;

import android.skymobi.messenger.net.beans.commons.AbstractCommonBean;

import com.skymobi.android.bean.tlv.TLVSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsBaseRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:42:06
 */
public class FsBaseRequest extends AbstractCommonBean implements TLVSignal {
    private String event;
    @TLVAttribute(tag = 50002, description = "0 http,1 tcp")
    private int reqType = 1;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

}
