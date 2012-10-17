/**
 * 
 */
package com.skymobi.android.transport;

import android.skymobi.messenger.x;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.util.ByteUtils;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoServiceStatistics;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hp
 *
 */
public class TCPConnector {
	private static final Logger logger = LoggerFactory.getLogger(TCPConnector.class);
    private ScheduledExecutorService exec = 
        Executors.newSingleThreadScheduledExecutor();
    
    private String					name = "connector";
    private String      			destIp = null;
    private int         			destPort = -1;
    private IoConnector				connector = null;
    private	EndpointFactory			endpointFactory = null;
    private	ProtocolCodecFactory	codecFactory = null;
    private	long					retryTimeout = 10*1000L;
    private long                    connectTimeout = 15*1000L;
    private AccessEndpoint accessEndPoint;
    private INetStateNotify netStateNotify;
    private ConnectFuture connectFuture = null;
    
    //已经连接的状态
	private boolean connect = false;
	
	//正在连接
	private boolean connecting = false;
	
	private String getDesc() {
    	return	"[" + name + "]";
    }
    
    private class IOHandler extends IoHandlerAdapter {
        
        @Override
        public void messageReceived(IoSession session, Object msg) throws Exception {
            Endpoint endpoint = TransportUtils.getEndpointOfSession(session);
        	if ( null != endpoint ) {
        		endpoint.messageReceived(session, msg);
        	}
        }
        
        @Override
		public void sessionCreated(IoSession session) throws Exception {
            //初始化密钥
            byte[] ci = ByteUtils.cc(ByteUtils.ci);
            if(null != ci){
                x.i(ci);
            }
            if ( logger.isInfoEnabled() ) {
                logger.info(getDesc() + " sessionCreated: " + session);
            }
        	try {
	    		Endpoint endpoint = endpointFactory.createEndpoint(session);
	    		if ( null != endpoint ) {
	    			TransportUtils.attachEndpointToSession(session, endpoint);
	    		}
        	}catch (Exception e) {
        		//创建SESSSION失败，即连接丢失，需要重连
        		logger.warn("\t>>>>> session.create.exception:"+e);
            	session.close(true);
        	}
		}
        
		@Override
        public void sessionOpened(IoSession session) throws Exception {
            if ( logger.isInfoEnabled() ) {
                logger.info(getDesc() + " sessionOpened: " + session);
            }
            connect = session.isConnected();
            connecting = false;
		}
        

        @Override
        public void sessionClosed(final IoSession session) throws Exception {
            logger.debug(getClass().getSimpleName()+"sessionClosed ");
            if ( logger.isInfoEnabled() ) {
                logger.info(getDesc() + " sessionClosed: " + session);
                logger.debug("\t>>>>>> sessionClosed.isClose:"+session.isClosing());
                logger.debug("\t>>>>>> sessionClosed.isConnected:"+session.isConnected());
            }
            
            //没有正在在连接
            connecting = false;
            //断开连接
            connect = false;
            
            //TODO 非重连时才向上通知网络断开
            if(null != netStateNotify){
                logger.debug("\t>>>>>> sessionClosed.notify2UI network broken");
                netStateNotify.netStateNotify(Integer.valueOf(-1));
            }
            
            //端点类停止
        	Endpoint endpoint = TransportUtils.getEndpointOfSession(session);
        	if ( null != endpoint ) {
        		//问题处在这 stop中将定时服务ScheduleExectorService这个服务给关闭了
        		endpoint.stop();
        	}
        	
        	//处理关闭连接后的操作
            onSessionClosed();
        }
        
        @Override
        public void exceptionCaught(IoSession session, Throwable e) throws Exception {
            logger.warn("\t>>>>>> IoHandlerAdapter.exceptionCaugth:"+e.getMessage());
            //发生异常时，关闭连接
            session.close(true);
        }

        
    }
    
    private IOHandler ioHandler = new IOHandler();
    
	private synchronized void doConnect() {
        logger.debug("\t>>>>>> doConnect connecting:"+connecting+"\t connect:"+connect);
        if(!connecting && !connect){
            logger.debug("\t>>>>> do connect to server ");
    	    connecting = true;
    	    long start = System.currentTimeMillis();
    		if ( null == destIp || destIp.equals("") ) {
    			logger.warn(getDesc()+" destIp is null, disable this connector.");
    			return;
    		}
            if(null == connector.getHandler()){
                start = System.currentTimeMillis();
                connector.setHandler(ioHandler);
                logger.debug("\t>>>>> doConnect.setHandler:"+(System.currentTimeMillis()-start));
            }else{
                logger.debug("\t>>>>> doConnect.handler is not null");
            }
            
            start = System.currentTimeMillis();
            //设置网络连接超时的时间
            connector.setConnectTimeoutMillis(connectTimeout);
            connectFuture = 
                connector.connect(new InetSocketAddress(destIp, destPort));
            if(null != connectFuture){
                logger.debug("\t>>>>> doConnect.connect:"+(System.currentTimeMillis()-start));
                connectFuture.addListener(new IoFutureListener<ConnectFuture>(){
                    @Override
                    public void operationComplete(final ConnectFuture connectFuture) {
                        exec.submit( new Runnable(){
                            @Override
                            public void run() {
                                onConnectComplete(connectFuture);
                            }} 
                        );
                    }
                });
            }
        }else{
            logger.debug("\t>>>>>> request connect to server ,but refuse because connect is doing or connect have been done!");
        }
	}
	
    private	void onConnectComplete(ConnectFuture connectFuture) {
        logger.debug("\t>>>>> "+getClass().getSimpleName()+" onConnectComplete");
        connect = connectFuture.isConnected();
    	if (!connect) {
			logger.debug(getDesc() + " connect [" + this.destIp +":"+ this.destPort + "] with in ["+(retryTimeout/1000)+"]s ");
			onSessionClosed();
    	}
    }
    
    private void onSessionClosed() {
		logger.warn(getDesc() + " session closed, reconnect!");
		//重连服务器
        exec.schedule(new Runnable(){
			@Override
			public void run() {
		        //没有正在在连接
		        connecting = false;
		        //连接关闭
		        connect = false;
		        //启动连接
	    		doConnect();
			}
		}, 
		retryTimeout, TimeUnit.MILLISECONDS);
    }
    
    public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public int getDestPort() {
		return destPort;
	}

	public void setDestPort(int destPort) {
		this.destPort = destPort;
	}

	public ProtocolCodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(ProtocolCodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	public EndpointFactory getEndpointFactory() {
		return endpointFactory;
	}

	public void setEndpointFactory(EndpointFactory endpointFactory) {
		this.endpointFactory = endpointFactory;
	}
	
	public TCPConnector(String name,IoConnector connector) {
		this.name = name;
        this.connector = connector;
    }
	
	public TCPConnector(String name) {
		this.name = name;
        this.connector = new NioSocketConnector();
   }
	
    public synchronized void start(boolean isReconnect) {
        long start = System.currentTimeMillis();
    	if(!isReconnect){
    	    if(null != codecFactory){
    	        DefaultIoFilterChainBuilder filter = connector.getFilterChain();
    	        if(null == filter.get("codec")){
    	            connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(codecFactory));
    	        }
    	    }
    	}
    	logger.debug("\t>>>> TCPConnector (外部调用)重连:"+(isReconnect?"是":"否"));
        logger.debug("\t>>>> start.spend:" + (System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        if(exec.isShutdown()){
            exec = 
                Executors.newSingleThreadScheduledExecutor();
        }
        logger.debug("\t>>>> exec.spend:" + (System.currentTimeMillis()-start));
        doConnect();
    }

	public void stop() {
		this.exec.shutdownNow();
		if(null != accessEndPoint)
		    accessEndPoint.stop();
	}

	public long getRetryTimeout() {
		return retryTimeout;
	}

	public void setRetryTimeout(long retryTimeout) {
		this.retryTimeout = retryTimeout;
	}
	
	//连接是否成功
	public boolean isConnected() {
		return connect;
	}
	
	public IoServiceStatistics getStatistics() {
		return	connector.getStatistics();
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

    /**
     * @return the netStateNotify
     */
    public INetStateNotify getNetStateNotify() {
        return netStateNotify;
    }

    /**
     * @param netStateNotify the netStateNotify to set
     */
    public void setNetStateNotify(INetStateNotify netStateNotify) {
        this.netStateNotify = netStateNotify;
    }

    /**
     * @return the accessEndPoint
     */
    public AccessEndpoint getAccessEndPoint() {
        return accessEndPoint;
    }

    /**
     * @param accessEndPoint the accessEndPoint to set
     */
    public void setAccessEndPoint(AccessEndpoint accessEndPoint) {
        this.accessEndPoint = accessEndPoint;
    }

    /**
     * @return the connectTimeout
     */
    public long getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * @param connectTimeout the connectTimeout to set
     */
    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

}
