package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 设置是否推荐响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA834)
public class SxSetRecommendedResp extends ShouxinRespHeader {

}
