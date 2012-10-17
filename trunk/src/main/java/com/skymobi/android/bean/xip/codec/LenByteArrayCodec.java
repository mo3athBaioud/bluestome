/**
 * 
 */
package com.skymobi.android.bean.xip.codec;

import com.skymobi.android.bean.bytebean.core.ByteFieldCodec;
import com.skymobi.android.bean.bytebean.core.DecContext;
import com.skymobi.android.bean.bytebean.core.DecContextFactory;
import com.skymobi.android.bean.bytebean.core.DecResult;
import com.skymobi.android.bean.bytebean.core.EncContext;
import com.skymobi.android.bean.bytebean.core.EncContextFactory;
import com.skymobi.android.bean.bytebean.core.FieldCodecCategory;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author isdom
 *
 */
public class LenByteArrayCodec implements ByteFieldCodec {

    private static final Logger logger = LoggerFactory.getLogger(LenByteArrayCodec.class);
    
//	private	DecContextFactory	decContextFactory;
//	private	EncContextFactory	encContextFactory;
	
    public DecContextFactory getDecContextFactory() {
		return null;	//decContextFactory;
	}

	public void setDecContextFactory(DecContextFactory decContextFactory) {
//		this.decContextFactory = decContextFactory;
	}

	public EncContextFactory getEncContextFactory() {
		return null;	//encContextFactory;
	}

	public void setEncContextFactory(EncContextFactory encContextFactory) {
//		this.encContextFactory = encContextFactory;
	}
	
    public DecResult decode(DecContext ctx) {
        DecResult ret = ctx.getCodecOf(int.class).decode(
        		ctx.getDecContextFactory().createDecContext(
        				ctx.getDecBytes(), int.class, ctx.getDecOwner(), null) );
        int arrayLength = (Integer)ret.getValue();
        byte[] bytes = ret.getRemainBytes();
        
        if ( bytes.length < arrayLength ) {
            String  errmsg = "ByteArrayCodec: not enough bytes for decode, need [" + arrayLength 
                            + "], actually [" + bytes.length +"].";
            if ( null != ctx.getField() ) {
                errmsg += "/ cause field is [" + ctx.getField() + "]";
            }
            logger.error(errmsg);
            throw   new RuntimeException(errmsg);
            //return makeDecResult( new byte[0], bytes );
        }
        
        return  new DecResult( (byte[])ArrayUtils.subarray(bytes, 0, arrayLength), 
                ArrayUtils.subarray(bytes, arrayLength, bytes.length));
    }

    public byte[] encode(EncContext ctx) {
        byte[] array = (byte[])ctx.getEncObject();
        
        return  (byte[])ArrayUtils.addAll( 
                    ctx.getCodecOf(int.class).encode(
                    		ctx.getEncContextFactory().createEncContext(
                    				(int)( null == array ? 0 : array.length ), 
                    				int.class, null) )
                    , array);
    }

    public Class<?>[] getFieldType() {
        return new Class<?>[]{ byte[].class };
    }

    public FieldCodecCategory getCategory() {
        return null;
    }
}
