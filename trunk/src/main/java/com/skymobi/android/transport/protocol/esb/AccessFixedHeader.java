package com.skymobi.android.transport.protocol.esb;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
//0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7  
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|           srcmodule(2)        |            dstmodule(2)       |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|             length(2)         |            flags(2)       	  |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|            msgCode(2)         |          
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
* @author Marvin.Ma
*
*/
public class AccessFixedHeader implements EsbHeaderable {

    //通用头长度 srcmodule(2)|dstmodule(2)|length(2)|flags(2)
    public static final int COMMON_HEADER_SIZE = 8;
    
	public static final int FIXED_HEADER_SIZE = 14;
	
	public static final int WITHOUT_SEQ_NUM_HEADER_SIZE = 10;
	
    @EsbField(index = 1,bytes = 2)
	private int srcESBAddr;

    @EsbField(index = 2,bytes = 2)
	private int dstESBAddr;

    @EsbField(index = 3)
	private short length;

     @EsbField(index = 4)
	private short flags = 0x0120;

    @EsbField(index = 7,bytes = 2)
	private int msgCode;
    
    public void setSrcESBAddr(int srcESBAddr) {
        this.srcESBAddr = srcESBAddr;
    }


    public void setDstESBAddr(int dstESBAddr) {
        this.dstESBAddr = dstESBAddr;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    @Override
    public boolean checkIntegrity() {
        return true;
    }

    public void setFlags(short flags) {
        this.flags = flags;
    }


    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public int getSrcESBAddr() {
        return srcESBAddr;
    }

    @Override
    public int getDstESBAddr() {
        return dstESBAddr;
    }

    public short getSeqNum() {
        return 0;
    }
    
    @Override
    public short getResult() {
        return 0;
    }

    @Override
    public short getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return "AccessFixedHeader{" +
                "srcESBAddr=0x" + Integer.toHexString(srcESBAddr).toUpperCase() +
                ", dstESBAddr=0x" + Integer.toHexString(dstESBAddr).toUpperCase() +
                ", length=" + length +
                ", flags=" + flags +
                ", msgCode=0x" + Integer.toHexString(msgCode).toUpperCase() +
                '}';
    }
}
