package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;

@EsbSignal( messageCode = 0x9805 )
public class HeartbeatToAccessReq extends AbstractEsbT2ASignal {
    @EsbField(index = 1,fixedLength = 9)
    private byte[] data = new byte[]{0,0,0,0,0,0,0,0,0};

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
    
}
