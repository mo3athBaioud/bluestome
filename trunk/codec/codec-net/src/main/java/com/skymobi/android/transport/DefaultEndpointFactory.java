/**
 * 
 */
package com.skymobi.android.transport;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.mina.core.session.IoSession;

/**
 * @author hp
 *
 */
public class DefaultEndpointFactory implements EndpointFactory {

    private BlockingQueue<Object>	pendings = null;
    
    private MutableEndpoint endPoint;
    
    public DefaultEndpointFactory(boolean shareSendQueue, int cachedMessageCount) {
    	if ( shareSendQueue ) {
			this.pendings = new LinkedBlockingQueue<Object>(cachedMessageCount);
    	}
    	else {
			this.pendings = new LinkedBlockingQueue<Object>(100);
    	}
    }
    
	@Override
	public MutableEndpoint createEndpoint(IoSession session) {
		if(null != endPoint){
			endPoint.setIoSession(session);
			if ( null != pendings ) {
				endPoint.setSendQueue(pendings);
			}
			//2012-01-05 端点类已经OK,开始准备发送消息
			endPoint.setStatus(100);
			endPoint.start();
		}
		return endPoint;
	}
	
	public void stop() {
		if ( null != this.pendings ) {
			this.pendings.clear();
		}
		//TODO 2012-01-05 新增
		if(null != endPoint){
			//准备断开连接，将状态置为无连接状态
			endPoint.setStatus(-1);
			endPoint.stop();
		}
	}
	
	public void send(Object bean) {
		if ( null != this.pendings ) {
			pendings.add(bean);
		}
		else {
			throw new RuntimeException("Unsupport Operation for send.");
		}
	}

	public int getPendingCount() {
		if ( null != this.pendings ) {
			return	this.pendings.size();
		}
		else {
			return	-1;
		}
	}
	
    public MutableEndpoint getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(MutableEndpoint endPoint) {
		this.endPoint = endPoint;
	}
	
	/**
	 * 返回端点类的状态
	 * @return
	 */
	public int getEndpointStatus(){
		return endPoint.getStatus();
	}

}