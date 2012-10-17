/**
 * 
 */
package com.skymobi.android.transport;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.mina.core.session.IoSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Bluestome.Zhang
 *
 */
public class DefaultEndpoint extends AbstractEndpoint implements MutableEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultEndpoint.class);
    private ExecutorService exec = 
        Executors.newSingleThreadExecutor();
    
    private	long			waitTimeout = 1000;
    private Receiver receiver;
    
    private void doSendPending() {
		exec.submit(new Runnable() {
			@Override
			public void run() {
				sendPending();
			}} 
		);
    }
    
    private	void sendPending() {
    	try {
	    	if ( null == session ) {
	    		Thread.sleep(waitTimeout);	// sleep 1s 
	    	}
	    	else {
	    		Object bean = pendings.poll(waitTimeout, TimeUnit.MILLISECONDS);
	    		if ( null != bean) {
	    			if(bean instanceof String)
	    			{
	    				String msg = (String)bean;
	    				session.write(msg);
	    			}
	    			else if(bean instanceof byte[])
	    			{
	    				byte[] bs = (byte[])bean;
	    				session.write(bs);
	    			}
	    			else
	    			{
	    				session.write(bean);
	    			}
	    		}
	    	}
    	} catch (InterruptedException e) {
    		logger.error("sendPending,exception:"+e);
		}
    	finally {
    		doSendPending();
    	}
    }
    
	public long getWaitTimeout() {
		return waitTimeout;
	}

	public void setWaitTimeout(long waitTimeout) {
		this.waitTimeout = waitTimeout;
	}
	
	@Override
	public void start() {
		//
		doSendPending();
	}
	
	@Override
	public void stop() {
		super.stop();
		
        this.exec.shutdownNow();
	}

	/**
	 * 消息处理
	 */
	@Override
	public void messageReceived(IoSession session, Object msg) {
		// TODO Auto-generated method stub
		if(msg instanceof String){
			//TODO 自定义消息处理类
			if(null != receiver)
			{
				receiver.messageReceived(session, msg);
			}
		}else if(msg instanceof byte[]){
			if(null != receiver)
			{
				receiver.messageReceived(session, msg);
			}
		}else{
			super.messageReceived(session, msg);
		}
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

}
