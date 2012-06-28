/**
 * 
 */
package com.skymobi.android.transport.protocol.esb.codec;

import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.protocol.esb.ESBFixedHeader;
import com.skymobi.android.util.ByteUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @author isdom
 *
 */
public class MinaEsbDecoder extends CumulativeProtocolDecoder {
	
    private static final Logger logger = 
    	LoggerFactory.getLogger(MinaEsbDecoder.class);
    
    private final AttributeKey FIXED_HEADER = 
    	new AttributeKey(getClass(), "EsbFixedHeader");

    private	BeanFieldCodec 		esbBeanCodec;
    private Int2TypeMetainfo 	esbTypeMetainfo;
    
    private	int					maxMessageLength = -1;
    
    private int dumpBytes = 256;
    private	boolean	isDebugEnabled;
    
	/* (non-Javadoc)
     * @see org.apache.mina.filter.codec.CumulativeProtocolDecoder#doDecode(org.apache.mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer, org.apache.mina.filter.codec.ProtocolDecoderOutput)
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        
    	ESBFixedHeader fixedHdr = (ESBFixedHeader)session.getAttribute(FIXED_HEADER);
    	
        if ( null == fixedHdr ) {
            if ( in.remaining() < ESBFixedHeader.FIXED_HEADER_SIZE ) {
                return false;
            }
            else {
                if ( logger.isInfoEnabled() && isDebugEnabled ) {
                    logger.info("parse header... try parse...");
                }
                byte[] headerBytes = new byte[ESBFixedHeader.FIXED_HEADER_SIZE];
                in.get(headerBytes);
                
                if ( logger.isInfoEnabled() && isDebugEnabled) {
                    logger.info("header raw bytes \r\n"+ 
                    		ByteUtils.bytesAsHexString(headerBytes, dumpBytes) );
                }
                
                
            	fixedHdr = EsbSignalCodecUtils.decodeSignalHeader(headerBytes, esbBeanCodec);
            	
            	if ( null == fixedHdr ) {
            		logger.error( " Can't decode esb's fixed header" );
            		return	false;
            	}
            	
                if ( logger.isInfoEnabled() && isDebugEnabled) {
                    logger.info("fixed header:" + fixedHdr);
                }
                
                if ( maxMessageLength > 0 ) {
                	if ( fixedHdr.getLength() > maxMessageLength ) {
                		logger.error( "header.length "+ByteUtils.bytesAsHexString(headerBytes, dumpBytes)+" exceed maxMessageLength "+fixedHdr.getLength()+", "
                				+ " so drop this connection.\r\ndump bytes received:"+maxMessageLength+"\r\n");
                		session.close(true);
                		return	false;
                	}
                }
                
                // Update the session attribute.
                recordFixedHeader(session, fixedHdr);
            }
        }
        
        int bodyLength = fixedHdr.getLength() - ESBFixedHeader.FIXED_HEADER_SIZE;
        if ( in.remaining() < bodyLength ) {
            return false;
        }
        else {
            //  涓轰笅涓�鍦ㄥ悓涓�ession涓婅繘琛寈ip鎺ュ彈鍒濆鍖栫幆澧�
            removeSessionHeaders(session);
            
            byte[] bytes = new byte[bodyLength];
            in.get(bytes);
            
            if ( logger.isInfoEnabled() && isDebugEnabled) {
                logger.info("body raw bytes "+ByteUtils.bytesAsHexString(bytes, dumpBytes)+"\r\n");
            }
            
            EsbHeaderable signal = EsbSignalCodecUtils.decodeSignalBody(
            		fixedHdr, bytes, esbBeanCodec, esbTypeMetainfo);
            
            if ( null != signal ) {
                if ( logger.isDebugEnabled() && isDebugEnabled) {
                    logger.info("session "+session+" \r\nrecv signal:"+signal);
                }
                
                out.write( signal );
                
                return true;
            }
            else {
	            if ( logger.isInfoEnabled() && isDebugEnabled) {
	                logger.info("can't decode esb signal body, " +
	                		"maybe unknow protocol code "+fixedHdr.getProtocolCode() +"\r\n");
	            }
	            
	        	return	false;
            }
        }
    }

	@Override
    public void dispose(IoSession session) throws Exception {
        super.dispose(session);
        
        //  our dispose
        removeSessionHeaders(session);
    }
    
    private void recordFixedHeader(IoSession session, ESBFixedHeader header) {
        session.setAttribute(FIXED_HEADER, header);
    }
    
    private void removeSessionHeaders(IoSession session) {
        session.removeAttribute(FIXED_HEADER);
    }

    /**
     * @param dumpBytes the dumpBytes to set
     */
    public void setDumpBytes(int dumpBytes) {
        this.dumpBytes = dumpBytes;
    }
    
	public int getDumpBytes() {
		return dumpBytes;
	}

	public boolean isDebugEnabled() {
		return isDebugEnabled;
	}

	public void setDebugEnabled(boolean isDebugEnabled) {
		this.isDebugEnabled = isDebugEnabled;
	}

	/**
	 * @return the esbBeanCodec
	 */
	public BeanFieldCodec getEsbBeanCodec() {
		return esbBeanCodec;
	}

	/**
	 * @param esbBeanCodec the esbBeanCodec to set
	 */
	public void setEsbBeanCodec(BeanFieldCodec esbBeanCodec) {
		this.esbBeanCodec = esbBeanCodec;
	}

	/**
	 * @return the esbTypeMetainfo
	 */
	public Int2TypeMetainfo getEsbTypeMetainfo() {
		return esbTypeMetainfo;
	}

	/**
	 * @param esbTypeMetainfo the esbTypeMetainfo to set
	 */
	public void setEsbTypeMetainfo(Int2TypeMetainfo esbTypeMetainfo) {
		this.esbTypeMetainfo = esbTypeMetainfo;
	}

	/**
	 * @return the maxMessageLength
	 */
	public int getMaxMessageLength() {
		return maxMessageLength;
	}

	/**
	 * @param maxMessageLength the maxMessageLength to set
	 */
	public void setMaxMessageLength(int maxMessageLength) {
		this.maxMessageLength = maxMessageLength;
	}

}
