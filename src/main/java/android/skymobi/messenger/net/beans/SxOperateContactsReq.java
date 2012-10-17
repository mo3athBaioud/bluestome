package android.skymobi.messenger.net.beans;

import android.skymobi.messenger.net.beans.commons.Contacts;
import android.skymobi.messenger.net.beans.header.ShouxinReqHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 批量操作联系人 请求(增加操作时，是上传通讯录操作)
 * @author Bluestome.Zhang
 *
 */
@EsbSignal(messageCode=0xA801)
public class SxOperateContactsReq extends ShouxinReqHeader {

    @TLVAttribute(tag = 1002,description="默认设置18003，服务端用于区分应用")
    private short sourceId = 18003;
    
	@TLVAttribute(tag=20000001)
	private ArrayList<Contacts> contactsList = new ArrayList<Contacts>();

	public ArrayList<Contacts> getContactsList() {
		return contactsList;
	}

	public void setContactsList(ArrayList<Contacts> contactsList) {
		this.contactsList = contactsList;
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
	
	
}
