package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetAddFriendResponse
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 上午09:30:26
 */
public class NetAddFriendResponse extends NetResponse {

    /**
     * 更新时间
     */
    private long updateTime;
    
    /**
     * 联系人ID
     */
    private int contactId;
    
    /**
     * 联系人是否在你好友列表中
     * false: 被添加人不在你的好友列表中
     * true: 被添加人在你的好友列表中
     */
    private boolean isFriendExists = false;
    
    /**
     * @return the updateTime
     */
    public long getUpdateTime() {
        return updateTime;
    }
    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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
    /**
     * @return the isFriendExists
     */
    public boolean isFriendExists() {
        return isFriendExists;
    }
    /**
     * @param isFriendExists the isFriendExists to set
     */
    public void setFriendExists(boolean isFriendExists) {
        this.isFriendExists = isFriendExists;
    }
    
}
