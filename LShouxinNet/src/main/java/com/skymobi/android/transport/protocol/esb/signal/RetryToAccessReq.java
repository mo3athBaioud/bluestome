package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 重连Access请求
 * @ClassName: RetryToAccess
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-24 下午02:03:56
 */
@EsbSignal(messageCode=0x9807)
public class RetryToAccessReq extends AbstractEsbT2ASignal {

    @EsbField(index = 1, fixedLength = 7)
    private byte[] protocolBegin = new byte[]{(byte) 0x8A, (byte) 0xED, (byte) 0x9C, (byte) 0xF3, 0x7E, 0x32, (byte) 0xB9};
    @EsbField(index = 2) 
    private short srcModuleId;
    @EsbField(index = 3)
    private short accessModuleId = (short) 0x9800;
    @EsbField(index = 4)
    private short length;
    /**
     * 0x0          普通消息
       0x120        添加了序列号的消息
       0x420        普通加密消息
       0x520        加密加了序列号的消息
     */
    @EsbField(index = 5)
    private short flags = 0x0;
    @EsbField(index = 6)
    private short msgCode = (short) 0x9807;
    @EsbField(index = 7)
    private int accessIndex;
    @EsbField(index = 8)
    private int authCode;
    /**
     * @return the protocolBegin
     */
    public byte[] getProtocolBegin() {
        return protocolBegin;
    }
    /**
     * @param protocolBegin the protocolBegin to set
     */
    public void setProtocolBegin(byte[] protocolBegin) {
        this.protocolBegin = protocolBegin;
    }
    /**
     * @return the accessIndex
     */
    public int getAccessIndex() {
        return accessIndex;
    }
    /**
     * @param accessIndex the accessIndex to set
     */
    public void setAccessIndex(int accessIndex) {
        this.accessIndex = accessIndex;
    }
    /**
     * @return the authCode
     */
    public int getAuthCode() {
        return authCode;
    }
    /**
     * @param authCode the authCode to set
     */
    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }
    /**
     * @return the srcModuleId
     */
    public short getSrcModuleId() {
        return srcModuleId;
    }
    /**
     * @param srcModuleId the srcModuleId to set
     */
    public void setSrcModuleId(short srcModuleId) {
        this.srcModuleId = srcModuleId;
    }
    /**
     * @return the accessModuleId
     */
    public short getAccessModuleId() {
        return accessModuleId;
    }
    /**
     * @param accessModuleId the accessModuleId to set
     */
    public void setAccessModuleId(short accessModuleId) {
        this.accessModuleId = accessModuleId;
    }
    /**
     * @return the length
     */
//    public short getLength() {
//        return length;
//    }
    /**
     * @param length the length to set
     */
    public void setLength(short length) {
        this.length = length;
    }
    /**
     * @return the msgCode
     */
    public short getMsgCode() {
        return msgCode;
    }
    /**
     * @param msgCode the msgCode to set
     */
    public void setMsgCode(short msgCode) {
        this.msgCode = msgCode;
    }
    /**
     * @param flags the flags to set
     */
    public void setFlags(short flags) {
        this.flags = flags;
    }
    
}
