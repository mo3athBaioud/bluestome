package android.skymobi.messenger.net.client.bean;

/**
 *  计算推荐好友响应
 * @ClassName: NetCalcFriendsResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-5-3 下午03:27:10
 */
public class NetCalcFriendsResponse extends NetResponse {

    //目标skyid
    private int destSkyid;
    
    //第一次打招呼显示的提示语
    private String talkReason;

    private boolean auth = true;
    /**
     * @return the destSkyid
     */
    public int getDestSkyid() {
        return destSkyid;
    }

    /**
     * @param destSkyid the destSkyid to set
     */
    public void setDestSkyid(int destSkyid) {
        this.destSkyid = destSkyid;
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

    /**
     * @return the auth
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }
    
}
