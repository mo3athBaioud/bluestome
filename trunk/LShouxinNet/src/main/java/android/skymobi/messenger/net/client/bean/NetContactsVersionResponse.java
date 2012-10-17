package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetContactsVersionResponse
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-15 下午02:59:56
 */
public class NetContactsVersionResponse extends NetResponse {

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
