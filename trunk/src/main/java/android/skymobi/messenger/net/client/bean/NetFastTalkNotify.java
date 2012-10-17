
package android.skymobi.messenger.net.client.bean;

/**
 * 快聊通知接口
 * 
 * @ClassName: NetFastTalkNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午10:49:31
 */
public class NetFastTalkNotify extends NetNotify {

    // 目标用户的SKYID
    private int destSkyid;

    // 通知是否成功
    private boolean success;

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
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
