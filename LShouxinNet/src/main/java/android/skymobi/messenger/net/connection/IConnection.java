
package android.skymobi.messenger.net.connection;

import android.skymobi.messenger.net.notify.IClientNotify;

/**
 * 网络连接接口
 * 
 * @author Bluestome.Zhang
 */
public interface IConnection {

    /**
     * 网络初始化
     */
    boolean init(NetConfig config);

    /**
     * 带客户端通知接口初始化方法
     * 
     * @param config 网络配置类
     * @param clientNotify 客户端通知类
     * @return
     */
    boolean init(NetConfig config, IClientNotify clientNotify);

    /**
     * 网络连接
     */
    void connect();

    /**
     * 网络重连
     */
    void reconnect();

    /**
     * 关闭网络
     */
    void close();

    /**
     * 发送消息
     * 
     * @param value 消息内容
     * @return
     */
    int send(Object value);

    /**
     * 获取网络状态
     * 
     * @return
     */
    int getStatus();

    /**
     * 网络是否连接
     * 
     * @return
     */
    boolean isConnect();
}
