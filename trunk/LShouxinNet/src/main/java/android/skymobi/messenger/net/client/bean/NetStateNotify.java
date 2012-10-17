package android.skymobi.messenger.net.client.bean;

/**
 * @ClassName: NetStateNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-23 下午01:35:46
 */
public class NetStateNotify extends NetNotify {

    public enum ConnectStatus{
        CONNECTED,UNCONNECTED,RECONNECTED
    };
    
    //网络连接状态属性
    private ConnectStatus state = ConnectStatus.CONNECTED;

    /**
     * @return the state
     */
    public ConnectStatus getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(ConnectStatus state) {
        this.state = state;
    }
    
    
}
