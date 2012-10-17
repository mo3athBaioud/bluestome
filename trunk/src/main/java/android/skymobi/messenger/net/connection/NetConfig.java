
package android.skymobi.messenger.net.connection;

import android.skymobi.messenger.net.client.NetListener;
import android.skymobi.messenger.net.notify.IServerBizNotify;
import android.skymobi.messenger.net.notify.IServerNotify;
import android.skymobi.messenger.net.notify.impl.ServerBizNotify;
import android.skymobi.messenger.net.notify.impl.ServerNotify;

import com.skymobi.android.TerminalInfo;

/**
 * 网络连接配置类
 * 
 * @author Bluestome.Zhang
 */
public class NetConfig {

    // 服务器IP 公司内网
    private String ip = "messenger.skymobiapp.com";

    // 服务器端口 公司内网端口
    private int port = 10122;

    // 连接超时时间
    private long conTimeout = 20 * 1000l;

    // 心跳间隔时间
    private long heartBeatTime = 60 * 1000l;

    //终端属性对象
    private TerminalInfo terminal = new TerminalInfo();
    
    // 请求超时时间
    private Long requestTimeout = 30*1000L;

    //服务端通知接口
    private IServerNotify serverNotify = new ServerNotify(new NetListener());

    //服务端业务通知接口
    private IServerBizNotify serverBizNotify = new ServerBizNotify();
    
    //是否需要加密处理
    private boolean encryptProtocol;

    public NetConfig() {
    }

    public NetConfig(String ip, int port, long conTimeout, long heartBeatTime,
            TerminalInfo terminal, IServerNotify serverNotify,boolean encryptProtocol) {
        this.ip = ip;
        this.port = port;
        this.conTimeout = conTimeout;
        this.heartBeatTime = heartBeatTime;
        this.terminal = terminal;
        this.serverNotify = serverNotify;
        this.encryptProtocol = encryptProtocol;
    }

    public final String getIp() {
        return ip;
    }

    public final void setIp(String ip) {
        this.ip = ip;
    }

    public final int getPort() {
        return port;
    }

    public final void setPort(int port) {
        this.port = port;
    }

    public final long getConTimeout() {
        return conTimeout;
    }

    public final void setConTimeout(long conTimeout) {
        this.conTimeout = conTimeout;
    }

    public final long getHeartBeatTime() {
        return heartBeatTime;
    }

    public final void setHeartBeatTime(long heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }

    public final TerminalInfo getTerminal() {
        return terminal;
    }

    public final void setTerminal(TerminalInfo terminal) {
        this.terminal = terminal;
    }

    public IServerNotify getServerNotify() {
        return serverNotify;
    }

    public void setServerNotify(IServerNotify serverNotify) {
        this.serverNotify = serverNotify;
    }

    public IServerBizNotify getServerBizNotify() {
        return serverBizNotify;
    }

    public void setServerBizNotify(IServerBizNotify serverBizNotify) {
        this.serverBizNotify = serverBizNotify;
    }

    public Long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public boolean isEncryptProtocol() {
        return encryptProtocol;
    }

    public void setEncryptProtocol(boolean encryptProtocol) {
        this.encryptProtocol = encryptProtocol;
    }

}
