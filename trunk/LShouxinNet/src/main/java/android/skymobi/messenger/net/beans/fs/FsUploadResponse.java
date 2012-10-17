package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: FsUploadResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午07:24:04
 */
public class FsUploadResponse {
    
    @TLVAttribute(tag=90049,description="md5值")
    private byte[] md5;

    @TLVAttribute(tag=202,description="响应对象")
    private FsResponseInfo responseInfo;

    /**
     * @return the md5
     */
    public byte[] getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(byte[] md5) {
        this.md5 = md5;
    }

    /**
     * @return the responseInfo
     */
    public FsResponseInfo getResponseInfo() {
        return responseInfo;
    }

    /**
     * @param responseInfo the responseInfo to set
     */
    public void setResponseInfo(FsResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
    
}
