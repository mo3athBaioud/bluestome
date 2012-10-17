/**
 * 
 */
package com.skymobi.android.bean.tlv.encode.encoders;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.bean.tlv.encode.TLVEncodeContext;
import com.skymobi.android.bean.tlv.encode.TLVEncoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


/**
 * @author hp
 *
 */
public class StringTLVEncoder implements TLVEncoder {

    private static final Logger logger = LoggerFactory.getLogger(StringTLVEncoder.class);
    
	public List<byte[]> encode(Object from, TLVEncodeContext ctx) {
        String src = (String)from;
        String charset = "UTF-8";
        Field field = ctx.getValueField();
        if ( null != field ) {
	        TLVAttribute attr = field.getAnnotation(TLVAttribute.class);
	        if ( null != attr ) {
	        	if ( !"".equals( attr.charset() ) ) {
	        		charset = attr.charset();
	        	}
	        }
        }
        try {
            return Arrays.asList( src.getBytes(charset) );
        } catch (UnsupportedEncodingException e) {
        	logger.error("StringTLVEncoder:", e);
        }
        return  null;
	}
}
