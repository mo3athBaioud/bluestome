/**
 * 
 */
package com.skymobi.android.bean.bytebean.core;

/**
 * @author hp
 *
 */
public interface EncContextFactory {
	public EncContext	createEncContext(
			Object encObject, Class<?> type, ByteFieldDesc desc);
}
