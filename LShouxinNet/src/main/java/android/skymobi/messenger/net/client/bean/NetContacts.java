package android.skymobi.messenger.net.client.bean;

import java.util.ArrayList;

/**
 * @ClassName: NetContacts
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-7 下午01:00:53
 */
public class NetContacts {
    
    /** 联系人ID **/
    private Integer contactId =0;
    
    /** 联系人所在手机数据库的ID **/
    private Integer sequenceId =0;
    
    /** 联系人名称 **/
    private String contactName;
    
    /** 联系人备注 **/
    private String memo;
    
    /** 联系人号码列表 **/
    private ArrayList<NetContactsPhone> phoneList = new ArrayList<NetContactsPhone>();
    
    /** 联系人所属组 **/
    private Byte group = 0;
    
    /** 操作方法 
     * 1 新增
     * 2 删除
     * 3 修改
     * **/
    private Byte action = 0;
    
    /** 联系人类型 
     * 0：手机导入
     * 1：运营账号
     * 2：推荐联系人
     * 3：手工添加
     **/
    private Byte contactType;
    
    /**
     * @return the contactId
     */
    public Integer getContactId() {
        return contactId;
    }
    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    /**
     * @return the sequenceId
     */
    public Integer getSequenceId() {
        return sequenceId;
    }
    /**
     * @param sequenceId the sequenceId to set
     */
    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }
    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }
    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    /**
     * @return the phoneList
     */
    public ArrayList<NetContactsPhone> getPhoneList() {
        return phoneList;
    }
    /**
     * @param phoneList the phoneList to set
     */
    public void setPhoneList(ArrayList<NetContactsPhone> phoneList) {
        this.phoneList = phoneList;
    }
    /**
     * @return the group
     */
    public Byte getGroup() {
        return group;
    }
    /**
     * @param group the group to set
     */
    public void setGroup(Byte group) {
        this.group = group;
    }
    /**
     * @return the action
     */
    public Byte getAction() {
        if(null == action)
            action = 0;
        return action;
    }
    /**
     * @param action the action to set
     */
    public void setAction(Byte action) {
        this.action = action;
    }
    /**
     * @return the contactType
     */
    public Byte getContactType() {
        return contactType;
    }
    /**
     * @param contactType the contactType to set
     */
    public void setContactType(Byte contactType) {
        this.contactType = contactType;
    }

    
}
