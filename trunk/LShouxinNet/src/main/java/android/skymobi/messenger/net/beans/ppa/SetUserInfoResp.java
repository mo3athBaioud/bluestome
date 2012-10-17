package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 设置用户信息响应对象
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode = 0xC220)
public class SetUserInfoResp extends PPAResponseHeader {

}
