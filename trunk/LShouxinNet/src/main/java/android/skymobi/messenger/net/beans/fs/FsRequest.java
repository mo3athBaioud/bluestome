package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsRequest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午09:44:11
 */
@TLVAttribute(tag=99027)
public class FsRequest extends FsBaseRequest {
    
    @TLVAttribute(tag = 90049)
    private byte[] md5;
    
    @TLVAttribute(tag = 90080)
    private int startPos;

    public byte[] getMd5() {
        return md5;
    }

    public void setMd5(byte[] md5) {
        this.md5 = md5;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

}
