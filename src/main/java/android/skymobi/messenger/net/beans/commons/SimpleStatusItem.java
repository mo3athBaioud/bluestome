package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 联系人状态项
 * @ClassName: SimpleStatusItem
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午07:30:05
 */
public class SimpleStatusItem {

    @TLVAttribute(tag=10000001,description="联系人ID")
    private int contactId;
    
    @TLVAttribute(tag=20000011,description="联系人状态列表")
    private ArrayList<SimpleStatus> list = new ArrayList<SimpleStatus>();

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

    /**
     * @return the list
     */
    public ArrayList<SimpleStatus> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<SimpleStatus> list) {
        this.list = list;
    }
    
    
}
