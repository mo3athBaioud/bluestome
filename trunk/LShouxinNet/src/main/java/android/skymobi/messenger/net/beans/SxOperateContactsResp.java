package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.ResultInfo;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 批量操作联系人 响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA802)
public class SxOperateContactsResp extends ShouxinRespHeader {
	@TLVAttribute(tag = 10000007)
	private long updateTime;
	@TLVAttribute(tag = 20000004)
	private ArrayList<ResultInfo> resultInfo = new ArrayList<ResultInfo>();

	public long getUpdateTime() {
		return updateTime;
	}

	public ArrayList<ResultInfo> getResultInfo() {
		return resultInfo;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public void setResultInfo(ArrayList<ResultInfo> resultInfo) {
		this.resultInfo = resultInfo;
	}
}
