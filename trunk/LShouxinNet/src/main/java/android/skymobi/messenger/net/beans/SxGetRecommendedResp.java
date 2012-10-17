package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取用户设置的是否被推荐的值的响应
 * @ClassName: SxGetRecommendedResp
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-10 下午01:30:51
 */
@EsbSignal(messageCode=0xA850)
public class SxGetRecommendedResp extends ShouxinRespHeader {

    /**
     * 是否推荐
     */
    @TLVAttribute(tag=10000055,description="0：否，1：是")
    private byte recommended;

    @TLVAttribute(tag=10000068,description="0：不隐藏LBS信息；1：隐藏LBS信息")
    private byte hideLBS;
    
    /**
     * @return the recommended
     */
    public byte getRecommended() {
        return recommended;
    }

    /**
     * @param recommended the recommended to set
     */
    public void setRecommended(byte recommended) {
        this.recommended = recommended;
    }

    /**
     * @return the hideLBS
     */
    public byte getHideLBS() {
        return hideLBS;
    }

    /**
     * @param hideLBS the hideLBS to set
     */
    public void setHideLBS(byte hideLBS) {
        this.hideLBS = hideLBS;
    }

}
