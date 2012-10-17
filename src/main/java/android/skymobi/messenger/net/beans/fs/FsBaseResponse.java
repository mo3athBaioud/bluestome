
package android.skymobi.messenger.net.beans.fs;

import android.skymobi.messenger.net.beans.commons.AbstractCommonBean;

import com.skymobi.android.bean.tlv.TLVSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsBaseResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:47:52
 */
public class FsBaseResponse extends AbstractCommonBean implements TLVSignal {
    @TLVAttribute(tag = 202)
    private FsResponseInfo responseInfo = new FsResponseInfo();

    public FsResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(FsResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

}
