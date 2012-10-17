package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 添加反馈信息响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA824)
public class SxAddFeedBackResp extends ShouxinRespHeader {

}
