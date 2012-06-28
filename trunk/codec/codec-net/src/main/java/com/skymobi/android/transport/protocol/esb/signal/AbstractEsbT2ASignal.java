package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.core.AbstractEsbHeaderable;
import com.skymobi.android.bean.esb.core.EsbTerminal2AccessSignal;

public abstract class AbstractEsbT2ASignal extends AbstractEsbHeaderable
		implements EsbTerminal2AccessSignal {

    private short ack;
    //密钥
    private int encryptKey;

    @Override
    public short getAck() {
        return ack;
    }

    @Override
    public void setAck(short ack) {
        this.ack = ack;
    }

    public boolean checkIntegrity() {
        return true;
    }

    @Override
    public short getFlags() {
		return 0x0120;  //T2A
	}

    public int getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(int encryptKey) {
        this.encryptKey = encryptKey;
    }
    
}
