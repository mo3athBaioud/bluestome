package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: SxSaveUserlocationResp 保存用户地理位置信息响应
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:17:55
 */
public class SxSaveUserlocationResp extends ShouxinRespHeader {

    @TLVAttribute(tag=34010017,description="如果为0 ，则是没时间间隔，下次启动时上传。单位：分")
    private int nextUploadTime = 0;

    /**
     * @return the nextUploadTime
     */
    public int getNextUploadTime() {
        return nextUploadTime;
    }

    /**
     * @param nextUploadTime the nextUploadTime to set
     */
    public void setNextUploadTime(int nextUploadTime) {
        this.nextUploadTime = nextUploadTime;
    }
    
    
}
