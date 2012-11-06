
package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbField;
import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable;

@EsbSignal(messageCode = 0x9824)
public class GenerateUniqueIDResp extends AbstractAccessHeaderable {
    @EsbField(index = 8)
    private byte result4T = 0;

    @EsbField(index = 9, fixedLength = 32)
    private String uid;

    public short getResult4T() {
        return result4T;
    }

    public void setResult4T(byte result4T) {
        this.result4T = result4T;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
