package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.ContactsStatusItem;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取联系人状态响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal( messageCode = 0xA812 )
public class SxGetContactsStatusResp extends ShouxinRespHeader {
	
	@TLVAttribute(tag = 10000041)
	private int totalSize;
	
	@TLVAttribute(tag = 10000047)
	private int start;
	
    @TLVAttribute(tag=10000007)
    private long updateTime;
    
	@TLVAttribute(tag = 20000007)
	private ArrayList<ContactsStatusItem> items = new ArrayList<ContactsStatusItem>();

	public ArrayList<ContactsStatusItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<ContactsStatusItem> items) {
		this.items = items;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
}
