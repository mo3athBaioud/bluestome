package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 设置用户信息响应
 * @ClassName: SxSetUserinfo
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-12 下午05:25:41
 */
@EsbSignal(messageCode=0xA838)
public class SxSetUserinfoResp extends ShouxinRespHeader {
    
}
