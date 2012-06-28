/**
 * 
 */
package com.skymobi.android.transport;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.protocol.esb.AccessFixedHeader;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RetryToAccessResp;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.BlockingQueue;
/**
 * 抽象端点类
 * @author hp
 */
public abstract class AbstractEndpoint implements MutableEndpoint {
    
    private static final Logger logger = LoggerFactory.getLogger(AbstractEndpoint.class);
	
	protected	Receiver		nextReceiver;
	protected 	IoSession		session = null;
    
    protected 	BlockingQueue<Object>	pendings = null;
    
    protected	String	objectNamePrefix;
    
    protected int status = -1;
    
	public String getObjectNamePrefix() {
		return objectNamePrefix;
	}

	public void setObjectNamePrefix(String objectNamePrefix) {
		this.objectNamePrefix = objectNamePrefix;
	}

	@Override
	public void send(Object bean) {
		if ( null != this.pendings ) {
			pendings.add(bean);
		}
	}

	@Override
	public void messageReceived(IoSession session, Object msg) {
	    logger.debug(" > "+getClass().getSimpleName()+" messageReceivered");
		if ( null != nextReceiver ) {
			this.nextReceiver.messageReceived(session, msg);
		}
	}

	@Override
	public void setIoSession(IoSession session) {
		this.session = session;
	}

	@Override
	public void stop() {
	    logger.debug("\t>>>>> AbstractEndpoint.stop.in");
		if(null != session)
		{
		    if(!session.isClosing() && session.isConnected()){
		        session.close(true);
		    }
		}
        logger.debug("\t>>>>> AbstractEndpoint.stop.out");
	}

	@Override
	public void setSendQueue(BlockingQueue<Object> queue) {
		this.pendings = queue;
	}
	
	
	public void setNextReceiver(Receiver nextReceiver) {
		this.nextReceiver = nextReceiver;
	}

	public int getPendingCount() {
		if ( null != this.pendings ) {
			return	this.pendings.size();
		}
		else {
			return	-1;
		}
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 接收到注册到ACCESS响应
	 * 由子类实现
     * @param resp
	 */
	public void online(RegisterToAccessResp resp){
		//TODO 
	}
	
	/**
	 * 接收到心跳响应
	 * 由子类实现
	 * @param resp
	 */
	public void recvd(HeartbeatToAccessResp resp){
		//TODO 
	}
	
	/**
	 * 重连到ACCESS响应
	 * 由子类实现
	 * @param resp
	 */
	public void retry2access(RetryToAccessResp resp){
	    
	}
	
	/**
	 * 收到消息
	 */
	public void reqRecvd(){
	    
	}
	
	/**
	 * 获取SEQ值
	 * @return
	 */
	public int getSeqNum(){
	    return 0;
	}
	
	/**
	 * 设置ACK值
	 * @param ack
	 */
	public void setAck(short ack){
	    
	}
	
	/**
	 * 设置SEQ值
	 * @param seq
	 */
	public void setSeq(short seq){
	    
	}

	/**
	 * 设置是否重连标识
	 * @param isRetry
	 */
	public void setRetryStatus(boolean isRetry){
	    
	}
	/**
	 * 获取ACK值
	 * @return
	 */
	public short getAck(){
	    return 0;
	}
	
	/**
	 * 获取当前状态
	 */
	@Override
	public int getStatus(){
		return status;
	}

}
