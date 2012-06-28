package com.skymobi.android.transport;

import com.skymobi.android.bean.esb.core.EsbTerminal2AccessSignal;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessResp;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ESBAccessEndpoint extends AbstractEndpoint implements
		MutableEndpoint {

    private static final Logger logger =
            LoggerFactory.getLogger(ESBAccessEndpoint.class);

    private ScheduledExecutorService exec =
            Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {

                @Override
				public Thread newThread(Runnable r) {
                    return new Thread(r, "ESBModule-Sender-thread");
                }
            });

    private Receiver receiver;

    private int moduleId;
    private int myAppId;

    //	esbConnectionState enum
    private static final int CONN_STATE_OFFLINE = 0;
    private static final int CONN_STATE_REGISTERING = 1;
    private static final int CONN_STATE_ONLINE = 2;

    //	esbHeartbeatState enum
    private static final int HB_STATE_IDLE = 0;
    private static final int HB_STATE_SENDED = 1;
    private static final int HB_STATE_RECVD = 2;

    private int esbConnectionState = CONN_STATE_OFFLINE;
    private int esbHeartbeatState = HB_STATE_IDLE;

    private long checkESBDelay = 5 * 1000;
    private long esbRespTimeout = 10 * 1000;
    private long esbHeartbeatInterval = 110 * 1000;
    private long fetchSignalTimeout = 1000;
    private long lastRegisterToEsbTime;
    private int reRegisterCount = 0;
    private long lastHeartbeatToEsbTime = 0;
    private long esbHeartbeatTimeout = 10 * 1000;
    private int reHeartbeatCount = 0;
    //序列号必须以1开始
    private short seq = 1;
    private short ack;

    private void doSendPending() {
        exec.submit(new Runnable() {

            @Override
			public void run() {
                sendPending();
            }
        });
    }

    private boolean isESBConnectionValid() {
        return ((null != session) && (CONN_STATE_ONLINE == esbConnectionState));
    }

    private void sendPending() {
        if (isESBConnectionValid()) {
            try {
                Object bean = pendings.poll(fetchSignalTimeout, TimeUnit.MILLISECONDS);
                if (null != bean) {
                    if(bean instanceof EsbTerminal2AccessSignal){
                        ((EsbTerminal2AccessSignal)bean).setSeqNum(seq++);
                        ((EsbTerminal2AccessSignal)bean).setAck(ack);
                    }
                    session.write(bean);
                }
            } catch (InterruptedException e) {
                logger.error("sendPending", e);
            } finally {
                doSendPending();
            }
        } else {
            //	esb not connected or not registered
            exec.schedule(new Runnable() {

                @Override
				public void run() {
                    sendPending();
                }
            }, fetchSignalTimeout, TimeUnit.MILLISECONDS);
        }
    }

    private boolean isHeartbeatToEsbTimeout() {
        long duration = System.currentTimeMillis() - lastHeartbeatToEsbTime;
        return (duration >= esbHeartbeatTimeout);
    }

    private boolean isHeartbeatToEsbExceedInterval() {
        long duration = System.currentTimeMillis() - lastHeartbeatToEsbTime;
        return (duration >= esbHeartbeatInterval);
    }

    private void sendHeartbeatToAccess() {
        lastHeartbeatToEsbTime = System.currentTimeMillis();
        HeartbeatToAccessReq req = new HeartbeatToAccessReq();

        req.setDstESBAddr(0x9800);
        req.setFlags((short)0x0520);
        //req.setSeqNum(seq++);
        req.setAck(ack);

        send(req);
    }

    private void doProcessHeartbeat() {
        if (HB_STATE_IDLE == this.esbHeartbeatState) {
            if (isHeartbeatToEsbExceedInterval()) {
                esbHeartbeatState = HB_STATE_SENDED;
                sendHeartbeatToAccess();
            }
        } else if (HB_STATE_SENDED == this.esbHeartbeatState) {
            if (isHeartbeatToEsbTimeout()) {
                if (reHeartbeatCount < 1) {
                    reHeartbeatCount++;
                    sendHeartbeatToAccess();
                } else {
                    logger.info("heartbeat timeout for twice, disconnect session");
                    this.session.close(true);
                }
            }
        } else if (HB_STATE_RECVD == this.esbHeartbeatState) {
            reHeartbeatCount = 0;
            esbHeartbeatState = HB_STATE_IDLE;
        } else {
            throw new RuntimeException("Internal Error: Unknown ESB Heartbeat State ["
                    + esbHeartbeatState + "]");
        }
    }

    private boolean isRegisterToEsbTimeout() {
        long duration = System.currentTimeMillis() - lastRegisterToEsbTime;
        return (duration >= esbRespTimeout);
    }

    private void sendRegisterToAccess() {
        lastRegisterToEsbTime = System.currentTimeMillis();
        RegisterToAccessReq req = new RegisterToAccessReq();

        req.setAccessModuleId((short) 0x9800);
        req.setAppId(myAppId);
        req.setSeqNum(seq++);
        req.setAck(ack);
        req.setSrcModuleId((short) this.moduleId);

        session.write(req);
    }

    private void doCheckESB() {
    	logger.debug(" > esbConnectionState:"+esbConnectionState);
        if (CONN_STATE_OFFLINE == esbConnectionState) {
            sendRegisterToAccess();
            esbConnectionState = CONN_STATE_REGISTERING;
        } else if (CONN_STATE_REGISTERING == esbConnectionState) {
            //	check registering timeout
            if (isRegisterToEsbTimeout()) {
                reRegisterCount++;
                //	register to esb timeout, try re-register
                sendRegisterToAccess();
            }
        } else if (CONN_STATE_ONLINE == esbConnectionState) {
            //	check weather to send heartbeat
            doProcessHeartbeat();
        } else {
            throw new RuntimeException("Internal Error: Unknown ESB Connection State ["
                    + esbConnectionState + "]");
        }
    }

    @Override
	public void start() {
    	logger.debug(" > ESBAccessEndpoint.start");
        exec.scheduleWithFixedDelay(new Runnable() {

            @Override
			public void run() {
                doCheckESB();
            }
        },
                0, checkESBDelay, TimeUnit.MILLISECONDS);

        doSendPending();
    }

    @Override
	public void stop() {
        super.stop();

        this.exec.shutdownNow();
    }

    /* (non-Javadoc)
      * @see com.skymobi.transport.AbstractEndpoint#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
      */
    public void messageReceived2(IoSession session, Object msg) {
    	logger.debug("ESBAccessEndpoint.messageReceived:"+msg.getClass());
        if (msg instanceof AbstractAccessHeaderable) {
            ack = (short) ((AbstractAccessHeaderable) msg).getSeqNum();
            if (msg instanceof RegisterToAccessResp) {
                RegisterToAccessResp resp = (RegisterToAccessResp)msg;
                if (0 == resp.getResult()) {
                    esbConnectionState = CONN_STATE_ONLINE;
                    //	succees
                    if (logger.isDebugEnabled()) {
                        logger.debug("register to access with moduleId:"
                                + this.moduleId + " succeed.");
                    }
                } else {
                    logger.warn("register to esb with moduleId:"
                            + this.moduleId + " FAILED, result:" + resp.getResult());
                }
            } else if (msg instanceof HeartbeatToAccessResp) {
                esbHeartbeatState = HB_STATE_RECVD;
                if (logger.isDebugEnabled()) {
                    logger.debug("recvd heartbeat to access:" + msg);
                }
            } else if(receiver != null){
                receiver.messageReceived(session,msg);
            }
        } else {
            super.messageReceived(session,msg);
        }
    }
    
    

    @Override
	public void messageReceived(IoSession session, Object msg) {
    	logger.debug("ESBAccessEndpoint.messageReceived:"+msg.getClass());
        if (msg instanceof AbstractAccessHeaderable) {
            ack = (short) ((AbstractAccessHeaderable) msg).getSeqNum();
            logger.debug(" > ack:"+ack);
            if (msg instanceof RegisterToAccessResp) {
                RegisterToAccessResp resp = (RegisterToAccessResp)msg;
                if (0 == resp.getResult()) {
                    esbConnectionState = CONN_STATE_ONLINE;
                    //	succees
                    if (logger.isDebugEnabled()) {
                        logger.debug("register to access with moduleId:"
                                + this.moduleId + " succeed.");
                    }
                } else {
                    logger.warn("register to esb with moduleId:"
                            + this.moduleId + " FAILED, result:" + resp.getResult());
                }
            } else if (msg instanceof HeartbeatToAccessResp) {
                esbHeartbeatState = HB_STATE_RECVD;
                if (logger.isDebugEnabled()) {
                    logger.debug("recvd heartbeat to access:" + msg);
                }
            } else if(receiver != null){
                receiver.messageReceived(session,msg);
            }
        } else {
            super.messageReceived(session,msg);
        }
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

    /**
     * @return the checkESBDelay
     */
    public long getCheckESBDelay() {
        return checkESBDelay;
    }

    /**
     * @param checkESBDelay the checkESBDelay to set
     */
    public void setCheckESBDelay(long checkESBDelay) {
        this.checkESBDelay = checkESBDelay;
    }

    /**
     * @return the esbHeartbeatInterval
     */
    public long getEsbHeartbeatInterval() {
        return esbHeartbeatInterval;
    }

    /**
     * @param esbHeartbeatInterval the esbHeartbeatInterval to set
     */
    public void setEsbHeartbeatInterval(long esbHeartbeatInterval) {
        this.esbHeartbeatInterval = esbHeartbeatInterval;
    }

    /**
     * @return the esbRespTimeout
     */
    public long getEsbRespTimeout() {
        return esbRespTimeout;
    }

    /**
     * @param esbRespTimeout the esbRespTimeout to set
     */
    public void setEsbRespTimeout(long esbRespTimeout) {
        this.esbRespTimeout = esbRespTimeout;
    }

    /**
     * @return the reRegisterCount
     */
    public int getReRegisterCount() {
        return reRegisterCount;
    }

    /**
     * @return the esbConnectionState
     */
    public int getEsbConnectionState() {
        return esbConnectionState;
    }

    /**
     * @return the esbHeartbeatState
     */
    public int getEsbHeartbeatState() {
        return esbHeartbeatState;
    }

    /**
     * @return the esbHeartbeatTimeout
     */
    public long getEsbHeartbeatTimeout() {
        return esbHeartbeatTimeout;
    }

    /**
     * @param esbHeartbeatTimeout the esbHeartbeatTimeout to set
     */
    public void setEsbHeartbeatTimeout(long esbHeartbeatTimeout) {
        this.esbHeartbeatTimeout = esbHeartbeatTimeout;
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
}
