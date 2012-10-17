package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class ContactsStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int contactId;
	
	@TLVAttribute(tag = 1005)
	private int skyId;
	
	@TLVAttribute(tag = 10000005)
	private String phone;
	
	@TLVAttribute(tag = 10000020)
	private byte status;
	
	@TLVAttribute(tag = 10000021)
	private byte relation = -1;
	
	@TLVAttribute(tag = 10000011)
	private String imageHead;
	
	@TLVAttribute(tag = 10000052)
	private byte userType = 0;
	
	@TLVAttribute(tag = 11010077)
	private String nickname;
	
    @TLVAttribute(tag = 11010003)
	private String userName;

    @TLVAttribute(tag = 14010020)
    private String usignature;
    
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getSkyId() {
		return skyId;
	}

	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getRelation() {
		return relation;
	}

	public void setRelation(byte relation) {
		this.relation = relation;
	}
	
	@Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getImageHead() {
		return imageHead;
	}

	public void setImageHead(String imageHead) {
		this.imageHead = imageHead;
	}

	public byte getUserType() {
		return userType;
	}

	public void setUserType(byte userType) {
		this.userType = userType;
	}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the usignature
     */
    public String getUsignature() {
        return usignature;
    }

    /**
     * @param usignature the usignature to set
     */
    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }
	
    
}
