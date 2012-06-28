/**
 * 
 */
package com.skymobi.android.bean.tlv.decode.decoders;

import com.skymobi.android.bean.tlv.decode.TLVDecodeContext;
import com.skymobi.android.bean.tlv.decode.TLVDecoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author hp
 *
 */
public class ByteArrayTLVDecoder implements TLVDecoder {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ByteArrayTLVDecoder.class);
    
	/* (non-Javadoc)
	 * @see com.skymobi.bean.tlv.TLVDecoder#decode(int, byte[], com.skymobi.bean.tlv.TLVDecodeContext)
	 */
	public Object decode(int tlvLength, byte[] tlvValue, TLVDecodeContext ctx) {
		if ( tlvLength == tlvValue.length ) {
			return tlvValue;
		}
		else {
			return	ArrayUtils.subarray(tlvValue, 0, tlvLength);
		}
	}

}
