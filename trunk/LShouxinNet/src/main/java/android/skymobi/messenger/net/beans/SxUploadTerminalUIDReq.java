
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 上传终端唯一ID
 * 
 * @ClassName: SxUploadTerminalUIDReq
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-25 下午03:41:18
 */
@EsbSignal(messageCode = 0xA877)
public class SxUploadTerminalUIDReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 10000080, description = "终端唯一ID")
    private byte[] tuid;

    /**
     * @return the tuid
     */
    public byte[] getTuid() {
        return tuid;
    }

    /**
     * @param tuid the tuid to set
     */
    public void setTuid(byte[] tuid) {
        this.tuid = tuid;
    }

}
