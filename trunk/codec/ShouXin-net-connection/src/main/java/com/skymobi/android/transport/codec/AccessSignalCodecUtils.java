
package com.skymobi.android.transport.codec;

import android.skymobi.messenger.x;

import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.bytebean.core.DecContext;
import com.skymobi.android.bean.bytebean.core.DecResult;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
import com.skymobi.android.bean.esb.core.EsbTerminal2AccessSignal;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.protocol.esb.AccessFixedHeader;
import com.skymobi.android.transport.protocol.esb.AccessRespHeader;
import com.skymobi.android.transport.protocol.esb.AccessWithSeqFixedHeader;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;
import com.skymobi.android.transport.protocol.esb.signal.EsbAccess2TerminalSignal;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessReq;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessWithoutProtocolReq;
import com.skymobi.android.transport.protocol.esb.signal.RetryToAccessReq;
import com.skymobi.android.util.ByteUtils;

import org.apache.commons.lang.ArrayUtils;

import java.nio.ByteBuffer;

public class AccessSignalCodecUtils {

    private static final Logger logger =
            LoggerFactory.getLogger(AccessSignalCodecUtils.class);

    /* msgCode 定义 */
    public final static short ESB_MSG_CODE_REGISTER_ACCESS_RESP = (short) 0x9804; /*
                                                                                   * 终端接入access应答
                                                                                   * 。
                                                                                   */
    public final static short ESB_MSG_CODE_RECONN_ACCESS_RESP = (short) 0x9808; /*
                                                                                 * 终端重连access应答
                                                                                 * 。
                                                                                 */

    public static AccessFixedHeader decodeAccessHeader(
            byte[] headerBytes,
            BeanFieldCodec esbBeanCodec) throws Exception {
        if (headerBytes.length < AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE) {
            return null;
        }

        AccessFixedHeader header = null;

        if (headerBytes.length == AccessWithSeqFixedHeader.WITH_SEQ_NUM_HEADER_SIZE) {
            header = (AccessWithSeqFixedHeader) esbBeanCodec.decode(
                    esbBeanCodec.getDecContextFactory().createDecContext(
                            headerBytes, AccessWithSeqFixedHeader.class, null, null)).getValue();
        } else if (headerBytes.length == AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE) {
            header = (AccessFixedHeader) esbBeanCodec.decode(
                    esbBeanCodec.getDecContextFactory().createDecContext(
                            headerBytes, AccessFixedHeader.class, null, null)).getValue();
        } else {
            logger.warn("can't decode AccessHeader with "+headerBytes.length+" bytes");
        }
        
        return header;
    }

    
    public static EsbAccess2TerminalSignal decodeSignalBody(
            AccessFixedHeader fixedHdr,
            byte[] bodyBytes,
            BeanFieldCodec esbBeanCodec,
            Int2TypeMetainfo esbTypeMetainfo) throws Exception {
        switch (fixedHdr.getMsgCode()) {
            case ESB_MSG_CODE_REGISTER_ACCESS_RESP:
                return decodeUaResponse(fixedHdr, bodyBytes, esbBeanCodec, esbTypeMetainfo);
            case ESB_MSG_CODE_RECONN_ACCESS_RESP:
                //TODO 处理服务端返回的重连响应
                return decodeAccess2Terminal(fixedHdr, bodyBytes, esbBeanCodec, esbTypeMetainfo);
            default:
                return decodeTerminal2Access(fixedHdr, bodyBytes, esbBeanCodec, esbTypeMetainfo);
        }
    }

    private static EsbAccess2TerminalSignal decodeTerminal2Access(
            AccessFixedHeader fixedHdr,
            byte[] bytes,
            BeanFieldCodec esbBeanCodec,
            Int2TypeMetainfo esbTypeMetainfo) {
        DecResult rslt = esbBeanCodec.decode(
                esbBeanCodec.getDecContextFactory().createDecContext(
                        bytes, AccessRespHeader.class, null, null));

        AccessRespHeader a2tHdr = (AccessRespHeader) rslt.getValue();

        if (logger.isWarnEnabled()) {
            logger.debug("m2a header "+a2tHdr);
        }

        // message body bytes
        bytes = rslt.getRemainBytes();

        EsbAccess2TerminalSignal signal = null;
        Class<?> type = esbTypeMetainfo.find(fixedHdr.getMsgCode());
        if (null == type) {
            logger.error("unknow common message code for t2a:0x"
                    + Integer.toHexString(fixedHdr.getMsgCode()).toUpperCase() + "("
                    + fixedHdr.getMsgCode() + ")");
            return null;
        }

        DecContext decCtx = esbBeanCodec.getDecContextFactory().createDecContext(
                bytes, type, null, null);

        // 支持在EsbDecoder中尽可能早的进行hdr相关赋值
        decCtx.setProperty(EsbHeaderable.class.getSimpleName(), fixedHdr);

        signal = (EsbAccess2TerminalSignal) esbBeanCodec.decode(decCtx).getValue();

        return signal;
    }

    private static EsbAccess2TerminalSignal decodeAccess2Terminal(
            AccessFixedHeader fixedHdr,
            byte[] bytes,
            BeanFieldCodec esbBeanCodec,
            Int2TypeMetainfo esbTypeMetainfo) {
        EsbAccess2TerminalSignal signal = null;
        Class<?> type = esbTypeMetainfo.find(fixedHdr.getMsgCode());
        if (null == type) {
            logger.error("unknow access to terminal message code for t2a:0x"
                    + Integer.toHexString(fixedHdr.getMsgCode()).toUpperCase() + "(" + fixedHdr.getMsgCode() + ")");
            return null;
        }

        DecContext decCtx = esbBeanCodec.getDecContextFactory().createDecContext(
                bytes, type, null, null);

        //  支持在EsbDecoder中尽可能早的进行hdr相关赋值
        decCtx.setProperty(EsbHeaderable.class.getSimpleName(), fixedHdr);

        signal = (EsbAccess2TerminalSignal) esbBeanCodec.decode(decCtx).getValue();

        return signal;
    }
    
    /**
     * UA响应解码
     */
    private static EsbAccess2TerminalSignal decodeUaResponse(
            AccessFixedHeader fixedHdr,
            byte[] bytes,
            BeanFieldCodec esbBeanCodec,
            Int2TypeMetainfo esbTypeMetainfo) {
        //UA重定向
        if(bytes[0] == 2){
            fixedHdr.setMsgCode(0x98042);
        }
        return decodeAccess2Terminal(fixedHdr, bytes, esbBeanCodec, esbTypeMetainfo);
    }
    
    public static byte[] encodeSignal(
            EsbHeaderable signal,
            BeanFieldCodec esbBeanCodec,
            int myESBAddr) throws Exception {

        if (null == signal || !signal.checkIntegrity()) {
            logger.error("invalid signal, signal is null or checkIntegrity failed");
            return null;
        }

        byte[] bytes = null;
        if (signal instanceof EsbTerminal2AccessSignal) {
            bytes = encodeTerminal2Access((EsbTerminal2AccessSignal) signal, esbBeanCodec,
                    myESBAddr);
        } else {
            logger.error("encode: bean " + signal+ " is not EsbTerminal2AccessSignal.");
        }

        return bytes;
    }

    private static byte[] encodeTerminal2Access(
            EsbTerminal2AccessSignal signal,
            BeanFieldCodec esbBeanCodec,
            int myESBAddr) throws Exception {
        // once
        if (signal instanceof RegisterToAccessReq) {
            RegisterToAccessReq req = (RegisterToAccessReq) signal;
            req.setLength((short) (73 + 18 + req.getHsman().length() +
                    req.getHstype().length() - 7));
        }
        
        // 不带7个字节握手协议头的UA注册
        if (signal instanceof RegisterToAccessWithoutProtocolReq) {
            RegisterToAccessWithoutProtocolReq req = (RegisterToAccessWithoutProtocolReq) signal;
            req.setLength((short) (73 + 18 + req.getHsman().length() +
                    req.getHstype().length() - 7));
        }
        
        // 2012-02-29 设置重连请求包大小，需要将协议头中的7个字节去掉
        if(signal instanceof RetryToAccessReq){
            RetryToAccessReq req = (RetryToAccessReq) signal;
            req.setLength((short) (25 - 7));
        }

        //正文
        byte[] bytesBody =
                esbBeanCodec.encode(
                        esbBeanCodec.getEncContextFactory().createEncContext(
                                signal, signal.getClass(), null));
        
        if (signal instanceof RegisterToAccessReq || signal instanceof RegisterToAccessWithoutProtocolReq || signal instanceof RetryToAccessReq) {
            return bytesBody;
        }

        int bodySize = (null != bytesBody) ? bytesBody.length : 0;
        if (0 == bodySize) {
            logger.debug("\t>>>>>> invalid esb signal content, no body.");
        }
        
        EsbSignal attr = signal.getClass().getAnnotation(EsbSignal.class);
        if (null == attr) {
            logger.debug("\t>>>>>> invalid esb signal, bcs of no messageCode.");
        }

        // create esb terminal2access header
        AccessWithSeqFixedHeader fixedHdr = new AccessWithSeqFixedHeader();
        fixedHdr.setDstESBAddr(signal.getDstESBAddr());
        fixedHdr.setFlags(signal.getFlags());
        fixedHdr.setMsgCode((short) attr.messageCode());
        fixedHdr.setSeqNum(signal.getSeqNum());
        fixedHdr.setAck(signal.getAck());
        fixedHdr.setSrcESBAddr(((0 == signal.getSrcESBAddr()) ? myESBAddr : signal
                .getSrcESBAddr()));

        byte[] bytesHeader = esbBeanCodec.encode(
                        esbBeanCodec.getEncContextFactory().createEncContext(
                                fixedHdr, AccessFixedHeader.class, null));
        
        if(signal instanceof AbstractEsbT2ASignal && ((AbstractEsbT2ASignal) signal).getEncryptKey() != 0){
            int length = AccessFixedHeader.COMMON_HEADER_SIZE + bodySize+10;
            //需要修改为动态的字节数组
            ByteBuffer byteBuf = ByteBuffer.allocate(length);
            //将头的前8位放入字节缓存中
            byteBuf.put(bytesHeader, 0, AccessWithSeqFixedHeader.COMMON_HEADER_SIZE);
            //encrypt body
            byte[] encryptKey = ByteBuffer.allocate(4)
                    .putInt(((AbstractEsbT2ASignal) signal).getEncryptKey()).array();
            byte[] in = ArrayUtils
                    .addAll(ByteUtils.copyOfRange(bytesHeader,
                            AccessWithSeqFixedHeader.COMMON_HEADER_SIZE, bytesHeader.length),
                            bytesBody);
            bytesBody = x.e(encryptKey, in);
            byteBuf.put(bytesBody);
            byteBuf.putShort(4,(short)(length));
            byte[] body = byteBuf.array();
            return body;
        }else{
            int length = AccessFixedHeader.FIXED_HEADER_SIZE + bodySize;
            //需要修改为动态的字节数组
            ByteBuffer byteBuf = ByteBuffer.allocate(length);
            byteBuf.put(bytesHeader);
            byteBuf.put(bytesBody);
            byteBuf.putShort(4,(short)length);
            byte[] body = byteBuf.array();
            return body;
        }
    }
}
