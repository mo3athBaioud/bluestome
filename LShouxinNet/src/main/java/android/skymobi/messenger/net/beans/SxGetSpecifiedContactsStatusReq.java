package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 获取指定联系人状态
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA827)
public class SxGetSpecifiedContactsStatusReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
	@TLVAttribute(tag=10000053)
	private String contactSkyids;
	
	@TLVAttribute(tag=10000054)
	private String contactPhones;
	
    @TLVAttribute(tag=10000001)
	private int contactId;

	public String getContactSkyids() {
		return contactSkyids;
	}

	public void setContactSkyids(String contactSkyids) {
		this.contactSkyids = contactSkyids;
	}

	public String getContactPhones() {
		return contactPhones;
	}

	public void setContactPhones(String contactPhones) {
		this.contactPhones = contactPhones;
	}

    /**
     * @return the sourceId
     */
    public short getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(short sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
	
}
