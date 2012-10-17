package android.skymobi.messenger.net.beans.commons;


import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 手信复合类型 
 * @author Bluestome.Zhang
 *
 */
public class Phone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TLVAttribute(tag=10000016,description="新增的时候不用传,")
	private Integer buddyId;
	@TLVAttribute(tag=10000004)
	private Byte index;
	@TLVAttribute(tag=10000005)
	private String phone;
	@TLVAttribute(tag=11010077)
	private String nickname;
	public Integer getBuddyId() {
		return buddyId;
	}
	public String getPhone() {
		return phone;
	}
	public void setBuddyId(Integer buddyId) {
		this.buddyId = buddyId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Byte getIndex() {
		return index;
	}
	public void setIndex(Byte index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
