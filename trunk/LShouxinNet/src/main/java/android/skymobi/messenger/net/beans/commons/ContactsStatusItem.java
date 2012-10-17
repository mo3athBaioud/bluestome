package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactsStatusItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TLVAttribute(tag = 10000001)
	private int contactId;
	
	@TLVAttribute(tag = 20000005)
	private ArrayList<ContactsStatus> items = new ArrayList<ContactsStatus>();

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public ArrayList<ContactsStatus> getItems() {
		return items;
	}

	public void setItems(ArrayList<ContactsStatus> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
