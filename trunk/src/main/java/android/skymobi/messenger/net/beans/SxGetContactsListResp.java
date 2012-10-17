package android.skymobi.messenger.net.beans;



import android.skymobi.messenger.net.beans.commons.Contacts;
import android.skymobi.messenger.net.beans.header.ShouxinRespHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 获取联系人列表响应
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA804)
public class SxGetContactsListResp extends ShouxinRespHeader{
	@TLVAttribute(tag = 20000001)
	private ArrayList<Contacts> contactsList = new ArrayList<Contacts>();
	@TLVAttribute(tag = 10000007)
	private long updateTime = 0;
	@TLVAttribute(tag = 10000041)
	private int totalSize;
	@TLVAttribute(tag = 10000047)
	private int start;
	public ArrayList<Contacts> getContactsList() {
		return contactsList;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setContactsList(ArrayList<Contacts> contactsList) {
		this.contactsList = contactsList;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
