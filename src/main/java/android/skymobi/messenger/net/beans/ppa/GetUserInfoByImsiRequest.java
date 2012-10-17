
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPARequestHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC237)
public class GetUserInfoByImsiRequest extends PPARequestHeader {

}
