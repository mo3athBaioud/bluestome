package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

import java.util.ArrayList;

/**
 * 简单用户信息列表对象
 * @ClassName: SimpleUserInfoItem
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-23 下午08:31:23
 */
public class SimpleUserInfoItem {
    @TLVAttribute(tag=10000001,description="联系人ID")
    private int contactId;
    
    @TLVAttribute(tag=20000013,description="联系人信息列表")
    private ArrayList<SimpleUserInfo> list = new ArrayList<SimpleUserInfo>();

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
    public ArrayList<SimpleUserInfo> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<SimpleUserInfo> list) {
        this.list = list;
    }
    

}
