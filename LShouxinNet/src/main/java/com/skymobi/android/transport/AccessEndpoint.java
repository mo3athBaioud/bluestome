
package com.skymobi.android.transport;

import com.skymobi.android.TerminalInfo;
import com.skymobi.android.bean.esb.core.EsbTerminal2AccessSignal;
import com.skymobi.android.codec.AccessDecoder;
import com.skymobi.android.codec.AccessEncoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.constants.NetConstants;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;
import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDReq;
import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDResp;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessWithoutProtocolReq;
import com.skymobi.android.transport.protocol.esb.signal.RetryToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.RetryToAccessResp;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AccessEndpoint
 * @Description: 注意:
 *               只要有session.close(true);的方法，最终会调用IoHandler中的sessionClose()方法，
 *               并且会将重连标识置为true，也就是默认会走重连，除非重连2次失败.
 * @author Bluestome.Zhang
 * @date 2012-5-31 下午08:47:30
 */
public class AccessEndpoint extends AbstractEndpoint implements
        MutableEndpoint {

    private static final Logger logger =
            LoggerFactory.getLogger(AccessEndpoint.class);

    private ScheduledExecutorService exec =
            Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "AccessEndpoint-Sender-thread");
                }
            }
                    );

    private Receiver receiver;

    private AccessDecoder decoder;

    private AccessEncoder encoder;

    private TerminalInfo terminal = new TerminalInfo();

    private int moduleId;

    // 2934 手信APPID
    private int myAppId = 2934;

    // 网络连接状态
    private static final int CONN_STATE_OFFLINE = 0;
    private static final int CONN_STATE_REGISTERING = 1;
    private static final int CONN_STATE_ONLINE = 2;

    // 心跳包状态
    private static final int HB_STATE_IDLE = 0;
    private static final int HB_STATE_SENDED = 1;
    private static final int HB_STATE_RECVD = 2;

    // 网络连接和心跳状态
    private int connectionState = CONN_STATE_OFFLINE;
    private int headrtbeatState = HB_STATE_IDLE;

    private long checkAccessDelay = 5 * 1000;

    // 心跳间隔时间
    private long heartBeatInterval = 30 * 1000;

    // 这个时间特别注意,因为为每次从请求队列获取对象的时间
    private long fetchSignalTimeout = 500L;

    // 最后一次注册到ACCESS和和发送心跳的时间
    private long lastRegisterToAccessTime = 0;
    private long lastHB2AccessTime = 0;
    private long lastRetry2AccessTime = 0L;

    // 最后一次数据接收时间
    private long lastDataRecvTime = 0L;
    // 最后一次接收到重连的时间
    private long lastRetryRecvTime = 0L;

    // ACCESS响应超时时间
    private long accessRespTimeout = 60 * 1000;

    // 重连的响应超时时间
    private final long retry2AccessTimeout = 20 * 1000;

    // ACCESS心跳超时时间
    private long accessHBTimeout = 20 * 1000;

    // 总的数据超时时间
    private long totalDataRecvTimeout = 150 * 1000;

    // 重连总超时时间
    private final long totalRetryDataRecvTimeout = 120 * 1000 + totalDataRecvTimeout;

    // 重发心跳次数
    private int reHeartbeatCount = 0;

    // 重连次数
    private int retry2accessCount = 0;

    // 序列号必须以1开始
    private short seq = 1;
    private short ack = 0;

    // 服务端返回的ACCESS索引值
    private static int accessIndex = 0;

    // 服务端返回的认证码
    private static int authCode = 0;

    // 重连请求是否发送
    private boolean isRetry2AccessSend = false;
    // 服务端加密密钥
    private int encryptKey;
    // 是否加密
    private boolean encryptProtocol;

    // TCP连接对象
    private TCPConnector tcpConnector;

    // 服务端下发的终端的UID
    private String uid = null;

    /**
     * 发送队列中的请求记录
     */
    private void doSendPending() {
        exec.submit(new Runnable() {
            @Override
            public void run() {
                sendPending();
            }
        });
    }

    /**
     * 是否连接
     * 
     * @return boolean
     */
    private boolean isConnectionValid() {
        return ((null != session) && (connectionState == CONN_STATE_ONLINE));
    }

    /**
     * 发送方法的执行方法
     */
    private void sendPending() {
        if (isConnectionValid()) {
            try {
                // BlockingQueue<Object>
                // 在对象在fetchSignalTimeout最大时间内没有数据，其效果类似于Thread.sleep(fetchSignalTimeout);
                Object bean = pendings.poll(fetchSignalTimeout, TimeUnit.MILLISECONDS);
                if (null != bean) {
                    if (bean instanceof EsbTerminal2AccessSignal) {
                        int flags = ((EsbTerminal2AccessSignal) bean).getFlags();
                        if (flags != 0x0 && flags != 0x420) { // 是否为需要带序列的标记
                            // 对seq的最大值进行判断
                            if (seq > 65535) {
                                seq = 1;
                            }
                            ((EsbTerminal2AccessSignal) bean).setSeqNum(++seq);
                            ((EsbTerminal2AccessSignal) bean).setAck(ack);
                        }
                    }
                    if (encryptProtocol && bean instanceof AbstractEsbT2ASignal) {
                        ((AbstractEsbT2ASignal) bean).setEncryptKey(encryptKey);
                    }
                    logger.debug("\t>>>>> SEQ:" + ((EsbTerminal2AccessSignal) bean).getSeqNum());
                    logger.debug("\t>>>>> ACK:" + ((EsbTerminal2AccessSignal) bean).getAck());
                    session.write(bean);
                    lastHB2AccessTime = System.currentTimeMillis();
                }
            } catch (InterruptedException e) {
                logger.error("\t>>>>>> 中断发送线程 ");
            } finally {
                doSendPending();
            }
        } else {
            // 未连接到服务器
            exec.schedule(new Runnable() {
                @Override
                public void run() {
                    sendPending();
                }
            }, fetchSignalTimeout, TimeUnit.MILLISECONDS);
        }
    }

    // 发送心跳请求是否超时
    private boolean isHeartbeatToAccessTimeout() {
        long duration = System.currentTimeMillis() - lastHB2AccessTime;
        logger.debug("\t>>>>> 心跳超时时间:" + (duration / 1000));
        return (duration >= accessHBTimeout);
    }

    // 心跳间隔
    private boolean isHeartbeatToEsbExceedInterval() {
        long duration = System.currentTimeMillis() - lastHB2AccessTime;
        logger.debug("\t>>>>> 心跳间隔时间:" + (duration / 1000));
        return (duration >= heartBeatInterval);
    }

    /**
     * 总的数据接收超时
     * 
     * @return
     */
    private boolean isTotalDataRecvTimeout() {
        long duration = System.currentTimeMillis() - lastDataRecvTime;
        logger.debug("\t>>>>> 接收数据时间间隔:" + (duration / 1000));
        return (duration >= totalDataRecvTimeout);
    }

    // 发送心跳
    private void sendHeartbeatToAccess() {
        lastHB2AccessTime = System.currentTimeMillis();
        HeartbeatToAccessReq req = new HeartbeatToAccessReq();
        req.setDstESBAddr((short) 0x9800);
        req.setFlags((short) 0x0120);
        send(req);
    }

    /**
     * 处理心跳
     */
    private void doProcessHeartbeat() {
        switch (headrtbeatState) {
            case HB_STATE_IDLE:
                logger.info(" > HB_STATE_IDLE in");
                // 判断是否需要发送心跳包 120s
                if (isHeartbeatToEsbExceedInterval()) {
                    sendHeartbeatToAccess();
                    headrtbeatState = HB_STATE_SENDED;
                }
                break;
            case HB_STATE_SENDED:
                logger.info(" > HB_STATE_SENDED in");
                // 判断发送的状态是否超时,现网的心跳时间最好为60s左右，心跳失败次数保持2次。
                if (isHeartbeatToAccessTimeout()) {
                    if (isTotalDataRecvTimeout()) {
                        logger.debug("\t>>>>>> 接收数据超时,断开连接,开始重连!");
                        // 总的数据接收时间超时，则关闭连接
                        session.close(true);
                    } else {
                        logger.debug("\t>>>>>> 发送心跳" + (reHeartbeatCount++));
                        if (seq > 1) {
                            seq = (ack);
                        }
                        // 总的数据时间未超时,继续发送心跳
                        sendHeartbeatToAccess();
                    }
                }
                break;
            case HB_STATE_RECVD:
                logger.info(" > HB_STATE_RECVD in");
                reHeartbeatCount = 0;
                retry2accessCount = 0;
                headrtbeatState = HB_STATE_IDLE;
                break;
            default:
                reHeartbeatCount = 0;
                retry2accessCount = 0;
                headrtbeatState = HB_STATE_IDLE;
                setStatus(-100);
                break;
        }
    }

    /**
     * 判断是否注册超时
     * 
     * @return
     */
    private boolean isRegister2AccessTimeout() {
        long duration = System.currentTimeMillis() - lastRegisterToAccessTime;
        return (duration >= accessRespTimeout);
    }

    /**
     * 判断是否注册超时
     * 
     * @return
     */
    private boolean isRetry2AccessTimeout() {
        long duration = System.currentTimeMillis() - lastRetry2AccessTime;
        return (duration >= retry2AccessTimeout);
    }

    /**
     * 最后一次重连时间是否超过允许重连的最大时间
     * 
     * @return
     */
    private boolean isTotalRetryTimeout() {
        long duration = System.currentTimeMillis() - lastRetryRecvTime;
        logger.debug("\t>>>>>> 重连时间间隔:" + (duration / 1000) + "s");
        return (duration >= totalRetryDataRecvTimeout);
    }

    /**
     * 向ACCESS发送注册请求，发送终端的UA信息
     */
    private void sendRegisterToAccess() {
        RegisterToAccessReq req = new RegisterToAccessReq();
        if (null != terminal) {
            req.setImei(terminal.getImei());
            req.setImsi(terminal.getImsi());
            req.setHsman(terminal.getHsman());
            req.setHstype(terminal.getHstype());
            req.setScreenHeight((short) terminal.getHeight());
            req.setScreenWidth((short) terminal.getWidth());
            req.setPlat((byte) 4);
            req.setAppId(terminal.getAppid());
            req.setReserved3((short) terminal.getAppver());
            req.setEnter(terminal.getEnter());
            req.setVmv(terminal.getVersion());
        }
        req.setAccessModuleId((short) 0x9800);
        // 判断是否需要加密，如果需要加密，则使用0x0520,否则使用0x120 标识
        if (encryptProtocol) {
            req.setFlags((short) 0x0520);
        } else {
            req.setFlags((short) 0x0120);
        }
        req.setSrcModuleId((short) this.moduleId);
        req.setSeqNum(1);
        req.setAck((short) 0);

        session.write(req);
        lastRegisterToAccessTime = System.currentTimeMillis();
    }

    /**
     * 向ACCESS发送不带握手协议的注册请求，发送终端的UA信息,该方法主要是用于在重连失败时，复用重连通道。
     */
    private void sendRegisterWithoutProtocolToAccess() {
        lastRegisterToAccessTime = System.currentTimeMillis();
        RegisterToAccessWithoutProtocolReq req = new RegisterToAccessWithoutProtocolReq();
        if (null != terminal) {
            req.setImei(terminal.getImei());
            req.setImsi(terminal.getImsi());
            req.setHsman(terminal.getHsman());
            req.setHstype(terminal.getHstype());
            req.setScreenHeight((short) terminal.getHeight());
            req.setScreenWidth((short) terminal.getWidth());
            req.setPlat((byte) 4);
            req.setAppId(terminal.getAppid());
            req.setReserved3((short) terminal.getAppver());
            req.setEnter(terminal.getEnter());
            req.setVmv(terminal.getVersion());
        }
        req.setAccessModuleId((short) 0x9800);
        // 判断是否需要加密，如果需要加密，则使用0x0520,否则使用0x120 标识
        if (encryptProtocol) {
            req.setFlags((short) 0x0520);
        } else {
            req.setFlags((short) 0x0120);
        }
        req.setSrcModuleId((short) this.moduleId);
        req.setSeqNum(1);
        req.setAck((short) 0);
        session.write(req);
        lastRegisterToAccessTime = System.currentTimeMillis();
    }

    /**
     * 发送重连信令到ACCESS
     */
    private void sendReconnectSignal2Access() {
        if (accessIndex != 0 && authCode != 0) {
            RetryToAccessReq req = new RetryToAccessReq();
            req.setAccessIndex(accessIndex);
            req.setAuthCode(authCode);
            req.setAccessModuleId((short) 0x9800);
            req.setSrcModuleId((short) this.moduleId);
            req.setFlags((short) 0x0);
            session.write(req);
            lastRetry2AccessTime = System.currentTimeMillis();
        } else {
            logger.debug("\t>>>>>> 重连的accessIndex和authCode不满足条件，执行UA注册");
            // 未获取access返回的accessIndex和authCode，则表示之前未连接成功，则不需要重连，关闭当前连接，新开连接
            sendRegisterToAccess();
        }
    }

    /**
     * 检查连接
     */
    private void doCheckAccess() {
        switch (connectionState) {
            case CONN_STATE_OFFLINE:
                if (seq > 1) {
                    seq = 1;
                    ack = 0;
                }
                sendRegisterToAccess();
                connectionState = CONN_STATE_REGISTERING;
                status = connectionState;
                break;
            case CONN_STATE_REGISTERING:
                // 注册到ACCESS是否超时
                if (isRegister2AccessTimeout()) {
                    logger.debug("\t>>>>>> Register to Access timeout!");
                    // 如果注册次数到达重连上限，则需要休眠一段时间再重连
                    isRetry2AccessSend = false;
                    // TODO 几次以后断开连接,需要指定条件
                    session.close(true);
                }
                break;
            case CONN_STATE_ONLINE:
                // 检查心跳
                isRetry2AccessSend = true;
                doProcessHeartbeat();
                break;
            default:
                setStatus(-100);
                break;
        }
    }

    @Override
    public void start() {
        resetData();
        if (exec.isShutdown()) {
            exec =
                    Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "AccessEndpoint-Sender-thread");
                        }
                    }
                            );
        }
        checkAccess(checkAccessDelay);
        doSendPending();
    }

    // 检查和服务器的连接
    private void checkAccess(long checkTimes) {
        exec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                boolean isReconnect = isRetry2AccessSend;
                logger.debug("\t>>>>>> 重连状态:" + (isReconnect ? "需要" : "不需要"));
                if (connectionState == CONN_STATE_ONLINE) {
                    doCheckAccess();
                } else {
                    if (isReconnect) {
                        // 单个重连超时
                        if (isRetry2AccessTimeout()) {
                            // 重连是否超过允许重连的最大时间
                            if (!isTotalRetryTimeout() && retry2accessCount < 3) {
                                logger.debug("\t>>>>>> 重连未超过最大重连时间["
                                        + (totalRetryDataRecvTimeout / 1000) + "s] 或者 重连次数["
                                        + retry2accessCount + "]小于 3,继续重连,当前为第"
                                        + (retry2accessCount++) + "次重连");
                                sendReconnectSignal2Access();
                            } else {
                                logger.debug("\t>>>>>> 重连超过最大重连时间["
                                        + (totalRetryDataRecvTimeout / 1000) + "s] 执行UA注册\t连接状态:"
                                        + connectionState);
                                isRetry2AccessSend = false;
                                session.close(true);
                            }
                        }
                    } else {
                        doCheckAccess();
                    }
                }
            }
        }, 100, checkTimes, TimeUnit.MILLISECONDS);
    }

    /**
     * 重置相关属性
     */
    private void resetData() {
        logger.debug("\t>>>>>> 重置数据 ,是否会进行重连:" + (isRetry2AccessSend ? "会" : "不会"));
        connectionState = CONN_STATE_OFFLINE;
        headrtbeatState = HB_STATE_IDLE;
        if (!isRetry2AccessSend) { // 如果不是重连，则需要将seq，ack等参数置为原始值.
            seq = 1;
            ack = 0;
            accessIndex = 0;
            authCode = 0;
            // 重置密钥相关数据
            encryptKey = 0;
            if (null != encoder) {
                encoder.setEncryptKey(encryptKey);
            }
            if (null != decoder) {
                decoder.setEncryptKey(encryptKey);
            }
            retry2accessCount = 0;
        }
        lastRegisterToAccessTime = 0L;
        lastDataRecvTime = 0L;
        lastRetry2AccessTime = 0L;
        reHeartbeatCount = 0;
    }

    @Override
    public void stop() {
        super.stop();
        resetData();
        exec.shutdownNow();
    }

    /**
     * 响应接收方法
     */
    @Override
    public void messageReceived(IoSession session, Object msg) {
        // 目前该方法接收的msg是从解码器中返回的内容，而解码器目前只产生byte[]数据
        if (receiver != null) {
            receiver.messageReceived(session, msg);
            // 处理完接收的消息后，调用[Thread.yield()]线程切换
            Thread.yield();
        } else {
            if (msg instanceof byte[]) {
                super.messageReceived(session, msg);
            } else {
                logger.warn(" unknow msg type ");
            }
        }
    }

    /**
     * 注册到ACCESS成功
     */
    @Override
    public void online(RegisterToAccessResp resp) {
        if (NetConstants.CONN_SUCCESS == resp.getResult()) {
            lastRegisterToAccessTime = System.currentTimeMillis();
            // 如果注册成功，则修改系统状态为上线状态,同时发送一条心跳包
            connectionState = CONN_STATE_ONLINE;
            status = connectionState;
            accessIndex = resp.getAccessIndex();
            authCode = resp.getAuthCode();
            logger.debug("\t>>>>>> accessIndex:" + accessIndex);
            logger.debug("\t>>>>>> authCode:" + authCode);
            // 获取服务端的返回的加密密钥
            if (resp.getEncryptKey() != 0) {
                logger.debug("received encryptKey:" + resp.getEncryptKey());
                this.encryptKey = resp.getEncryptKey();
                encoder.setEncryptKey(encryptKey);
                decoder.setEncryptKey(encryptKey);
            }
            // 检查是否需要向服务端请求
            if (uid == null) {
                logger.debug("\t>>>>>> GenerateUniqueIDReq");
                GenerateUniqueIDReq request = new GenerateUniqueIDReq();
                request.setDstESBAddr((short) 0x9800);
                request.setFlags((short) 0x120);
                send(request);
            }

        }
    }

    @Override
    public void applyUID(GenerateUniqueIDResp resp) {
        logger.debug("\t>>>>>> GenerateUniqueIDResp\t result4t=" + resp.getResult4T() + ",uid="
                + resp.getUid());
        if (0 == resp.getResult4T()) {
            this.uid = resp.getUid();
        }
    }

    /**
     * 更新状态为可接收服务端数据
     */
    @Override
    public void recvd(HeartbeatToAccessResp resp) {
        if (NetConstants.CONN_SUCCESS == resp.getResult()) {
            headrtbeatState = HB_STATE_RECVD;
            status = headrtbeatState;
            reHeartbeatCount = 0;
        }
    }

    /**
     * 重连服务器响应
     */
    @Override
    public void retry2access(RetryToAccessResp resp) {
        // 将是否重连标识设置为false,进入心跳后,再修改为true.
        isRetry2AccessSend = false;
        if (NetConstants.CONN_SUCCESS == resp.getResult()) {
            lastRetry2AccessTime = System.currentTimeMillis();
            lastRetryRecvTime = System.currentTimeMillis();
            logger.debug("\t>>>>>> 重连服务器成功 [" + resp.getResult() + "]");
            // 重连响应成功
            connectionState = CONN_STATE_ONLINE;
            headrtbeatState = HB_STATE_IDLE;
            status = headrtbeatState;
            reHeartbeatCount = 0;
            retry2accessCount = 0;
        } else {
            // TODO 重连失败
            logger.debug("\t>>>>>> 重连服务器失败 [" + resp.getResult() + "]");
            session.close(true);
        }
    }

    @Override
    public int getSeqNum() {
        return seq;
    }

    /**
     * @return the moduleId
     */
    public int getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId the moduleId to set
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * @return the fetchSignalTimeout
     */
    public long getFetchSignalTimeout() {
        return fetchSignalTimeout;
    }

    /**
     * @param fetchSignalTimeout the fetchSignalTimeout to set
     */
    public void setFetchSignalTimeout(long fetchSignalTimeout) {
        this.fetchSignalTimeout = fetchSignalTimeout;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public int getMyAppId() {
        return myAppId;
    }

    public void setMyAppId(int myAppId) {
        this.myAppId = myAppId;
    }

    public int getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(int connectionState) {
        this.connectionState = connectionState;
    }

    public int getHeadrtbeatState() {
        return headrtbeatState;
    }

    public void setHeadrtbeatState(int headrtbeatState) {
        this.headrtbeatState = headrtbeatState;
    }

    public long getCheckAccessDelay() {
        return checkAccessDelay;
    }

    public void setCheckAccessDelay(long checkAccessDelay) {
        this.checkAccessDelay = checkAccessDelay;
    }

    public long getHeartBeatInterval() {
        return heartBeatInterval;
    }

    public void setHeartBeatInterval(long heartBeatInterval) {
        this.heartBeatInterval = heartBeatInterval;
    }

    public long getLastRegisterToAccessTime() {
        return lastRegisterToAccessTime;
    }

    public void setLastRegisterToAccessTime(long lastRegisterToAccessTime) {
        this.lastRegisterToAccessTime = lastRegisterToAccessTime;
    }

    public long getLastHB2AccessTime() {
        return lastHB2AccessTime;
    }

    /**
     * 设置心跳时间
     */
    @Override
    public void setLastHB2AccessTime(long lastHB2AccessTime) {
        this.lastHB2AccessTime = lastHB2AccessTime;
        this.lastDataRecvTime = lastHB2AccessTime;
        this.lastRetry2AccessTime = lastHB2AccessTime;
        this.lastRetryRecvTime = lastHB2AccessTime;
    }

    public long getAccessRespTimeout() {
        return accessRespTimeout;
    }

    public void setAccessRespTimeout(long accessRespTimeout) {
        this.accessRespTimeout = accessRespTimeout;
    }

    public long getAccessHBTimeout() {
        return accessHBTimeout;
    }

    public void setAccessHBTimeout(long accessHBTimeout) {
        this.accessHBTimeout = accessHBTimeout;
    }

    public final TerminalInfo getTerminal() {
        return terminal;
    }

    public final void setTerminal(TerminalInfo terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    @Override
    public void setSeq(short seq) {
        this.seq = seq;
    }

    /**
     * @return the ack
     */
    @Override
    public short getAck() {
        return ack;
    }

    /**
     * @param ack the ack to set
     */
    @Override
    public void setAck(short ack) {
        if (ack > 0) {
            this.ack = ack;
        }
    }

    /**
     * 设置是否重连标识
     * 
     * @param isRetry
     */
    @Override
    public void setRetryStatus(boolean isRetry) {
        isRetry2AccessSend = isRetry;
    }

    /**
     * @return the encryptKey
     */
    public int getEncryptKey() {
        return encryptKey;
    }

    /**
     * @param encryptKey the encryptKey to set
     */
    public void setEncryptKey(int encryptKey) {
        this.encryptKey = encryptKey;
    }

    /**
     * @param tcpConnector the tcpConnector to set
     */
    public void setTcpConnector(TCPConnector tcpConnector) {
        this.tcpConnector = tcpConnector;
    }

    /**
     * @return the decoder
     */
    public AccessDecoder getDecoder() {
        return decoder;
    }

    /**
     * @param decoder the decoder to set
     */
    public void setDecoder(AccessDecoder decoder) {
        this.decoder = decoder;
    }

    /**
     * @return the encoder
     */
    public AccessEncoder getEncoder() {
        return encoder;
    }

    /**
     * @param encoder the encoder to set
     */
    public void setEncoder(AccessEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * @return the encryptProtocol
     */
    public boolean isEncryptProtocol() {
        return encryptProtocol;
    }

    /**
     * @param encryptProtocol the encryptProtocol to set
     */
    public void setEncryptProtocol(boolean encryptProtocol) {
        this.encryptProtocol = encryptProtocol;
    }

    /**
     * @return the lastDataRecvTime
     */
    public long getLastDataRecvTime() {
        return lastDataRecvTime;
    }

    /**
     * @param lastDataRecvTime the lastDataRecvTime to set
     */
    public void setLastDataRecvTime(long lastDataRecvTime) {
        this.lastDataRecvTime = lastDataRecvTime;
    }

    /**
     * @return the totalDataRecvTimeout
     */
    public long getTotalDataRecvTimeout() {
        return totalDataRecvTimeout;
    }

    /**
     * @param totalDataRecvTimeout the totalDataRecvTimeout to set
     */
    public void setTotalDataRecvTimeout(long totalDataRecvTimeout) {
        this.totalDataRecvTimeout = totalDataRecvTimeout;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

}
