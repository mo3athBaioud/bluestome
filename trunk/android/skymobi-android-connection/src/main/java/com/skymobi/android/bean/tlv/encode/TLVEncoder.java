/**
 * 
 */
package com.skymobi.android.bean.tlv.encode;

import java.util.List;

/**
 * @author hp
 *
 */
public interface TLVEncoder {
	public List<byte[]> encode(Object src, TLVEncodeContext ctx);
}
