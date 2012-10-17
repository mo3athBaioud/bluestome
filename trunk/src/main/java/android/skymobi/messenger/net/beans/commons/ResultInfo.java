package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class ResultInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TLVAttribute(tag=10000013,description="序列")
	private int sequenceId;
	@TLVAttribute(tag=10000001 ,description="通讯录id")
	private int contactId;
	@TLVAttribute(tag=10000014,description="1：成功，2失败，3：传递的action不合法")
	private byte code;
	@TLVAttribute(tag=10000015,description="1:新增2:删除 3:修改")
	private byte action;
	public int getSequenceId() {
		return sequenceId;
	}
	public int getContactId() {
		return contactId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public byte getCode() {
		return code;
	}
	public byte getAction() {
		return action;
	}
	public void setCode(byte code) {
		this.code = code;
	}
	public void setAction(byte action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
