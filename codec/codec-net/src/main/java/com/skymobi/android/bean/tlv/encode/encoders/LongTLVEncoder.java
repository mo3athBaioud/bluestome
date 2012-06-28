/**
 * 
 */
package com.skymobi.android.bean.tlv.encode.encoders;

import com.skymobi.android.bean.tlv.encode.TLVEncodeContext;
import com.skymobi.android.bean.tlv.encode.TLVEncoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author hp
 *
 */
public class LongTLVEncoder extends AbstractNumberTLVEncoder implements TLVEncoder {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LongTLVEncoder.class);
    private static final int DEFAULT_BYTE_SIZE = 8;
    
	public List<byte[]> encode(Object from, TLVEncodeContext ctx) {
		byte[] ret = null;
		
		if ( from instanceof Long ) {
			int byteSize = getAnnotationByteSize(ctx);
			if ( -1 == byteSize ) {
				byteSize = DEFAULT_BYTE_SIZE;
			}
			ret = ctx.getNumberCodec().long2Bytes( ((Long)from).longValue(), byteSize);
		}
		else {
			throw new RuntimeException("LongTLVEncoder: wrong source type. [" + from.getClass() + "]");
		}
		
		return	Arrays.asList( ret );
	}

}
