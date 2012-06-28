/**
 * 
 */
package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author hp
 *
 */
public class ProxyBuilder<T> {

    private static final Logger logger = 
    	LoggerFactory.getLogger(ProxyBuilder.class);
    
	private	AtomicReference<T> ref = new AtomicReference<T>();
	private	Class<T>[]	intf;
	
	private class Handler implements InvocationHandler {

		@Override
		public Object invoke(Object obj, Method method, Object[] args)
				throws Throwable {
			T impl = ref.get();
			if ( null != impl ) {
				return method.invoke(impl, args);
			}
			
			throw new ImplNotSetException("implementation Object !NOT! set yet.");
		}
		
	}
	
	private	Handler	handler = new Handler();
	
	@SuppressWarnings("unchecked")
    public ProxyBuilder(Class<T> intf) {
		this.intf = new Class[]{intf};
	}

    public ProxyBuilder(Class<T>[] intf) {
		this.intf = intf;
	}
	
	@SuppressWarnings("unchecked")
	public T buildProxy() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if ( logger.isDebugEnabled() ) {
			logger.debug("using ClassLoader "+cl+" to newProxyInstance");
		}
		return (T)Proxy.newProxyInstance(
				cl, intf, handler);
	}

	public void setImpl(T impl) {
		this.ref.set( impl );
	}

	@Override
	public String toString() {
		T impl = this.ref.get();
		
		if ( null != impl ) {
			return impl.toString();
		}
		
		return	"ProxyBuilder with null impl";
	}

}
