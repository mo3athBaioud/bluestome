
package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetChatResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-4-5 下午04:10:44
 */
public class NetChatResponse extends NetResponse {

    // 用户在线状态[普通消息]
    private boolean userOnline = true;

    // 用户是否离开[快聊会话]
    private boolean fastChatLeave = false;

    /**
     * @return the userOnline
     */
    public boolean isUserOnline() {
        return userOnline;
    }

    /**
     * @param userOnline the userOnline to set
     */
    public void setUserOnline(boolean userOnline) {
        this.userOnline = userOnline;
    }

    /**
     * @return the fastChatLeave
     */
    public boolean isFastChatLeave() {
        return fastChatLeave;
    }

    /**
     * @param fastChatLeave the fastChatLeave to set
     */
    public void setFastChatLeave(boolean fastChatLeave) {
        this.fastChatLeave = fastChatLeave;
    }

}
