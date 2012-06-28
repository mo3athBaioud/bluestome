/**
 * 
 */
package com.skymobi.android.bean.tlv.decode.decoders;

import com.skymobi.android.bean.tlv.decode.TLVDecodeContext;
import com.skymobi.android.bean.tlv.decode.TLVDecoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;


/**
 * @author hp
 *
 */
public class ShortTLVDecoder implements TLVDecoder {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ShortTLVDecoder.class);
    
	/* (non-Javadoc)
	 * @see com.skymobi.bean.tlv.TLVDecoder#decode(int, byte[], com.skymobi.bean.tlv.TLVDecodeContext)
	 */
	public Object decode(int tlvLength, byte[] tlvValue, TLVDecodeContext ctx) {
		return	ctx.getNumberCodec().bytes2Short(tlvValue, tlvLength);
	}

}
