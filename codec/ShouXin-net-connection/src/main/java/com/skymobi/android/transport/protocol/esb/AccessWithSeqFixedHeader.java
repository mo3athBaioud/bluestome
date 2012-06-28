package com.skymobi.android.transport.protocol.esb;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.core.EsbHeaderable;
//0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7  
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|           srcmodule(2)        |            dstmodule(2)       |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|             length(2)         |            flags(2)       	  |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|            seqNum(2)          |            ack(2)             |
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
//|            msgCode(2)         | 
//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
* @author Marvin.Ma
*
*/
public class AccessWithSeqFixedHeader extends AccessFixedHeader {

    public static final int WITH_SEQ_NUM_HEADER_SIZE = 14;
	
    @EsbField(index = 5, bytes = 2)
    private short seqNum = 0;

    @EsbField(index = 6)
    private short ack = 0;

    @Override
    public String toString() {
        return "AccessFixedHeader{" +
                "srcESBAddr=0x" + Integer.toHexString(getSrcESBAddr()).toUpperCase() +
                ", dstESBAddr=0x" + Integer.toHexString(getDstESBAddr()).toUpperCase() +
                ", length=" + getLength() +
                ", flags=" + getFlags() +
                ", seqNum=" + seqNum +
                ", ack=" + ack +
                '}';
    }


    /**
     * @return the ack
     */
    public short getAck() {
        return ack;
    }

    /**
     * @param ack the ack to set
     */
    public void setAck(short ack) {
        this.ack = ack;
    }

    /**
     * @param seqNum the seqNum to set
     */
    public void setSeqNum(short seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public short getSeqNum() {
        return seqNum;
    }
}
