package com.skymobi.android.bean.esb.core;

public abstract class AbstractEsbT2ASignal extends AbstractEsbHeaderable
		implements EsbTerminal2AccessSignal {

    private short ack;

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
}
