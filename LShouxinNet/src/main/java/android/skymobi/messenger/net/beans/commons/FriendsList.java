package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 手信复合类型 好友列表
 * @author Bluestome.Zhang
 *
 */
public class FriendsList implements Serializable{
	private static final long serialVersionUID = 1L;
    @TLVAttribute(tag = 1005)
	private int skyId;
	@TLVAttribute(tag = 11010077)
	private String nickname;
	@TLVAttribute(tag = 10000011)
	private String imageHead;
	@TLVAttribute(tag = 10000012)
	private String recommendReason;
	@TLVAttribute(tag = 10000046)
	private byte contactType;
	@TLVAttribute(tag = 10000049)
	private String detailReason;
	@TLVAttribute(tag=14010020)
	private String usignature;
	@TLVAttribute(tag=10000060)
	private String talkReason;
	@TLVAttribute(tag =14010006, description="性别:0未设置,1男,2女,3保密")
	private String sex;
	public int getSkyId() {
		return skyId;
	}

	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}

	public String getImageHead() {
		return imageHead;
	}

	public void setImageHead(String imageHead) {
		this.imageHead = imageHead;
	}

	public String getRecommendReason() {
		return recommendReason;
	}

	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte getContactType() {
		return contactType;
	}

	public void setContactType(byte contactType) {
		this.contactType = contactType;
	}

	public String getDetailReason() {
		return detailReason;
	}

	public void setDetailReason(String detailReason) {
		this.detailReason = detailReason;
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

    /**
     * @return the talkReason
     */
    public String getTalkReason() {
        return talkReason;
    }

    /**
     * @param talkReason the talkReason to set
     */
    public void setTalkReason(String talkReason) {
        this.talkReason = talkReason;
    }

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
}
