package android.skymobi.messenger.net.client.bean;

/**
 * 添加黑名单响应对象
 * @ClassName: NetAddBlackResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午04:57:23
 */
public class NetBlackResponse extends NetResponse {

    private long updateTime;

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
    
}
