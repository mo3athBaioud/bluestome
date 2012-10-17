package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Contacts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TLVAttribute(tag=10000001)
	private Integer contactId =0;
	@TLVAttribute(tag=10000013)
	private Integer sequenceId =0;
	@TLVAttribute(tag=10000002)
	private String contactName;
	@TLVAttribute(tag=10000003)
	private String memo;
	@TLVAttribute(tag=20000002)
	private ArrayList<Phone> phoneList = new ArrayList<Phone>();
	@TLVAttribute(tag=10000006)
	private Byte group = 0;
	@TLVAttribute(tag=10000015)
	private Byte action;
	@TLVAttribute(tag=10000046)
	private Byte contactType;
    @TLVAttribute(tag=10000062)
	private Byte contactNameType;
	public Integer getContactId() {
		return contactId;
	}
	public String getMemo() {
		return memo;
	}
	public ArrayList<Phone> getPhoneList() {
		return phoneList;
	}
	public Byte getGroup() {
		return group;
	}
	public Byte getAction() {
		return action;
	}
	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setPhoneList(ArrayList<Phone> phoneList) {
		this.phoneList = phoneList;
	}
	public void setGroup(Byte group) {
		this.group = group;
	}
	public void setAction(Byte action) {
		this.action = action;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Byte getContactType() {
		return contactType;
	}
	public void setContactType(Byte contactType) {
		this.contactType = contactType;
	}
    public Byte getContactNameType() {
        return contactNameType;
    }
    public void setContactNameType(Byte contactNameType) {
        this.contactNameType = contactNameType;
    }
    @Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
