package android.skymobi.messenger.net.beans.fs;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 图片上传响应对象
 * @ClassName: FsImageUploadResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-14 下午07:24:04
 */
public class FsImageUploadResponse {
    
    @TLVAttribute(tag=90089,description="上传头像成功返回的唯一标识")
    private String uuid;

    @TLVAttribute(tag=202,description="响应对象")
    private FsResponseInfo responseInfo;

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

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
   
}
