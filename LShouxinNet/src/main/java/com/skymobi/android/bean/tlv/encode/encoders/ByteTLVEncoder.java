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
public class ByteTLVEncoder extends AbstractNumberTLVEncoder implements TLVEncoder {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ByteTLVEncoder.class);
    private static final int DEFAULT_BYTE_SIZE = 1;
    
	public List<byte[]> encode(Object from, TLVEncodeContext ctx) {
		byte[] ret = null;
		
		int byteSize = getAnnotationByteSize(ctx);
		if ( -1 == byteSize ) {
			byteSize = DEFAULT_BYTE_SIZE;
		}
		
		if ( from instanceof Short ) {
			ret = ctx.getNumberCodec().short2Bytes( ((Short)from).shortValue(), byteSize);
		}
		else if ( from instanceof Integer ) {
			ret = ctx.getNumberCodec().int2Bytes( ((Integer)from).intValue(), byteSize);
		}
		else if ( from instanceof Long ) {
			ret = ctx.getNumberCodec().long2Bytes( ((Long)from).longValue(), byteSize);
		}
		else if ( from instanceof Byte ) {
			ret = new byte[]{ ((Byte)from).byteValue() };
		}
		else {
			throw new RuntimeException("ByteTLVEncoder: wrong source type. [" + from.getClass() + "]");
		}
		
		return	Arrays.asList( ret );
	}

}
