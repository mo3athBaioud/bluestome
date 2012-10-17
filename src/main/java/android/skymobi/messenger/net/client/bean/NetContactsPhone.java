package android.skymobi.messenger.net.client.bean;


/**
 * @ClassName: NetPhone
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-7 下午01:01:28
 */
public class NetContactsPhone {
    
    private Integer buddyId;
    private Byte index;
    //不需要设置，主要是服务端在推荐用户时会下发该字段，用户操作自己的联系人
    //时不需要操作该字段。
    private String phone;
    private String nickname;
    /**
     * @return the buddyId
     */
    public Integer getBuddyId() {
        return buddyId;
    }
    /**
     * @param buddyId the buddyId to set
     */
    public void setBuddyId(Integer buddyId) {
        this.buddyId = buddyId;
    }
    /**
     * @return the index
     */
    public Byte getIndex() {
        return index;
    }
    /**
     * @param index the index to set
     */
    public void setIndex(Byte index) {
        this.index = index;
    }
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
}
