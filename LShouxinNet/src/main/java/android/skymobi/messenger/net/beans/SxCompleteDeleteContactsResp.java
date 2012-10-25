
package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 彻底删除联系人请求
 * 
 * @author Bluestome.Zhang
 */
@EsbSignal(messageCode = 0xA882)
public class SxCompleteDeleteContactsResp extends ShouxinRespHeader {

}
