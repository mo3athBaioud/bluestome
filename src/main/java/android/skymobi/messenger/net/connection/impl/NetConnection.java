
package android.skymobi.messenger.net.connection.impl;

import android.skymobi.messenger.net.connection.IConnection;
import android.skymobi.messenger.net.connection.NetConfig;
import android.skymobi.messenger.net.constants.Constants;
import android.skymobi.messenger.net.notify.IClientNotify;
import android.skymobi.messenger.net.notify.IServerBizNotify;
import android.skymobi.messenger.net.notify.IServerNotify;
import android.skymobi.messenger.net.receiver.NetReceiver;

import com.skymobi.android.TerminalInfo;
import com.skymobi.android.bean.esb.util.MetainfoUtils;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.codec.AccessDecoder;
import com.skymobi.android.codec.AccessEncoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.AccessEndpoint;
import com.skymobi.android.transport.DefaultEndpointFactory;
import com.skymobi.android.transport.INetStateNotify;
import com.skymobi.android.transport.TCPConnector;
import com.skymobi.android.transport.codec.DefaultMinaCodecFactory;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 网络连接类
 * @ClassName: NetConnection
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午07:56:52
 */
public class NetConnection implements IConnection {
    
    private static final Logger logger = LoggerFactory.getLogger(NetConnection.class);

    // 单例实例
    private static NetConnection instance = null;

    // 网络配置对象
    private NetConfig config = null;

    // 客户端通知接口
    private IClientNotify clientNotify = null;

    // 网络初始化类
    private ConnectionInit connectionInit = null;

    /**
     * 私有构造函数
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param config
     * @param clientNotify
     */
    private NetConnection(NetConfig config, IClientNotify clientNotify) {
        this.config = config;
        this.clientNotify = clientNotify;
        if (null != clientNotify) {
            init(config);
        } else {
            init(config, clientNotify);
        }
    }

    /**
     * 获取网络连接对象实例,单例模式
     * @param config
     * @return
     */
    public synchronized static NetConnection getInstance(NetConfig config, IClientNotify clientNotify) {
        if (null == instance) {
            instance = new NetConnection(config, clientNotify);
        }
        return instance;
    }

    /**
     * 连接类初始化
     * @param config　网络配置类
     */
    public boolean init(NetConfig config) {
        // TODO Auto-generated method stub
        this.connectionInit = ConnectionInit.getInstance(this, config.getIp(), config.getPort(),
                config.getConTimeout(), config.getHeartBeatTime(), config.getTerminal(),
                config.getServerNotify(), config.getServerBizNotify(),config.isEncryptProtocol());
        return false;
    }

    /**
     * 连接类初始化
     * @param config　网络配置接口
     * @param clientNotify　客户端通知接口
     */
    public boolean init(NetConfig config, IClientNotify clientNotify) {
        // TODO Auto-generated method stub
        this.connectionInit = ConnectionInit.getInstance(this, config.getIp(), config.getPort(),
                config.getConTimeout(), config.getHeartBeatTime(), config.getTerminal(),
                config.getServerNotify(), config.getServerBizNotify(),config.isEncryptProtocol());
        return false;
    }

    /**
     * 连接到服务器
     * @param isReconnect
     * true: 重连
     * false: 非重连
     */
    public void connect() {
        if (null != connectionInit) {
            logger.debug("\t>>>>> init NetConnection again");
            init(config);
        }
        logger.debug(" connect connection status:"+connectionInit.isConnect());
        connectionInit.start(false);

    }

    /**
     * 重连服务器
     */
    public void reconnect() {
        if (null != connectionInit){
            logger.debug("\t>>>>> init NetConnection again");
            init(config);
        }
        logger.debug(" reconnection connection status:"+connectionInit.isConnect());
        connectionInit.start(true);
    }

    /**
     * 关闭连接
     */
    public void close() {
        logger.debug(" close ");
        if(null != connectionInit){
            connectionInit.stop();
        }

    }

    /**
     * 发送对象
     */
    public int send(Object value) {
        int status = connectionInit.send(value);
        return status;
    }

    /**
     * 获取当前网络连接状态
     */
    public int getStatus() {
        return 0;
    }

    /**
     * 判断网络是否连接上
     */
    public boolean isConnect() {
        if (null != connectionInit) {
            return connectionInit.isConnect();
        }
        return false;
    }

    public final NetConfig getConfig() {
        return config;
    }

    public final void setConfig(NetConfig config) {
        this.config = config;
    }

    public final IClientNotify getClientNotify() {
        return clientNotify;
    }

    public final void setClientNotify(IClientNotify clientNotify) {
        this.clientNotify = clientNotify;
    }

}

/**
 * 连接初始化类
 * 
 * @ClassName: ConnectionInit
 * @Description: 1.网络连接管理
 * @author Bluestome.Zhang
 * @date 2012-2-6 上午11:13:07
 */
final class ConnectionInit {
    
    private static Logger logger = LoggerFactory.getLogger(ConnectionInit.class);

    // 实例化主要依赖类
    AccessEndpoint accessEndPoint = null;
    DefaultEndpointFactory defaultEndpointFactory = null;
    TCPConnector connector = null;
    DefaultMinaCodecFactory codecFactory = null;

    // 编解码对象
    AccessEncoder encoder = null;
    AccessDecoder decoder = null;

    // 接收对象
    NetReceiver receiver = null;

    // 请求列表
    static ArrayList send_tmp_list = new ArrayList();

    // 多线程池
    ScheduledExecutorService exec =
            Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "Terminal-connector-thread");
                }
            });

    // 状态
    int status = -1;

    // 服务器IP
    String ip;
    // 服务器端口
    int port;
    // 连接断开的重置间隔
    long retryTime = 1000 * 10l;

    // 心跳时长
    long accessCheckTime = 30 * 1000l;
    // 终端对象
    TerminalInfo terminal = new TerminalInfo();
    // 服务端通知接口
    IServerNotify serverNotify = null;

    IServerBizNotify serverBizNotify = null;

    IConnection connection = null;
    
    boolean encryptProtocol;
    
    public static ConnectionInit connectionInit = null;

    private ConnectionInit(IConnection connection, String ip, int port, long conTimeout,
            long heartBeatTime, TerminalInfo terminal,
            IServerNotify serverNotify, IServerBizNotify serverBizNotify,boolean encryptProtocol) {
        this.connection = connection;
        this.serverBizNotify = serverBizNotify;
        this.ip = ip;
        this.port = port;
        this.retryTime = conTimeout;
        this.accessCheckTime = heartBeatTime;
        this.terminal = terminal;
        this.serverNotify = serverNotify;
        this.encryptProtocol = encryptProtocol;
        init();
    }
    
    public static ConnectionInit getInstance(IConnection connection, String ip, int port, long conTimeout,
            long heartBeatTime, TerminalInfo terminal,
            IServerNotify serverNotify, IServerBizNotify serverBizNotify,boolean encryptProtocol){
        if(connectionInit == null){
            connectionInit = new ConnectionInit(connection,ip,port,conTimeout,heartBeatTime,terminal,serverNotify,serverBizNotify,encryptProtocol);
        }
        return connectionInit;
    }

    /**
     * 初始化函数
     * 
     * @return
     */
    private void init() {
        try {
            if (null == codecFactory) {
                codecFactory = new DefaultMinaCodecFactory();
            }
            if (null == accessEndPoint) {
                accessEndPoint = new AccessEndpoint();
            }
            if (null == defaultEndpointFactory) {
                defaultEndpointFactory = new DefaultEndpointFactory(true, 1000);
            }
            if (null == connector) {
                connector = new TCPConnector("Android-Client");
            }

            Int2TypeMetainfo esbTypeMetainfo = MetainfoUtils
                    .createEsbTypeMetainfoByClasses(Constants.xipTypeMetainfoSet);

            // 编解码类初始化
            encoder = new AccessEncoder(serverNotify);
            decoder = new AccessDecoder(serverNotify);

            // 设置接收数据类
            receiver = new NetReceiver(serverNotify, serverBizNotify);
            receiver.setEsbTypeMetainfo(esbTypeMetainfo);
            receiver.setEndPoint(accessEndPoint);
            //设置连接对象
            receiver.setTcpConnector(connector);

            // 设置编解码类
            codecFactory.setEncoder(encoder);
            codecFactory.setDecoder(decoder);
            
            accessEndPoint.setEncryptProtocol(encryptProtocol);
            //设置accessEndPoint的编解码
            accessEndPoint.setEncoder(encoder);
            accessEndPoint.setDecoder(decoder);
            // 设置终端信息
            accessEndPoint.setTerminal(terminal);
            // 设置心跳包发送的时间间隔
            accessEndPoint.setHeartBeatInterval(accessCheckTime);
            // 设置ACCESS响应超时时间
            accessEndPoint.setModuleId(Integer.valueOf("BEE4", 16));
            accessEndPoint.setReceiver(receiver);
            accessEndPoint.setNextReceiver(receiver);
            accessEndPoint.setTcpConnector(connector);
            // 端点工厂类
            defaultEndpointFactory.setEndPoint(accessEndPoint);
            // 设置连接类属性
            connector.setEndpointFactory(defaultEndpointFactory);
            connector.setDestIp(ip);
            connector.setDestPort(port);
            //设置访问端点
            connector.setAccessEndPoint(accessEndPoint);
            INetStateNotify notify = new INetStateNotify(){

                @Override
                public void netStateNotify(Integer obj) {
                    serverNotify.onNetStateNotify(obj);
                }
                
            };
            
            connector.setNetStateNotify(notify);

            // 设置编解码工厂
            if (null == connector.getCodecFactory()) {
                connector.setCodecFactory(codecFactory);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 开启服务
     * @param isReconnect 是否重连
     * true: 重连
     * false: 非重连
     */
    protected void start(boolean isReconnect) {
        if (null != connector) {
            connector.start(isReconnect);
        }
    }

    /**
     * 关闭服务
     */
    protected void stop() {
        if (null != connector && connector.isConnected()) {
            connector.stop();
        }
    }

    /**
     * 获取是否连接的状态
     * 
     * @return
     */
    protected boolean isConnect() {
        if (null != connector) {
            return connector.isConnected();
        }
        return false;
    }

    /**
     * 发送网络消息
     * 
     * @param obj
     * @return -1:不正常 200:正常
     */
    public int send(Object obj) {
        if (isConnect()) {
            // TODO 状态正常可以发送消息
            try {
                logger.debug(" >>>> send "+obj);
                defaultEndpointFactory.send(obj);
                return 200;
            } catch (RuntimeException e) {
                return -1;
            }
        }
        return -1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(long retryTime) {
        this.retryTime = retryTime;
    }

    public TerminalInfo getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalInfo terminal) {
        this.terminal = terminal;
    }

    public IServerNotify getServerNotify() {
        return serverNotify;
    }

    public void setServerNotify(IServerNotify serverNotify) {
        this.serverNotify = serverNotify;
    }

    public IConnection getConnection() {
        return connection;
    }

    public void setConnection(IConnection connection) {
        this.connection = connection;
    }

    public boolean isEncryptProtocol() {
        return encryptProtocol;
    }

    public void setEncryptProtocol(boolean encryptProtocol) {
        this.encryptProtocol = encryptProtocol;
    }

}
