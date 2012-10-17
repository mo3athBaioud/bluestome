package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 添加黑名单用户响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA818)
public class SxAddBlackResp extends ShouxinRespHeader {

	@TLVAttribute(tag=10000007)
	private long updateTime;

	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
}
