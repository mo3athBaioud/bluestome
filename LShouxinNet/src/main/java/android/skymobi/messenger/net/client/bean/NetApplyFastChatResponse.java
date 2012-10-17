
package android.skymobi.messenger.net.client.bean;

/**
 * 申请快聊响应
 * 
 * @ClassName: NetApplyFastChatResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-10-11 上午09:51:53
 */
public class NetApplyFastChatResponse extends NetResponse {

    // 是否新会话
    private boolean isNew;

    // 目标SKYID
    private int destSkyid;

    // 等待时间
    private int createQueueWaitTime;

    /**
     * @return the isNew
     */
    public boolean isNew() {
        return isNew;
    }

    /**
     * @param isNew the isNew to set
     */
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

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
     * @return the createQueueWaitTime
     */
    public int getCreateQueueWaitTime() {
        return createQueueWaitTime;
    }

    /**
     * @param createQueueWaitTime the createQueueWaitTime to set
     */
    public void setCreateQueueWaitTime(int createQueueWaitTime) {
        this.createQueueWaitTime = createQueueWaitTime;
    }

}
