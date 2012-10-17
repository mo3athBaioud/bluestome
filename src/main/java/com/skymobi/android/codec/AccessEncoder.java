
package com.skymobi.android.codec;

import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.notify.INetClientNotify;
import com.skymobi.android.transport.codec.AccessSignalCodecUtils;
import com.skymobi.android.transport.protocol.esb.codec.EsbSignalCodecUtils;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;
import com.skymobi.android.util.ByteUtils;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class AccessEncoder implements ProtocolEncoder {

    private static final Logger logger = LoggerFactory.getLogger(AccessEncoder.class);

    private int dumpBytes = 256;
    private boolean isDebugEnabled;
    private BeanFieldCodec esbBeanCodec;

    private int myESBAddr;
    private INetClientNotify notifys;

    //密钥
    private int encryptKey;
    public AccessEncoder(INetClientNotify notify) {
        esbBeanCodec = new EsbBeanCodecSupportTLV();
        isDebugEnabled = true;
        this.notifys = notify;
    }

    /**
     * @return the myESBAddr
     */
    public int getMyESBAddr() {
        return myESBAddr;
    }

    /**
     * @param myESBAddr the myESBAddr to set
     */
    public void setMyESBAddr(int myESBAddr) {
        this.myESBAddr = myESBAddr;
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

    @Override
    public void encode(IoSession session, Object msg, ProtocolEncoderOutput out)
            throws Exception {
        logger.debug("\t>>>>>> encode.session:"+session);
        logger.debug("\t>>>>>> AccessEncoder.doEncode : " + encryptKey);
        byte[] bytes = null;
        try {
            if (msg instanceof AbstractEsbT2ASignal) {
                bytes = AccessSignalCodecUtils.encodeSignal(
                        (EsbHeaderable) msg, esbBeanCodec, myESBAddr);
            } else {
                bytes = EsbSignalCodecUtils.encodeSignal(
                        (EsbHeaderable) msg, esbBeanCodec, myESBAddr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != bytes) {
            int length = bytes.length;
            // 添加统计流量
            notifys.addByteLength(length);
            logger.info("\t>>>>>>> encoder.bytes:" + ByteUtils.bytesAsHexString(bytes,length));
            out.write(IoBuffer.wrap(bytes));
        }
    }

    public boolean isDebugEnabled() {
        return isDebugEnabled;
    }

    public void setDebugEnabled(boolean isDebugEnabled) {
        this.isDebugEnabled = isDebugEnabled;
    }

    public int getDumpBytes() {
        return dumpBytes;
    }

    /**
     * @param dumpBytes the dumpBytes to set
     */
    public void setDumpBytes(int dumpBytes) {
        this.dumpBytes = dumpBytes;
    }

    @Override
    public void dispose(IoSession session) throws Exception {
        logger.debug("encoder.dispose");
    }

    public INetClientNotify getNotifys() {
        return notifys;
    }

    public void setNotifys(INetClientNotify notifys) {
        this.notifys = notifys;
    }

    /**
     * @param encryptKey the encryptKey to set
     */
    public void setEncryptKey(int encryptKey) {
        this.encryptKey = encryptKey;
    }

}
