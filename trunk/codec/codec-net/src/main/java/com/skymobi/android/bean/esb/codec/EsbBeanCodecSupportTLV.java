/**
 * 
 */
package com.skymobi.android.bean.esb.codec;

import com.skymobi.android.bean.bytebean.codec.BeanCodecUtil;
import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.bytebean.core.ByteFieldCodec;
import com.skymobi.android.bean.bytebean.core.ByteFieldDesc;
import com.skymobi.android.bean.bytebean.core.DecContext;
import com.skymobi.android.bean.bytebean.core.DecContextFactory;
import com.skymobi.android.bean.bytebean.core.DecResult;
import com.skymobi.android.bean.bytebean.core.EncContext;
import com.skymobi.android.bean.bytebean.core.EncContextFactory;
import com.skymobi.android.bean.bytebean.core.FieldCodecCategory;
import com.skymobi.android.bean.esb.core.EsbFieldDesc;
import com.skymobi.android.bean.esb.core.EsbFieldToDesc;
import com.skymobi.android.bean.esb.core.EsbFieldToDescForTLV;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
import com.skymobi.android.bean.esb.core.EsbMutableHeaderable;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;
import com.skymobi.android.bean.tlv.decode.TLVDecodeContext;
import com.skymobi.android.bean.tlv.decode.TLVDecoder;
import com.skymobi.android.bean.tlv.decode.TLVDecoderOfBean;
import com.skymobi.android.bean.tlv.decode.decoders.BeanTLVDecoder;
import com.skymobi.android.bean.tlv.encode.TLVEncoderOfBean;
import com.skymobi.android.bean.tlv.encode.encoders.BeanTLVEncoder;
import com.skymobi.android.bean.tlv.meta.TLVCodecUtils;
import com.skymobi.android.bean.tlv.meta.TLVFieldMetainfo;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.factory.AccessDecContextFactory;
import com.skymobi.android.transport.factory.AccessEncContextFactory;
import com.skymobi.android.util.ByteUtils;
import com.skymobi.android.util.FieldUtils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author isdom
 *
 */
public class EsbBeanCodecSupportTLV implements BeanFieldCodec {
    public static final String TLV_LENGTH_KEY = "_TLV_LENGTH_SIZE";

    //ESBAccess2ModuleHeader中的tlv tag=2字节 length=2字节 不能变 需要和ACCESS保存一致
    private static final int TAG_LEN_BYTESIZE = 2;

    private static final Logger logger =
            LoggerFactory.getLogger(EsbBeanCodecSupportTLV.class);

    private DecContextFactory decContextFactory = AccessDecContextFactory.getInstance();
    private EncContextFactory encContextFactory = AccessEncContextFactory.getInstance();

    private BeanCodecUtil util;
    private BeanCodecUtil utilForTLV;
    private TLVEncoderOfBean tlvEncoderOfBean;
    private TLVDecoderOfBean tlvDecoderOfBean;

    public EsbBeanCodecSupportTLV() {
        util = new BeanCodecUtil(new EsbFieldToDesc());
        utilForTLV = new BeanCodecUtil(new EsbFieldToDescForTLV());
        
        tlvEncoderOfBean = new BeanTLVEncoder();
        tlvDecoderOfBean = new BeanTLVDecoder();
    }

    public static EsbFieldDesc tag2desc(List<ByteFieldDesc> desces, int tag) {
        for (ByteFieldDesc desc : desces) {
            if (((EsbFieldDesc) desc).getTag() == tag) {
                return (EsbFieldDesc) desc;
            }
        }

        return null;
    }

    public static Class<?> desc2class(EsbFieldDesc desc) {
        Class<?> fieldType = desc.getFieldType();

        return fieldType.equals(ArrayList.class)
                ? FieldUtils.getComponentClass(desc.getField())
                : fieldType;
    }

    public DecResult decode(DecContext ctx) {
        byte[] bytes = ctx.getDecBytes();
        Class<?> clazz = ctx.getDecClass();
        ByteFieldDesc selfDesc = ctx.getFieldDesc();
        if (null != selfDesc) {
            Class<?> customClass = selfDesc.getCustomType(ctx.getDecOwner());
            if (null != customClass) {
                clazz = customClass;
            }
        }
        Object target = null;

        try {
            target = clazz.newInstance();

            if (target instanceof EsbMutableHeaderable) {
                EsbHeaderable hdr =
                        (EsbHeaderable) ctx.getProperty(EsbHeaderable.class.getSimpleName());
                if (null != hdr) {
                    EsbMutableHeaderable signal = (EsbMutableHeaderable) target;

                    signal.setSrcESBAddr(hdr.getSrcESBAddr());
                    signal.setDstESBAddr(hdr.getDstESBAddr());
                    signal.setSeqNum(hdr.getSeqNum());
                    signal.setResult(hdr.getResult());
                    signal.setFlags(hdr.getFlags());
                }
            }

            List<ByteFieldDesc> offDesces = util.getFieldDesces(clazz);

            ByteFieldCodec anyCodec = ctx.getCodecOf(FieldCodecCategory.ANY);

            for (ByteFieldDesc desc : offDesces) {
                if (0 == bytes.length) {
                    break;
                }

                Field field = desc.getField();

                Class<?> fieldClass = field.getType();

                DecResult ret = anyCodec.decode(
                        decContextFactory.createDecContext(
                                bytes, fieldClass, target, desc));

                Object fieldValue = ret.getValue();
                bytes = ret.getRemainBytes();

                field.setAccessible(true);
                field.set(target, fieldValue);
            }

            //	for esb header tlv support
            List<ByteFieldDesc> tlvDesces = utilForTLV.getFieldDesces(clazz);

            if ((null != tlvDesces) && !tlvDesces.isEmpty()) {
                while (bytes.length >= 2 * TAG_LEN_BYTESIZE) {
                    bytes = decodeEsbTlv(ctx, bytes, target, anyCodec, tlvDesces);
                }
            }

            // for normal tlv support
            Field[] fields = TLVCodecUtils.getTLVFieldsOf(clazz);
            if (bytes.length >= 8 && fields.length > 0) {
                bytes = decodeTlv(tlvDecoderOfBean.getDecodeContextFactory().createDecodeContext(
                        clazz, null), bytes, target);
                if(bytes.length > 0){
                    logger.warn("decoder remain "+bytes.length+" bytes,maybe a bug?");
                }
            }

        } catch (InstantiationException e) {
            logger.error("EsbBeanCodecSupportTLV:", e);
        } catch (IllegalAccessException e) {
            logger.error("EsbBeanCodecSupportTLV:", e);
        } catch (Exception e) {
            logger.error("EsbBeanCodecSupportTLV:", e);
        }

        return new DecResult(target, bytes);
    }

    private byte[] decodeTlv(TLVDecodeContext ctx, byte[] tlvValue, Object target) {
        int offset = 0;
        while (offset + 8 <= tlvValue.length) {
            byte[] tagBytes = ArrayUtils.subarray(tlvValue, offset, offset + 4);
            int tag = ctx.getNumberCodec().bytes2Int(tagBytes, 4);

            offset += 4;
            byte[] lenBytes = ArrayUtils.subarray(tlvValue, offset, offset + 4);
            int len = ctx.getNumberCodec().bytes2Int(lenBytes, 4);
            offset += 4;

            Int2TypeMetainfo typeMetainfo = ctx.getTypeMetainfo();
            Class<?> type = typeMetainfo.find(tag);
            if (null == type) {
                // unknow tag, just ignore
                logger.info("unknow tag:" + tag + ", just ignore.");
                offset += len;
                continue;
            }

            TLVDecoder decoder = ctx.getDecoderRepository().getDecoderOf(type);
            if (null == decoder) {
                // unknow tag, just ignore
                logger.info("unknow decoder for [tag]:" + tag + ",[type]:" + type + " just ignore.");
                offset += len;
                continue;
            }

            byte[] valueBytes = ArrayUtils.subarray(tlvValue, offset, offset + len);
            offset += len;

            TLVFieldMetainfo fieldMetainfo = ctx.getFieldMetainfo();
            Field field = fieldMetainfo.get(tag);
            Object bean = null;
            try {
                bean = decoder.decode(len, valueBytes,
                        tlvDecoderOfBean.getDecodeContextFactory().createDecodeContext(type, field));
            } catch (RuntimeException e) {
                logger.error("Decode tag "+new String(Hex.encodeHex(tagBytes)).toUpperCase()+"("+tag+") error!");
                throw e;
            }

            TLVAttribute param = field.getAnnotation(TLVAttribute.class);
            Class<?> fieldType = param.type();
            if (TLVAttribute.class.equals(fieldType)) {
                fieldType = field.getType();
            }
            if (fieldType.equals(ArrayList.class)) {
                try {
                    ArrayList<Object> list = (ArrayList<Object>) field.get(target);
                    if (null == list) {
                        list = new ArrayList<Object>();
                        field.set(target, list);
                    }
                    list.add(bean);
                } catch (IllegalArgumentException e) {
                    logger.error("BeanTLVDecoder:", e);
                } catch (IllegalAccessException e) {
                    logger.error("BeanTLVDecoder:", e);
                }
            } else {
                try {
                    field.set(target, bean);
                } catch (IllegalArgumentException e) {
                    logger.error("BeanTLVDecoder:", e);
                } catch (IllegalAccessException e) {
                    logger.error("BeanTLVDecoder:", e);
                }
            }
        }

        return ArrayUtils.subarray(tlvValue, offset,tlvValue.length);
    }

    private byte[] decodeEsbTlv(DecContext ctx, byte[] bytes, Object target,
                                ByteFieldCodec anyCodec, List<ByteFieldDesc> tlvDesces) {
        int tag = ctx.getNumberCodec().bytes2Int(bytes, TAG_LEN_BYTESIZE);

        bytes = ArrayUtils.subarray(bytes, TAG_LEN_BYTESIZE, bytes.length);

        int len = ctx.getNumberCodec().bytes2Int(bytes, TAG_LEN_BYTESIZE);

        byte[] bytesValue = ArrayUtils.subarray(bytes, TAG_LEN_BYTESIZE, TAG_LEN_BYTESIZE + len);

        bytes = ArrayUtils.subarray(bytes, TAG_LEN_BYTESIZE + len, bytes.length);

        EsbFieldDesc desc = tag2desc(tlvDesces, tag);

        if (null != desc) {
            DecContext valueCtx = ctx.getDecContextFactory().createDecContext(
                    bytesValue, desc2class(desc), ctx.getDecOwner(), desc);

            valueCtx.setProperty(TLV_LENGTH_KEY, new Integer(len));

            DecResult ret = anyCodec.decode(valueCtx);

            Field field = desc.getField();

            field.setAccessible(true);
            Class<?> fieldType = desc.getFieldType();
            if (fieldType.equals(ArrayList.class)) {
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Object> list = (ArrayList<Object>) field.get(target);
                    if (null == list) {
                        list = new ArrayList<Object>();
                        field.set(target, list);
                    }
                    list.add(ret.getValue());
                } catch (IllegalArgumentException e) {
                    logger.error("EsbBeanCodecSupportTLV:", e);
                } catch (IllegalAccessException e) {
                    logger.error("EsbBeanCodecSupportTLV:", e);
                }
            } else {
                try {
                    //当字符串长度为零时需要特殊处理 其余数据类型忽略
                    if (ret.getValue() == null && fieldType.equals(String.class)) {
                        field.set(target, "");
                    } else {
                        field.set(target, ret.getValue());
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("EsbBeanCodecSupportTLV:", e);
                } catch (IllegalAccessException e) {
                    logger.error("EsbBeanCodecSupportTLV:", e);
                }
            }
        } else {
            //	skip unknow tag
            logger.warn("EsbBeanCodecSupportTLV: unknown tag: "+tag+", just skip len: "+len);
        }

        return bytes;
    }

    private byte[] encodeEsbTLV(
            EncContext ctx,
            ByteFieldCodec anyCodec,
            Object fieldValue,
            Class<?> fieldType,
            ByteFieldDesc desc) {
        byte[] ret = null;
        byte[] bytesValue = anyCodec.encode(
                encContextFactory.createEncContext(
                        fieldValue, fieldType, desc));
        if (null != bytesValue) { //允许发送长度为零的属性值
            //	tag
            byte[] bytesTag = ctx.getNumberCodec().int2Bytes(
                    ((EsbFieldDesc) desc).getTag(), TAG_LEN_BYTESIZE);

            //	len
            byte[] bytesLen = ctx.getNumberCodec().int2Bytes(
                    bytesValue.length, TAG_LEN_BYTESIZE);

            ret = ArrayUtils.addAll(ret, bytesTag);
            ret = ArrayUtils.addAll(ret, bytesLen);
            ret = ArrayUtils.addAll(ret, bytesValue);
        }

        return ret;
    }

    public byte[] encode(EncContext ctx) {
        Object bean = ctx.getEncObject();
        if (null == bean) {
            String errmsg = "EarlyStopBeanCodec: bean is null";
            if (null != ctx.getField()) {
                errmsg += "/ cause field is [" + ctx.getField() + "]";
            } else {
                errmsg += "/ cause type is [" + ctx.getEncClass() + "]";
            }
            logger.error(errmsg);
            throw new RuntimeException(errmsg);
        }

        List<ByteFieldDesc> offDesces = util.getFieldDesces(bean.getClass());
        byte[] ret = null;
        ByteFieldCodec anyCodec = ctx.getCodecOf(FieldCodecCategory.ANY);

        for (ByteFieldDesc desc : offDesces) {
            Field field = desc.getField();
            Class<?> fieldClass = field.getType();
            field.setAccessible(true);
            Object fieldValue = null;

            try {
                fieldValue = field.get(bean);
            } catch (IllegalArgumentException e) {
                logger.error("EsbBeanCodecSupportTLV:", e);
            } catch (IllegalAccessException e) {
                logger.error("EsbBeanCodecSupportTLV:", e);
            }

            ret = ArrayUtils.addAll(ret,
                    anyCodec.encode(
                            encContextFactory.createEncContext(
                                    fieldValue, fieldClass, desc)));
        }

        //	for esb tlv support
        List<ByteFieldDesc> tlvDesces = utilForTLV.getFieldDesces(bean.getClass());
        for (ByteFieldDesc desc : tlvDesces) {
            Field field = desc.getField();
            field.setAccessible(true);
            Object fieldValue = null;

            try {
                fieldValue = field.get(bean);
            } catch (IllegalArgumentException e) {
                logger.error("EsbBeanCodecSupportTLV:", e);
            } catch (IllegalAccessException e) {
                logger.error("EsbBeanCodecSupportTLV:", e);
            }

            if (null == fieldValue) {
                continue;
            }

            Class<?> fieldClass = field.getType();
            if (fieldClass.equals(ArrayList.class)) {
                Class<?> componentType = FieldUtils.getComponentClass(field);
                ArrayList<Object> list = (ArrayList<Object>) fieldValue;
                for (Object component : list) {
                    byte[] bytesTLV = encodeEsbTLV(ctx, anyCodec, component, componentType, desc);
                    if (null != bytesTLV) {
                        ret = ArrayUtils.addAll(ret, bytesTLV);
                    } else {
                        logger.warn("can't encode field:"+field);
                    }
                }
            } else {
                byte[] bytesTLV = encodeEsbTLV(ctx, anyCodec, fieldValue, fieldClass, desc);
                if (null != bytesTLV) {
                    ret = ArrayUtils.addAll(ret, bytesTLV);
                } else {
                    logger.warn("can't encode field:"+field);
                }
            }
        }

        //	for normal tlv support
        byte[] tlvBytes =
                ByteUtils.union(
                        tlvEncoderOfBean.encode(bean,
                                tlvEncoderOfBean.getEncodeContextFactory().createEncodeContext(bean.getClass(), null)));
        if (null != tlvBytes) {
            ret = ArrayUtils.addAll(ret, tlvBytes);
        }
        return ret;
    }

    public FieldCodecCategory getCategory() {
        return FieldCodecCategory.BEAN;
    }

    public int getStaticByteSize(Class<?> clazz) {

        List<ByteFieldDesc> desces = util.getFieldDesces(clazz);

        if (null == desces || desces.isEmpty()) {
            return -1;
        }

        int staticByteSize = 0;

        for (ByteFieldDesc desc : desces) {
            int fieldByteSize = desc.getByteSize();

            if (fieldByteSize <= 0) {
                fieldByteSize = getStaticByteSize(desc.getFieldType());
            }

            if (fieldByteSize <= 0) {
                return -1;
            }
            staticByteSize += fieldByteSize;
        }

        return staticByteSize;
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

    public void setTlvEncoderOfBean(TLVEncoderOfBean tlvEncoderOfBean) {
        this.tlvEncoderOfBean = tlvEncoderOfBean;
    }

    public void setTlvDecoderOfBean(TLVDecoderOfBean tlvDecoderOfBean) {
        this.tlvDecoderOfBean = tlvDecoderOfBean;
    }
}
