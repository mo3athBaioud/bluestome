/**
 * 
 */
package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.collections.Closure;

import java.util.concurrent.ExecutorService;

/**
 * @author hp
 *
 */
public class WrapAsyncClosure implements Closure {

    private static final Logger logger = LoggerFactory.getLogger(WrapAsyncClosure.class);
    
	private ExecutorService	executorService;
	private	Closure			impl;
	private	String			description;
	
	public WrapAsyncClosure(String desc) {
		description = desc;
	}
	
	public void setImpl(Closure impl) {
		this.impl = impl;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void destroy() {
		if ( null != executorService ) {
			executorService.shutdownNow();
		}
	}
	
	@Override
	public void execute(final Object input) {
		executorService.submit(new Runnable(){

			@Override
			public void run() {
				try {
					impl.execute(input);
				}
				catch (Exception e) {
		        	logger.error("execute:", e);
				}
			}});
	}

	public String getDescription() {
		return description;
	}

}
