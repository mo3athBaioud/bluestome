/**
 * 
 */
package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * @author hp
 *
 */
public class FunctorAsync implements ClosureEx {

    private static final Logger logger = LoggerFactory.getLogger(FunctorAsync.class);
	private Executor	executor;
	private	ClosureEx	impl;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return null != impl ? impl.toString() : "functorAsync(null)";
	}

	/* (non-Javadoc)
	 * @see com.skymobi.util.VaryingParamClosure#execute(java.lang.Object[])
	 */
	@Override
	public void execute(final Object... args) {
		executor.execute(new Runnable(){

			@Override
			public void run() {
				try {
					impl.execute(args);
				}
				catch (Exception e) {
					logger.error("execute:", e);
				}
			}});
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public ClosureEx getImpl() {
		return impl;
	}

	public void setImpl(ClosureEx impl) {
		this.impl = impl;
	}

	@Override
	public void setCanceled(boolean canceled) {
		this.impl.setCanceled(canceled);
	}

}
