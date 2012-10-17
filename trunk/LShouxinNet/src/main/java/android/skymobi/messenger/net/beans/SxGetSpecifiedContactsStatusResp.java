package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.ContactsStatusItem;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取指定联系人状态响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA828)
public class SxGetSpecifiedContactsStatusResp extends ShouxinRespHeader {

	
	@TLVAttribute(tag=20000007)
	private ArrayList<ContactsStatusItem> contactsStatusItem = new ArrayList<ContactsStatusItem>();
	
	@TLVAttribute(tag=10000041)
	private int totalSize;
	
	@TLVAttribute(tag=10000007)
	private long updateTime;

	public ArrayList<ContactsStatusItem> getContactsStatusItem() {
		return contactsStatusItem;
	}

	public void setContactsStatusItem(
			ArrayList<ContactsStatusItem> contactsStatusItem) {
		this.contactsStatusItem = contactsStatusItem;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
