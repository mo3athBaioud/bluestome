
package android.skymobi.messenger.net.client;

/**
 * 网络操作业务接口
 * @ClassName: INetBiz
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-28 下午01:37:25
 */
public interface INetBiz {
    
    /**
     * 开启连接
     */
    void connect();
    
    /**
     * 重连接口
     */
    void reconnect();
    
    /**
     * 关闭连接
     */
    void close();
    
    /**
     * 是否联网
     * @return
     */
    boolean isConnect();
}
