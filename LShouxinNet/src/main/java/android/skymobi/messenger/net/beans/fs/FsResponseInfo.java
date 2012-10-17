
package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ClassName: FsResponseInfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:49:16
 */
public class FsResponseInfo {
    @TLVAttribute(tag = 20201)
    private int responseCode;

    @TLVAttribute(tag = 20202)
    private String responseMsg;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(FsResponseInfo.class,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
