package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

@EsbSignal( messageCode = 0x9806 )
public class HeartbeatToAccessResp extends AbstractAccessHeaderable {
    
    @EsbField(index = 8)
    private byte data;
    
}