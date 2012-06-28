
package com.skymobi.android.codec;

import android.skymobi.messenger.x;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.notify.INetClientNotify;
import com.skymobi.android.transport.protocol.esb.AccessFixedHeader;
import com.skymobi.android.util.ByteUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class AccessDecoder extends CumulativeProtocolDecoder {

    private static final Logger logger = LoggerFactory.getLogger(AccessDecoder.class);
    
    // 大于1M的数据包不建议走ESB
    private int maxMessageLength = 1024 * 1024;

    private int dumpBytes = 256;
    private boolean isDebugEnabled;
    private INetClientNotify notifys;
    //密钥
    private int encryptKey;

    public AccessDecoder(INetClientNotify notifys) {
        // TODO SET 类型
        isDebugEnabled = true;
        this.notifys = notifys;
    }
    
    

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        byte[] body = null;
        logger.debug("\t>>>>>> decode.session:"+session);
        logger.debug("\t>>>>>> AccessEncoder.doDecode : " + encryptKey);
        if(!in.hasRemaining()){
            logger.debug("\t>>>>>> no data");
            return false;
        }
        while(in.remaining() > 0){
            if (in.remaining() < AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE) {
                return false;
            }
    
            // 第4字节开始取2个字节内容，该内容为包长度
            short packageLength = in.getShort(in.position()+4);
            if (packageLength > maxMessageLength || packageLength < 0) {
                logger.error("\t>>>>>> 包长度超过["+maxMessageLength+"]或者小于规定的大小[0]");
                return false;
            }
            
            logger.debug("\t>>>>>>> decoder.packageLength:"+packageLength);
            // TODO 新增流量统计
            notifys.addByteLength(packageLength);
            // 长度不足
            if (in.remaining() < packageLength) {
                return false;
            }
            // 设置包的大小
            byte[] pack = new byte[packageLength];
            in.get(pack);
            
            logger.debug("\t>>>>>>> before encrypted decoder.bytes:"+ByteUtils.bytesAsHexString(pack,pack.length));
            logger.debug("\t>>>>>>> encryptKey:"+encryptKey +"| other.condition:"+(packageLength > AccessFixedHeader.COMMON_HEADER_SIZE));
            //解码
            if (encryptKey != 0 && packageLength > AccessFixedHeader.COMMON_HEADER_SIZE) {
                byte[] key = ByteBuffer.allocate(4).putInt(encryptKey).array(); 
                byte[] unEncrypted = ByteUtils.copyOfRange(pack, 0, AccessFixedHeader.COMMON_HEADER_SIZE);   //access固定头不加密
                byte[] encrypted = ByteUtils.copyOfRange(pack, AccessFixedHeader.COMMON_HEADER_SIZE, packageLength);
                encrypted = x.d(key, encrypted);
                pack = ArrayUtils.addAll(unEncrypted, encrypted);
            }
            logger.debug("\t>>>>>>> decoder.bytes.length:"+pack.length);
            logger.debug("\t>>>>>>> decoder.bytes:"+ByteUtils.bytesAsHexString(pack,pack.length));
            logger.debug("\t>>>>>>> decoder.position:"+in.position());
            if(in.remaining() > 0){
                //丢掉剩余的内容
                logger.debug("\t>>>>>>> remaining ["+in.remaining()+"] bytes");
                in.mark();
            }
            body = pack;
            break;
        }
        if(null != body && body.length > 0){
            out.write(body);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 二进制转十进制
     * @param b
     * @return
     */
    private int byteToInt2(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < b.length; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
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
