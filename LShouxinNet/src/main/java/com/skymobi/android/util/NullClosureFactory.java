/**
 * 
 */
package com.skymobi.android.util;

import org.apache.commons.collections.Closure;

/**
 * @author isdom
 *
 */
public class NullClosureFactory implements ClosureFactory {

	/* (non-Javadoc)
	 * @see com.skymobi.util.ClosureFactory#createClosure()
	 */
	@Override
	public Closure createClosure() {
		return new Closure() {

			@Override
			public void execute(Object input) {
			}};
	}

}
