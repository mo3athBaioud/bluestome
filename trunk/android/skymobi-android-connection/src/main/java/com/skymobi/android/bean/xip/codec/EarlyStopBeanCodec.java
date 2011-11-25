/**
 * 
 */
package com.skymobi.android.bean.xip.codec;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skymobi.android.bean.bytebean.codec.BeanCodecUtil;
import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.bytebean.core.ByteFieldCodec;
import com.skymobi.android.bean.bytebean.core.ByteFieldDesc;
import com.skymobi.android.bean.bytebean.core.DecContext;
import com.skymobi.android.bean.bytebean.core.DecContextFactory;
import com.skymobi.android.bean.bytebean.core.DecResult;
import com.skymobi.android.bean.bytebean.core.EncContext;
import com.skymobi.android.bean.bytebean.core.EncContextFactory;
import com.skymobi.android.bean.bytebean.core.Field2Desc;
import com.skymobi.android.bean.bytebean.core.FieldCodecCategory;

/**
 * @author isdom
 *
 */
public class EarlyStopBeanCodec implements BeanFieldCodec {

    private static final Logger logger = LoggerFactory.getLogger(EarlyStopBeanCodec.class);
    
	private	DecContextFactory	decContextFactory;
	private	EncContextFactory	encContextFactory;
	
    @SuppressWarnings("unused")
	private Class<?>        beanClass;
    
    private BeanCodecUtil   util;
    
    public EarlyStopBeanCodec(Class<?> beanClass, Field2Desc field2Desc) {
        this.beanClass = beanClass;
        util = new BeanCodecUtil(field2Desc);
    }
    
    public DecResult decode(DecContext ctx) {
        byte[]  bytes   = ctx.getDecBytes();
        Class<?>  clazz = ctx.getDecClass();
        ByteFieldDesc selfDesc = ctx.getFieldDesc();
        if ( null != selfDesc ) {
            Class<?> customClass = selfDesc.getCustomType( ctx.getDecOwner() );
            if ( null != customClass) {
                clazz = customClass;
            }
        }
        Object target = null;

        try {
            target = clazz.newInstance();
            
            List<ByteFieldDesc> desces = util.getFieldDesces(clazz);
            
            ByteFieldCodec  anyCodec = ctx.getCodecOf(FieldCodecCategory.ANY);
            
            for ( ByteFieldDesc desc : desces ) {
                if ( 0 == bytes.length ) {
                    //  瑙ｇ爜鎵�敤鐨勫瓧鑺傛祦宸插埌缁撴潫浣嶇疆
//                    if ( logger.isDebugEnabled() ) {
//                        logger.debug( "bytes2Compound: encounter end of decode bytes stream.");
//                    }
                    break;
                }
                
                Field   field = desc.getField();
                
                Class<?> fieldClass = field.getType();
                
//                if ( logger.isDebugEnabled() ) {
//                    logger.debug( "bytes2Compound: filling Field[" + field.toString() + "]");
//                }
                DecResult ret = anyCodec.decode(
                		decContextFactory.createDecContext(
                				bytes, fieldClass, target, desc) );
//                        new DefaultDecContext()
//                        .setCodecProvider(ctx)
//                        .setDecBytes(bytes)
//                        .setDecClass(fieldClass)
//                        .setFieldDesc(desc)
//                        .setNumberCodec(ctx.getNumberCodec())
//                        .setDecOwner(target));
                
                Object fieldValue = ret.getValue();
                bytes = ret.getRemainBytes();
                
                field.setAccessible(true);
                field.set(target, fieldValue);
//                if (logger.isDebugEnabled()) {
//                    logger.debug( "bytes2Compound: filled Field[" + field.toString() 
//                            + "] with value:" + fieldValue );
//                }
            }
            
        } catch (InstantiationException e) {
            logger.error( "BeanCodec:", e);
        } catch (IllegalAccessException e) {
            logger.error( "BeanCodec:", e);
        }
        
        return  new DecResult( target, bytes);
    }

    public byte[] encode(EncContext ctx) {
        Object bean = ctx.getEncObject();
        if ( null == bean ) {
            String  errmsg = "EarlyStopBeanCodec: bean is null";
            if ( null != ctx.getField() ) {
                errmsg += "/ cause field is [" + ctx.getField() + "]";
            }
            else {
                errmsg += "/ cause type is [" + ctx.getEncClass() + "]";
            }
            logger.error( errmsg );
            throw new RuntimeException(errmsg);
            //return  new byte[0];
        }
        List<ByteFieldDesc> desces = util.getFieldDesces( bean.getClass() );
        byte[]  ret = null;
        ByteFieldCodec  anyCodec = ctx.getCodecOf(FieldCodecCategory.ANY);
        
        for ( ByteFieldDesc desc : desces ) {
            Field   field = desc.getField();
            Class<?> fieldClass = field.getType();
            field.setAccessible(true);
            Object fieldValue = null;
            
            try {
                fieldValue = field.get(bean);
            } catch (IllegalArgumentException e) {
                logger.error( "BeanCodec:", e);
            } catch (IllegalAccessException e) {
                logger.error( "BeanCodec:", e);
            }

            if ( null == fieldValue ) {
//                if ( logger.isDebugEnabled() ) {
//                    logger.debug( "compound2Bytes: field ["+ field.getName() +"] value is null" );
//                }
            }
            else {
//                if ( fieldValue instanceof ByteBean ) {
//                    ((ByteBean)fieldValue).setXipProtocol(bean.getXipProtocol());
//                }
            }
            
//            if ( logger.isDebugEnabled() ) {
//                logger.debug( "compound2Bytes: Field[" + field.toString() + "]"
//                        + ", value:" + fieldValue);
//            }
            
            ret = (byte[]) ArrayUtils.addAll(ret, 
                    anyCodec.encode(
                    		encContextFactory.createEncContext(
                    				fieldValue, fieldClass, desc) ) );
//                            new DefaultEncContext()
//                            .setCodecProvider(ctx)
//                            .setEncObject(fieldValue)
//                            .setEncClass(fieldClass)
//                            .setFieldDesc(desc)
//                            .setNumberCodec(ctx.getNumberCodec())));
        }
        
        return  ret;
    }

    public FieldCodecCategory getCategory() {
        return FieldCodecCategory.BEAN;
    }

    public int getStaticByteSize(Class<?> clazz) {
    	
        List<ByteFieldDesc> desces = util.getFieldDesces(clazz);
        
        if ( null == desces || desces.isEmpty() ) {
        	return	-1;
        }
        
        int staticByteSize = 0;
        
        for ( ByteFieldDesc desc : desces ) {
            int fieldByteSize = desc.getByteSize();
            
            if ( fieldByteSize <= 0 ) {
            	fieldByteSize = getStaticByteSize( desc.getFieldType() );
            }
            
            if ( fieldByteSize <= 0 ) {
                return  -1;
            }
            staticByteSize += fieldByteSize;
        }
            
        return  staticByteSize;
    }

    public Class<?>[] getFieldType() {
        return null;
    }

	public DecContextFactory getDecContextFactory() {
		return decContextFactory;
	}

	public void setDecContextFactory(DecContextFactory decContextFactory) {
		this.decContextFactory = decContextFactory;
	}

	public EncContextFactory getEncContextFactory() {
		return encContextFactory;
	}

	public void setEncContextFactory(EncContextFactory encContextFactory) {
		this.encContextFactory = encContextFactory;
	}
}
